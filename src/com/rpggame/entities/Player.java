package com.rpggame.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    private int x;
    private int y;
    private int size;
    private int speed;

    // Stats
    private int hp;
    private int mp;
    private int strength;
    private int defense;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 50;
        this.speed = 5; // smoother movement

        // Example stats
        this.hp = 100;
        this.mp = 100;
        this.strength = 10;
        this.defense = 5;
    }

    // Movement with boundary checks
    public void moveUp() {
        if (y - speed >= 0) {
            y -= speed;
        }
    }

    public void moveDown(int panelHeight) {
        if (y + speed + size <= panelHeight) {
            y += speed;
        }
    }

    public void moveLeft() {
        if (x - speed >= 0) {
            x -= speed;
        }
    }

    public void moveRight(int panelWidth) {
        if (x + speed + size <= panelWidth) {
            x += speed;
        }
    }

    // Render player
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, size, size);
    }

    // Getters for stats
    public int getHp() { return hp; }
    public int getMp() { return mp; }
    public int getStrength() { return strength; }
    public int getDefense() { return defense; }
}
