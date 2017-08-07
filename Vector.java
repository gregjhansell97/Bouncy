import java.lang.Math;
/*
This class represents the motion and locatin of a ball
*/
public class Vector{
  //vectors get used a lot... It's way easier to have all the state variables be public
  public double x, y, vx, vy, ux, uy; //location(x, y), velecity(vx, vy), unit vector(ux, uy)


  public Vector(double x, double y, double vx, double vy){
    this.x = x;
    this.y = y;
    set_velocity(vx, vy);
  }

  public Vector(Vector v){
    this.x  = v.x;
    this.y = v.y;
    this.vx = v.vx;
    this.vy = v.vy;
    this.ux = this.ux;
    this.uy = this.uy;
  }

  void set_velocity(double vx, double vy){
    this.vx = vx;
    this.vy = vy;
    double magnitude = Math.sqrt(vx*vx + vy*vy);
    ux = vx/magnitude;
    uy = vy/magnitude;
  }

  double distance_to(Vector v){
    double dx = (x - v.x);
    double dy = (y - v.y);
    return Math.sqrt(dx*dx + dy*dy); //Pythagorean Theorem
  }

  void handle_boundary_collision(int zerox, int zeroy, int length, int height){
    if(x < zerox){
      set_velocity(-vx, vy);
      x = zerox;
    } else if (x > length - Ball.DIAMETER){
      set_velocity(-vx, vy);
      x = length - Ball.DIAMETER;
    }
    if(y < zeroy){
      set_velocity(vx, -vy);
      y = zeroy;
    } else if (y > height - Ball.DIAMETER){
      set_velocity(vx, -vy);
      y = height - Ball.DIAMETER;
    }
  }

//This handle collision method was based off of the information given in the collision formulas pdf
  void handle_ball_collision(Vector v){
    Vector normal_vec = new Vector(0, 0, x - v.x, y - v.y);
    Vector tangent_vec = new Vector(0, 0, -normal_vec.vy, normal_vec.vx);
    double normal_vself, normal_v, tangent_vself, tangent_v;

    //dot products that projects normal and tangential velocity
    normal_vself = normal_vec.ux*vx + normal_vec.uy*vy;
    normal_v = normal_vec.ux*v.vx + normal_vec.uy*v.vy;
    tangent_vself = tangent_vec.ux*vx + tangent_vec.uy*vy;
    tangent_v = tangent_vec.ux*v.vx + tangent_vec.uy*v.vy;

    //swap normal velocities
    double temp = normal_vself;
    normal_vself = normal_v;
    normal_v = temp;

    set_velocity(normal_vself*normal_vec.ux + tangent_vself*tangent_vec.ux, normal_vself*normal_vec.uy + tangent_vself*tangent_vec.uy);
    v.set_velocity(normal_v*normal_vec.ux + tangent_v*tangent_vec.ux, normal_v*normal_vec.uy + tangent_v*tangent_vec.uy);
  }

//Every clock tick in the game represents a new step where the locations are changed from the velocity
  void tick(){
    if(!Animation.game_active) return;
    x += vx;
    y += vy;
  }

//Used to take a step back incase of a collision
  void undo(){
    x -= vx;
    y -= vy;
  }

}
