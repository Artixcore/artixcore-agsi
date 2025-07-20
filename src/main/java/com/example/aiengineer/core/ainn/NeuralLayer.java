package com.example.aiengineer.core.ainn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class NeuralLayer {
    private String layerId;
    private String name;
    private NeuralLayerType type;
    private List<String> nodeIds;
    private int maxNodes;
    private boolean enabled;
    private LocalDateTime created;
    private LocalDateTime lastActivation;
    
    // Layer metrics
    private final AtomicLong totalActivations = new AtomicLong(0);
    private final AtomicLong successfulProcessings = new AtomicLong(0);
    private final AtomicLong failedProcessings = new AtomicLong(0);
    
    public NeuralLayer(String layerId, String name, NeuralLayerType type) {
        this.layerId = layerId;
        this.name = name;
        this.type = type;
        this.nodeIds = new ArrayList<>();
        this.maxNodes = getDefaultMaxNodes(type);
        this.enabled = true;
        this.created = LocalDateTime.now();
        this.lastActivation = LocalDateTime.now();
    }
    
    private int getDefaultMaxNodes(NeuralLayerType type) {
        switch (type) {
            case INPUT:
                return 100;
            case HIDDEN:
                return 200;
            case OUTPUT:
                return 50;
            case MEMORY:
                return 1000;
            case LEARNING:
                return 100;
            default:
                return 100;
        }
    }
    
    public void addNode(String nodeId) {
        if (nodeIds.size() < maxNodes) {
            nodeIds.add(nodeId);
        } else {
            throw new NeuralProcessingException("Layer " + layerId + " has reached maximum capacity");
        }
    }
    
    public void removeNode(String nodeId) {
        nodeIds.remove(nodeId);
    }
    
    public boolean containsNode(String nodeId) {
        return nodeIds.contains(nodeId);
    }
    
    public int getNodeCount() {
        return nodeIds.size();
    }
    
    public boolean isFull() {
        return nodeIds.size() >= maxNodes;
    }
    
    public boolean isEmpty() {
        return nodeIds.isEmpty();
    }
    
    public void activate() {
        if (!enabled) {
            throw new NeuralProcessingException("Layer is disabled: " + layerId);
        }
        
        totalActivations.incrementAndGet();
        lastActivation = LocalDateTime.now();
    }
    
    public void enable() {
        this.enabled = true;
    }
    
    public void disable() {
        this.enabled = false;
    }
    
    public boolean isActive() {
        return enabled && !nodeIds.isEmpty();
    }
    
    public double getActivationRate() {
        long total = totalActivations.get();
        if (total == 0) return 0.0;
        return (double) successfulProcessings.get() / total * 100;
    }
    
    public boolean isRecentlyActive() {
        return lastActivation.isAfter(LocalDateTime.now().minusMinutes(5));
    }
    
    public String getLayerDescription() {
        return String.format("%s Layer (%s) - %d nodes, %s", 
                           name, type, nodeIds.size(), 
                           enabled ? "enabled" : "disabled");
    }
    
    // Getters and Setters
    public String getLayerId() { return layerId; }
    public void setLayerId(String layerId) { this.layerId = layerId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public NeuralLayerType getType() { return type; }
    public void setType(NeuralLayerType type) { this.type = type; }
    
    public List<String> getNodeIds() { return new ArrayList<>(nodeIds); }
    public void setNodeIds(List<String> nodeIds) { this.nodeIds = new ArrayList<>(nodeIds); }
    
    public int getMaxNodes() { return maxNodes; }
    public void setMaxNodes(int maxNodes) { this.maxNodes = maxNodes; }
    
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    
    public LocalDateTime getLastActivation() { return lastActivation; }
    public void setLastActivation(LocalDateTime lastActivation) { this.lastActivation = lastActivation; }
    
    public long getTotalActivations() { return totalActivations.get(); }
    public long getSuccessfulProcessings() { return successfulProcessings.get(); }
    public void incrementSuccessfulProcessings() { successfulProcessings.incrementAndGet(); }
    public long getFailedProcessings() { return failedProcessings.get(); }
    public void incrementFailedProcessings() { failedProcessings.incrementAndGet(); }
    
    @Override
    public String toString() {
        return String.format("NeuralLayer{id=%s, name=%s, type=%s, nodes=%d/%d, enabled=%s}",
                           layerId, name, type, nodeIds.size(), maxNodes, enabled);
    }
} 