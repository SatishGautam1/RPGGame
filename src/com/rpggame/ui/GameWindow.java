package com.rpggame.ui;

import javax.swing.JFrame;


public class GameWindow {
	
	private JFrame frame;
	private GamePanel panel;
	
	public GameWindow() {
		//Create the window
		frame = new JFrame("RPG Game");
		
		//Create a custom panel
		panel = new GamePanel();
		frame.add(panel);
		
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		//Run the window
		new GameWindow();
	}

}
