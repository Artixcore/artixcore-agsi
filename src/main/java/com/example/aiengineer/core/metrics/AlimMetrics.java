package com.example.aiengineer.core.metrics;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AlimMetrics {
    
    private final AtomicLong totalRequests = new AtomicLong(0);
    private final AtomicLong successfulRequests = new AtomicLong(0);
    private final AtomicLong failedRequests = new AtomicLong(0);
    private final Map<String, AtomicLong> requestsByType = new ConcurrentHashMap<>();
    private final Map<String, AtomicLong> researchDomains = new ConcurrentHashMap<>();
    private final Map<String, AtomicLong> islamicKnowledgeRequests = new ConcurrentHashMap<>();
    private final long startTime = System.currentTimeMillis();

    public void incrementTotalRequests() {
        totalRequests.incrementAndGet();
    }

    public void incrementSuccessfulRequests() {
        successfulRequests.incrementAndGet();
    }

    public void incrementFailedRequests() {
        failedRequests.incrementAndGet();
    }

    public void incrementRequestType(String type) {
        requestsByType.computeIfAbsent(type, k -> new AtomicLong(0)).incrementAndGet();
    }

    public void incrementResearchDomain(String domain) {
        researchDomains.computeIfAbsent(domain, k -> new AtomicLong(0)).incrementAndGet();
    }

    public void incrementIslamicKnowledgeRequest(String type) {
        islamicKnowledgeRequests.computeIfAbsent(type, k -> new AtomicLong(0)).incrementAndGet();
    }

    public Map<String, Object> getMetrics() {
        return Map.of(
            "total_requests", totalRequests.get(),
            "successful_requests", successfulRequests.get(),
            "failed_requests", failedRequests.get(),
            "success_rate", calculateSuccessRate(),
            "uptime_ms", System.currentTimeMillis() - startTime,
            "requests_by_type", getRequestsByType(),
            "research_domains", getResearchDomains(),
            "islamic_knowledge_requests", getIslamicKnowledgeRequests(),
            "agent_info", Map.of(
                "name", "Alim 1.0",
                "inspiration", "Al-Kindi",
                "capabilities", 9,
                "status", "active"
            )
        );
    }

    private double calculateSuccessRate() {
        long total = totalRequests.get();
        if (total == 0) return 0.0;
        return (double) successfulRequests.get() / total * 100;
    }

    private Map<String, Long> getRequestsByType() {
        Map<String, Long> result = new ConcurrentHashMap<>();
        requestsByType.forEach((key, value) -> result.put(key, value.get()));
        return result;
    }

    private Map<String, Long> getResearchDomains() {
        Map<String, Long> result = new ConcurrentHashMap<>();
        researchDomains.forEach((key, value) -> result.put(key, value.get()));
        return result;
    }

    private Map<String, Long> getIslamicKnowledgeRequests() {
        Map<String, Long> result = new ConcurrentHashMap<>();
        islamicKnowledgeRequests.forEach((key, value) -> result.put(key, value.get()));
        return result;
    }
} 