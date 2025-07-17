package com.example.aiengineer.core.dto;

import java.util.List;

public class AgentDiscoveryRequest {
    private String agentType; // chatbot, math_engine, tutor, custom, etc.
    private List<String> requiredCapabilities;
    private String query; // natural language query to find agents
    private Boolean onlyActive = true;
    private Integer limit = 10;
    private String sortBy = "relevance"; // relevance, performance, popularity

    public String getAgentType() { return agentType; }
    public void setAgentType(String agentType) { this.agentType = agentType; }
    
    public List<String> getRequiredCapabilities() { return requiredCapabilities; }
    public void setRequiredCapabilities(List<String> requiredCapabilities) { this.requiredCapabilities = requiredCapabilities; }
    
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }
    
    public Boolean getOnlyActive() { return onlyActive; }
    public void setOnlyActive(Boolean onlyActive) { this.onlyActive = onlyActive; }
    
    public Integer getLimit() { return limit; }
    public void setLimit(Integer limit) { this.limit = limit; }
    
    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
} 