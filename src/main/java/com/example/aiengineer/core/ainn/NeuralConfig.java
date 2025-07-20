package com.example.aiengineer.core.ainn;

import java.util.HashMap;
import java.util.Map;

public class NeuralConfig {
    // Network configuration
    private int maxNodes = 1000;
    private int maxConnections = 5000;
    private int maxLayers = 10;
    private int maxPatterns = 1000;
    
    // Processing configuration
    private double minConnectionWeight = 0.1;
    private double maxConnectionWeight = 1.0;
    private double defaultActivationThreshold = 0.5;
    private double memoryThreshold = 0.7;
    private double learningRate = 0.01;
    
    // Performance configuration
    private int maxConcurrentSignals = 100;
    private long signalTimeoutMs = 30000;
    private boolean enableOptimization = true;
    private int optimizationIntervalMs = 60000;
    
    // Memory configuration
    private int shortTermMemorySize = 1000;
    private int longTermMemorySize = 10000;
    private long memoryCleanupIntervalMs = 300000; // 5 minutes
    
    // Learning configuration
    private boolean enableLearning = true;
    private double minConfidenceThreshold = 0.3;
    private double maxConfidenceThreshold = 0.9;
    private int minPatternFrequency = 3;
    
    // Custom parameters
    private Map<String, Object> customParameters = new HashMap<>();
    
    public NeuralConfig() {
        // Initialize with default values
    }
    
    public void updateFrom(NeuralConfig other) {
        this.maxNodes = other.maxNodes;
        this.maxConnections = other.maxConnections;
        this.maxLayers = other.maxLayers;
        this.maxPatterns = other.maxPatterns;
        this.minConnectionWeight = other.minConnectionWeight;
        this.maxConnectionWeight = other.maxConnectionWeight;
        this.defaultActivationThreshold = other.defaultActivationThreshold;
        this.memoryThreshold = other.memoryThreshold;
        this.learningRate = other.learningRate;
        this.maxConcurrentSignals = other.maxConcurrentSignals;
        this.signalTimeoutMs = other.signalTimeoutMs;
        this.enableOptimization = other.enableOptimization;
        this.optimizationIntervalMs = other.optimizationIntervalMs;
        this.shortTermMemorySize = other.shortTermMemorySize;
        this.longTermMemorySize = other.longTermMemorySize;
        this.memoryCleanupIntervalMs = other.memoryCleanupIntervalMs;
        this.enableLearning = other.enableLearning;
        this.minConfidenceThreshold = other.minConfidenceThreshold;
        this.maxConfidenceThreshold = other.maxConfidenceThreshold;
        this.minPatternFrequency = other.minPatternFrequency;
        this.customParameters = new HashMap<>(other.customParameters);
    }
    
    public void setCustomParameter(String key, Object value) {
        customParameters.put(key, value);
    }
    
    public Object getCustomParameter(String key) {
        return customParameters.get(key);
    }
    
    public Object getCustomParameter(String key, Object defaultValue) {
        return customParameters.getOrDefault(key, defaultValue);
    }
    
    public Map<String, Object> getAllCustomParameters() {
        return new HashMap<>(customParameters);
    }
    
    // Getters and Setters
    public int getMaxNodes() { return maxNodes; }
    public void setMaxNodes(int maxNodes) { this.maxNodes = Math.max(1, maxNodes); }
    
    public int getMaxConnections() { return maxConnections; }
    public void setMaxConnections(int maxConnections) { this.maxConnections = Math.max(1, maxConnections); }
    
    public int getMaxLayers() { return maxLayers; }
    public void setMaxLayers(int maxLayers) { this.maxLayers = Math.max(1, maxLayers); }
    
    public int getMaxPatterns() { return maxPatterns; }
    public void setMaxPatterns(int maxPatterns) { this.maxPatterns = Math.max(1, maxPatterns); }
    
    public double getMinConnectionWeight() { return minConnectionWeight; }
    public void setMinConnectionWeight(double minConnectionWeight) { 
        this.minConnectionWeight = Math.max(0.0, Math.min(1.0, minConnectionWeight)); 
    }
    
