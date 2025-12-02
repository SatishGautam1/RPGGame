package com.rpggame.core;

public class Game {

    // Basic game loop flags
    private boolean running = true;

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();
        game.shutdown();
    }

    private void init() {
        // Load config, initialize systems (input, rendering, audio later)
        System.out.println("Initializing RPG Game...");
    }

    private void run() {
        System.out.println("Starting game loop...");
        long lastTime = System.nanoTime();
        final double targetFps = 60.0;
        final double nsPerFrame = 1_000_000_000.0 / targetFps;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            lastTime = now;

            // Update fixed-step logic
            while (delta >= 1) {
                update();
                delta -= 1;
            }

            // Render placeholder (hook in a renderer later)
            render();

            // Temporary break to avoid infinite loop in console
            // Remove this once you add actual input or frame limiting
            if (System.currentTimeMillis() % 2000 < 20) {
                running = false; // end after ~2 seconds
            }
        }
    }

    private void update() {
        // Game state updates: player movement, NPC AI, combat timers
        // For now, just a placeholder
        System.out.println("Updating game state...");
    }

    private void render() {
        // Rendering placeholder (swap with a real renderer or UI later)
        System.out.println("Rendering frame...");
    }

    private void shutdown() {
        // Clean up resources
        System.out.println("Shutting down. Goodbye!");
    }
}
