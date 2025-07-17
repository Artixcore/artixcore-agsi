package com.example.aiengineer.api.artengine;

import com.example.aiengineer.core.dto.*;
import com.example.aiengineer.core.metrics.ArtEngineMetrics;
import com.example.aiengineer.services.artengine.ArtEngineService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.Map;

@Controller
public class ArtEngineGraphQLController {

    private final ArtEngineService artEngineService;
    private final ArtEngineMetrics artEngineMetrics;

    public ArtEngineGraphQLController(ArtEngineService artEngineService, ArtEngineMetrics artEngineMetrics) {
        this.artEngineService = artEngineService;
        this.artEngineMetrics = artEngineMetrics;
    }

    @QueryMapping
    public Map<String, Object> engineStatus() {
        return Map.of(
            "engine", "ART-Engine",
            "version", "1.0",
            "status", "active",
            "capabilities", artEngineService.getCapabilities(),
            "uptime", System.currentTimeMillis()
        );
    }

    @QueryMapping
    public Map<String, Object> engineMetrics() {
        return artEngineMetrics.getMetrics();
    }

    @MutationMapping
    public Map<String, Object> chatCompletion(@Argument Map<String, Object> input) {
        ChatRequest request = new ChatRequest();
        request.setMessage((String) input.get("message"));
        request.setModel((String) input.getOrDefault("model", "ollama"));
        request.setSessionId((String) input.get("sessionId"));
        
        artEngineMetrics.incrementTotalRequests();
        artEngineMetrics.incrementRequestType("chat");
        
        Map<String, Object> response = artEngineService.processChatCompletion(request);
        
        if ((Boolean) response.get("success")) {
            artEngineMetrics.incrementSuccessfulRequests();
        } else {
            artEngineMetrics.incrementFailedRequests();
        }
        
        return response;
    }

    @MutationMapping
    public Map<String, Object> generateImage(@Argument Map<String, Object> input) {
        ImageRequest request = new ImageRequest();
        request.setPrompt((String) input.get("prompt"));
        request.setStyle((String) input.getOrDefault("style", "realistic"));
        
        artEngineMetrics.incrementTotalRequests();
        artEngineMetrics.incrementRequestType("image");
        
        Map<String, Object> response = artEngineService.generateImage(request);
        
        if ((Boolean) response.get("success")) {
            artEngineMetrics.incrementSuccessfulRequests();
        } else {
            artEngineMetrics.incrementFailedRequests();
        }
        
        return response;
    }

    @MutationMapping
    public Map<String, Object> analyzeText(@Argument Map<String, Object> input) {
        TextAnalysisRequest request = new TextAnalysisRequest();
        request.setText((String) input.get("text"));
        request.setLanguage((String) input.getOrDefault("language", "en"));
        
        artEngineMetrics.incrementTotalRequests();
        artEngineMetrics.incrementRequestType("text_analysis");
        
        Map<String, Object> response = artEngineService.analyzeText(request);
        
        if ((Boolean) response.get("success")) {
            artEngineMetrics.incrementSuccessfulRequests();
        } else {
            artEngineMetrics.incrementFailedRequests();
        }
        
        return response;
    }
}