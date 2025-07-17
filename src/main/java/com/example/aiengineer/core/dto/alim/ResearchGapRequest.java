package com.example.aiengineer.core.dto.alim;

import java.util.List;

public class ResearchGapRequest {
    private String domain;
    private List<String> keywords;
    private String timeframe = "5_years";
    private Boolean includeEmergentGaps = true;
    private Boolean includeFundingOpportunities = true;

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    
    public List<String> getKeywords() { return keywords; }
    public void setKeywords(List<String> keywords) { this.keywords = keywords; }
    
    public String getTimeframe() { return timeframe; }
    public void setTimeframe(String timeframe) { this.timeframe = timeframe; }
    
    public Boolean getIncludeEmergentGaps() { return includeEmergentGaps; }
    public void setIncludeEmergentGaps(Boolean includeEmergentGaps) { this.includeEmergentGaps = includeEmergentGaps; }
    
    public Boolean getIncludeFundingOpportunities() { return includeFundingOpportunities; }
    public void setIncludeFundingOpportunities(Boolean includeFundingOpportunities) { this.includeFundingOpportunities = includeFundingOpportunities; }
} 