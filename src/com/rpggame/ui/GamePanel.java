package com.rpggame.ui;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

	public GamePanel() {
		//Set the background color
		this.setBackground(Color.BLACK);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Draw a simple player (rectangle)
		g.setColor(Color.GREEN);
		g.fillRect(100, 100, 50, 50); //x, y, width, height
	}
}
