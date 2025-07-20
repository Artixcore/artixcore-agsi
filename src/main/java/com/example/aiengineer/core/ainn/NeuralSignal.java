package com.example.aiengineer.core.ainn;

import java.util.HashMap;
import java.util.Map;

public class NeuralSignal {
    private String signalId;
    private String sourceNodeId;
    private String targetNodeId;
    private Map<String, Object> data;
    private double strength; // 0.0 to 1.0
    private double significance; // 0.0 to 1.0
    private SignalType type;
    private long timestamp;
    private Map<String, Object> metadata;
    
    public NeuralSignal() {
        this.signalId = "signal_" + System.currentTimeMillis() + "_" + System.nanoTime();
        this.data = new HashMap<>();
        this.metadata = new HashMap<>();
        this.strength = 1.0;
        this.significance = 0.5;
        this.type = SignalType.INFORMATION;
        this.timestamp = System.currentTimeMillis();
    }
    
    public NeuralSignal(String sourceNodeId, Map<String, Object> data) {
        this();
        this.sourceNodeId = sourceNodeId;
        this.data = new HashMap<>(data);
    }
    
    // Data management
    public void addData(String key, Object value) {
        data.put(key, value);
    }
    
    public Object getData(String key) {
        return data.get(key);
    }
    
    public Map<String, Object> getData() {
        return new HashMap<>(data);
    }
    
    public void setData(Map<String, Object> data) {
        this.data = new HashMap<>(data);
    }
    
    // Metadata management
    public void addMetadata(String key, Object value) {
        metadata.put(key, value);
    }
    
    public Object getMetadata(String key) {
        return metadata.get(key);
    }
    
    public Map<String, Object> getMetadata() {
        return new HashMap<>(metadata);
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = new HashMap<>(metadata);
    }
    
    // Signal processing
    public NeuralSignal amplify(double factor) {
        this.strength = Math.min(1.0, this.strength * factor);
        return this;
    }
    
    public NeuralSignal attenuate(double factor) {
        this.strength = Math.max(0.0, this.strength * factor);
        return this;
    }
    
    public NeuralSignal enhanceSignificance(double factor) {
        this.significance = Math.min(1.0, this.significance * factor);
        return this;
    }
    
    public NeuralSignal merge(NeuralSignal other) {
        // Merge data from another signal
        this.data.putAll(other.getData());
        this.metadata.putAll(other.getMetadata());
        
        // Average strength and significance
        this.strength = (this.strength + other.getStrength()) / 2.0;
        this.significance = Math.max(this.significance, other.getSignificance());
        
        return this;
    }
    
    public NeuralSignal clone() {
        NeuralSignal clone = new NeuralSignal();
        clone.signalId = this.signalId + "_clone";
        clone.sourceNodeId = this.sourceNodeId;
        clone.targetNodeId = this.targetNodeId;
        clone.data = new HashMap<>(this.data);
        clone.strength = this.strength;
        clone.significance = this.significance;
        clone.type = this.type;
        clone.timestamp = System.currentTimeMillis();
        clone.metadata = new HashMap<>(this.metadata);
        return clone;
    }
    
    // Signal analysis
    public boolean isStrong() {
        return strength > 0.7;
    }
    
    public boolean isSignificant() {
        return significance > 0.7;
    }
    
    public boolean isWeak() {
        return strength < 0.3;
    }
    
    public boolean isNoise() {
        return significance < 0.2;
    }
    
    public boolean containsData(String key) {
        return data.containsKey(key);
    }
    
    public boolean containsMetadata(String key) {
        return metadata.containsKey(key);
    }
    
    // Getters and Setters
    public String getSignalId() { return signalId; }
    public void setSignalId(String signalId) { this.signalId = signalId; }
    
    public String getSourceNodeId() { return sourceNodeId; }
    public void setSourceNodeId(String sourceNodeId) { this.sourceNodeId = sourceNodeId; }
    
    public String getTargetNodeId() { return targetNodeId; }
    public void setTargetNodeId(String targetNodeId) { this.targetNodeId = targetNodeId; }
    
    public double getStrength() { return strength; }
    public void setStrength(double strength) { this.strength = Math.max(0.0, Math.min(1.0, strength)); }
    
    public double getSignificance() { return significance; }
    public void setSignificance(double significance) { this.significance = Math.max(0.0, Math.min(1.0, significance)); }
    
    public SignalType getType() { return type; }
    public void setType(SignalType type) { this.type = type; }
    
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    
    @Override
    public String toString() {
        return String.format("NeuralSignal{id=%s, source=%s, strength=%.2f, significance=%.2f, type=%s}",
                           signalId, sourceNodeId, strength, significance, type);
    }
} 