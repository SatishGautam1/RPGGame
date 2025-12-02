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
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		player.draw(g); //Draw player
		
		// -- HUD Section --
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		
		int hudX = 10; //left margin
		int hudY = 20; //starting height
		
		g.drawString("HP: " + player.getHp(), hudX, hudY);
		g.drawString("MP: " + player.getMp(), hudX, hudY + 20);
		g.drawString("Strength: " + player.getStrength(), hudX, hudY + 40);
		g.drawString("Defense: " + player.getDefense(), hudX, hudY + 60);
		
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
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		//Not used yet
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//Not used
	}
}
