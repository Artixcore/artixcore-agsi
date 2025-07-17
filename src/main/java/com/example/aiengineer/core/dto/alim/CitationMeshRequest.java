package com.example.aiengineer.core.dto.alim;

import java.util.List;

public class CitationMeshRequest {
    private String researchPaper;
    private List<String> relatedPapers;
    private String timeRange = "10_years";
    private Boolean includeInfluenceTracking = true;
    private Boolean includeImpactPrediction = true;
    private Boolean includeCrossDisciplineCitations = true;

    public String getResearchPaper() { return researchPaper; }
    public void setResearchPaper(String researchPaper) { this.researchPaper = researchPaper; }
    
    public List<String> getRelatedPapers() { return relatedPapers; }
    public void setRelatedPapers(List<String> relatedPapers) { this.relatedPapers = relatedPapers; }
    
    public String getTimeRange() { return timeRange; }
    public void setTimeRange(String timeRange) { this.timeRange = timeRange; }
    
    public Boolean getIncludeInfluenceTracking() { return includeInfluenceTracking; }
    public void setIncludeInfluenceTracking(Boolean includeInfluenceTracking) { this.includeInfluenceTracking = includeInfluenceTracking; }
    
    public Boolean getIncludeImpactPrediction() { return includeImpactPrediction; }
    public void setIncludeImpactPrediction(Boolean includeImpactPrediction) { this.includeImpactPrediction = includeImpactPrediction; }
    
    public Boolean getIncludeCrossDisciplineCitations() { return includeCrossDisciplineCitations; }
    public void setIncludeCrossDisciplineCitations(Boolean includeCrossDisciplineCitations) { this.includeCrossDisciplineCitations = includeCrossDisciplineCitations; }
} 