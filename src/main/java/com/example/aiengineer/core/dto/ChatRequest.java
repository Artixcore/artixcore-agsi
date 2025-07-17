package com.example.aiengineer.core.dto;

public class ChatRequest {
    private String message;
    private String model = "ollama";
    private String sessionId;
    private Double temperature = 0.7;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
}