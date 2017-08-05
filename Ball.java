import java.awt.Graphics;
import java.awt.Color;
import java.util.*;

/*
This class represents a ball in the graphics. it has a vector thats modified
to keep track of it's location. It is also inherited
*/
public class Ball{
    //Every ball that is inititialized is created here so they can all be updated at once
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

    //this updates the ball on the screen specifically
    public void update(Graphics g){
      g.setColor(c);
      g.fillOval(v.x, v.y, DIAMETER, DIAMETER);
    }

    public void tick(int length, int height){
      handle_collisions(); //Balls colliding needs to be fixed
      update_velocity(0, 0, length, height); //checks the boundaries to make sure no balls go out of bounds
      v.tick(); //updates the location
    }

    private boolean collision(Ball b){
      return b.v.distance_to(v) <= DIAMETER;
    }

    private boolean near_by(Ball b){ // verifies quickly if a ball is near_by
      return v.x - b.v.x < DIAMETER && v.y - b.v.y < DIAMETER;
    }

    private void handle_collisions(){
     //Ball.hold = true;
     int i = 0;
     for(Ball b : balls){ //goes through every ball except itself
        if(b != this){ //test for self
          if(near_by(b)){
            if(collision(b)){
              if(b instanceof MainBall || this instanceof MainBall){
                //Lost game, trigger events
                /*
                Still need to work on reset trigger for this. NOTE
                MUST COMPLETE Still
                */
                System.exit(0);
              }

              v.collide_with(b.v);

              v.tick();
              b.v.tick();
              if(b.v.distance_to(v) > DIAMETER){ //There wont be collision issues
                v.undo();
                b.v.undo();
                return;
              }
              //There are collision issues:
              /*
                going to take each ball and step them away from each other one
                vector step back at a time
              */
              v.undo();
              b.v.undo();

              Vector temp_v = new Vector(v);
              Vector temp_bv = new Vector(b.v);

              v.set_velocity(1, 0); //setting velocity to one step
              if(v.x <= b.v.x){
                v.set_velocity(-v.vx, 0); //swaps them based on position
              }
              b.v.set_velocity(-v.vx, 0);


              int saftey = 0; //prevents an infinite loop
              //steps them away from each other until they're no longer collided
              while(saftey < 25 && b.v.distance_to(v) <= DIAMETER){
                update_velocity(0, 0, 2000, 1100);
                v.tick();
                if(b.v.distance_to(v) > DIAMETER) break;
                b.v.tick();
                saftey++;
              }
              v.set_velocity(temp_v.vx, temp_v.vy);
              b.v.set_velocity(temp_bv.vx, temp_bv.vy);
              return;
            }
          }
        }
      }
    }
    /*
      This function handles the balls bouncing on the walls, so that either the
      x or the y direction is flipped based on what wall is hit.
    */
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
