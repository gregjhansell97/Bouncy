import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

import controller.MouseBallController;

import helpers.Random;

import model.Ball;
import model.PlayerBall;
import model.Boundary;

import view.GameJPanel;

/**
 * 
 * @author Daemon the Koala
 *
 * The main class houses the main function which is the start of the program
 */
public class Main {
    protected static Random rand = new Random(); //Used to assign all the balls velocities
    private final static int BALLRATIO = 30;
    private final static int LIVES = 4;
    
    public static void startGame(JFrame frame) {
        //get size of the computer screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int)dim.getWidth();
        final int height = (int)dim.getHeight();
        
        //create model
        double area = (width*height)/BALLRATIO; //area of ball BALLRATIO times smaller than screen (pixels)
        int radius = (int)Math.sqrt(area/Math.PI);
        Boundary bounds = new Boundary(0, 0, width, height);

        final PlayerBall playerBall = PlayerBall.createBall(width/2, height/2, bounds, radius);
        
        
        //create view
        frame.setSize(width, height);
        GameJPanel game = new GameJPanel(width, height, Ball.balls);
        frame.add(game); 
        
        //generate controller
        MouseBallController mouseListener = new MouseBallController(playerBall);
        game.addMouseMotionListener(mouseListener);
        
        Timer addBall = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setBackground(Random.getRandomColor());
                playerBall.addBall(2*playerBall.getRadius()/3);
            }
        });
        
        //The clock that updates all the current events going on
        Timer clock = null;
        clock = new Timer(15, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final int height = game.getHeight();
                final int width = game.getWidth();
                bounds.setHeight(height);
                bounds.setWidth(width);
                
                //tick modifies the ArrayList so we make a copy so we can iterate through it in peace
                @SuppressWarnings("unchecked")
                ArrayList<Ball> clone = (ArrayList<Ball>) Ball.balls.clone();
                for(Ball b : clone) {
                    b.tick();
                }

                if(playerBall.getHitCount() >= LIVES) { //you lost the game
                    //reset the model
                    Ball.balls.clear();
                    double area = (width*height)/BALLRATIO; //area of ball BALLRATIO times smaller than screen (pixels)
                    int radius = (int)Math.sqrt(area/Math.PI);
                    PlayerBall.createBall(height/2, width/2, bounds, radius);
                    Ball.balls.add(playerBall);
                    
                    game.setBackground(Color.white);
                }
                
                game.repaint();
            }
        });
        
        clock.start();
        addBall.start();

    }
    
    public static void main(String[] args) {
        
        //create the jframe the game will be placed on
        JFrame frame = new JFrame("Bouncy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        startGame(frame);

    }

}
