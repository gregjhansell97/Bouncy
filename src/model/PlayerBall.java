package model;

import java.awt.Color;

import main.Random;

/**
 * 
 * @author Daemon the Koala
 *
 * Follows the singleton design pattern. A ball whose velocity is set by
 * it's motion. The motion is controlled by the setLocation function
 * which changes the x and y coordinates between ticks
 */
public class PlayerBall extends Ball {
	
	private static PlayerBall pb = null; //reference to singleton
	private double priorX; //prior location (x) of ball (used to calculate velocity)
	private double priorY; //prior location (y) of ball (used to calculate velocity)
	private int hitCount = 0; //the amount of times the mainball has been hit
	
	/**
	 * There can only be one player ball, static singleton creator ensures that
	 * @param x starting location x of playerBall
	 * @param y starting location y of playerBall
	 * @param radius size of playerBall
	 * @param bounds the boundaries of the main ball
	 * @return instance of PlayerBall singleton
	 */
	public static PlayerBall createBall(double x, double y, Boundary bounds, int radius) {
		if(pb == null) {
			pb = new PlayerBall(x, y, bounds, radius);
			return pb;
		}else {
			pb.radius = radius;
			pb.priorX = x;
			pb.priorY = y;
			pb.setLocation(x, y);
			pb.bounds = bounds;
			pb.hitCount = 0;
			return pb;
		}
	}
	
	/**
	 * private constructor used to create singleton
	 * @param x
	 * @param y
	 * @param radius
	 * @param bounds
	 */
	private PlayerBall(double x, double y, Boundary bounds, int radius) {
		super(new Vector(x, y, 0, 0), bounds, Color.black, radius);
		priorX = x;
		priorY = y;
	}
	
	/**
	 * Accessor: number of times that the ball has been hit
	 * @return number of times the ball has been hit
	 */
	public int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * setLocation changes the location of the playerball
	 * @param x the x location of playerBall where 0 is furthest left of screen (bound dependent)
	 * @param y the y location of playerBall where 0 is the top of the screen (bound dependent)
	 */
	public void setLocation(double x, double y) {
		this.vec.setX(x);
		this.vec.setY(y);
	}
	
	/**
	 * Mutator: increases hitCount by one and decreases the size of the ball
	 */
	public void incrementHitCount() {
		this.hitCount++;
		this.radius = 2*this.radius/3;
	}
	
	/**
	 * TODO comment code
	 * @param radius
	 */
	public void addBall(int radius) {
		double vx = Random.getRandomInteger()%15;
		double vy = Random.getRandomInteger()%15;
		
		//puts vx and vy in directions opposite to balls vx and vy
		if(this.vec.getVelocityX() != 0) {
			vx *= Math.signum(vec.getVelocityX()*(-vx));
		}
		if(this.vec.getVelocityY() != 0) {
			vy *= Math.signum(vec.getVelocityY()*(-vy));
		}
		
		Vector newBallVec = new Vector(0, 0, vx, vy);
		Vector unitVec = newBallVec.getUnitVector();
		//places near playerBall in the right direction based on unit vectors
		double x = (this.radius*2)*unitVec.getVelocityX() + this.vec.getX();
		double y = (this.radius*2)*unitVec.getVelocityY() + this.vec.getY();
		newBallVec.setX(x);
		newBallVec.setY(y);
		new ComputerBall(newBallVec, this.bounds, radius);
	}
	
	@Override
	public void handleBallCollision() {
		Ball b = this.getBallCollision();
		if(b != null) {
			Ball.balls.remove(b);
			this.incrementHitCount();
		}
	}

	@Override
	public void tick() {
		super.tick();
		
		//determines velocity:
		double x = this.vec.getX();
		double y = this.vec.getY();
		double vx = x - this.priorX;
		double vy = y - this.priorY;
		this.vec.setVelocity(vx, vy);
		this.priorX = x; 
		this.priorY = y;
	}

}
