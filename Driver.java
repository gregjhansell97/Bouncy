import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Driver{
  public static final int WIDTH = 1925, HEIGHT = 1100;
  private static Animation a;
  private static JFrame frame;
  public static void main(String [] args){

    frame = new JFrame("Bouncy"); //title of the page
    frame.setSize(WIDTH,HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    create_animation();

    //Timer adds a new ball every 10 seconds or so
    javax.swing.Timer level_creator  = new javax.swing.Timer(6000, new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(Animation.game_active) a.addBall();
      }
    });

    //Timer updates all the balls and repains the window
    javax.swing.Timer clock = new javax.swing.Timer(15, new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(Animation.game_active){
          a.tick();
          a.repaint();
        }
      }
    });

    clock.start();
    level_creator.start();

  }

//The two functions are used when restarting the game
  public static void create_animation(){
    a = new Animation(WIDTH, HEIGHT);
    frame.add(a);
    frame.setVisible(true);
  }

  public static void remove_animation(){
    frame.remove(a);
  }
}
