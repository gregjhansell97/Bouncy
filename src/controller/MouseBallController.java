package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import model.PlayerBall;

/**
 * 
 * @author Daemon the Koala
 *
 * Extends the BallController class. Implements the MouseMotionListener interface
 * to set the location of the PlayerBall
 */
public class MouseBallController extends BallController implements MouseMotionListener {
	
	/**
	 * constructor: chains the super constructor
	 * @param playerBall PlayerBall passed by reference (no copy)
	 */
	public MouseBallController(PlayerBall playerBall) {
		super(playerBall);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		this.setLocation(x, y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		this.setLocation(x, y);
	}
	
	

}
