package model;

import java.lang.Math;
/**
 * 
 * @author Daemon the Koala
 *
 * The vector class represents the location and velocity of an object.
 */
public class Vector{
  //vectors get used a lot... It's way easier to have all the state variables be public
  private double x, y, vx, vy, ux, uy; //location(x, y), velecity(vx, vy), unit vector(ux, uy)

/**
 * Constructor for the vector class
 * @param x = the x location of vector
 * @param y = the y location of vector
 * @param vx = the velocity in the x direction of the vector
 * @param vy = the velocity in the y direction of the vector
 */
  public Vector(double x, double y, double vx, double vy){
    this.x = x;
    this.y = y;
    setVelocity(vx, vy);
  }

/**
 * The copy constructor
 * @param v the vector who's values are being copied
 */
  public Vector(Vector v){
    this.x  = v.x;
    this.y = v.y;
    this.vx = v.vx;
    this.vy = v.vy;
    this.ux = v.ux;
    this.uy = v.uy;
  }
  
/**
 * accessor: x location of vector
 * @return x location of vector
 */
  public double getX() {
      return this.x;
  }
  
/**
 * accessor: y location of vector
 * @return y location of vector
 */
  public double getY() {
      return this.y;
  }
  
  /**
   * accessor: unit vector parrallel with current vector
   * @return unit vector as a new vector
   */
  public Vector getUnitVector() {
      return new Vector(x, y, ux, uy);
  }
  

  /**
   * accessor: velocity of vector in x direction
   * @return velocity in the x direction
   */
  public double getVelocityX() {
      return this.vx;
  }
  
  /**
   * accessor: veclocity of vector in y direction
   * @return velocity in the y direction
   */
  public double getVelocityY() {
      return this.vy;
  }
  
  /**
   * mutator: changes x location of vector
   * @param x x location of vector
   */
  public void setX(double x) {
      this.x = x;
  }
  
  /**
   * mutator: changes y location of vector
   * @param y y location of vector
   */
  public void setY(double y) {
      this.y = y; 
  }
  
  /**
   * mutator: changes x directional velocity of vector
   * @param vx x directional velocity of vector
   */
  public void setVelocityX(double vx) {
      //decorates setVelocity
      setVelocity(vx, this.vy);
  }
  
  /**
   * mutator: changes y directional velocity of vector
   * @param vy y directional velocity of vector
   */
  public void setVelocityY(double vy) {
      //decorates setVelocity
      setVelocity(this.vx, vy);
  }
  
/**
 * mutator: changes both x directional velocity and y directional velocity
 * @param vx y directional velocity of vector
 * @param vy x directional velocity of vector
 */
  public void setVelocity(double vx, double vy){
    this.vx = vx;
    this.vy = vy;
    
    //keep unit vector correct
    double magnitude = Math.sqrt(vx*vx + vy*vy);
    ux = vx/magnitude;
    uy = vy/magnitude;
  }
  
  /**
   * mutator: 180 degree flip in x directional velocity
   */
  public void reverseVelocityX() {
      this.vx = -this.vx; 
  }
  
  /**
   * mutator: 180 degree flip in y directional velocity
   */
  public void reverseVelocityY() {
      this.vy = -this.vy;
  }

/**
 * The distance from Vector this to Vector v
 * @param v the other vector
 * @return the distance (as a double) between the two vectors
 */
  public double distanceTo(Vector v){
    double dx = (x - v.x);
    double dy = (y - v.y);
    return Math.sqrt(dx*dx + dy*dy); //Pythagorean Theorem
  }
  
  //This handle collision method was based off of the information given in the collision formulas pdf
  void handle_collision(Vector v){
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

    this.setVelocity(normal_vself*normal_vec.ux + tangent_vself*tangent_vec.ux, normal_vself*normal_vec.uy + tangent_vself*tangent_vec.uy);
    v.setVelocity(normal_v*normal_vec.ux + tangent_v*tangent_vec.ux, normal_v*normal_vec.uy + tangent_v*tangent_vec.uy);
  }

  
  
/**
 * Moves x and y locations based on their velocity
 */
  void tick(){
    x += vx;
    y += vy;
  }

/**
 * Moves x and y locations to their prior location before tick
 */
  void undo(){
    x -= vx;
    y -= vy;
  }

}
