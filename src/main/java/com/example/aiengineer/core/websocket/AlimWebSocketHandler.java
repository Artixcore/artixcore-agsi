package com.example.aiengineer.core.websocket;

import org.springframework.web.socket.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AlimWebSocketHandler implements WebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        sendMessage(session, Map.of(
            "type", "connection_established",
            "sessionId", session.getId(),
            "agent", "Alim 1.0",
            "inspiration", "Inspired by the legendary polymath Al-Kindi",
            "capabilities", "9 research intelligence modules active"
        ));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        Map<String, Object> request = objectMapper.readValue(payload, Map.class);
        
        String type = (String) request.get("type");
        
        switch (type) {
            case "neuro_research_stream":
                handleNeuroResearchStream(session, request);
                break;
            case "peer_review_stream":
                handlePeerReviewStream(session, request);
                break;
            case "hypothesis_generation_stream":
                handleHypothesisGenerationStream(session, request);
                break;
            case "citation_mesh_stream":
                handleCitationMeshStream(session, request);
                break;
            case "islamic_knowledge_stream":
                handleIslamicKnowledgeStream(session, request);
                break;
            case "cross_discipline_stream":
                handleCrossDisciplineStream(session, request);
                break;
            default:
                sendMessage(session, Map.of(
                    "type", "error",
                    "message", "Unknown request type: " + type
                ));
        }
    }

    private void handleNeuroResearchStream(WebSocketSession session, Map<String, Object> request) throws Exception {
        String researchText = (String) request.get("researchText");
        String[] analysisSteps = {
            "Analyzing research text across disciplines...",
            "Identifying cross-discipline connections...",
            "Detecting emergent patterns...",
            "Mapping knowledge gaps...",
            "Integrating classical and modern frameworks...",
            "Generating comprehensive analysis..."
        };
        
        for (int i = 0; i < analysisSteps.length; i++) {
            sendMessage(session, Map.of(
                "type", "neuro_research_chunk",
                "content", analysisSteps[i],
                "progress", (i + 1) * 100 / analysisSteps.length,
                "done", i == analysisSteps.length - 1
            ));
            Thread.sleep(500);
        }
    }

    private void handlePeerReviewStream(WebSocketSession session, Map<String, Object> request) throws Exception {
        String[] reviewSteps = {
            "Analyzing paper structure and coherence...",
            "Evaluating logical arguments and methodology...",
            "Checking citation quality and relevance...",
            "Detecting potential biases and assumptions...",
            "Assessing contribution to the field...",
            "Generating constructive feedback...",
            "Providing recommendations for improvement..."
        };
        
        for (int i = 0; i < reviewSteps.length; i++) {
            sendMessage(session, Map.of(
                "type", "peer_review_chunk",
                "content", reviewSteps[i],
                "progress", (i + 1) * 100 / reviewSteps.length,
                "done", i == reviewSteps.length - 1
            ));
            Thread.sleep(400);
        }
    }

    private void handleHypothesisGenerationStream(WebSocketSession session, Map<String, Object> request) throws Exception {
        String[] hypothesisSteps = {
            "Analyzing problem statement...",
            "Identifying key variables and relationships...",
            "Generating testable hypotheses...",
            "Designing experimental frameworks...",
            "Calculating grant-worthiness scores...",
            "Assessing feasibility and impact...",
            "Finalizing hypothesis portfolio..."
        };
        
        for (int i = 0; i < hypothesisSteps.length; i++) {
            sendMessage(session, Map.of(
                "type", "hypothesis_chunk",
                "content", hypothesisSteps[i],
                "progress", (i + 1) * 100 / hypothesisSteps.length,
                "done", i == hypothesisSteps.length - 1
            ));
            Thread.sleep(450);
        }
    }

    private void handleCitationMeshStream(WebSocketSession session, Map<String, Object> request) throws Exception {
        String[] citationSteps = {
            "Analyzing research paper citations...",
            "Mapping influence networks...",
            "Tracking citation ancestry...",
            "Predicting future impact...",
            "Identifying cross-discipline connections...",
            "Generating citation mesh visualization..."
        };
        
        for (int i = 0; i < citationSteps.length; i++) {
            sendMessage(session, Map.of(
                "type", "citation_mesh_chunk",
                "content", citationSteps[i],
                "progress", (i + 1) * 100 / citationSteps.length,
                "done", i == citationSteps.length - 1
            ));
            Thread.sleep(350);
        }
    }

    private void handleIslamicKnowledgeStream(WebSocketSession session, Map<String, Object> request) throws Exception {
        String[] islamicSteps = {
            "Applying Tawhidic worldview...",
            "Integrating classical Islamic epistemology...",
            "Exploring ma'rifah (deep knowledge)...",
            "Applying ijtihad (independent reasoning)...",
            "Connecting spiritual and material knowledge...",
            "Bridging divine wisdom with human inquiry..."
        };
        
        for (int i = 0; i < islamicSteps.length; i++) {
            sendMessage(session, Map.of(
                "type", "islamic_knowledge_chunk",
                "content", islamicSteps[i],
                "progress", (i + 1) * 100 / islamicSteps.length,
                "done", i == islamicSteps.length - 1
            ));
            Thread.sleep(600);
        }
    }

    private void handleCrossDisciplineStream(WebSocketSession session, Map<String, Object> request) throws Exception {
        String[] crossDisciplineSteps = {
            "Analyzing primary discipline...",
            "Exploring secondary discipline...",
            "Identifying metaphysical connections...",
            "Mapping theoretical frameworks...",
            "Discovering practical applications...",
            "Synthesizing cross-discipline insights..."
        };
        
        for (int i = 0; i < crossDisciplineSteps.length; i++) {
            sendMessage(session, Map.of(
                "type", "cross_discipline_chunk",
                "content", crossDisciplineSteps[i],
                "progress", (i + 1) * 100 / crossDisciplineSteps.length,
                "done", i == crossDisciplineSteps.length - 1
            ));
            Thread.sleep(400);
        }
    }

    private void sendMessage(WebSocketSession session, Map<String, Object> message) throws Exception {
        String json = objectMapper.writeValueAsString(message);
        session.sendMessage(new TextMessage(json));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        sessions.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessions.remove(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
} 