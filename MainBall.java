import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Random;

//Inherits from the ball class, acts as the ball the user controls.
public class MainBall extends Ball{
  private Vector last_v; //Keeps track of the last vector to calculate projected velocity
  public MainBall(int x, int y){
    super(Color.BLACK, x, y, 0, 0);
    last_v = new Vector(v.x, v.y, 0, 0);
  }

  public void change_location(int x, int y){
    last_v.x = v.x;
    last_v.y = v.y;
    v.x = x;
    v.y = y;
  }

  public double get_velocity_x(){
    return v.x - last_v.x;
  }

  public double get_velocity_y(){
    return v.y - last_v.y;
  }

  public void update(Graphics g){
    super.update(g);
    g.setColor(Color.RED);
    g.setFont(new Font("SansSerit", Font.PLAIN, 25));
    if(Animation.game_active){
      //49 is the number of letter_balls
      g.drawString("" + (Ball.balls.size() - 49), (int)(v.x + 0.5) + DIAMETER/2, (int)(v.y + 0.5) + DIAMETER/2); //This is the score (how long the user has lasted)
    }else{
      g.drawString("Drag", (int)(v.x + 0.5) + DIAMETER/5, (int)(v.y + 0.5) + DIAMETER/2);
    }
  }
}
