package com.rpggame.entities;

import java.awt.*;
import javax.swing.ImageIcon;


public class Player {
    private int x, y, size, speed;
    
    //Sprites
    private Image[] idleSprites;
    private Image[] runSprites;
    private Image currentSprite;
    
    private Image[] attack1Sprites;
    private Image[] attack2Sprites;
    private Image[] attack3Sprites;

    private boolean isAttacking = false;
    private int attackType = 0; // 1, 2, or 3
    
    // Stats
    private int hp, mp, strength, defense;
    
    //Animate state
    private int frameIndex = 0;
    private int frameCounter = 0;
    private boolean isRunning = false;
    private boolean isRunningLeft = false;
    private boolean isRunningRight = false;
    private boolean isRunningUp = false;
    private boolean isRunningDown = false;
    
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
        idleSprites = new Image[8]; 
		for (int i = 0; i < 8; i++) {
			idleSprites[i] = new ImageIcon(
					getClass().getResource("/com/rpggame/assets/idle/HeroKnight_Idle_" + i + ".png")
			).getImage();
		};      
		
        //Load run sprites (add more frames if available)
        runSprites = new Image[10]; 
        		for (int i = 0; i < 10; i++) {
        			runSprites[i] = new ImageIcon(
        					getClass().getResource("/com/rpggame/assets/run/HeroKnight_Run_" + i + ".png")
        			).getImage();
        };
        currentSprite = idleSprites[0];
        
     // Attack1 (0–5)
        attack1Sprites = new Image[6];
        for (int i = 0; i < 6; i++) {
            attack1Sprites[i] = new ImageIcon(
                getClass().getResource("/com/rpggame/assets/attack1/HeroKnight_Attack1_" + i + ".png")
            ).getImage();
        }

        // Attack2 (0–5)
        attack2Sprites = new Image[6];
        for (int i = 0; i < 6; i++) {
            attack2Sprites[i] = new ImageIcon(
                getClass().getResource("/com/rpggame/assets/attack2/HeroKnight_Attack2_" + i + ".png")
            ).getImage();
        }

        // Attack3 (0–7)
        attack3Sprites = new Image[8];
        for (int i = 0; i < 8; i++) {
            attack3Sprites[i] = new ImageIcon(
                getClass().getResource("/com/rpggame/assets/attack3/HeroKnight_Attack3_" + i + ".png")
            ).getImage();
        }

    }

    // Movement with boundary checks
    public void moveUp() {
    	y = Math.max(y - speed, 0);
    	setRunning();
    }
    
    public void moveDown(int panelHeight) { 
    	y = Math.min(y + speed, panelHeight - size);
    	setRunning();
    }
    
    public void moveLeft() {
        x = Math.max(x - speed, 0);
        setRunning();
        isRunningLeft = true;
        isRunningRight = isRunningUp = isRunningDown = false;
    }

    public void moveRight(int panelWidth) {
        x = Math.min(x + speed, panelWidth - size);
        setRunning();
        isRunningRight = true;
        isRunningLeft = isRunningUp = isRunningDown = false;
    }

    
    public void setRunning() {
        isRunning = true;
    }

    public void setIdle() {
        isRunning = false;
        frameIndex = 0;
        frameCounter = 0;
        currentSprite = idleSprites[0];
    }
    
    public void startAttack(int type) {
        isAttacking = true;
        attackType = type;
        frameIndex = 0;
        frameCounter = 0;
    }

    private void endAttack() {
        isAttacking = false;
        attackType = 0;
        setIdle(); // return to idle after attack
    }


    // Update animation frames
    public void update() {
        frameCounter++;

        if (isRunning) {
            // Animate running faster
            if (frameCounter >= 5) { // change frame every 5 ticks
                frameIndex = (frameIndex + 1) % runSprites.length;
                currentSprite = runSprites[frameIndex];
                frameCounter = 0; // reset counter
            }
        } else {
            // Animate idle slower
            if (frameCounter >= 10) { // change frame every 10 ticks
                frameIndex = (frameIndex + 1) % idleSprites.length;
                currentSprite = idleSprites[frameIndex];
                frameCounter = 0; // reset counter
            }
        }
        
        if (isAttacking) {
            if (frameCounter >= 5) { // change frame every 5 ticks
                frameIndex++;
                switch (attackType) {
                    case 1:
                        if (frameIndex < attack1Sprites.length) {
                            currentSprite = attack1Sprites[frameIndex];
                        } else {
                            endAttack();
                        }
                        break;
                    case 2:
                        if (frameIndex < attack2Sprites.length) {
                            currentSprite = attack2Sprites[frameIndex];
                        } else {
                            endAttack();
                        }
                        break;
                    case 3:
                        if (frameIndex < attack3Sprites.length) {
                            currentSprite = attack3Sprites[frameIndex];
                        } else {
                            endAttack();
                        }
                        break;
                }
                frameCounter = 0;
            }
        } else if (isRunning) {
            if (frameCounter >= 5) {
                frameIndex = (frameIndex + 1) % runSprites.length;
                currentSprite = runSprites[frameIndex];
                frameCounter = 0;
            }
        } else {
            if (frameCounter >= 10) {
                frameIndex = (frameIndex + 1) % idleSprites.length;
                currentSprite = idleSprites[frameIndex];
                frameCounter = 0;
            }
        }
    }

    // Render player
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (isRunningLeft) {
            g2d.drawImage(currentSprite, x + size, y, -size, size, null);
        } else {
            g2d.drawImage(currentSprite, x, y, size, size, null);
        }
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
