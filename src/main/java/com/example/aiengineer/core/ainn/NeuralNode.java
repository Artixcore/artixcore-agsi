package com.example.aiengineer.core.ainn;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;

public class NeuralNode {
    private String nodeId;
    private String name;
    private String layerId;
    private NodeType type;
    private NodeState state;
    private double activationThreshold;
    private double currentActivation;
    private Map<String, Object> parameters;
    
    // Processing metrics
    private final AtomicLong processedSignals = new AtomicLong(0);
    private final AtomicLong successfulProcessings = new AtomicLong(0);
    private final AtomicLong failedProcessings = new AtomicLong(0);
    private LocalDateTime lastActivation;
    private LocalDateTime created;
    
    public NeuralNode(String nodeId, String name, String layerId, NodeType type) {
        this.nodeId = nodeId;
        this.name = name;
        this.layerId = layerId;
        this.type = type;
        this.state = NodeState.IDLE;
        this.activationThreshold = 0.5;
        this.currentActivation = 0.0;
        this.created = LocalDateTime.now();
        this.lastActivation = LocalDateTime.now();
    }
    
    public NeuralSignal processSignal(NeuralSignal inputSignal) {
        processedSignals.incrementAndGet();
        lastActivation = LocalDateTime.now();
        
        try {
            NeuralSignal outputSignal = new NeuralSignal();
            outputSignal.setSourceNodeId(nodeId);
            outputSignal.setTimestamp(System.currentTimeMillis());
            
            switch (type) {
                case GATEWAY:
                    outputSignal = processGatewaySignal(inputSignal);
                    break;
                case PROCESSOR:
                    outputSignal = processProcessorSignal(inputSignal);
                    break;
                case DECISION:
                    outputSignal = processDecisionSignal(inputSignal);
                    break;
                case CONTROLLER:
                    outputSignal = processControllerSignal(inputSignal);
                    break;
                case ENGINE:
                    outputSignal = processEngineSignal(inputSignal);
                    break;
                default:
                    outputSignal = processDefaultSignal(inputSignal);
            }
            
            // Update activation level
            updateActivation(outputSignal.getStrength());
            
            successfulProcessings.incrementAndGet();
            return outputSignal;
            
        } catch (Exception e) {
            failedProcessings.incrementAndGet();
            throw new NeuralProcessingException("Node processing failed: " + e.getMessage(), e);
        }
    }
    
    private NeuralSignal processGatewaySignal(NeuralSignal inputSignal) {
        // Gateway nodes simply pass through signals with minimal processing
        NeuralSignal outputSignal = new NeuralSignal();
        outputSignal.setData(inputSignal.getData());
        outputSignal.setStrength(inputSignal.getStrength());
        outputSignal.setSignificance(inputSignal.getSignificance());
        outputSignal.setMetadata(inputSignal.getMetadata());
        return outputSignal;
    }
    
    private NeuralSignal processProcessorSignal(NeuralSignal inputSignal) {
        // Processor nodes apply computational transformations
        NeuralSignal outputSignal = new NeuralSignal();
        
        // Apply processing based on node parameters
        Map<String, Object> data = inputSignal.getData();
        if (data.containsKey("text")) {
            // Text processing
            String text = (String) data.get("text");
            String processedText = processText(text);
            outputSignal.addData("processed_text", processedText);
        } else if (data.containsKey("numbers")) {
            // Numerical processing
            List<Double> numbers = (List<Double>) data.get("numbers");
            List<Double> processedNumbers = processNumbers(numbers);
            outputSignal.addData("processed_numbers", processedNumbers);
        }
        
        outputSignal.setStrength(inputSignal.getStrength() * 0.9); // Slight signal degradation
        outputSignal.setSignificance(inputSignal.getSignificance());
        return outputSignal;
    }
    
    private NeuralSignal processDecisionSignal(NeuralSignal inputSignal) {
        // Decision nodes make choices based on input
        NeuralSignal outputSignal = new NeuralSignal();
        
        double decisionThreshold = (Double) parameters.getOrDefault("decision_threshold", 0.7);
        double inputStrength = inputSignal.getStrength();
        
        if (inputStrength > decisionThreshold) {
            outputSignal.addData("decision", "ACCEPT");
            outputSignal.setStrength(1.0);
        } else {
            outputSignal.addData("decision", "REJECT");
            outputSignal.setStrength(0.1);
        }
        
        outputSignal.setSignificance(inputSignal.getSignificance());
        return outputSignal;
    }
    
