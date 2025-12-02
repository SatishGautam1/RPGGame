package com.rpggame.entities;

import java.awt.*;
import javax.swing.ImageIcon;


public class Player {
    private int x;
    private int y;
    private int size;
    private int speed;
    
    private Image sprite;

    // Stats
    private int hp;
    private int mp;
    private int strength;
    private int defense;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 64;
        this.speed = 5; // smoother movement

        // Example stats
        this.hp = 100;
        this.mp = 100;
        this.strength = 10;
        this.defense = 5;
        
        //Load default sprite (Idel)
        sprite = new ImageIcon(getClass().getResource("/com/rpggame/assets/HeroKnight_Idel_0.png")).getImage();
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
       g.drawImage(sprite, x, y, size, size, null);
    }

    // Getters for stats
    public int getHp() { return hp; }
    public int getMp() { return mp; }
    public int getStrength() { return strength; }
    public int getDefense() { return defense; }
    
    public void takeDamage(int amount) {
    	hp -= amount;
    	if (hp < 0) hp = 0; //prevent negative hp
    }
    
    public void heal(int amount) {
    	hp += amount;
    	if (hp > 100) hp = 100; //cap at max hp
    }
    
    public void useMana(int amount) {
    	mp -= amount;
    	if (mp < 0) mp = 0;
    }
    
    public void restoreMana(int amount) {
    	mp += amount;
    	if (mp > 100) mp = 100;
    }
}
