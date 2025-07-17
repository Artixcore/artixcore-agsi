package com.example.aiengineer.core.metrics;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ArtEngineMetrics {
    
    private final AtomicLong totalRequests = new AtomicLong(0);
    private final AtomicLong successfulRequests = new AtomicLong(0);
    private final AtomicLong failedRequests = new AtomicLong(0);
    private final Map<String, AtomicLong> requestsByType = new ConcurrentHashMap<>();
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

    public Map<String, Object> getMetrics() {
        return Map.of(
            "total_requests", totalRequests.get(),
            "successful_requests", successfulRequests.get(),
            "failed_requests", failedRequests.get(),
            "success_rate", calculateSuccessRate(),
            "uptime_ms", System.currentTimeMillis() - startTime,
            "requests_by_type", getRequestsByType()
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
}