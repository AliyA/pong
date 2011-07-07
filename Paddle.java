import java.awt.Rectangle;

/**
 * Write a description of class Paddle here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Paddle {
	/**
	 * A reference to the game to which this Creature belongs.
	 */
	private Game game;

	/**
	 * The area that this creature occupies on the screen.
	 */
	private Rectangle area;

	/**
	 * This variable is used as a temporary storage location for calculating the
	 * next area that the creature plans to move to.
	 */
	private Rectangle nextArea;

	/**
	 * Constructor for objects of class Paddle
	 */
	public Paddle(Game game, int across, int down) {
		this.game = game;
		this.area = new Rectangle(across, down - 26, 10, 52);
		this.nextArea = new Rectangle(across, down - 26, 10, 52);
	}

	/**
	 * Returns true if the creature can move to the specified area.
	 * 
	 * @return true if the creature can move to the specified area
	 */
	private int canMoveTo(Rectangle area) {
		return game.isWithinBounds(area);
	}

	/**
	 * Returns the area currently occupied by this creature.
	 * 
	 * @return the area currently occupied by this creature.
	 */
	public Rectangle getArea() {
		return area;
	}

	/**
	 * Asks this player to make its move for the current tick of time. As a
	 * result, this player should move 1 step (1 pixel) in its current
	 * direction, and clear any items from the game that it comes into contact
	 * with. Then, if the last item has been cleared, the game is moved into the
	 * Game.State.WON state. If the player has come into contact with a monster,
	 * the game is moved into the Game.State.LOST state.
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
