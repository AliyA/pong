import java.awt.Rectangle;
import java.util.Random;

/**
 * Ein Punkt („Ball“) bewegt sich auf dem Bildschirm hin und her. Zwei Spieler steuert einen 
 * senkrechten Strich („Balken“), welcher er nach oben und unten verschieben kann. Lässt man den
 * „Ball“ am „Balken“ vorbei, erhält der Gegner einen Punkt.
 * 
 * Die Klasse beinhaltet Methoden wie zum Beispiel start, welcher das Spiel startet,  
 * oder Tick, welche das Spiel laufen lässt. Zusätzlich gibt es Attribute welche zum Beispiel  
 * die Grösse des Spiels in Pixel definieren.  
 *  
 * Wenn das Spiel gestartet wird, ruft es einen Thread auf, welcher eine Schleife beinhaltet. 
 * Diese Schleife ruft andauernd die Methode Tick auf, bis das Spiel zu Ende ist. 
 *  
 * Es gibt 6 Statusse in denen sich das Spiel befinden kann ; IDLE (Leerlauf), PLAYING (Spielen),  
 * WON (gewonnen), LOST (verloren), NEW_LEVEL (neues Level),INVINCIBLE (unsichtbar). 
 *
 * @Michael Kressibucher
 */
public class Game {
	/**
	 * Dieses Thread ruft im Hintergrund mittels einer Schleife andauernd die Tick Methode auf.
	 */
	private class PlayThread extends Thread {
		/**
		 * Tick welche sich im 10 millisekunden Takt wiederholt,
		 * bis das Spiel zu Ende ist.
		 */
		@Override
		public void run() {
			while (getGameState() == Game.State.PLAYING) {
				try {
					Thread.sleep(2 + (11 - level) / 2);
				} catch (Exception e) {
				}
				tick();
			}
		}
	}

	/**
	 * Dies ist eine innere Klasse (State) welche zu Game gehört und den Status des Spiels enthält
	 * Es gibt 6 Statusse: IDLE (Leerlauf), PLAYING (Spielen), WON (gewonnen), LOST (verloren), 
         * NEW_LEVEL (neues Level),INVINCIBLE (unsichtbar). Diese werden in statischen Attributen festgehalten
	 */
	public static class State {
		public static final State IDLE = new State();
		public static final State PLAYING = new State();
		public static final State WON = new State();
		public static final State LOST = new State();
		public static final State NEW_LEVEL = new State();

		private State() {
			// add sound
		}
	}

	/**
	 * Die Grösse des Spiels
	 */
	private final int SIZE = 500;
	private final int SizeX = this.SIZE;
	private final int SizeY = 2 * this.SIZE / 3;

	/**
	 * Das Level in welches sich der Spieler befindet.
	 */
	private int level = 1;

	/**
	 * Die Grösse des Spiels in Pixels.
	 */
	private Rectangle bounds;

	/**
	 * Der momentane Status des Spieles.
	 */
	private Game.State state;

	/**
	 * Der Ball.
	 */
	private Ball ball;
	private int ballY;

	/**
	 * Die Spielbalken des Spielers und des Computers.
	 */
	private Paddle player, comp;


	/**
	 * Speichert die Bewegungen des Spielers/Computers.
	 */
	private int moveplayer;
	
	private Random random;

	/**
	 * Erstellt ein neues Spiel.
	 */
	public Game() {
		random = new Random();
		
		bounds = new Rectangle(SizeX, SizeY);
		state = State.IDLE; 
		
		ballY = (SizeY) / 3 + random.nextInt(SizeY/3);
		ball = new Ball(this, 30, ballY);

		comp = new Paddle(this, 20, SizeY / 2);
		player = new Paddle(this, SizeX - 20, SizeY / 2);

		moveplayer = 0;
	}

	/**
	 * Gibt den Ball zurück.
	 * 
	 */
	public Ball getBall() {
		return ball;
	}

	/**
	 * Gibt die Grösse des Spiels zurück.
	 */
	public Rectangle getBounds() {
		return bounds;
	}

	/**
	 * Gibt den Computer Spielerbalken zurück.
	 */
	public Paddle getComp() {
		return comp;
	}

	/**
	 * Gibt den aktuellen Status des Spiels zurück.
	 * 
	 */
	public State getGameState() {
		return this.state;
	}

	/**
	 * Gibt die Levelhöhe zurück.
	 * 
	 * @return the level number the player is on
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gibt den Spieler Spielbalken zurück.
	 */
	public Paddle getPlayer() {
		return player;
	}

	/**
	 * Gibt den Wert true zurück wenn die angegebene Position einen Balken trifft.
         * Wenn nicht, gibt es false zurück.
	 */
	public boolean hitsPaddle(Rectangle area) {
		if (((area.x + Ball.SIZE == player.getArea().x)
				&& (area.y >= player.getArea().y) && (area.y + Ball.SIZE < player
				.getArea().y + player.getArea().height))
				|| ((area.x == comp.getArea().x + comp.getArea().width)
						&& (area.y >= comp.getArea().y) && (area.y + Ball.SIZE < comp
						.getArea().y + comp.getArea().height))) {
			return true;
		}

		return false;
	}

	/**
	 * Setzt das Spiel in den Leerlauf Status
	 */
	public void idle() {
		state = State.IDLE;
	}


         /**
	 * Gibt den Wert true zurück wenn die angegebene Position sich im Rahmen des Spieles befindet.
         * Wenn nicht, gibt es false zurück.
         * @return 1,2,3,4 im Uhrzeigersinn. Startet links.
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
	 * Setzt das Spiel in den Verloren Status
	 */
	public void lose() {
		state = State.LOST;
	}
        /**
	 * Setzt das Spiel in den Neues Level Status
	 */
	public void newlevel() {
		state = State.NEW_LEVEL;
	}

	/**
	 * Setzt das Level
	 */
	public void setLevel(int levelnum) {
		level = levelnum;
	}

	/**
	 *Setzt die Richtung in welcher sich der Spieler bewegen wird.
	 */
	public void setMoveplayer(int direction) {
		moveplayer = direction;
	}

	/**
	 * Startet das Spiel. Ein seperater Thread (PlayThread) wird aufgerufen. 
	 */
	public synchronized void start() {
		ball.restart(40, ballY, random.nextInt(2));
		this.ballY =  (SizeY - Ball.SIZE) / 3 + random.nextInt(SizeY/3  - Ball.SIZE);

		comp.setPosition(20, SizeY / 2);
		player.setPosition(SizeX - 20, SizeY / 2);

		moveplayer = 0;

		if (state != State.PLAYING) {
			state = State.PLAYING;
			new PlayThread().start();
		}
	}

	/**
	 * Macht einen Tick im Spiel. In diesem Tick bewegen sich beide Spielerbalken um einen Pixel.
	 * Diese Methode wird durch die Schleife in PlayThread andauernd aufgerufen
	 */
	public void tick() {
		// Move player if instructed
		if (moveplayer != 0) {
			player.makeMove(moveplayer);
			setMoveplayer(0);
		}

		// Move the computer player (left)
		if (( ball.isMovingLeft() &&
				( 20 * ball.getArea().x < (5 + getLevel()) * SizeX ))) {
			if (ball.getArea().y > comp.getArea().y + comp.getArea().height / 2) {
				comp.makeMove(1);
			} else if (ball.getArea().y < comp.getArea().y + comp.getArea().height / 2) {
				comp.makeMove(-1);
			}
		}

		// Move the ball
		ball.makeMove();
	}
         /**
	 * Setzt das Spiel in den Gewonnen Status
	 */
	public void win() {
		state = State.WON;
	}
}
