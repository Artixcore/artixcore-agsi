package com.example.aiengineer.core.ainn;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class NeuralConnection {
    private String connectionId;
    private String fromNodeId;
    private String toNodeId;
    private ConnectionType type;
    private double weight; // 0.0 to 1.0
    private boolean enabled;
    private LocalDateTime created;
    private LocalDateTime lastUsed;
    
    // Transmission metrics
    private final AtomicLong totalTransmissions = new AtomicLong(0);
    private final AtomicLong successfulTransmissions = new AtomicLong(0);
    private final AtomicLong failedTransmissions = new AtomicLong(0);
    
    public NeuralConnection(String connectionId, String fromNodeId, String toNodeId, 
                          ConnectionType type, double weight) {
        this.connectionId = connectionId;
        this.fromNodeId = fromNodeId;
        this.toNodeId = toNodeId;
        this.type = type;
        this.weight = Math.max(0.0, Math.min(1.0, weight));
        this.enabled = true;
        this.created = LocalDateTime.now();
        this.lastUsed = LocalDateTime.now();
    }
    
    public NeuralSignal transmitSignal(NeuralSignal inputSignal) {
        if (!enabled) {
            throw new NeuralProcessingException("Connection is disabled: " + connectionId);
        }
        
        totalTransmissions.incrementAndGet();
        lastUsed = LocalDateTime.now();
        
        try {
            NeuralSignal outputSignal = inputSignal.clone();
            
            // Apply connection weight
            outputSignal.setStrength(inputSignal.getStrength() * weight);
            
            // Apply connection type specific processing
            switch (type) {
                case DIRECT:
                    // Direct transmission with minimal processing
                    break;
                case BIDIRECTIONAL:
                    // Bidirectional connection - signal can flow both ways
                    outputSignal.addMetadata("bidirectional", true);
                    break;
                case INHIBITORY:
                    // Inhibitory connection - reduces signal strength
                    outputSignal.setStrength(outputSignal.getStrength() * 0.5);
                    outputSignal.addMetadata("inhibitory", true);
                    break;
                case EXCITATORY:
                    // Excitatory connection - enhances signal strength
                    outputSignal.setStrength(Math.min(1.0, outputSignal.getStrength() * 1.2));
                    outputSignal.addMetadata("excitatory", true);
                    break;
                case MODULATORY:
                    // Modulatory connection - affects signal characteristics
                    outputSignal.enhanceSignificance(1.1);
                    outputSignal.addMetadata("modulatory", true);
                    break;
                default:
                    // Default processing
                    break;
            }
            
            // Set target node
            outputSignal.setTargetNodeId(toNodeId);
            
            successfulTransmissions.incrementAndGet();
            return outputSignal;
            
        } catch (Exception e) {
            failedTransmissions.incrementAndGet();
            throw new NeuralProcessingException("Signal transmission failed: " + e.getMessage(), e);
        }
    }
    
    public void strengthen(double factor) {
        this.weight = Math.min(1.0, this.weight * factor);
    }
    
    public void weaken(double factor) {
        this.weight = Math.max(0.0, this.weight * factor);
    }
    
    public void enable() {
        this.enabled = true;
    }
    
    public void disable() {
        this.enabled = false;
    }
    
    public boolean isStrong() {
        return weight > 0.7;
    }
    
    public boolean isWeak() {
        return weight < 0.3;
    }
    
    public boolean isActive() {
        return enabled && weight > 0.1;
    }
    
    public double getTransmissionSuccessRate() {
        long total = totalTransmissions.get();
        if (total == 0) return 0.0;
        return (double) successfulTransmissions.get() / total * 100;
    }
    
    public boolean isRecentlyUsed() {
        return lastUsed.isAfter(LocalDateTime.now().minusMinutes(5));
    }
    
    // Getters and Setters
    public String getConnectionId() { return connectionId; }
    public void setConnectionId(String connectionId) { this.connectionId = connectionId; }
    
    public String getFromNodeId() { return fromNodeId; }
    public void setFromNodeId(String fromNodeId) { this.fromNodeId = fromNodeId; }
    
    public String getToNodeId() { return toNodeId; }
    public void setToNodeId(String toNodeId) { this.toNodeId = toNodeId; }
    
    public ConnectionType getType() { return type; }
    public void setType(ConnectionType type) { this.type = type; }
    
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = Math.max(0.0, Math.min(1.0, weight)); }
    
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    
    public LocalDateTime getLastUsed() { return lastUsed; }
    public void setLastUsed(LocalDateTime lastUsed) { this.lastUsed = lastUsed; }
    
    public long getTotalTransmissions() { return totalTransmissions.get(); }
    public long getSuccessfulTransmissions() { return successfulTransmissions.get(); }
    public long getFailedTransmissions() { return failedTransmissions.get(); }
    
    @Override
    public String toString() {
        return String.format("NeuralConnection{id=%s, from=%s, to=%s, type=%s, weight=%.2f, enabled=%s}",
                           connectionId, fromNodeId, toNodeId, type, weight, enabled);
    }
} 