package com.example.aiengineer.core.dto.alim;

public class ImpactPredictionRequest {
    private String researchWork;
    private String predictionTimeframe = "5_years";
    private Boolean includeAcademicImpact = true;
    private Boolean includeSocietalImpact = true;
    private Boolean includeEconomicImpact = true;
    private Boolean includeCrossDisciplineImpact = true;

    public String getResearchWork() { return researchWork; }
    public void setResearchWork(String researchWork) { this.researchWork = researchWork; }
    
    public String getPredictionTimeframe() { return predictionTimeframe; }
    public void setPredictionTimeframe(String predictionTimeframe) { this.predictionTimeframe = predictionTimeframe; }
    
    public Boolean getIncludeAcademicImpact() { return includeAcademicImpact; }
    public void setIncludeAcademicImpact(Boolean includeAcademicImpact) { this.includeAcademicImpact = includeAcademicImpact; }
    
    public Boolean getIncludeSocietalImpact() { return includeSocietalImpact; }
    public void setIncludeSocietalImpact(Boolean includeSocietalImpact) { this.includeSocietalImpact = includeSocietalImpact; }
    
    public Boolean getIncludeEconomicImpact() { return includeEconomicImpact; }
    public void setIncludeEconomicImpact(Boolean includeEconomicImpact) { this.includeEconomicImpact = includeEconomicImpact; }
    
    public Boolean getIncludeCrossDisciplineImpact() { return includeCrossDisciplineImpact; }
    public void setIncludeCrossDisciplineImpact(Boolean includeCrossDisciplineImpact) { this.includeCrossDisciplineImpact = includeCrossDisciplineImpact; }
} 