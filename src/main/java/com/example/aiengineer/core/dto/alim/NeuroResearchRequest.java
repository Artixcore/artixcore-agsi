package com.example.aiengineer.core.dto.alim;

import java.util.List;

public class NeuroResearchRequest {
    private String researchText;
    private List<String> disciplines;
    private String analysisDepth = "comprehensive";
    private Boolean includeCrossDiscipline = true;
    private Boolean includeEmergentPatterns = true;

    public String getResearchText() { return researchText; }
    public void setResearchText(String researchText) { this.researchText = researchText; }
    
    public List<String> getDisciplines() { return disciplines; }
    public void setDisciplines(List<String> disciplines) { this.disciplines = disciplines; }
    
    public String getAnalysisDepth() { return analysisDepth; }
    public void setAnalysisDepth(String analysisDepth) { this.analysisDepth = analysisDepth; }
    
    public Boolean getIncludeCrossDiscipline() { return includeCrossDiscipline; }
    public void setIncludeCrossDiscipline(Boolean includeCrossDiscipline) { this.includeCrossDiscipline = includeCrossDiscipline; }
    
    public Boolean getIncludeEmergentPatterns() { return includeEmergentPatterns; }
    public void setIncludeEmergentPatterns(Boolean includeEmergentPatterns) { this.includeEmergentPatterns = includeEmergentPatterns; }
} 