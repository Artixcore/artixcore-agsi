package com.example.aiengineer.api.artengine;

import com.example.aiengineer.core.dto.*;
import com.example.aiengineer.services.artengine.ArtEngineService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/art-engine")
@CrossOrigin(origins = "*")
public class ArtEngineController {

    private final ArtEngineService artEngineService;

    public ArtEngineController(ArtEngineService artEngineService) {
        this.artEngineService = artEngineService;
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        return ResponseEntity.ok(Map.of(
            "engine", "ART-Engine",
            "version", "1.0",
            "status", "active",
            "capabilities", artEngineService.getCapabilities()
        ));
    }

    @PostMapping("/chat/completion")
    public ResponseEntity<Map<String, Object>> chatCompletion(@RequestBody ChatRequest request) {
        return ResponseEntity.ok(artEngineService.processChatCompletion(request));
    }

    @PostMapping("/image/generate")
    public ResponseEntity<Map<String, Object>> generateImage(@RequestBody ImageRequest request) {
        return ResponseEntity.ok(artEngineService.generateImage(request));
    }

    @PostMapping("/text/analyze")
    public ResponseEntity<Map<String, Object>> analyzeText(@RequestBody TextAnalysisRequest request) {
        return ResponseEntity.ok(artEngineService.analyzeText(request));
    }

    @PostMapping("/data/process")
    public ResponseEntity<Map<String, Object>> processData(@RequestBody DataProcessingRequest request) {
        return ResponseEntity.ok(artEngineService.processData(request));
    }
}