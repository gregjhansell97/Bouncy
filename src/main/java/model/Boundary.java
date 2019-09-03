package model;

/**
 * 
 * @author Daemon the Koala
 *
 * coupled with the ball class. Ensures that the ball is within boundaries
 */
public class Boundary {

    private int x, y, width, height;
    
    /**
     * Constructor that creates a border for a ball. x and y are the upper left coordinates (typically start at 0, 0)
     * down to lower right is (x + width), (y + height)
     * @param x left most coordinate
     * @param y upper most coordinate
     * @param width width of the border(starting from x and going right)
     * @param height height of the border(starting from y and going down)
     */
    public Boundary(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Accessor method
     * @return the smallest y coordinate (upper most)
     */
    public int getTop() {
        return y;
    }
    
    /**
     * Accessor method
     * @return the largest y coordinate (lower most)
     */
    public int getBottom() {
        return y + height;
    }
    
    /**
     * Accessor method
     * @return the largest x coordinate (right most)
     */
    public int getRight() {
        return x + width;
    }
    
    /**
     * Accessor method
     * @return the smallest x coordinate (left most)
     */
    public int getLeft() {
        return x;
    }
    
    /**
     * Given a ball b, is it past the top border?
     * @param b ball having it's border tested
     * @return true if ball is inside the border, false otherwise
     */
    public boolean ballHitTop(Ball b) {
        int top = getTop(); 
        int ballMinTop = (int)b.getVec().getY() - b.getRadius();
        return ballMinTop < top; 
    }
    
    /**
     * Given a ball b, is it past the bottom border?
     * @param b ball having it's border tested
     * @return true if ball is inside the border, false otherwise
     */
    public boolean ballHitBottom(Ball b) {
        int bottom = getBottom();
        int ballMaxBottom = (int)b.getVec().getY() + b.getRadius();
        return ballMaxBottom > bottom;
    }
    
    /**
     * Given a ball b, is it past the right border?
     * @param b ball having it's border tested
     * @return true if ball is inside the border, false otherwise
     */
    public boolean ballHitRight(Ball b) {
        int right = getRight();
        int ballMaxRight = (int)b.getVec().getX() + b.getRadius();
        return ballMaxRight > right;
    }
    
    /**
     * Given a ball b, is it past the left border?
     * @param b ball having it's border tested
     * @return true if ball is inside the border, false otherwise
     */
    public boolean ballHitLeft(Ball b) {
        int left = getLeft();
        int ballMinLeft = (int)b.getVec().getX() - b.getRadius(); 
        return ballMinLeft < left;
    }
    
    /**
     * Mutato: changes the width of the boundary
     * @param width width of the boundary (does not change upper left location)
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Mutator: changes the height of the boundary
     * @param height height of the boundary (does not change upper left location)
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