    private NeuralSignal processControllerSignal(NeuralSignal inputSignal) {
        // Controller nodes manage system behavior
        NeuralSignal outputSignal = new NeuralSignal();
        
        // Apply control logic based on input
        Map<String, Object> data = inputSignal.getData();
        if (data.containsKey("command")) {
            String command = (String) data.get("command");
            String response = executeCommand(command);
            outputSignal.addData("response", response);
        }
        
        outputSignal.setStrength(inputSignal.getStrength());
        outputSignal.setSignificance(inputSignal.getSignificance());
        return outputSignal;
    }
    
    private NeuralSignal processEngineSignal(NeuralSignal inputSignal) {
        // Engine nodes perform complex computations
        NeuralSignal outputSignal = new NeuralSignal();
        
        // Apply engine-specific processing
        Map<String, Object> data = inputSignal.getData();
        if (data.containsKey("learning_task")) {
            String task = (String) data.get("learning_task");
            Object result = executeLearningTask(task);
            outputSignal.addData("learning_result", result);
        }
        
        outputSignal.setStrength(inputSignal.getStrength() * 0.8);
        outputSignal.setSignificance(inputSignal.getSignificance() * 1.2);
        return outputSignal;
    }
    
    private NeuralSignal processDefaultSignal(NeuralSignal inputSignal) {
        // Default processing for unknown node types
        NeuralSignal outputSignal = new NeuralSignal();
        outputSignal.setData(inputSignal.getData());
        outputSignal.setStrength(inputSignal.getStrength() * 0.5);
        outputSignal.setSignificance(inputSignal.getSignificance());
        return outputSignal;
    }
    
    private String processText(String text) {
        // Basic text processing
        return text.toUpperCase().trim();
    }
    
    private List<Double> processNumbers(List<Double> numbers) {
        // Basic numerical processing
        return numbers.stream()
                     .map(n -> n * 2.0)
                     .collect(java.util.stream.Collectors.toList());
    }
    
    private String executeCommand(String command) {
        // Basic command execution
        return "Command executed: " + command;
    }
    
    private Object executeLearningTask(String task) {
        // Basic learning task execution
        return "Learning task completed: " + task;
    }
    
    private void updateActivation(double signalStrength) {
        currentActivation = Math.min(1.0, currentActivation + signalStrength * 0.1);
        
        if (currentActivation >= activationThreshold) {
            state = NodeState.ACTIVE;
        } else {
            state = NodeState.IDLE;
        }
    }
    
    // Getters and Setters
    public String getNodeId() { return nodeId; }
    public void setNodeId(String nodeId) { this.nodeId = nodeId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLayerId() { return layerId; }
    public void setLayerId(String layerId) { this.layerId = layerId; }
    
    public NodeType getType() { return type; }
    public void setType(NodeType type) { this.type = type; }
    
    public NodeState getState() { return state; }
    public void setState(NodeState state) { this.state = state; }
    
    public double getActivationThreshold() { return activationThreshold; }
    public void setActivationThreshold(double activationThreshold) { this.activationThreshold = activationThreshold; }
    
    public double getCurrentActivation() { return currentActivation; }
    public void setCurrentActivation(double currentActivation) { this.currentActivation = currentActivation; }
    
    public Map<String, Object> getParameters() { return parameters; }
    public void setParameters(Map<String, Object> parameters) { this.parameters = parameters; }
    
    public long getProcessedSignals() { return processedSignals.get(); }
    public long getSuccessfulProcessings() { return successfulProcessings.get(); }
    public long getFailedProcessings() { return failedProcessings.get(); }
    
    public LocalDateTime getLastActivation() { return lastActivation; }
    public void setLastActivation(LocalDateTime lastActivation) { this.lastActivation = lastActivation; }
    
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    
    public double getSuccessRate() {
        long total = processedSignals.get();
        if (total == 0) return 0.0;
        return (double) successfulProcessings.get() / total * 100;
    }
    
    public boolean isActive() {
        return state == NodeState.ACTIVE;
    }
} 