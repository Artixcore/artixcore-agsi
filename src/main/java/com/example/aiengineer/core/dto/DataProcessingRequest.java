package com.example.aiengineer.core.dto;

import java.util.Map;

public class DataProcessingRequest {
    private Map<String, Object> data;
    private String operation;
    private Map<String, Object> parameters;

    public Map<String, Object> getData() { return data; }
    public void setData(Map<String, Object> data) { this.data = data; }
    
    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
    
    public Map<String, Object> getParameters() { return parameters; }
    public void setParameters(Map<String, Object> parameters) { this.parameters = parameters; }
}