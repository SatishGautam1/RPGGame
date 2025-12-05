package com.rpggame.ui;

import com.rpggame.entities.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener{
	
	private Player player;

	public GamePanel() {
		//Set the background color
		this.setBackground(Color.BLACK);
		
		//Create a new player at starting position
		player = new Player(100, 100);
		
		//Enable focus so panel can receive key events
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		
		//Timer; fires every 16ms (60 FPS)
		Timer timer = new Timer(16, e-> {
			update(); //update game state
			repaint(); //redraw panel
		});
		timer.start();
	}
	
	private void update() {
		//For now, nothing special in update
		player.update();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		player.draw(g); //Draw player
		
		// -- HUD Section --
		g.setFont(new Font("Arial", Font.BOLD, 16));
		
		int hudX = 10; //left margin
		int hudY = 20; //starting height
		
		//HP Bar
		int maxHp = 100; // for now, fixed max
		int hpWidth = 200; //pixel width for bar
		int hpHeight = 20;
		
		g.setColor(Color.GREEN);
		int currentHpWidth = (int) ((player.getHp() / (double) maxHp) * hpWidth);
		g.fillRect(hudX, hudY, currentHpWidth, hpHeight);
		g.setColor(Color.WHITE);
		g.drawRect(hudX, hudY, hpWidth, hpHeight);
		g.drawString("HP: " + player.getHp(), hudX + hpWidth + 10, hudY + 15);
		
		//HP Bar
		int maxMp = 100; // for now, fixed max
		int mpWidth = 200; //pixel width for bar
		int mpHeight = 20;
		int mpY = hudY + 40;
				
		g.setColor(Color.GREEN);
		int currentMpWidth = (int) ((player.getMp() / (double) maxMp) * mpWidth);
		g.fillRect(hudX, mpY, currentMpWidth, mpHeight);
		g.setColor(Color.WHITE);
		g.drawRect(hudX, mpY, mpWidth, mpHeight);
		g.drawString("MP: " + player.getMp(), hudX + mpWidth + 10, mpY + 15);
		
		//Strength & Defense as text
		g.setColor(Color.WHITE);
		g.drawString("Strength: " + player.getStrength(), hudX, mpY + 50);
		g.drawString("Defense: " + player.getDefense(), hudX, mpY + 70);
	}
	
	//-- Keyboard Listener --
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		switch (code) {
			case KeyEvent.VK_W: player.moveUp();
			break;
			case KeyEvent.VK_S: player.moveDown(getHeight());
			break;
			case KeyEvent.VK_A: player.moveLeft();
			break;
			case KeyEvent.VK_D: player.moveRight(getWidth());
			break;
			
			//--Simulate damage/healing
			case KeyEvent.VK_J: //damage hp
				player.takeDamage(10);
				break;
			case KeyEvent.VK_K: //heal hp
				player.heal(10);
				break;
			case KeyEvent.VK_U: //Use mp
				player.useMana(10);
				break;
			case KeyEvent.VK_I: //Restore mp
				player.restoreMana(10);
				break;
			case KeyEvent.VK_SPACE: // Attack1
			    player.startAttack(1);
			    break;
			case KeyEvent.VK_SHIFT: // Attack2
			    player.startAttack(2);
			    break;
			case KeyEvent.VK_CONTROL: // Attack3
			    player.startAttack(3);
			    break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		//When movement keys are released , go back to idle
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_A || code == KeyEvent.VK_S || code == KeyEvent.VK_D) {
			player.setIdle();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//Not used
	}

}
