package com.example.aiengineer.core.dto;

import java.util.Map;

public class AgentExecutionRequest {
    private String agentId;
    private String operation; // process, analyze, generate, etc.
    private Map<String, Object> input;
    private String sessionId;
    private String userId;
    private Boolean streaming = false;
    private Integer timeout = 30000; // milliseconds
    private Map<String, Object> metadata;

    public String getAgentId() { return agentId; }
    public void setAgentId(String agentId) { this.agentId = agentId; }
    
    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
    
    public Map<String, Object> getInput() { return input; }
    public void setInput(Map<String, Object> input) { this.input = input; }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public Boolean getStreaming() { return streaming; }
    public void setStreaming(Boolean streaming) { this.streaming = streaming; }
    
    public Integer getTimeout() { return timeout; }
    public void setTimeout(Integer timeout) { this.timeout = timeout; }
    
    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
} 