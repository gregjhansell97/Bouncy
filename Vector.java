import java.lang.Math;
/*
This class represents the motion and locatin of a ball
*/
public class Vector{
  public int x, y, prior_x, prior_y = 0; //location and velocity in x and y
  public double vx, vy, ux, uy;

  public Vector(int x, int y, double vx, double vy){
    this.x = x;
    this.y = y;
    set_velocity(vx, vy);
  }

  public Vector(Vector v){
    this.x  = v.x;
    this.y = v.y;
    this.prior_x = prior_x;
    this.prior_y = prior_y;
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

  void collide_with(Vector v){
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
    prior_x = x;
    prior_y = y;
    if(vx == 0 && vy == 0) return;
    double round_off_x = 0.5;
    double round_off_y = 0.5;
    if(Math.abs(vx) < 0.5) round_off_x = 1;
    if(Math.abs(vy) < 0.5) round_off_y = 1;
    if(vx < 0) round_off_x *= -1;
    if(vy < 0) round_off_y *= -1;

    x = (int)(vx + x + round_off_x);
    y = (int)(vy + y + round_off_y);
  }

//Used to take a step back incase of a collision
  void undo(){
    x = prior_x;
    y = prior_y;
  }

}
