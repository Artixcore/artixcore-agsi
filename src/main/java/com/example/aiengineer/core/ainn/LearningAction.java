package com.example.aiengineer.core.ainn;

@FunctionalInterface
public interface LearningAction {
    /**
     * Execute a learning action on a neural signal
     * @param signal The input neural signal
     * @return The processed neural signal
     */
    NeuralSignal execute(NeuralSignal signal);
} 