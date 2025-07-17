package com.example.aiengineer.core.dto.alim;

public class CrossDisciplineRequest {
    private String primaryDiscipline;
    private String secondaryDiscipline;
    private String researchQuestion;
    private Boolean includeMetaphysicalConnections = true;
    private Boolean includePracticalApplications = true;
    private Boolean includeTheoreticalFrameworks = true;

    public String getPrimaryDiscipline() { return primaryDiscipline; }
    public void setPrimaryDiscipline(String primaryDiscipline) { this.primaryDiscipline = primaryDiscipline; }
    
    public String getSecondaryDiscipline() { return secondaryDiscipline; }
    public void setSecondaryDiscipline(String secondaryDiscipline) { this.secondaryDiscipline = secondaryDiscipline; }
    
    public String getResearchQuestion() { return researchQuestion; }
    public void setResearchQuestion(String researchQuestion) { this.researchQuestion = researchQuestion; }
    
    public Boolean getIncludeMetaphysicalConnections() { return includeMetaphysicalConnections; }
    public void setIncludeMetaphysicalConnections(Boolean includeMetaphysicalConnections) { this.includeMetaphysicalConnections = includeMetaphysicalConnections; }
    
    public Boolean getIncludePracticalApplications() { return includePracticalApplications; }
    public void setIncludePracticalApplications(Boolean includePracticalApplications) { this.includePracticalApplications = includePracticalApplications; }
    
    public Boolean getIncludeTheoreticalFrameworks() { return includeTheoreticalFrameworks; }
    public void setIncludeTheoreticalFrameworks(Boolean includeTheoreticalFrameworks) { this.includeTheoreticalFrameworks = includeTheoreticalFrameworks; }
} 