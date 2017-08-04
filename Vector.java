import java.lang.Math;
/*
This class represents the motion and locatin of a ball
*/
public class Vector{
  public int x, y, vx, vy = 0; //location and velocity in x and y

  public Vector(int x, int y, int vx, int vy){
    this.x = x;
    this.y = y;
    set_velocity(vx, vy);
  }

  public Vector(Vector v){
    this.x  = v.x;
    this.y = v.y;
    this.vx = v.vx;
    this.vy = v.vy;
  }

  void set_velocity(int vx, int vy){
    this.vx = vx;
    this.vy = vy;
  }

  double distance_to(Vector v){
    double dx = (x - v.x);
    double dy = (y - v.y);
    return Math.sqrt(dx*dx + dy*dy); //Pythagorean Theorem
  }

//Every clock tick in the game represents a new step where the locations are changed from the velocity
  void tick(){
    x += vx;
    y += vy;
  }

//Used to take a step back incase of a collision
  void undo(){
    x -= vx;
    y -= vy;
  }

}
