package com.example.aiengineer.core.agent;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class AgentRegistry {
    
    private final Map<String, RegisteredAgent> agents = new ConcurrentHashMap<>();
    private final Map<String, List<String>> agentsByType = new ConcurrentHashMap<>();
    private final Map<String, List<String>> agentsByCapability = new ConcurrentHashMap<>();

    public RegisteredAgent registerAgent(RegisteredAgent agent) {
        // Validate agent
        if (agent.getAgentId() == null || agent.getAgentId().trim().isEmpty()) {
            throw new IllegalArgumentException("Agent ID is required");
        }
        
        if (agents.containsKey(agent.getAgentId())) {
            throw new IllegalArgumentException("Agent with ID " + agent.getAgentId() + " already exists");
        }
        
        // Set registration time
        agent.setRegisteredAt(LocalDateTime.now());
        agent.setLastUsed(LocalDateTime.now());
        
        // Store agent
        agents.put(agent.getAgentId(), agent);
        
        // Index by type
        agentsByType.computeIfAbsent(agent.getAgentType(), k -> new ArrayList<>())
                   .add(agent.getAgentId());
        
        // Index by capabilities
        if (agent.getCapabilities() != null) {
            for (String capability : agent.getCapabilities()) {
                agentsByCapability.computeIfAbsent(capability, k -> new ArrayList<>())
                                 .add(agent.getAgentId());
            }
        }
        
        return agent;
    }

    public RegisteredAgent getAgent(String agentId) {
        return agents.get(agentId);
    }

    public List<RegisteredAgent> getAllAgents() {
        return new ArrayList<>(agents.values());
    }

    public List<RegisteredAgent> getAgentsByType(String agentType) {
        List<String> agentIds = agentsByType.get(agentType);
        if (agentIds == null) return new ArrayList<>();
        
        return agentIds.stream()
                      .map(agents::get)
                      .filter(Objects::nonNull)
                      .collect(Collectors.toList());
    }

    public List<RegisteredAgent> getAgentsByCapability(String capability) {
        List<String> agentIds = agentsByCapability.get(capability);
        if (agentIds == null) return new ArrayList<>();
        
        return agentIds.stream()
                      .map(agents::get)
                      .filter(Objects::nonNull)
                      .collect(Collectors.toList());
    }

    public List<RegisteredAgent> searchAgents(String query, List<String> requiredCapabilities, 
                                            String agentType, Boolean onlyActive) {
        return agents.values().stream()
                    .filter(agent -> matchesQuery(agent, query))
                    .filter(agent -> matchesCapabilities(agent, requiredCapabilities))
                    .filter(agent -> matchesType(agent, agentType))
                    .filter(agent -> matchesActiveStatus(agent, onlyActive))
                    .sorted(Comparator.comparing(RegisteredAgent::getSuccessRate).reversed())
                    .collect(Collectors.toList());
    }

    public RegisteredAgent updateAgent(String agentId, RegisteredAgent updatedAgent) {
        RegisteredAgent existingAgent = agents.get(agentId);
        if (existingAgent == null) {
            throw new IllegalArgumentException("Agent with ID " + agentId + " not found");
        }
        
        // Update fields
        existingAgent.setAgentName(updatedAgent.getAgentName());
        existingAgent.setDescription(updatedAgent.getDescription());
        existingAgent.setVersion(updatedAgent.getVersion());
        existingAgent.setEndpoint(updatedAgent.getEndpoint());
        existingAgent.setApiKey(updatedAgent.getApiKey());
        existingAgent.setCapabilities(updatedAgent.getCapabilities());
        existingAgent.setConfiguration(updatedAgent.getConfiguration());
        existingAgent.setIsActive(updatedAgent.getIsActive());
        existingAgent.setHealthCheckEndpoint(updatedAgent.getHealthCheckEndpoint());
        existingAgent.setMaxConcurrentRequests(updatedAgent.getMaxConcurrentRequests());
        existingAgent.setAuthenticationMethod(updatedAgent.getAuthenticationMethod());
        
        // Re-index capabilities if they changed
        if (!Objects.equals(existingAgent.getCapabilities(), updatedAgent.getCapabilities())) {
            reindexCapabilities(existingAgent, updatedAgent.getCapabilities());
        }
        
        return existingAgent;
    }

    public void unregisterAgent(String agentId) {
        RegisteredAgent agent = agents.remove(agentId);
        if (agent != null) {
            // Remove from type index
            agentsByType.get(agent.getAgentType()).remove(agentId);
            
            // Remove from capability index
            if (agent.getCapabilities() != null) {
                for (String capability : agent.getCapabilities()) {
                    agentsByCapability.get(capability).remove(agentId);
                }
            }
        }
    }

    public void updateAgentHealth(String agentId, Boolean isHealthy) {
        RegisteredAgent agent = agents.get(agentId);
        if (agent != null) {
            agent.setIsHealthy(isHealthy);
            agent.setLastHealthCheck(LocalDateTime.now());
        }
    }

    public void updateAgentMetrics(String agentId, Boolean success, Double responseTime) {
        RegisteredAgent agent = agents.get(agentId);
        if (agent != null) {
            agent.incrementTotalRequests();
            if (success) {
                agent.incrementSuccessfulRequests();
            } else {
                agent.incrementFailedRequests();
            }
            
            // Update average response time
            double currentAvg = agent.getAverageResponseTime();
            long totalRequests = agent.getTotalRequests();
            agent.setAverageResponseTime((currentAvg * (totalRequests - 1) + responseTime) / totalRequests);
            
            agent.setLastUsed(LocalDateTime.now());
        }
    }

    public List<RegisteredAgent> getAvailableAgents() {
        return agents.values().stream()
                    .filter(RegisteredAgent::canAcceptRequest)
                    .collect(Collectors.toList());
    }

    public Map<String, Object> getRegistryStats() {
        long totalAgents = agents.size();
        long activeAgents = agents.values().stream().filter(RegisteredAgent::getIsActive).count();
        long healthyAgents = agents.values().stream().filter(RegisteredAgent::getIsHealthy).count();
        
        Map<String, Long> agentsByTypeCount = agents.values().stream()
                .collect(Collectors.groupingBy(RegisteredAgent::getAgentType, Collectors.counting()));
        
        return Map.of(
            "total_agents", totalAgents,
            "active_agents", activeAgents,
            "healthy_agents", healthyAgents,
            "agents_by_type", agentsByTypeCount,
            "total_requests", agents.values().stream().mapToLong(RegisteredAgent::getTotalRequests).sum(),
            "average_success_rate", agents.values().stream().mapToDouble(RegisteredAgent::getSuccessRate).average().orElse(0.0)
        );
    }

    private boolean matchesQuery(RegisteredAgent agent, String query) {
        if (query == null || query.trim().isEmpty()) return true;
        
        String lowerQuery = query.toLowerCase();
        return (agent.getAgentName() != null && agent.getAgentName().toLowerCase().contains(lowerQuery)) ||
               (agent.getDescription() != null && agent.getDescription().toLowerCase().contains(lowerQuery)) ||
               (agent.getAgentType() != null && agent.getAgentType().toLowerCase().contains(lowerQuery));
    }

    private boolean matchesCapabilities(RegisteredAgent agent, List<String> requiredCapabilities) {
        if (requiredCapabilities == null || requiredCapabilities.isEmpty()) return true;
        if (agent.getCapabilities() == null) return false;
        
        return requiredCapabilities.stream().allMatch(cap -> agent.getCapabilities().contains(cap));
    }

    private boolean matchesType(RegisteredAgent agent, String agentType) {
        if (agentType == null || agentType.trim().isEmpty()) return true;
        return agentType.equals(agent.getAgentType());
    }

    private boolean matchesActiveStatus(RegisteredAgent agent, Boolean onlyActive) {
        if (onlyActive == null) return true;
        return !onlyActive || agent.getIsActive();
    }

    private void reindexCapabilities(RegisteredAgent agent, List<String> newCapabilities) {
        // Remove old capabilities
        if (agent.getCapabilities() != null) {
            for (String capability : agent.getCapabilities()) {
                List<String> agentIds = agentsByCapability.get(capability);
                if (agentIds != null) {
                    agentIds.remove(agent.getAgentId());
                }
            }
        }
        
        // Add new capabilities
        if (newCapabilities != null) {
            for (String capability : newCapabilities) {
                agentsByCapability.computeIfAbsent(capability, k -> new ArrayList<>())
                                 .add(agent.getAgentId());
            }
        }
    }
} 