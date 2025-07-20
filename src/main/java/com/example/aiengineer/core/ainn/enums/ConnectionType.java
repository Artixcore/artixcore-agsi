package com.example.aiengineer.core.ainn.enums;

public enum ConnectionType {
    DIRECT,        // Direct one-way connection
    BIDIRECTIONAL, // Two-way connection
    INHIBITORY,    // Inhibitory connection (reduces signal strength)
    EXCITATORY,    // Excitatory connection (enhances signal strength)
    MODULATORY,    // Modulatory connection (affects signal characteristics)
    CONDITIONAL,   // Conditional connection (only active under certain conditions)
    FEEDBACK       // Feedback connection (from output back to input)
} 