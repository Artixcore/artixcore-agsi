package com.example.aiengineer.core.ainn.enums;

public enum NodeState {
    IDLE,        // Node is idle and waiting for signals
    ACTIVE,      // Node is actively processing signals
    PROCESSING,  // Node is currently processing a signal
    LEARNING,    // Node is in learning mode
    OPTIMIZING,  // Node is optimizing its parameters
    ERROR,       // Node is in error state
    DISABLED     // Node is disabled
} 