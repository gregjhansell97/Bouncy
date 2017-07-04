import java.awt.Graphics;
import java.awt.Color;
import java.util.*;

public class Ball{
    public static ArrayList<Ball> balls = new ArrayList<Ball>();
    protected Vector v;
    private Color c;
    public final static int DIAMETER = 100;
    static public boolean hold = false;

    public Ball(){
      v = new Vector(0, 0, 0, 0);
    }

    public Ball(Color c, int x, int y, int v_x, int v_y){
      v = new Vector(x, y, v_x, v_y);
      this.c = c;
      balls.add(this);
    }

    public void update(Graphics g){
      g.setColor(c);
      g.fillOval(v.x, v.y, DIAMETER, DIAMETER);
    }

    public void tick(int length, int height){
      handle_collisions();
      update_velocity(0, 0, length, height);
      v.tick();
    }

    private boolean collision(Ball b){
      return b.v.distance_to(v) <= DIAMETER;
    }

    private boolean near_by(Ball b){
      return v.x - b.v.x < DIAMETER && v.y - b.v.y < DIAMETER;
    }

    private void handle_collisions(){
     //Ball.hold = true;
     int i = 0;
     for(Ball b : balls){
        if(b != this){
          if(near_by(b)){
            if(collision(b)){
              if(b instanceof MainBall || this instanceof MainBall){
                //Lost game, trigger events
                System.exit(0);
              }

              int temp_vx, temp_vy, temp_bvx, temp_bvy = 0;
              temp_vx = v.vx;
              temp_vy = v.vy;
              temp_bvx = b.v.vx;
              temp_bvy = b.v.vy;
              v.set_velocity(temp_bvx, temp_bvy);
              b.v.set_velocity(temp_vx, temp_vy);

              v.tick();
              b.v.tick();
              if(b.v.distance_to(v) > DIAMETER){
                v.undo();
                b.v.undo();
                v.set_velocity(temp_vx, temp_vy);
                b.v.set_velocity(temp_bvx, temp_bvy);
                return;
              }
              v.undo();
              b.v.undo();
              v.set_velocity(1, 0);
              if(v.x <= b.v.x){
                v.set_velocity(-v.vx, 0);
              }
              b.v.set_velocity(-v.vx, 0);


              int saftey = 0;
              //This is a very half assed way to handle collisions... I apologize

              while(saftey < 20 && b.v.distance_to(v) <= DIAMETER){
                update_velocity(0, 0, 2000, 1100);
                v.tick();
                if(b.v.distance_to(v) > DIAMETER) break;
                b.v.tick();
                saftey++;
              }
              v.set_velocity(temp_bvx, temp_bvy);
              b.v.set_velocity(temp_vx, temp_vy);
              //System.out.println(saftey);
              return;
            }
          }
        }
      }
    }

    protected void update_velocity(int zerox, int zeroy, int length, int height){
      if(v.x < zerox){
        v.set_velocity(-v.vx, v.vy);
        v.x = zerox;
      } else if (v.x > length - DIAMETER){
        v.set_velocity(-v.vx, v.vy);
        v.x = length - DIAMETER;
      }
      if(v.y < zeroy){
        v.set_velocity(v.vx, -v.vy);
        v.y = zeroy;
      } else if (v.y > height - DIAMETER){
        v.set_velocity(v.vx, -v.vy);
        v.y = height - DIAMETER;
      }
    }
}
