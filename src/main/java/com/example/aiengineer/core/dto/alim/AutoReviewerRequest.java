package com.example.aiengineer.core.dto.alim;

import java.util.List;

public class AutoReviewerRequest {
    private String paperContent;
    private String journalStandards = "high_impact";
    private List<String> reviewFocus;
    private Boolean includeLogicFlaws = true;
    private Boolean includeCitationAnalysis = true;
    private Boolean includeBiasDetection = true;

    public String getPaperContent() { return paperContent; }
    public void setPaperContent(String paperContent) { this.paperContent = paperContent; }
    
    public String getJournalStandards() { return journalStandards; }
    public void setJournalStandards(String journalStandards) { this.journalStandards = journalStandards; }
    
    public List<String> getReviewFocus() { return reviewFocus; }
    public void setReviewFocus(List<String> reviewFocus) { this.reviewFocus = reviewFocus; }
    
    public Boolean getIncludeLogicFlaws() { return includeLogicFlaws; }
    public void setIncludeLogicFlaws(Boolean includeLogicFlaws) { this.includeLogicFlaws = includeLogicFlaws; }
    
    public Boolean getIncludeCitationAnalysis() { return includeCitationAnalysis; }
    public void setIncludeCitationAnalysis(Boolean includeCitationAnalysis) { this.includeCitationAnalysis = includeCitationAnalysis; }
    
    public Boolean getIncludeBiasDetection() { return includeBiasDetection; }
    public void setIncludeBiasDetection(Boolean includeBiasDetection) { this.includeBiasDetection = includeBiasDetection; }
} 