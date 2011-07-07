import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Die Klasse Ballview. In dieser Klasse wird das Objekt Ball definiert und erstellt.
 * 
 * @Michael
 */
public class BallView {
	private Ball ball;

	/**
	 * Konstruktor des Balles
	 */
	public BallView(Ball ball) {
		this.ball = ball;
	}

	/**
	 * Zeigt den Ball auf dem Spielfeld
	 * 
	 * @param g
	 *            Der grafische Index in der sich das Objekt befindet.
	 */
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);

		Rectangle area = ball.getArea();
		g.fillOval(area.x, area.y, area.width, area.height);
	}
}
