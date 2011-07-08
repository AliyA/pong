import java.awt.Rectangle;

/**
 * Die Klasse Paddle beschreibt das Verhalten der Spielbalken.
 * 
 * @Olivier Favre
 */
public class Paddle {

	private Game game;

	/**
	 * Die Area in welcher sich das Objekt aufhält.
	 */
	private Rectangle area;

	/**
	 * Dies variabel wird temporär genutzt um die nächste Position des Balkens
	 * zu berechnen.
	 */
	private Rectangle nextArea;

	/**
	 * Konstruktor der Klasse Paddle
	 */
	public Paddle(Game game, int across, int down) {
		this.game = game;
		this.area = new Rectangle(across, down - 26, 10, 52);
		this.nextArea = new Rectangle(across, down - 26, 10, 52);
	}

	/**
	 * Meldet ob der Balken sich in diese Richtung bewegen darf.
	 */
	private int canMoveTo(Rectangle area) {
		return game.isWithinBounds(area);
	}

	/**
	 * Meldet die Position des Balkens
	 */
	public Rectangle getArea() {
		return area;
	}

	/**
	 * Bewegt den Spielbalken bei der Eingabe um einen Pixel.
	 */
	public void makeMove(int direction) {
		nextArea.y = area.y + direction;

		if (canMoveTo(nextArea) == 0) {
			area.x = (int) nextArea.getX();
			area.y = (int) nextArea.getY();
		}
	}

	public void setPosition(int left, int down) {
		area.x = left;
		area.y = down - area.height / 2;
	}
}
