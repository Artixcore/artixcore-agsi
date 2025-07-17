package com.example.aiengineer.core.dto.alim;

public class HypothesisForgeRequest {
    private String problemStatement;
    private String domain;
    private Integer hypothesisCount = 5;
    private Boolean includeExperimentDesign = true;
    private Boolean includeGrantWorthiness = true;
    private Boolean includeFeasibilityScoring = true;

    public String getProblemStatement() { return problemStatement; }
    public void setProblemStatement(String problemStatement) { this.problemStatement = problemStatement; }
    
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    
    public Integer getHypothesisCount() { return hypothesisCount; }
    public void setHypothesisCount(Integer hypothesisCount) { this.hypothesisCount = hypothesisCount; }
    
    public Boolean getIncludeExperimentDesign() { return includeExperimentDesign; }
    public void setIncludeExperimentDesign(Boolean includeExperimentDesign) { this.includeExperimentDesign = includeExperimentDesign; }
    
    public Boolean getIncludeGrantWorthiness() { return includeGrantWorthiness; }
    public void setIncludeGrantWorthiness(Boolean includeGrantWorthiness) { this.includeGrantWorthiness = includeGrantWorthiness; }
    
    public Boolean getIncludeFeasibilityScoring() { return includeFeasibilityScoring; }
    public void setIncludeFeasibilityScoring(Boolean includeFeasibilityScoring) { this.includeFeasibilityScoring = includeFeasibilityScoring; }
} 