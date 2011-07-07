import java.awt.Rectangle;

/**
 * Die Ball Klasse
 * 
 * @Michael Kressibucher
 */
public class Ball {
	/**
	 * Die grösse des Balles in Pixel.
	 */
	public static final int SIZE = 10;

	/**
	 * The area which this item occupies on the screen.
	 */
	private Rectangle area;
	

	/**
	 * Diese Variable wird temporär für das Berechnen der nächsten Position des Balles genutzt.
	 */
	private Rectangle nextArea;

	/**
	 * Die Variabel Game
	 */
	private Game game;

	/**
	 * Die Richtung
	 */
	private int directionX, directionY;

	/**
	 * Erstellt die Anfangsposition
	 * 
	 * @param col
	 *            Die vertikale Position
	 * @param row
	 *            Die horizontale Position
	 */
	public Ball(Game game, int across, int down) {
		this.game = game;
		area = new Rectangle(across, down, SIZE, SIZE);
		nextArea = new Rectangle(across, down, SIZE, SIZE);
		this.directionX = 1;
		this.directionY = 1;
	}

	/**
	 * Gibt die aktuelle Position des Balles wieder.
	 * 
	 */
	public Rectangle getArea() {
		return area;
	}

	/**
	 * Fragt in welche Richtung der Ball gehen sollte und bewegt den Ball 1 Pixel in die richtige Richtung.
	 * Wenn der Ball rechts an den Rand kommt, hat der Spieler verloren und das Spiel wird in den Game.State.WON
         * gesetzt. Wenn der Ball links landet, gewinnt der Spieler und das Spiel wird in Game.State.LOST gesetzt.
         * (Then, if
	 * the ball has reached the right, the player loses and the game is moved
	 * into the Game.State.WON state. If the ball has reached the left, the game
	 * is moved into the Game.State.LOST state.)
	 */
	public void makeMove() {
		nextArea.x = area.x + directionX;
		nextArea.y = area.y + directionY;

		if (game.hitsPaddle(nextArea))
			directionX = -directionX;

		switch (game.isWithinBounds(nextArea)) {
		case 1:
			game.win();
			game.setLevel(game.getLevel() + 1);
			break;
		case 2:
			directionY = -directionY;
			break;
		case 3:
			game.lose();
			game.setLevel(1);
			break;
		case 4:
			directionY = -directionY;
			break;
		default:
			area.x = (int) nextArea.getX();
			area.y = (int) nextArea.getY();
			break;
		}
	}

	public void restart(int left, int down, int directionY) {
		area.x = left;
		area.y = down;

		directionX = 1;
		
		switch (directionY) {
		case 0:
			this.directionY = -1;
			break;
		case 1:
			this.directionY = 1;
			break;
		default:
			this.directionY = -1;
		}
	}
	
	public boolean isMovingLeft () {
		if (directionX > 0) {
			return false;
		}
		else {
			return true;
		}
	}
}
