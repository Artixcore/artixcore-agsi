package com.example.aiengineer.core.agent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RegisteredAgent {
    private String agentId;
    private String agentName;
    private String agentType;
    private String description;
    private String version;
    private String endpoint;
    private String apiKey;
    private List<String> capabilities;
    private Map<String, Object> configuration;
    private Boolean isActive;
    private String healthCheckEndpoint;
    private Integer maxConcurrentRequests;
    private String authenticationMethod;
    
    // Runtime metrics
    private final AtomicInteger currentRequests = new AtomicInteger(0);
    private final AtomicLong totalRequests = new AtomicLong(0);
    private final AtomicLong successfulRequests = new AtomicLong(0);
    private final AtomicLong failedRequests = new AtomicLong(0);
    private LocalDateTime lastHealthCheck;
    private Boolean isHealthy = true;
    private Double averageResponseTime = 0.0;
    private LocalDateTime registeredAt;
    private LocalDateTime lastUsed;

    public RegisteredAgent() {
        this.registeredAt = LocalDateTime.now();
        this.lastUsed = LocalDateTime.now();
    }

    // Getters and Setters
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
    
    // Runtime metrics methods
    public Integer getCurrentRequests() { return currentRequests.get(); }
    public void incrementCurrentRequests() { currentRequests.incrementAndGet(); }
    public void decrementCurrentRequests() { currentRequests.decrementAndGet(); }
    
    public Long getTotalRequests() { return totalRequests.get(); }
    public void incrementTotalRequests() { totalRequests.incrementAndGet(); }
    
    public Long getSuccessfulRequests() { return successfulRequests.get(); }
    public void incrementSuccessfulRequests() { successfulRequests.incrementAndGet(); }
    
    public Long getFailedRequests() { return failedRequests.get(); }
    public void incrementFailedRequests() { failedRequests.incrementAndGet(); }
    
    public LocalDateTime getLastHealthCheck() { return lastHealthCheck; }
    public void setLastHealthCheck(LocalDateTime lastHealthCheck) { this.lastHealthCheck = lastHealthCheck; }
    
    public Boolean getIsHealthy() { return isHealthy; }
    public void setIsHealthy(Boolean isHealthy) { this.isHealthy = isHealthy; }
    
    public Double getAverageResponseTime() { return averageResponseTime; }
    public void setAverageResponseTime(Double averageResponseTime) { this.averageResponseTime = averageResponseTime; }
    
    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }
    
    public LocalDateTime getLastUsed() { return lastUsed; }
    public void setLastUsed(LocalDateTime lastUsed) { this.lastUsed = lastUsed; }
    
    public Double getSuccessRate() {
        long total = totalRequests.get();
        if (total == 0) return 0.0;
        return (double) successfulRequests.get() / total * 100;
    }
    
    public Boolean canAcceptRequest() {
        return isActive && isHealthy && currentRequests.get() < maxConcurrentRequests;
    }
} 