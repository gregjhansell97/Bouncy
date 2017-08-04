import java.awt.Graphics;
import java.awt.Color;
import java.util.*;

//This class creates letters out of LetterBall objects. When the game starts these balls become active
public class Letter{
    protected Vector v;
    public static int width;
    public static int height;
    private Color c;
    private ArrayList<LetterBall> letter_balls = new ArrayList<LetterBall>();

    public Letter(){
      c = Color.RED;
    }
    public Letter(Color c, int x, int y, boolean[][] f){
      this.c = c;
      //moving balls in a grid like fassion
      for(int row = 0; row < 5; row++){
        int b_x = x;
        for(int col = 0; col < 3; col++){
          if(f[row][col]){
            letter_balls.add(new LetterBall(c, b_x, y));
          }
          b_x += Ball.DIAMETER + 1;
        }
        y += Ball.DIAMETER + 1;
      }
    }
    public void start(){
      for(LetterBall lb : letter_balls){
        lb.start();
      }
    }
}
