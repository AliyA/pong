import java.awt.Rectangle;

/**
 * Insert description here
 *
 * @author
 */
public class Ball
{
    /**
     * The size of an item in pixels.
     */
    public static final int SIZE = 10;

    /**
     * The area which this item occupies on the screen.
     */
    private Rectangle area;
    
    /**
     * This variable is used as a temporary storage location for calculating
     * the next area that the creature plans to move to.
     */
    private Rectangle nextArea;
    
    /**
     * The game
     */
    private Game game;
    
    /**
     * The direction
     */
    private int directionX, directionY;

    /**
     * Creates an item at the specified location.
     *
     * @param col the column in which to place the Item.
     * @param row the row in which to place the Item.
     */
    public Ball(Game game, int across, int down)
    {
        this.game = game;
        area = new Rectangle(across, down, SIZE, SIZE);
        nextArea = new Rectangle(across, down, SIZE, SIZE);
        directionX = directionY = 1;
    }
    
    public void restart(int left, int down)
    {
        area.x = left;
        area.y = down;
        
        directionX = directionY = 1;
    }

    /**
     * Returns the area that this item occupies.
     *
     * @return the area that this item occupies.
     */
    public Rectangle getArea()
    {
        return area;
    }
    
    /**
     * Asks the ball to make its move for the current tick of time. As a
     * result, this ball should move 1 step (1 pixel) in its current
     * direction.  Then, if the ball has reached the right, the player loses
     * and the game is moved into the Game.State.WON state. If the ball has 
     * reached the left, the game is moved into the Game.State.LOST state.
     */
    public void makeMove()
    {
        nextArea.x = area.x + directionX;
        nextArea.y = area.y + directionY;
        
        if(game.hitsPaddle(nextArea))
            directionX = -directionX;
            
        switch(game.isWithinBounds(nextArea))
        {
            case 1: game.win();
                    game.setLevel(game.getLevel()+1);
                    break;
            case 2: directionY = -directionY;
                    break;
            case 3: game.lose();
                    game.setLevel(1);
                    break;
            case 4: directionY = -directionY;
                    break;
            default: area.x = (int)nextArea.getX();
                     area.y = (int)nextArea.getY();
                     break;
        }
    }
}
