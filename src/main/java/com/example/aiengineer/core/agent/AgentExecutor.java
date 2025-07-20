package com.example.aiengineer.core.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.List;

@Service
public class AgentExecutor {
    
    @Autowired
    private AgentRegistry agentRegistry;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public Map<String, Object> executeAgent(String agentId, String operation, Map<String, Object> input, 
                                          String sessionId, String userId, Boolean streaming, Integer timeout) {
        RegisteredAgent agent = agentRegistry.getAgent(agentId);
        if (agent == null) {
            return Map.of(
                "success", false,
                "error", "Agent not found: " + agentId,
                "timestamp", System.currentTimeMillis()
            );
        }
        
        if (!agent.canAcceptRequest()) {
            return Map.of(
                "success", false,
                "error", "Agent is not available: " + agentId,
                "timestamp", System.currentTimeMillis()
            );
        }
        
        Instant startTime = Instant.now();
        agent.incrementCurrentRequests();
        
        try {
            // Prepare request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            // Add authentication
            if ("api_key".equals(agent.getAuthenticationMethod()) && agent.getApiKey() != null) {
                headers.set("Authorization", "Bearer " + agent.getApiKey());
            }
            
            // Add metadata
            Map<String, Object> requestBody = Map.of(
                "operation", operation,
                "input", input,
                "sessionId", sessionId,
                "userId", userId,
                "streaming", streaming != null ? streaming : false,
                "metadata", Map.of(
                    "requestedAt", System.currentTimeMillis(),
                    "timeout", timeout != null ? timeout : 30000
                )
            );
            
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
            
            // Execute request with timeout
            int requestTimeout = timeout != null ? timeout : 30000;
            ResponseEntity<Map> response = restTemplate.exchange(
                agent.getEndpoint(),
                HttpMethod.POST,
                request,
                Map.class
            );
            
            // Calculate response time
            Duration responseTime = Duration.between(startTime, Instant.now());
            
            // Update metrics
            agentRegistry.updateAgentMetrics(agentId, true, (double) responseTime.toMillis());
            
            // Return response
            Map<String, Object> result = response.getBody();
            result.put("agent_id", agentId);
            result.put("response_time_ms", responseTime.toMillis());
            result.put("timestamp", System.currentTimeMillis());
            
            return result;
            
        } catch (ResourceAccessException e) {
            // Timeout or connection error
            Duration responseTime = Duration.between(startTime, Instant.now());
            agentRegistry.updateAgentMetrics(agentId, false, (double) responseTime.toMillis());
            agentRegistry.updateAgentHealth(agentId, false);
            
            return Map.of(
                "success", false,
                "error", "Agent execution failed: " + e.getMessage(),
                "agent_id", agentId,
                "response_time_ms", responseTime.toMillis(),
                "timestamp", System.currentTimeMillis()
            );
            
        } catch (Exception e) {
            // Other errors
            Duration responseTime = Duration.between(startTime, Instant.now());
            agentRegistry.updateAgentMetrics(agentId, false, (double) responseTime.toMillis());
            
            return Map.of(
                "success", false,
                "error", "Agent execution error: " + e.getMessage(),
                "agent_id", agentId,
                "response_time_ms", responseTime.toMillis(),
                "timestamp", System.currentTimeMillis()
            );
            
        } finally {
            agent.decrementCurrentRequests();
        }
    }
    
    public CompletableFuture<Map<String, Object>> executeAgentAsync(String agentId, String operation, 
                                                                   Map<String, Object> input, String sessionId, 
                                                                   String userId, Boolean streaming, Integer timeout) {
        return CompletableFuture.supplyAsync(() -> 
            executeAgent(agentId, operation, input, sessionId, userId, streaming, timeout)
        );
    }
    
    public Map<String, Object> executeWithLoadBalancing(String agentType, String operation, 
                                                       Map<String, Object> input, String sessionId, 
                                                       String userId, Boolean streaming, Integer timeout) {
        // Get available agents of the specified type
        var availableAgents = agentRegistry.getAgentsByType(agentType).stream()
                .filter(RegisteredAgent::canAcceptRequest)
                .sorted((a1, a2) -> Double.compare(a2.getSuccessRate(), a1.getSuccessRate()))
                .toList();
        
        if (availableAgents.isEmpty()) {
            return Map.of(
                "success", false,
                "error", "No available agents of type: " + agentType,
                "timestamp", System.currentTimeMillis()
            );
        }
        
        // Try agents in order of success rate
        for (RegisteredAgent agent : availableAgents) {
            Map<String, Object> result = executeAgent(agent.getAgentId(), operation, input, 
                                                    sessionId, userId, streaming, timeout);
            
            if ((Boolean) result.get("success")) {
                return result;
            }
        }
        
        // If all agents failed, return the last error
        return executeAgent(availableAgents.get(0).getAgentId(), operation, input, 
                          sessionId, userId, streaming, timeout);
    }
    
    public Map<String, Object> executeWithCapabilityRouting(List<String> requiredCapabilities, String operation, 
                                                          Map<String, Object> input, String sessionId, 
                                                          String userId, Boolean streaming, Integer timeout) {
        // Find agents with all required capabilities
        var availableAgents = agentRegistry.getAgentsByCapability(requiredCapabilities.get(0)).stream()
                .filter(agent -> requiredCapabilities.stream().allMatch(cap -> 
                    agent.getCapabilities() != null && agent.getCapabilities().contains(cap)))
                .filter(RegisteredAgent::canAcceptRequest)
                .sorted((a1, a2) -> Double.compare(a2.getSuccessRate(), a1.getSuccessRate()))
                .toList();
        
        if (availableAgents.isEmpty()) {
            return Map.of(
                "success", false,
                "error", "No available agents with capabilities: " + requiredCapabilities,
                "timestamp", System.currentTimeMillis()
            );
        }
        
        // Try the best agent
        return executeAgent(availableAgents.get(0).getAgentId(), operation, input, 
                          sessionId, userId, streaming, timeout);
    }
    
    public Map<String, Object> healthCheck(String agentId) {
        RegisteredAgent agent = agentRegistry.getAgent(agentId);
        if (agent == null) {
            return Map.of(
                "success", false,
                "error", "Agent not found: " + agentId,
                "timestamp", System.currentTimeMillis()
            );
        }
        
        if (agent.getHealthCheckEndpoint() == null) {
            return Map.of(
                "success", false,
                "error", "No health check endpoint configured",
                "timestamp", System.currentTimeMillis()
            );
        }
        
        try {
            HttpHeaders headers = new HttpHeaders();
            if ("api_key".equals(agent.getAuthenticationMethod()) && agent.getApiKey() != null) {
                headers.set("Authorization", "Bearer " + agent.getApiKey());
            }
            
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<Map> response = restTemplate.exchange(
                agent.getHealthCheckEndpoint(),
                HttpMethod.GET,
                request,
                Map.class
            );
            
            boolean isHealthy = response.getStatusCode().is2xxSuccessful();
            agentRegistry.updateAgentHealth(agentId, isHealthy);
            
            return Map.of(
                "success", true,
                "healthy", isHealthy,
                "status_code", response.getStatusCodeValue(),
                "timestamp", System.currentTimeMillis()
            );
            
        } catch (Exception e) {
            agentRegistry.updateAgentHealth(agentId, false);
            return Map.of(
                "success", false,
                "healthy", false,
                "error", "Health check failed: " + e.getMessage(),
                "timestamp", System.currentTimeMillis()
            );
        }
    }
} 