    public double getMaxConnectionWeight() { return maxConnectionWeight; }
    public void setMaxConnectionWeight(double maxConnectionWeight) { 
        this.maxConnectionWeight = Math.max(0.0, Math.min(1.0, maxConnectionWeight)); 
    }
    
    public double getDefaultActivationThreshold() { return defaultActivationThreshold; }
    public void setDefaultActivationThreshold(double defaultActivationThreshold) { 
        this.defaultActivationThreshold = Math.max(0.0, Math.min(1.0, defaultActivationThreshold)); 
    }
    
    public double getMemoryThreshold() { return memoryThreshold; }
    public void setMemoryThreshold(double memoryThreshold) { 
        this.memoryThreshold = Math.max(0.0, Math.min(1.0, memoryThreshold)); 
    }
    
    public double getLearningRate() { return learningRate; }
    public void setLearningRate(double learningRate) { 
        this.learningRate = Math.max(0.0, Math.min(1.0, learningRate)); 
    }
    
    public int getMaxConcurrentSignals() { return maxConcurrentSignals; }
    public void setMaxConcurrentSignals(int maxConcurrentSignals) { 
        this.maxConcurrentSignals = Math.max(1, maxConcurrentSignals); 
    }
    
    public long getSignalTimeoutMs() { return signalTimeoutMs; }
    public void setSignalTimeoutMs(long signalTimeoutMs) { 
        this.signalTimeoutMs = Math.max(1000, signalTimeoutMs); 
    }
    
    public boolean isEnableOptimization() { return enableOptimization; }
    public void setEnableOptimization(boolean enableOptimization) { this.enableOptimization = enableOptimization; }
    
    public int getOptimizationIntervalMs() { return optimizationIntervalMs; }
    public void setOptimizationIntervalMs(int optimizationIntervalMs) { 
        this.optimizationIntervalMs = Math.max(1000, optimizationIntervalMs); 
    }
    
    public int getShortTermMemorySize() { return shortTermMemorySize; }
    public void setShortTermMemorySize(int shortTermMemorySize) { 
        this.shortTermMemorySize = Math.max(1, shortTermMemorySize); 
    }
    
    public int getLongTermMemorySize() { return longTermMemorySize; }
    public void setLongTermMemorySize(int longTermMemorySize) { 
        this.longTermMemorySize = Math.max(1, longTermMemorySize); 
    }
    
    public long getMemoryCleanupIntervalMs() { return memoryCleanupIntervalMs; }
    public void setMemoryCleanupIntervalMs(long memoryCleanupIntervalMs) { 
        this.memoryCleanupIntervalMs = Math.max(1000, memoryCleanupIntervalMs); 
    }
    
    public boolean isEnableLearning() { return enableLearning; }
    public void setEnableLearning(boolean enableLearning) { this.enableLearning = enableLearning; }
    
    public double getMinConfidenceThreshold() { return minConfidenceThreshold; }
    public void setMinConfidenceThreshold(double minConfidenceThreshold) { 
        this.minConfidenceThreshold = Math.max(0.0, Math.min(1.0, minConfidenceThreshold)); 
    }
    
    public double getMaxConfidenceThreshold() { return maxConfidenceThreshold; }
    public void setMaxConfidenceThreshold(double maxConfidenceThreshold) { 
        this.maxConfidenceThreshold = Math.max(0.0, Math.min(1.0, maxConfidenceThreshold)); 
    }
    
    public int getMinPatternFrequency() { return minPatternFrequency; }
    public void setMinPatternFrequency(int minPatternFrequency) { 
        this.minPatternFrequency = Math.max(1, minPatternFrequency); 
    }
    
    @Override
    public String toString() {
        return String.format("NeuralConfig{maxNodes=%d, maxConnections=%d, maxLayers=%d, enableLearning=%s}",
                           maxNodes, maxConnections, maxLayers, enableLearning);
    }
} 