package com.example.aiengineer.core.dto.alim;

public class ExperimentDesignRequest {
    private String hypothesis;
    private String researchDomain;
    private String experimentType = "controlled";
    private Boolean includeMethodology = true;
    private Boolean includeStatisticalAnalysis = true;
    private Boolean includeTimeline = true;

    public String getHypothesis() { return hypothesis; }
    public void setHypothesis(String hypothesis) { this.hypothesis = hypothesis; }
    
    public String getResearchDomain() { return researchDomain; }
    public void setResearchDomain(String researchDomain) { this.researchDomain = researchDomain; }
    
    public String getExperimentType() { return experimentType; }
    public void setExperimentType(String experimentType) { this.experimentType = experimentType; }
    
    public Boolean getIncludeMethodology() { return includeMethodology; }
    public void setIncludeMethodology(Boolean includeMethodology) { this.includeMethodology = includeMethodology; }
    
    public Boolean getIncludeStatisticalAnalysis() { return includeStatisticalAnalysis; }
    public void setIncludeStatisticalAnalysis(Boolean includeStatisticalAnalysis) { this.includeStatisticalAnalysis = includeStatisticalAnalysis; }
    
    public Boolean getIncludeTimeline() { return includeTimeline; }
    public void setIncludeTimeline(Boolean includeTimeline) { this.includeTimeline = includeTimeline; }
} 