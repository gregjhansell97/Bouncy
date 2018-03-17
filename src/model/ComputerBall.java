package model;

/**
 * 
 * @author Daemon the Koala
 *
 * A ball whose motion is controlled by it's velocity, collisions are perfectly elastic
 * and all balls invariant of size have the same mass.
 */
public class ComputerBall extends Ball {

	/**
	 * Ball whose motion is controlled by it's velocity. Color is randomly
	 * generated
	 * @param vec the location and direction of ball (at center of ball)
	 * @param bounds the border that the ball is constrained to
	 * @param radius the radius of the ball
	 */
	public ComputerBall(Vector vec, Boundary b, int radius) {
		super(vec, b, radius);
	}
	
	@Override
	public void tick() {
		super.tick();
		this.vec.tick();
	}
	
	@Override
	public void handleWallCollision() {
		if(this.bounds.ballHitTop(this)) {
			this.vec.setY(this.bounds.getTop() + this.getRadius());
			this.vec.reverseVelocityY();
		}
		if(this.bounds.ballHitBottom(this)) {
			this.vec.setY(this.bounds.getBottom() - this.getRadius());
			this.vec.reverseVelocityY();
		}
		if(this.bounds.ballHitLeft(this)) {
			this.vec.setX(this.bounds.getLeft() + this.getRadius());
			this.vec.reverseVelocityX();
		}
		if(this.bounds.ballHitRight(this)) {
			this.vec.setX(this.bounds.getRight() - this.getRadius());
			this.vec.reverseVelocityX();
		}
		
	}

	@Override
	public void handleBallCollision() {
		Ball b = this.getBallCollision();
		if(b == null) return;
		if(b instanceof PlayerBall) {
			return;
		}
		this.vec.handle_collision(b.vec);
		
		/* 
		 * Once the vectors are swaped, need to move the balls
		 * so that they are no longer touching each other
		 */
		double dx = this.vec.getX() - b.vec.getX();
		double dy = this.vec.getY() - b.vec.getY();
		double distance = b.vec.distanceTo(this.vec);
		double ratio = (b.getRadius() + this.getRadius())/distance;
		/*
		 * grow dx and dy by the ratio needed to keep them within touching distance
		 * of each other
		*/
		dx *= ratio; 
		dy *= ratio;
		dx += 0.5;
		dy += 0.5;
		double x = dx + b.vec.getX();
		double y = dy + b.vec.getY();
		this.vec.setX(x);
		this.vec.setY(y);
	}

}
