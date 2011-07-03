import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A Game is a rectangular area containing a ball and two paddles (a Player and
 * a computer player). Only one instance of the Game class need be created in
 * order to play a game.
 * <p>
 * The game provides methods such as "start" which starts the game, and "tick"
 * which makes the game move forward in time one step. The game also has
 * attributes such as "bounds" which indicates the width and height of the game
 * area in pixels.
 * <p>
 * When the game is started, it starts a loop in a background thread which
 * repeatedly calls the "tick" method until the game is over.
 * <p>
 * A game can be in one of 5 states: Game.State.IDLE, Game.State.PLAYING,
 * Game.State.WON, Game.State.LOST, Game.State.NEW_LEVEL. The current state of a
 * game can be discovered by calling the "getState" method.
 * 
 * @author
 */
public class Game {
	/**
	 * This thread is responsible for repeatedly calling the tick method in a
	 * loop.
	 */
	private class PlayThread extends Thread {
		/**
		 * Tick repeatedly at intervals of 10 milliseconds until the game is
		 * over.
		 */
		@Override
		public void run() {
			while (getGameState() == Game.State.PLAYING) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
				}
				tick();
			}
		}
	}

	/**
	 * This is an "inner" class belonging to Game representing the states of a
	 * game. There are 6 states: IDLE, PLAYING, WON, LOST, NEW_LEVEL, and
	 * INVINCIBLE which are stored in 6 public static final attributes.
	 */
	public static class State {
		public static final State IDLE = new State();
		public static final State PLAYING = new State();
		public static final State WON = new State();
		public static final State LOST = new State();
		public static final State NEW_LEVEL = new State();

		private State() {
		}
	}

	AudioClip myMusic;

	/**
	 * The size of the game
	 */
	private final int SIZE = 500;

	/**
	 * The level the player is on
	 */
	private int level = 1;

	/**
	 * The size of the game area in pixels.
	 */
	private Rectangle bounds;

	/**
	 * The current state of the game.
	 */
	private Game.State state;

	/**
	 * The ball
	 */
	private Ball ball;

	/**
	 * The player and comp in the game.
	 */
	private Paddle player, comp;

	//
	// ACCESSOR METHODS
	//

	/**
	 * Store values of the movement of the player / comp
	 */
	private int moveplayer;

	/**
	 * Creates a new Game.
	 */
	public Game() {
		try {
			myMusic = Applet.newAudioClip(new URL("file:badgerbadger.wav"));
		} catch (MalformedURLException e) {
		}

		bounds = new Rectangle(SIZE, SIZE);
		state = State.IDLE;

		ball = new Ball(this, 30, SIZE / 2 - (Ball.SIZE / 2));

		comp = new Paddle(this, 20, SIZE / 2 - 20);
		player = new Paddle(this, SIZE - 20, SIZE / 2 - 20);

		moveplayer = 0;
	}

	/**
	 * Returns the ball in this game.
	 * 
	 * @return the ball in this game.
	 */
	public Ball getBall() {
		return ball;
	}

	/**
	 * Returns the size of this game in pixels.
	 * 
	 * @return the size of this game in pixels.
	 */
	public Rectangle getBounds() {
		return bounds;
	}

	/**
	 * Returns the computer player in this game.
	 * 
	 * @return the computer player in this game.
	 */
	public Paddle getComp() {
		return comp;
	}

	/**
	 * Returns the current state of this game.
	 * 
	 * @return the current state of this game.
	 */
	public State getGameState() {
		return this.state;
	}

	//
	// OPERATIONS
	//

	/**
	 * Returns the level that the player is on
	 * 
	 * @return the level number the player is on
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Returns the player in this game.
	 * 
	 * @return the player in this game.
	 */
	public Paddle getPlayer() {
		return player;
	}

	/**
	 * Returns true if the given area hits a paddle, or false otherwise.
	 * 
	 * @return true if the given area hits a paddle, or false otherwise.
	 */
	public boolean hitsPaddle(Rectangle area) {
		if (((area.x + Ball.SIZE == player.getArea().x)
				&& (area.y >= player.getArea().y) && (area.y + Ball.SIZE < player
				.getArea().y + player.getArea().height))
				|| ((area.x == comp.getArea().x + comp.getArea().width)
						&& (area.y >= comp.getArea().y) && (area.y + Ball.SIZE < comp
						.getArea().y + comp.getArea().height))) {
			myMusic.play();
			return true;
		}

		return false;
	}

	/**
	 * Puts the game into the Game.State.IDLE state
	 */
	public void idle() {
		state = State.IDLE;
	}

	/**
	 * Returns true if the given area is within the bounds of the game, or false
	 * otherwise.
	 * 
	 * @return 1,2,3,4 in clockwise order starting on left
	 */
	public int isWithinBounds(Rectangle area) {
		if (area.x < bounds.x)
			return 1;
		if (area.y < bounds.y)
			return 2;
		if (area.x + area.width > bounds.width)
			return 3;
		if (area.y + area.height > bounds.height)
			return 4;

		return 0;
	}

	/**
	 * Puts the game into the Game.State.LOST state. This method should be
	 * called when the game is lost.
	 */
	public void lose() {
		state = State.LOST;
	}

	/**
	 * Puts the game into the Game.State.NEW_LEVEL state
	 */
	public void newlevel() {
		state = State.NEW_LEVEL;
	}

	/**
	 * Sets the level
	 */
	public void setLevel(int levelnum) {
		level = levelnum;
	}

	/**
	 * Sets the direction in which the player is going to move
	 */
	public void setMoveplayer(int direction) {
		moveplayer = direction;
	}

	/**
	 * Starts the game. A separate thread will be started to repeatedly call the
	 * tick() method in a loop.
	 */
	public synchronized void start() {
		ball.restart(30, SIZE / 2 - (Ball.SIZE / 2));

		comp.setPosition(20, SIZE / 2 - 20);
		player.setPosition(SIZE - 20, SIZE / 2 - 20);

		moveplayer = 0;

		if (state != State.PLAYING) {
			state = State.PLAYING;
			new PlayThread().start();
		}
	}

	/**
	 * Executes one tick of time in the game. In one tick, the player and each
	 * monster should move one pixel. This method is called over and over again
	 * by the game loop in the PlayThread.
	 */
	public void tick() {
		// Move player if instructed
		if (moveplayer != 0) {
			player.makeMove(moveplayer);
			setMoveplayer(0);
		}

		// Move the computer player
		if (ball.getArea().y > comp.getArea().y + 15) {
			comp.makeMove(1);
		} else if (ball.getArea().y < comp.getArea().y + 15) {
			comp.makeMove(-1);
		}

		// Move the ball
		ball.makeMove();
	}

	/**
	 * Puts the game into the Game.State.WON state. This method should be called
	 * when the game is won.
	 */
	public void win() {
		state = State.WON;
	}
}
