package com.example.aiengineer.api.ainn;

import com.example.aiengineer.core.ainn.*;
import com.example.aiengineer.core.ainn.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/ainn")
@CrossOrigin(origins = "*")
public class AINNController {

    @Autowired
    private AINNCore ainnCore;

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        return ResponseEntity.ok(Map.of(
            "network", "AINN (AI Neural Network)",
            "version", "1.0",
            "status", "active",
            "description", "Core Neural Processing Engine - The Foundation of All AI",
            "analysis", ainnCore.analyzeNetwork()
        ));
    }

    // Neural Signal Processing
    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> processSignal(@RequestBody Map<String, Object> request) {
        try {
            Map<String, Object> data = (Map<String, Object>) request.get("data");
            String sourceNodeId = (String) request.get("sourceNodeId");
            double strength = (Double) request.getOrDefault("strength", 1.0);
            double significance = (Double) request.getOrDefault("significance", 0.5);
            
            NeuralSignal signal = new NeuralSignal(sourceNodeId, data);
            signal.setStrength(strength);
            signal.setSignificance(significance);
            
            NeuralSignal result = ainnCore.processSignal(signal);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "result", result,
                "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage(),
                "timestamp", System.currentTimeMillis()
            ));
        }
    }

    // Node Management
    @PostMapping("/nodes")
    public ResponseEntity<Map<String, Object>> createNode(@RequestBody Map<String, Object> request) {
        try {
            String nodeId = (String) request.get("nodeId");
            String name = (String) request.get("name");
            String layerId = (String) request.get("layerId");
            NodeType type = NodeType.valueOf((String) request.get("type"));
            
            NeuralNode node = ainnCore.createNode(nodeId, name, layerId, type);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "node", node,
                "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage(),
                "timestamp", System.currentTimeMillis()
            ));
        }
    }

    @GetMapping("/nodes")
    public ResponseEntity<Map<String, Object>> getAllNodes() {
        List<NeuralNode> nodes = ainnCore.getAllNodes();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "nodes", nodes,
            "count", nodes.size(),
            "timestamp", System.currentTimeMillis()
        ));
    }

    @GetMapping("/nodes/{nodeId}")
    public ResponseEntity<Map<String, Object>> getNode(@PathVariable String nodeId) {
        NeuralNode node = ainnCore.getNode(nodeId);
        if (node == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
            "success", true,
            "node", node,
            "timestamp", System.currentTimeMillis()
        ));
    }

    @GetMapping("/nodes/layer/{layerId}")
    public ResponseEntity<Map<String, Object>> getNodesByLayer(@PathVariable String layerId) {
        List<NeuralNode> nodes = ainnCore.getNodesByLayer(layerId);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "nodes", nodes,
            "layerId", layerId,
            "count", nodes.size(),
            "timestamp", System.currentTimeMillis()
        ));
    }

    // Connection Management
    @PostMapping("/connections")
    public ResponseEntity<Map<String, Object>> createConnection(@RequestBody Map<String, Object> request) {
        try {
            String fromNodeId = (String) request.get("fromNodeId");
            String toNodeId = (String) request.get("toNodeId");
            ConnectionType type = ConnectionType.valueOf((String) request.get("type"));
            double weight = (Double) request.getOrDefault("weight", 1.0);
            
            NeuralConnection connection = ainnCore.createConnection(fromNodeId, toNodeId, type, weight);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "connection", connection,
                "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage(),
                "timestamp", System.currentTimeMillis()
            ));
        }
    }

    @GetMapping("/connections/{connectionId}")
    public ResponseEntity<Map<String, Object>> getConnection(@PathVariable String connectionId) {
        NeuralConnection connection = ainnCore.getConnection(connectionId);
        if (connection == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
            "success", true,
            "connection", connection,
            "timestamp", System.currentTimeMillis()
        ));
    }

    // Layer Management
    @PostMapping("/layers")
    public ResponseEntity<Map<String, Object>> createLayer(@RequestBody Map<String, Object> request) {
        try {
            String layerId = (String) request.get("layerId");
            String name = (String) request.get("name");
            NeuralLayerType type = NeuralLayerType.valueOf((String) request.get("type"));
            
            NeuralLayer layer = ainnCore.createLayer(layerId, name, type);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "layer", layer,
                "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage(),
                "timestamp", System.currentTimeMillis()
            ));
        }
    }

    @GetMapping("/layers")
    public ResponseEntity<Map<String, Object>> getAllLayers() {
        List<NeuralLayer> layers = ainnCore.getAllLayers();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "layers", layers,
            "count", layers.size(),
            "timestamp", System.currentTimeMillis()
        ));
    }

    @GetMapping("/layers/{layerId}")
    public ResponseEntity<Map<String, Object>> getLayer(@PathVariable String layerId) {
        NeuralLayer layer = ainnCore.getLayer(layerId);
        if (layer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
            "success", true,
            "layer", layer,
            "timestamp", System.currentTimeMillis()
        ));
    }

    // Pattern Management
    @PostMapping("/patterns")
    public ResponseEntity<Map<String, Object>> createPattern(@RequestBody Map<String, Object> request) {
        try {
            String patternId = (String) request.get("patternId");
            String name = (String) request.get("name");
            Map<String, Object> data = (Map<String, Object>) request.get("data");
            
            NeuralPattern pattern = ainnCore.createPattern(patternId, name, data);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "pattern", pattern,
                "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage(),
                "timestamp", System.currentTimeMillis()
            ));
        }
    }

    @PostMapping("/patterns/search")
    public ResponseEntity<Map<String, Object>> searchPatterns(@RequestBody Map<String, Object> criteria) {
        List<NeuralPattern> patterns = ainnCore.findPatterns(criteria);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "patterns", patterns,
            "count", patterns.size(),
            "criteria", criteria,
            "timestamp", System.currentTimeMillis()
        ));
    }

    // Memory Management
    @PostMapping("/memory/short-term")
    public ResponseEntity<Map<String, Object>> storeInShortTermMemory(@RequestBody Map<String, Object> request) {
        String key = (String) request.get("key");
        Object value = request.get("value");
        
        ainnCore.storeInShortTermMemory(key, value);
        
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Stored in short-term memory",
            "key", key,
            "timestamp", System.currentTimeMillis()
        ));
    }

    @GetMapping("/memory/short-term/{key}")
    public ResponseEntity<Map<String, Object>> getFromShortTermMemory(@PathVariable String key) {
        Object value = ainnCore.getFromShortTermMemory(key);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "key", key,
            "value", value,
            "timestamp", System.currentTimeMillis()
        ));
    }

    @PostMapping("/memory/long-term")
    public ResponseEntity<Map<String, Object>> storeInLongTermMemory(@RequestBody Map<String, Object> request) {
        String key = (String) request.get("key");
        Object value = request.get("value");
        
        ainnCore.storeInLongTermMemory(key, value);
        
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Stored in long-term memory",
            "key", key,
            "timestamp", System.currentTimeMillis()
        ));
    }

    @GetMapping("/memory/long-term/{key}")
    public ResponseEntity<Map<String, Object>> getFromLongTermMemory(@PathVariable String key) {
        Object value = ainnCore.getFromLongTermMemory(key);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "key", key,
            "value", value,
            "timestamp", System.currentTimeMillis()
        ));
    }

    @DeleteMapping("/memory/short-term")
    public ResponseEntity<Map<String, Object>> clearShortTermMemory() {
        ainnCore.clearShortTermMemory();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Short-term memory cleared",
            "timestamp", System.currentTimeMillis()
        ));
    }

    // Learning Pattern Management
    @PostMapping("/learning-patterns")
    public ResponseEntity<Map<String, Object>> createLearningPattern(@RequestBody Map<String, Object> request) {
        try {
            String patternId = (String) request.get("patternId");
            String name = (String) request.get("name");
            Map<String, Object> criteria = (Map<String, Object>) request.get("criteria");
            
            // Create a simple learning action
            LearningAction action = signal -> {
                // Default learning action - amplify the signal
                return signal.amplify(1.1);
            };
            
            LearningPattern pattern = ainnCore.createLearningPattern(patternId, name, criteria, action);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "pattern", pattern,
                "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage(),
                "timestamp", System.currentTimeMillis()
            ));
        }
    }

    // Network Analysis
    @GetMapping("/analysis")
    public ResponseEntity<Map<String, Object>> analyzeNetwork() {
        Map<String, Object> analysis = ainnCore.analyzeNetwork();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "analysis", analysis,
            "timestamp", System.currentTimeMillis()
        ));
    }

    // Network Optimization
    @PostMapping("/optimize")
    public ResponseEntity<Map<String, Object>> optimizeNetwork() {
        try {
            ainnCore.optimizeNetwork();
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Network optimization completed",
                "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage(),
                "timestamp", System.currentTimeMillis()
            ));
        }
    }

    // Configuration
    @GetMapping("/config")
    public ResponseEntity<Map<String, Object>> getConfig() {
        NeuralConfig config = ainnCore.getConfig();
        return ResponseEntity.ok(Map.of(
            "success", true,
            "config", config,
            "timestamp", System.currentTimeMillis()
        ));
    }

    @PutMapping("/config")
    public ResponseEntity<Map<String, Object>> updateConfig(@RequestBody NeuralConfig newConfig) {
        try {
            ainnCore.updateConfig(newConfig);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Configuration updated",
                "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage(),
                "timestamp", System.currentTimeMillis()
            ));
        }
    }
} 