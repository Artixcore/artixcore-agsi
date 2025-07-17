package com.example.aiengineer.api.alim;

import com.example.aiengineer.core.dto.alim.*;
import com.example.aiengineer.services.alim.AlimService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/alim")
@CrossOrigin(origins = "*")
public class AlimController {

    private final AlimService alimService;

    public AlimController(AlimService alimService) {
        this.alimService = alimService;
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        return ResponseEntity.ok(Map.of(
            "agent", "Alim 1.0",
            "version", "1.0",
            "status", "active",
            "description", "AI-powered Meta-Research Intelligence System",
            "capabilities", alimService.getCapabilities(),
            "inspiration", "Inspired by the legendary polymath Al-Kindi"
        ));
    }

    @PostMapping("/neuro-research/analyze")
    public ResponseEntity<Map<String, Object>> analyzeResearch(@RequestBody NeuroResearchRequest request) {
        return ResponseEntity.ok(alimService.analyzeResearch(request));
    }

    @PostMapping("/neuro-research/gap-analysis")
    public ResponseEntity<Map<String, Object>> findResearchGaps(@RequestBody ResearchGapRequest request) {
        return ResponseEntity.ok(alimService.findResearchGaps(request));
    }

    @PostMapping("/auto-reviewer/review")
    public ResponseEntity<Map<String, Object>> peerReview(@RequestBody AutoReviewerRequest request) {
        return ResponseEntity.ok(alimService.peerReview(request));
    }

    @PostMapping("/hypothesis-forge/generate")
    public ResponseEntity<Map<String, Object>> generateHypotheses(@RequestBody HypothesisForgeRequest request) {
        return ResponseEntity.ok(alimService.generateHypotheses(request));
    }

    @PostMapping("/hypothesis-forge/experiment-design")
    public ResponseEntity<Map<String, Object>> designExperiment(@RequestBody ExperimentDesignRequest request) {
        return ResponseEntity.ok(alimService.designExperiment(request));
    }

    @PostMapping("/deep-memory/citation-mesh")
    public ResponseEntity<Map<String, Object>> generateCitationMesh(@RequestBody CitationMeshRequest request) {
        return ResponseEntity.ok(alimService.generateCitationMesh(request));
    }

    @PostMapping("/deep-memory/impact-prediction")
    public ResponseEntity<Map<String, Object>> predictImpact(@RequestBody ImpactPredictionRequest request) {
        return ResponseEntity.ok(alimService.predictImpact(request));
    }

    @PostMapping("/islamic-knowledge/ma-rifah")
    public ResponseEntity<Map<String, Object>> deepKnowledgeAnalysis(@RequestBody IslamicKnowledgeRequest request) {
        return ResponseEntity.ok(alimService.deepKnowledgeAnalysis(request));
    }

    @PostMapping("/islamic-knowledge/ijtihad")
    public ResponseEntity<Map<String, Object>> independentReasoning(@RequestBody IjtihadRequest request) {
        return ResponseEntity.ok(alimService.independentReasoning(request));
    }

    @PostMapping("/cross-discipline/link")
    public ResponseEntity<Map<String, Object>> crossDisciplineLink(@RequestBody CrossDisciplineRequest request) {
        return ResponseEntity.ok(alimService.crossDisciplineLink(request));
    }

    @PostMapping("/research-strategy/plan")
    public ResponseEntity<Map<String, Object>> createResearchStrategy(@RequestBody ResearchStrategyRequest request) {
        return ResponseEntity.ok(alimService.createResearchStrategy(request));
    }
} 