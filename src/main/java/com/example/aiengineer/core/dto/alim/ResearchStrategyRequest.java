package com.example.aiengineer.core.dto.alim;

import java.util.List;

public class ResearchStrategyRequest {
    private String researchGoal;
    private List<String> targetDomains;
    private String timeline = "3_years";
    private Boolean includeFundingStrategy = true;
    private Boolean includeCollaborationOpportunities = true;
    private Boolean includePublicationStrategy = true;

    public String getResearchGoal() { return researchGoal; }
    public void setResearchGoal(String researchGoal) { this.researchGoal = researchGoal; }
    
    public List<String> getTargetDomains() { return targetDomains; }
    public void setTargetDomains(List<String> targetDomains) { this.targetDomains = targetDomains; }
    
    public String getTimeline() { return timeline; }
    public void setTimeline(String timeline) { this.timeline = timeline; }
    
    public Boolean getIncludeFundingStrategy() { return includeFundingStrategy; }
    public void setIncludeFundingStrategy(Boolean includeFundingStrategy) { this.includeFundingStrategy = includeFundingStrategy; }
    
    public Boolean getIncludeCollaborationOpportunities() { return includeCollaborationOpportunities; }
    public void setIncludeCollaborationOpportunities(Boolean includeCollaborationOpportunities) { this.includeCollaborationOpportunities = includeCollaborationOpportunities; }
    
    public Boolean getIncludePublicationStrategy() { return includePublicationStrategy; }
    public void setIncludePublicationStrategy(Boolean includePublicationStrategy) { this.includePublicationStrategy = includePublicationStrategy; }
} 