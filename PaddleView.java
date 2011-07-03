import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A PaddleView is a graphical representation of a Paddle object.
 *
 * @author
 */
public class PaddleView
{
    /**
     * The paddle being viewed.
     */
    private Paddle paddle;

    /**
     * Creates a PaddleView for the given paddle.
     *
     * @param paddle the paddle to view.
     */
    public PaddleView(Paddle paddle)
    {
        this.paddle = paddle;
    }

    /**
     * Renders this paddle onto the screen.
     *
     * @param g the graphics context in which to draw.
     */
    public void paint(Graphics g)
    {
        Rectangle area = paddle.getArea();
        g.fillRect(area.x,area.y,area.width,area.height);
    }
}
