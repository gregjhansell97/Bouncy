package controller;

import model.PlayerBall;


/**
 * 
 * @author Daemon the Koala
 *
 * Given the PlayerBall, any class that extends this class can call
 * change the location of the PlayerBall object
 */
public abstract class BallController{
    protected PlayerBall playerBall;
    
    /**
     * constructor: passes in playerBall as a reference (should not be a copy)
     * @param playerBall should be passed in by reference
     */
    public BallController(PlayerBall playerBall) {
        this.playerBall = playerBall;
    }
    
    /**
     * mutator: changes the location of playerBall to x and y
     * @param x modifies the x location of playerBall
     * @param y modifies the y location of playerBall
     */
    protected void setLocation(double x, double y) {
        this.playerBall.setLocation(x, y);
    }
    

}
