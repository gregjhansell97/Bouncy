package model;

import java.awt.Color;
import java.util.ArrayList;

import main.Random;

/**
 * 
 * @author Daemon the Koala
 *
 * Represents how a ball should act in the game given bounds, vector, color and radius
 */
public abstract class Ball {
	//static variables
	public static ArrayList<Ball> balls = new ArrayList<Ball>(); //every ball that get's instanciated is tracked

	//state variables
	protected Vector vec; //the location and direction of ball (at center of ball)
	protected Boundary bounds; //the border that the ball is constrained to
	private Color ballColor; //the color of the ball (created randomly if none set)
	protected int radius; //the radius of the ball
	
	public abstract void handleBallCollision();
	
	/**
	 * Root constructor that every class that inherits it should call so that ball
	 * gets added to the ArrayList of balls
	 * @param vec the location and direction of ball (at center of ball)
	 * @param bounds the border that the ball is constrained to
	 * @param ballColor the color of the ball
	 * @param radius the radius of the ball
	 */
	public Ball(Vector vec, Boundary bounds, Color ballColor, int radius) {
		this.vec = new Vector(vec);
		this.bounds = bounds;
		this.ballColor = ballColor;
		this.radius = radius;
		balls.add(this);
	}
	
	/**
	 * Invokes the root constructor that every class should call. It creates a random
	 * color for the ball
	 * @param vec the location and direction of ball (at center of ball)
	 * @param bounds  the border that the ball is constrained to
	 * @param radius the radius of the ball
	 */
	public Ball(Vector vec, Boundary bounds, int radius) {
		this(vec, bounds, Random.getRandomColor(), radius);
	}
	
	/**
	 * Accessor method
	 * @return the radius of the ball
	 */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * Accessor method
	 * @return the vector of the ball (sent as a copy)
	 */
	public Vector getVec() {
		return new Vector(vec);
	}
	
	/**
	 * Accessor method
	 * @return the color of the ball
	 */
	public Color getColor() {
		return ballColor;
	}
	
	/**
	 * The default implementation of how a ball should behave when it hits
	 * one of the boundaries (it should not go past the boundary)
	 */
	public void handleWallCollision() {
		if(this.bounds.ballHitTop(this)) {
			this.vec.setY(this.bounds.getTop() + this.getRadius());
		}
		if(this.bounds.ballHitBottom(this)) {
			this.vec.setY(this.bounds.getBottom() - this.getRadius());
		}
		if(this.bounds.ballHitLeft(this)) {
			this.vec.setX(this.bounds.getLeft() + this.getRadius());
		}
		if(this.bounds.ballHitRight(this)) {
			this.vec.setX(this.bounds.getRight() - this.getRadius());
		}
	}
	
	/**
	 * Given a ball b, is the ball in contact with "this" ball?
	 * @param b ball potentially touching "this" ball
	 * @return true if ball b is touching "this" ball, false otherwise
	 */
	private boolean collision(Ball b){
		int minDist = radius + b.radius;
		if(b == this) return false; //can't collide with itself
		if(vec.getX() - b.vec.getX() > minDist || vec.getY() - b.vec.getY() > minDist) return false; //far away don't worry about it
		return b.vec.distanceTo(vec) < minDist; //close by, check for collisions
	}
	
	/**
	 * Checks if "this" ball has collided with any other balls
	 * and returns the first ball found that "this" ball collided with 
	 * used in child classes
	 * @return first ball found that "this" ball collided with null if none found
	 * 
	 * TODO there seems like a better way to do this with hashing so that rather
	 * than iterating through every wall and checking collisions of every ball
	 * you could put them on some kind of map and see balls close by
	 */
	protected Ball getBallCollision() {
			for(Ball b : balls) {
				if(collision(b)) {
					return b;
				}
			}
			return null;
	}
	
	/**
	 * One clock tick in the game. Every ball
	 * has to check for collisions both with other
	 * balls and with the wall
	 * 
	 */
	public void tick() {
		this.handleWallCollision();
		this.handleBallCollision(); 
	}
}
