package com.example.aiengineer.core.websocket;

import org.springframework.web.socket.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArtEngineWebSocketHandler implements WebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        sendMessage(session, Map.of(
            "type", "connection_established",
            "sessionId", session.getId(),
            "engine", "ART-Engine 1.0"
        ));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        Map<String, Object> request = objectMapper.readValue(payload, Map.class);
        
        String type = (String) request.get("type");
        
        switch (type) {
            case "chat_stream":
                handleChatStream(session, request);
                break;
            case "image_progress":
                handleImageProgress(session, request);
                break;
            default:
                sendMessage(session, Map.of(
                    "type", "error",
                    "message", "Unknown request type: " + type
                ));
        }
    }

    private void handleChatStream(WebSocketSession session, Map<String, Object> request) throws Exception {
        String message = (String) request.get("message");
        
        // Simulate streaming response
        String[] words = message.split(" ");
        for (String word : words) {
            sendMessage(session, Map.of(
                "type", "chat_chunk",
                "content", word + " ",
                "done", false
            ));
            Thread.sleep(100); // Simulate processing delay
        }
        
        sendMessage(session, Map.of(
            "type", "chat_chunk",
            "content", "",
            "done", true
        ));
    }

    private void handleImageProgress(WebSocketSession session, Map<String, Object> request) throws Exception {
        String prompt = (String) request.get("prompt");
        
        // Simulate image generation progress
        for (int i = 0; i <= 100; i += 10) {
            sendMessage(session, Map.of(
                "type", "image_progress",
                "progress", i,
                "status", i < 100 ? "generating" : "complete"
            ));
            Thread.sleep(200);
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