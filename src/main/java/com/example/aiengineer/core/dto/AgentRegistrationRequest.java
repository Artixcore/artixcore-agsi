package com.example.aiengineer.core.dto;

import java.util.List;
import java.util.Map;

public class AgentRegistrationRequest {
    private String agentId;
    private String agentName;
    private String agentType; // chatbot, math_engine, tutor, custom, etc.
    private String description;
    private String version;
    private String endpoint;
    private String apiKey;
    private List<String> capabilities;
    private Map<String, Object> configuration;
    private Boolean isActive = true;
    private String healthCheckEndpoint;
    private Integer maxConcurrentRequests = 10;
    private String authenticationMethod = "api_key"; // api_key, oauth, jwt, none

    public String getAgentId() { return agentId; }
    public void setAgentId(String agentId) { this.agentId = agentId; }
    
    public String getAgentName() { return agentName; }
    public void setAgentName(String agentName) { this.agentName = agentName; }
    
    public String getAgentType() { return agentType; }
    public void setAgentType(String agentType) { this.agentType = agentType; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    
    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }
    
    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }
    
    public List<String> getCapabilities() { return capabilities; }
    public void setCapabilities(List<String> capabilities) { this.capabilities = capabilities; }
    
    public Map<String, Object> getConfiguration() { return configuration; }
    public void setConfiguration(Map<String, Object> configuration) { this.configuration = configuration; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public String getHealthCheckEndpoint() { return healthCheckEndpoint; }
    public void setHealthCheckEndpoint(String healthCheckEndpoint) { this.healthCheckEndpoint = healthCheckEndpoint; }
    
    public Integer getMaxConcurrentRequests() { return maxConcurrentRequests; }
    public void setMaxConcurrentRequests(Integer maxConcurrentRequests) { this.maxConcurrentRequests = maxConcurrentRequests; }
    
    public String getAuthenticationMethod() { return authenticationMethod; }
    public void setAuthenticationMethod(String authenticationMethod) { this.authenticationMethod = authenticationMethod; }
} 