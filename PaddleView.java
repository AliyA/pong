import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Die Paddleview Klasse visualisiert den Spielebalken.
 * 
 * @Olivier Favre
 */
public class PaddleView {
	/**
	 * Der Spielbalken
	 */
	private Paddle paddle;

	/**
	 * Erstellt eine Ansicht des Spielbalkens.
	 * 
	 * @param paddle
	 *            den Spielbalken den man sehen soll
	 */
	public PaddleView(Paddle paddle) {
		this.paddle = paddle;
	}

	/**
	 * Gibt diesen Spielbalken auf den Bildschirm wieder.
	 * 
	 * @param g
	 *            Die Grafische Umgebung des Spielbalkens.
	 */
	public void paint(Graphics g) {
		Rectangle area = paddle.getArea();
		g.fillRect(area.x, area.y, area.width, area.height);
	}
}
