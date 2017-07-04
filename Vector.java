import java.lang.Math;

public class Vector{
  public int x, y, vx, vy = 0;
  public double ux, uy = 0;

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
    return Math.sqrt(dx*dx + dy*dy);
  }

  void tick(){
    x += vx;
    y += vy;
  }

  void undo(){
    x -= vx;
    y -= vy;
  }

}
