package com.example.aiengineer.api.alim;

import com.example.aiengineer.core.dto.alim.*;
import com.example.aiengineer.core.metrics.AlimMetrics;
import com.example.aiengineer.services.alim.AlimService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.Map;

@Controller
public class AlimGraphQLController {

    private final AlimService alimService;
    private final AlimMetrics alimMetrics;

    public AlimGraphQLController(AlimService alimService, AlimMetrics alimMetrics) {
        this.alimService = alimService;
        this.alimMetrics = alimMetrics;
    }

    @QueryMapping
    public Map<String, Object> alimStatus() {
        return Map.of(
            "agent", "Alim 1.0",
            "version", "1.0",
            "status", "active",
            "description", "AI-powered Meta-Research Intelligence System",
            "capabilities", alimService.getCapabilities(),
            "inspiration", "Inspired by the legendary polymath Al-Kindi",
            "uptime", System.currentTimeMillis()
        );
    }

    @QueryMapping
    public Map<String, Object> alimMetrics() {
        return alimMetrics.getMetrics();
    }

    @MutationMapping
    public Map<String, Object> analyzeResearch(@Argument Map<String, Object> input) {
        NeuroResearchRequest request = new NeuroResearchRequest();
        request.setResearchText((String) input.get("researchText"));
        request.setDisciplines((java.util.List<String>) input.get("disciplines"));
        request.setAnalysisDepth((String) input.getOrDefault("analysisDepth", "comprehensive"));
        
        alimMetrics.incrementTotalRequests();
        alimMetrics.incrementRequestType("neuro_research");
        
        Map<String, Object> response = alimService.analyzeResearch(request);
        
        if ((Boolean) response.get("success")) {
            alimMetrics.incrementSuccessfulRequests();
        } else {
            alimMetrics.incrementFailedRequests();
        }
        
        return response;
    }

    @MutationMapping
    public Map<String, Object> findResearchGaps(@Argument Map<String, Object> input) {
        ResearchGapRequest request = new ResearchGapRequest();
        request.setDomain((String) input.get("domain"));
        request.setKeywords((java.util.List<String>) input.get("keywords"));
        request.setTimeframe((String) input.getOrDefault("timeframe", "5_years"));
        
        alimMetrics.incrementTotalRequests();
        alimMetrics.incrementRequestType("gap_analysis");
        
        Map<String, Object> response = alimService.findResearchGaps(request);
        
        if ((Boolean) response.get("success")) {
            alimMetrics.incrementSuccessfulRequests();
        } else {
            alimMetrics.incrementFailedRequests();
        }
        
        return response;
    }

    @MutationMapping
    public Map<String, Object> peerReview(@Argument Map<String, Object> input) {
        AutoReviewerRequest request = new AutoReviewerRequest();
        request.setPaperContent((String) input.get("paperContent"));
        request.setJournalStandards((String) input.getOrDefault("journalStandards", "high_impact"));
        request.setReviewFocus((java.util.List<String>) input.get("reviewFocus"));
        
        alimMetrics.incrementTotalRequests();
        alimMetrics.incrementRequestType("peer_review");
        
        Map<String, Object> response = alimService.peerReview(request);
        
        if ((Boolean) response.get("success")) {
            alimMetrics.incrementSuccessfulRequests();
        } else {
            alimMetrics.incrementFailedRequests();
        }
        
        return response;
    }

    @MutationMapping
    public Map<String, Object> generateHypotheses(@Argument Map<String, Object> input) {
        HypothesisForgeRequest request = new HypothesisForgeRequest();
        request.setProblemStatement((String) input.get("problemStatement"));
        request.setDomain((String) input.get("domain"));
        request.setHypothesisCount((Integer) input.getOrDefault("hypothesisCount", 5));
        
        alimMetrics.incrementTotalRequests();
        alimMetrics.incrementRequestType("hypothesis_generation");
        
        Map<String, Object> response = alimService.generateHypotheses(request);
        
        if ((Boolean) response.get("success")) {
            alimMetrics.incrementSuccessfulRequests();
        } else {
            alimMetrics.incrementFailedRequests();
        }
        
        return response;
    }

    @MutationMapping
    public Map<String, Object> deepKnowledgeAnalysis(@Argument Map<String, Object> input) {
        IslamicKnowledgeRequest request = new IslamicKnowledgeRequest();
        request.setResearchTopic((String) input.get("researchTopic"));
        request.setKnowledgeDepth((String) input.getOrDefault("knowledgeDepth", "comprehensive"));
        request.setIncludeSpiritual((Boolean) input.getOrDefault("includeSpiritual", true));
        
        alimMetrics.incrementTotalRequests();
        alimMetrics.incrementRequestType("islamic_knowledge");
        
        Map<String, Object> response = alimService.deepKnowledgeAnalysis(request);
        
        if ((Boolean) response.get("success")) {
            alimMetrics.incrementSuccessfulRequests();
        } else {
            alimMetrics.incrementFailedRequests();
        }
        
        return response;
    }

    @MutationMapping
    public Map<String, Object> crossDisciplineLink(@Argument Map<String, Object> input) {
        CrossDisciplineRequest request = new CrossDisciplineRequest();
        request.setPrimaryDiscipline((String) input.get("primaryDiscipline"));
        request.setSecondaryDiscipline((String) input.get("secondaryDiscipline"));
        request.setResearchQuestion((String) input.get("researchQuestion"));
        
        alimMetrics.incrementTotalRequests();
        alimMetrics.incrementRequestType("cross_discipline");
        
        Map<String, Object> response = alimService.crossDisciplineLink(request);
        
        if ((Boolean) response.get("success")) {
            alimMetrics.incrementSuccessfulRequests();
        } else {
            alimMetrics.incrementFailedRequests();
        }
        
        return response;
    }
} 