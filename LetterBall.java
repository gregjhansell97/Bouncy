import java.util.Random;
import java.awt.Color;

//Inherits from the Ball class
public class LetterBall extends Ball{

  public LetterBall(Color c, int x, int y){
    super(c, x, y, 0, 0);
  }
  public void start(){
    //creates a random velocity
    Random rand = new Random();
    while(super.v.vx == 0 && super.v.vy == 0){
      super.v.vx = rand.nextInt()%5;
      super.v.vy = rand.nextInt()%5;
    }
  }
  //The window size is bigger for the letterballs hence the factors infront of length and height two lines below
  protected void update_velocity(int zerox, int zeroy, int length, int height){
    super.update_velocity(-length*2, -height*2, length*3, height*3);
  }
}
