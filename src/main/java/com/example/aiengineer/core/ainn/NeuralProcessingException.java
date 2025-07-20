package com.example.aiengineer.core.ainn;

public class NeuralProcessingException extends RuntimeException {
    
    public NeuralProcessingException(String message) {
        super(message);
    }
    
    public NeuralProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public NeuralProcessingException(Throwable cause) {
        super(cause);
    }
} 