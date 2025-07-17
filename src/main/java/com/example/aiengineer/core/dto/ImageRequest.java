package com.example.aiengineer.core.dto;

public class ImageRequest {
    private String prompt;
    private String style = "realistic";
    private Integer width = 512;
    private Integer height = 512;
    private String format = "png";

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
    
    public String getStyle() { return style; }
    public void setStyle(String style) { this.style = style; }
    
    public Integer getWidth() { return width; }
    public void setWidth(Integer width) { this.width = width; }
    
    public Integer getHeight() { return height; }
    public void setHeight(Integer height) { this.height = height; }
    
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
}