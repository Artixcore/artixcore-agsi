package com.example.aiengineer.core.ainn;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class LearningPattern {
    private String patternId;
    private String name;
    private Map<String, Object> criteria;
    private LearningAction action;
    private double effectiveness; // 0.0 to 1.0
    private int usageCount;
    private LocalDateTime created;
    private LocalDateTime lastUsed;
    
    // Learning metrics
    private final AtomicLong totalApplications = new AtomicLong(0);
    private final AtomicLong successfulApplications = new AtomicLong(0);
    private final AtomicLong failedApplications = new AtomicLong(0);
    
    public LearningPattern(String patternId, String name, Map<String, Object> criteria, LearningAction action) {
        this.patternId = patternId;
        this.name = name;
        this.criteria = criteria;
        this.action = action;
        this.effectiveness = 0.5;
        this.usageCount = 0;
        this.created = LocalDateTime.now();
        this.lastUsed = LocalDateTime.now();
    }
    
    public boolean matches(NeuralSignal signal) {
        if (criteria == null || criteria.isEmpty()) {
            return true;
        }
        
        Map<String, Object> signalData = signal.getData();
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object expectedValue = entry.getValue();
            Object actualValue = signalData.get(key);
            
            if (!matchesValue(actualValue, expectedValue)) {
                return false;
            }
        }
        
        return true;
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
    
    public NeuralSignal apply(NeuralSignal signal) {
        totalApplications.incrementAndGet();
        usageCount++;
        lastUsed = LocalDateTime.now();
        
        try {
            NeuralSignal result = action.execute(signal);
            successfulApplications.incrementAndGet();
            effectiveness = Math.min(1.0, effectiveness + 0.01);
            return result;
        } catch (Exception e) {
            failedApplications.incrementAndGet();
            effectiveness = Math.max(0.0, effectiveness - 0.01);
            throw new NeuralProcessingException("Learning pattern application failed: " + e.getMessage(), e);
        }
    }
    
    public double getSuccessRate() {
        long total = totalApplications.get();
        if (total == 0) return 0.0;
        return (double) successfulApplications.get() / total * 100;
    }
    
    public boolean isEffective() {
        return effectiveness > 0.7;
    }
    
    public boolean isFrequentlyUsed() {
        return usageCount > 10;
    }
    
    public boolean isRecentlyUsed() {
        return lastUsed.isAfter(LocalDateTime.now().minusHours(1));
    }
    
    public void strengthen() {
        effectiveness = Math.min(1.0, effectiveness + 0.1);
    }
    
    public void weaken() {
        effectiveness = Math.max(0.0, effectiveness - 0.1);
    }
    
    // Getters and Setters
    public String getPatternId() { return patternId; }
    public void setPatternId(String patternId) { this.patternId = patternId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Map<String, Object> getCriteria() { return criteria; }
    public void setCriteria(Map<String, Object> criteria) { this.criteria = criteria; }
    
    public LearningAction getAction() { return action; }
    public void setAction(LearningAction action) { this.action = action; }
    
    public double getEffectiveness() { return effectiveness; }
    public void setEffectiveness(double effectiveness) { 
        this.effectiveness = Math.max(0.0, Math.min(1.0, effectiveness)); 
    }
    
    public int getUsageCount() { return usageCount; }
    public void setUsageCount(int usageCount) { this.usageCount = Math.max(0, usageCount); }
    
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    
    public LocalDateTime getLastUsed() { return lastUsed; }
    public void setLastUsed(LocalDateTime lastUsed) { this.lastUsed = lastUsed; }
    
    public long getTotalApplications() { return totalApplications.get(); }
    public long getSuccessfulApplications() { return successfulApplications.get(); }
    public long getFailedApplications() { return failedApplications.get(); }
    
    @Override
    public String toString() {
        return String.format("LearningPattern{id=%s, name=%s, effectiveness=%.2f, usage=%d}",
                           patternId, name, effectiveness, usageCount);
    }
} 