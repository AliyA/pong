import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Write a description of class BallView here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class BallView {
	private Ball ball;

	/**
	 * Constructor for objects of class BallView
	 */
	public BallView(Ball ball) {
		this.ball = ball;
	}

	/**
	 * Renders the ball onto the screen.
	 * 
	 * @param g
	 *            the graphics context in which to draw.
	 */
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);

		Rectangle area = ball.getArea();
		g.fillOval(area.x, area.y, area.width, area.height);
	}
}
