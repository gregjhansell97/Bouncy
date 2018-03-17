package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Ball;

public class GameJPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Color backgroundColor = Color.white;
	private BufferedImage screen; //used to prevent the Panel from getting glitchy
	private ArrayList<Ball> balls; //balls that will be painted onto the screen
	private int width;
	private int height;
	
	
	/**
	 * Creates a panel with a certain width, height and a list of balls that it must paint
	 * on to the screen
	 * @param width
	 * @param height
	 */
	public GameJPanel(int width, int height, ArrayList<Ball> balls) {
		super();
		this.screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.balls = balls;
	}
	
	/**
	 * The size of the panel (used for resizing)
	 * @return dimensions of the GameJPanel
	 */
	public Dimension getPrefferedSize() {
		return new Dimension(width, height);
	}
	
	/**
	 * instead of changing the panel background color, writes to the screen
	 * which then gets rendered by the graphics
	 */
	@Override
	public void setBackground(Color c) {
		this.backgroundColor = c;
	}
	
	@Override
	public Color getBackground() {
		return this.backgroundColor;
	}
	
	/**
	 * Repaints over all the balls in the screen with a new background
	 * used to update the screen and clear all the balls away
	 */
	private void clearScreen() {	
		int newWidth = this.getWidth(); 
		int newHeight = this.getHeight();
		if(this.width == newWidth && this.height == newHeight) {
			Graphics g  = this.screen.getGraphics();
			g.setColor(backgroundColor);
			g.fillRect(0, 0, this.width, this.height);
			return;
		}
		this.height = newHeight;
		this.width = newWidth;
		screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	/**
	 * Used in paint component to add all the balls
	 * to the screen. The x and y have to be adjusted
	 * paint the center of the ball (not upper left)
	 */
	private void updateScreen() {
		clearScreen();
		Graphics g = this.screen.getGraphics();
		//this.setBackground(backgroundColor);
		for(Ball b : this.balls) {
			g.setColor(b.getColor());
			int diameter = b.getRadius()*2;
			int x = (int)(b.getVec().getX() + 0.5) - b.getRadius(); 
			int y = (int)(b.getVec().getY() + 0.5) - b.getRadius();
			g.fillOval(x, y, diameter, diameter);
		}
	}
	
	/**
	 * Paints the screen to the image (after updating the screen first)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateScreen();
		g.drawImage(screen, 0, 0, null);
		g.dispose();
	}
}
