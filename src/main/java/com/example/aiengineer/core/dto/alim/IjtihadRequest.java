package com.example.aiengineer.core.dto.alim;

import java.util.List;

public class IjtihadRequest {
    private String researchQuestion;
    private List<String> sources;
    private String reasoningMethod = "analytical";
    private Boolean includeHistoricalContext = true;
    private Boolean includeContemporaryApplication = true;
    private Boolean includeEthicalConsiderations = true;

    public String getResearchQuestion() { return researchQuestion; }
    public void setResearchQuestion(String researchQuestion) { this.researchQuestion = researchQuestion; }
    
    public List<String> getSources() { return sources; }
    public void setSources(List<String> sources) { this.sources = sources; }
    
    public String getReasoningMethod() { return reasoningMethod; }
    public void setReasoningMethod(String reasoningMethod) { this.reasoningMethod = reasoningMethod; }
    
    public Boolean getIncludeHistoricalContext() { return includeHistoricalContext; }
    public void setIncludeHistoricalContext(Boolean includeHistoricalContext) { this.includeHistoricalContext = includeHistoricalContext; }
    
    public Boolean getIncludeContemporaryApplication() { return includeContemporaryApplication; }
    public void setIncludeContemporaryApplication(Boolean includeContemporaryApplication) { this.includeContemporaryApplication = includeContemporaryApplication; }
    
    public Boolean getIncludeEthicalConsiderations() { return includeEthicalConsiderations; }
    public void setIncludeEthicalConsiderations(Boolean includeEthicalConsiderations) { this.includeEthicalConsiderations = includeEthicalConsiderations; }
} 