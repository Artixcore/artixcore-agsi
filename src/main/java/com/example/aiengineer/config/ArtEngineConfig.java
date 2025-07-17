package com.example.aiengineer.config;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.stabilityai.StabilityAiImageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class ArtEngineConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ArtEngineWebSocketHandler(), "/ws/art-engine")
                .setAllowedOrigins("*");
    }

    @Bean
    public ArtEngineMetrics artEngineMetrics() {
        return new ArtEngineMetrics();
    }
}