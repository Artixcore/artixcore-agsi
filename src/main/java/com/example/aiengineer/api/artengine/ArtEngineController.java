package com.example.aiengineer.api.artengine;

import com.example.aiengineer.core.dto.*;
import com.example.aiengineer.core.agent.*;
import com.example.aiengineer.services.artengine.ArtEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/v1/art-engine")
@CrossOrigin(origins = "*")
public class ArtEngineController {

    private final ArtEngineService artEngineService;
    
    @Autowired
    private AgentRegistry agentRegistry;
    
    @Autowired
    private AgentExecutor agentExecutor;

    public ArtEngineController(ArtEngineService artEngineService) {
        this.artEngineService = artEngineService;
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        return ResponseEntity.ok(Map.of(
            "engine", "ART-Engine",
            "version", "1.0",
            "status", "active",
            "description", "Core AI Processor - The CPU for AI Agents",
            "capabilities", artEngineService.getCapabilities(),
            "agent_registry_stats", agentRegistry.getRegistryStats()
        ));
    }

    // Core AI Processing
    @PostMapping("/chat/completion")
    public ResponseEntity<Map<String, Object>> chatCompletion(@RequestBody ChatRequest request) {
        return ResponseEntity.ok(artEngineService.processChatCompletion(request));
    }

    @PostMapping("/image/generate")
    public ResponseEntity<Map<String, Object>> generateImage(@RequestBody ImageRequest request) {
        return ResponseEntity.ok(artEngineService.generateImage(request));
    }

    @PostMapping("/text/analyze")
    public ResponseEntity<Map<String, Object>> analyzeText(@RequestBody TextAnalysisRequest request) {
        return ResponseEntity.ok(artEngineService.analyzeText(request));
    }

    @PostMapping("/data/process")
    public ResponseEntity<Map<String, Object>> processData(@RequestBody DataProcessingRequest request) {
        return ResponseEntity.ok(artEngineService.processData(request));
    }

    // Agent Management
    @PostMapping("/agents/register")
    public ResponseEntity<Map<String, Object>> registerAgent(@RequestBody AgentRegistrationRequest request) {
        try {
            RegisteredAgent agent = new RegisteredAgent();
            agent.setAgentId(request.getAgentId());
            agent.setAgentName(request.getAgentName());
            agent.setAgentType(request.getAgentType());
            agent.setDescription(request.getDescription());
            agent.setVersion(request.getVersion());
            agent.setEndpoint(request.getEndpoint());
            agent.setApiKey(request.getApiKey());
            agent.setCapabilities(request.getCapabilities());
            agent.setConfiguration(request.getConfiguration());
            agent.setIsActive(request.getIsActive());
            agent.setHealthCheckEndpoint(request.getHealthCheckEndpoint());
            agent.setMaxConcurrentRequests(request.getMaxConcurrentRequests());
            agent.setAuthenticationMethod(request.getAuthenticationMethod());
            
            RegisteredAgent registeredAgent = agentRegistry.registerAgent(agent);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Agent registered successfully",
                "agent_id", registeredAgent.getAgentId(),
                "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage(),
                "timestamp", System.currentTimeMillis()
            ));
        }
    }

    @GetMapping("/agents")
    public ResponseEntity<Map<String, Object>> getAllAgents() {
        List<RegisteredAgent> agents = agentRegistry.getAllAgents();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "agents", agents,
            "count", agents.size(),
            "timestamp", System.currentTimeMillis()
        ));
    }

    @GetMapping("/agents/{agentId}")
    public ResponseEntity<Map<String, Object>> getAgent(@PathVariable String agentId) {
        RegisteredAgent agent = agentRegistry.getAgent(agentId);
        if (agent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
            "success", true,
            "agent", agent,
            "timestamp", System.currentTimeMillis()
        ));
    }

    @PostMapping("/agents/discover")
    public ResponseEntity<Map<String, Object>> discoverAgents(@RequestBody AgentDiscoveryRequest request) {
        List<RegisteredAgent> agents = agentRegistry.searchAgents(
            request.getQuery(),
            request.getRequiredCapabilities(),
            request.getAgentType(),
            request.getOnlyActive()
        );
        
        return ResponseEntity.ok(Map.of(
            "success", true,
            "agents", agents,
            "count", agents.size(),
            "query", request.getQuery(),
            "timestamp", System.currentTimeMillis()
        ));
    }

    @PostMapping("/agents/execute")
    public ResponseEntity<Map<String, Object>> executeAgent(@RequestBody AgentExecutionRequest request) {
        Map<String, Object> result = agentExecutor.executeAgent(
            request.getAgentId(),
            request.getOperation(),
            request.getInput(),
            request.getSessionId(),
            request.getUserId(),
            request.getStreaming(),
            request.getTimeout()
        );
        
        return ResponseEntity.ok(result);
    }

    @PostMapping("/agents/execute/load-balanced")
    public ResponseEntity<Map<String, Object>> executeWithLoadBalancing(@RequestBody Map<String, Object> request) {
        String agentType = (String) request.get("agentType");
        String operation = (String) request.get("operation");
        Map<String, Object> input = (Map<String, Object>) request.get("input");
        String sessionId = (String) request.get("sessionId");
        String userId = (String) request.get("userId");
        Boolean streaming = (Boolean) request.get("streaming");
        Integer timeout = (Integer) request.get("timeout");
        
        Map<String, Object> result = agentExecutor.executeWithLoadBalancing(
            agentType, operation, input, sessionId, userId, streaming, timeout
        );
        
        return ResponseEntity.ok(result);
    }

    @PostMapping("/agents/execute/capability-routing")
    public ResponseEntity<Map<String, Object>> executeWithCapabilityRouting(@RequestBody Map<String, Object> request) {
        List<String> requiredCapabilities = (List<String>) request.get("requiredCapabilities");
        String operation = (String) request.get("operation");
        Map<String, Object> input = (Map<String, Object>) request.get("input");
        String sessionId = (String) request.get("sessionId");
        String userId = (String) request.get("userId");
        Boolean streaming = (Boolean) request.get("streaming");
        Integer timeout = (Integer) request.get("timeout");
        
        Map<String, Object> result = agentExecutor.executeWithCapabilityRouting(
            requiredCapabilities, operation, input, sessionId, userId, streaming, timeout
        );
        
        return ResponseEntity.ok(result);
    }

    @GetMapping("/agents/{agentId}/health")
    public ResponseEntity<Map<String, Object>> healthCheck(@PathVariable String agentId) {
        Map<String, Object> result = agentExecutor.healthCheck(agentId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/agents/{agentId}")
    public ResponseEntity<Map<String, Object>> unregisterAgent(@PathVariable String agentId) {
        try {
            agentRegistry.unregisterAgent(agentId);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Agent unregistered successfully",
                "agent_id", agentId,
                "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage(),
                "timestamp", System.currentTimeMillis()
            ));
        }
    }

    @GetMapping("/agents/stats")
    public ResponseEntity<Map<String, Object>> getAgentStats() {
        return ResponseEntity.ok(agentRegistry.getRegistryStats());
    }
}