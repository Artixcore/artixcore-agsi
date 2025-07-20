package com.example.aiengineer.core.ainn;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class NeuralPattern {
    private String patternId;
    private String name;
    private Map<String, Object> data;
    private PatternType type;
    private double confidence; // 0.0 to 1.0
    private int frequency;
    private LocalDateTime created;
    private LocalDateTime lastMatched;
    
    // Pattern metrics
    private final AtomicLong totalMatches = new AtomicLong(0);
    private final AtomicLong successfulMatches = new AtomicLong(0);
    private final AtomicLong failedMatches = new AtomicLong(0);
    
    public NeuralPattern(String patternId, String name, Map<String, Object> data) {
        this.patternId = patternId;
        this.name = name;
        this.data = new HashMap<>(data);
        this.type = PatternType.RECOGNITION;
        this.confidence = 0.5;
        this.frequency = 1;
        this.created = LocalDateTime.now();
        this.lastMatched = LocalDateTime.now();
    }
    
    public boolean matches(Map<String, Object> criteria) {
        if (criteria == null || criteria.isEmpty()) {
            return true;
        }
        
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object expectedValue = entry.getValue();
            Object actualValue = data.get(key);
            
            if (!matchesValue(actualValue, expectedValue)) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean matches(NeuralSignal signal) {
        Map<String, Object> signalData = signal.getData();
        return matches(signalData);
    }
    
    private boolean matchesValue(Object actual, Object expected) {
        if (actual == null && expected == null) {
            return true;
        }
        if (actual == null || expected == null) {
            return false;
        }
        
        if (expected instanceof String) {
            String expectedStr = (String) expected;
            if (expectedStr.startsWith("*") && expectedStr.endsWith("*")) {
                // Wildcard matching
                String pattern = expectedStr.substring(1, expectedStr.length() - 1);
                return actual.toString().toLowerCase().contains(pattern.toLowerCase());
            } else if (expectedStr.startsWith("*")) {
                // Suffix matching
                String pattern = expectedStr.substring(1);
                return actual.toString().toLowerCase().endsWith(pattern.toLowerCase());
            } else if (expectedStr.endsWith("*")) {
                // Prefix matching
                String pattern = expectedStr.substring(0, expectedStr.length() - 1);
                return actual.toString().toLowerCase().startsWith(pattern.toLowerCase());
            } else {
                // Exact matching
                return actual.toString().equalsIgnoreCase(expectedStr);
            }
        } else {
            return actual.equals(expected);
        }
    }
    
    public void recordMatch(boolean successful) {
        totalMatches.incrementAndGet();
        lastMatched = LocalDateTime.now();
        
        if (successful) {
            successfulMatches.incrementAndGet();
            frequency++;
            confidence = Math.min(1.0, confidence + 0.01);
        } else {
            failedMatches.incrementAndGet();
            confidence = Math.max(0.0, confidence - 0.01);
        }
    }
    
    public double getMatchSuccessRate() {
        long total = totalMatches.get();
        if (total == 0) return 0.0;
        return (double) successfulMatches.get() / total * 100;
    }
    
    public boolean isFrequentlyUsed() {
        return frequency > 10;
    }
    
    public boolean isConfident() {
        return confidence > 0.7;
    }
    
    public boolean isRecentlyMatched() {
        return lastMatched.isAfter(LocalDateTime.now().minusHours(1));
    }
    
    public void strengthen() {
        confidence = Math.min(1.0, confidence + 0.1);
    }
    
    public void weaken() {
        confidence = Math.max(0.0, confidence - 0.1);
    }
    
    public NeuralPattern merge(NeuralPattern other) {
        // Merge data from another pattern
        this.data.putAll(other.getData());
        
        // Average confidence and sum frequency
        this.confidence = (this.confidence + other.getConfidence()) / 2.0;
        this.frequency += other.getFrequency();
        
        return this;
    }
    
    public Map<String, Object> getSignature() {
        // Create a signature for pattern comparison
        Map<String, Object> signature = new HashMap<>();
        signature.put("type", type);
        signature.put("confidence", confidence);
        signature.put("frequency", frequency);
        signature.put("data_keys", data.keySet());
        return signature;
    }
    
    // Getters and Setters
    public String getPatternId() { return patternId; }
    public void setPatternId(String patternId) { this.patternId = patternId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Map<String, Object> getData() { return new HashMap<>(data); }
    public void setData(Map<String, Object> data) { this.data = new HashMap<>(data); }
    
    public PatternType getType() { return type; }
    public void setType(PatternType type) { this.type = type; }
    
    public double getConfidence() { return confidence; }
    public void setConfidence(double confidence) { this.confidence = Math.max(0.0, Math.min(1.0, confidence)); }
    
    public int getFrequency() { return frequency; }
    public void setFrequency(int frequency) { this.frequency = Math.max(0, frequency); }
    
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    
    public LocalDateTime getLastMatched() { return lastMatched; }
    public void setLastMatched(LocalDateTime lastMatched) { this.lastMatched = lastMatched; }
    
    public long getTotalMatches() { return totalMatches.get(); }
    public long getSuccessfulMatches() { return successfulMatches.get(); }
    public long getFailedMatches() { return failedMatches.get(); }
    
    @Override
    public String toString() {
        return String.format("NeuralPattern{id=%s, name=%s, type=%s, confidence=%.2f, frequency=%d}",
                           patternId, name, type, confidence, frequency);
    }
} 