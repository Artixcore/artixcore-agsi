package com.example.aiengineer.core.dto;

import java.util.List;

public class TextAnalysisRequest {
    private String text;
    private List<String> analysisTypes;
    private String language = "en";

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    
    public List<String> getAnalysisTypes() { return analysisTypes; }
    public void setAnalysisTypes(List<String> analysisTypes) { this.analysisTypes = analysisTypes; }
    
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}