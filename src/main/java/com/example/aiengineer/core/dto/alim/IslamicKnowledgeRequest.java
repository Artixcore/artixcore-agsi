package com.example.aiengineer.core.dto.alim;

public class IslamicKnowledgeRequest {
    private String researchTopic;
    private String knowledgeDepth = "comprehensive";
    private Boolean includeSpiritual = true;
    private Boolean includeTawhidicWorldview = true;
    private Boolean includeClassicalEpistemology = true;
    private Boolean includeModernIntegration = true;

    public String getResearchTopic() { return researchTopic; }
    public void setResearchTopic(String researchTopic) { this.researchTopic = researchTopic; }
    
    public String getKnowledgeDepth() { return knowledgeDepth; }
    public void setKnowledgeDepth(String knowledgeDepth) { this.knowledgeDepth = knowledgeDepth; }
    
    public Boolean getIncludeSpiritual() { return includeSpiritual; }
    public void setIncludeSpiritual(Boolean includeSpiritual) { this.includeSpiritual = includeSpiritual; }
    
    public Boolean getIncludeTawhidicWorldview() { return includeTawhidicWorldview; }
    public void setIncludeTawhidicWorldview(Boolean includeTawhidicWorldview) { this.includeTawhidicWorldview = includeTawhidicWorldview; }
    
    public Boolean getIncludeClassicalEpistemology() { return includeClassicalEpistemology; }
    public void setIncludeClassicalEpistemology(Boolean includeClassicalEpistemology) { this.includeClassicalEpistemology = includeClassicalEpistemology; }
    
    public Boolean getIncludeModernIntegration() { return includeModernIntegration; }
    public void setIncludeModernIntegration(Boolean includeModernIntegration) { this.includeModernIntegration = includeModernIntegration; }
} 