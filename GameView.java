import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;

/**
 * A GameView is a graphical representation of a Game object. It renders the
 * contents of the game onto the screen so that the user can visualise the game,
 * its player, monsters, items and walls.
 * 
 * @author
 */
public class GameView extends JComponent implements MouseListener, KeyListener,
		MouseWheelListener {
	/**
	 * This thread runs in the background repeatedly calling the repaint()
	 * method so that the screen is continously redrawn to reflect the changing
	 * state of the game.
	 */
	private class AnimationThread extends Thread {
		/**
		 * Loop until the game stops, calling the repaint() method.
		 */
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
				}
				repaint();
			}
		}
	}

	private Game game;
	private Dimension size;
	private PaddleView playerView, compView;

	private BallView ballView;

	/**
	 * Creates a GameView to view the given Game.
	 * 
	 * @param game
	 *            the Game to view.
	 */
	public GameView(Game game) {
		this.game = game;

		addMouseListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);

		setFocusable(true);

		setBackground(Color.black);

		size = game.getBounds().getSize();

		setPreferredSize(size);

		playerView = new PaddleView(game.getPlayer());
		compView = new PaddleView(game.getComp());
		ballView = new BallView(game.getBall());

		new AnimationThread().start();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KeyEvent.VK_W:
			game.setMoveplayer(-4);
			break;
		case KeyEvent.VK_S:
			game.setMoveplayer(4);
			break;
		// KeyEvent.VK_UP
		case 38:
			game.setMoveplayer(-4);
			break;
		// KeyEvent.VK_DOWN
		case 40:
			game.setMoveplayer(4);
			break;
		}
	}

	/**
	 * When a key is pressed we want to move the paddles
	 */
	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	/**
	 * When the mouse is clicked, this method sends the "start" message to the
	 * game, if the game has not already been started.
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		synchronized (game) {
			if (game.getGameState() == Game.State.IDLE
					|| game.getGameState() == Game.State.NEW_LEVEL) {
				game.start();
				new AnimationThread().start();
			} else if (game.getGameState() == Game.State.WON) {
				game.newlevel();
			} else if (game.getGameState() == Game.State.LOST) {
				game.idle();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

	// We don't care about these events.
	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();

		game.setMoveplayer(4 * notches);
	}

	/**
	 * Renders the game onto the screen.
	 * 
	 * @param g
	 *            the graphics context in which to draw.
	 */
	@Override
	public void paintComponent(Graphics g) {
		// Draw the background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size.width, size.height);

		// Draw the wall
		// g.setColor(Color.WHITE);
		// g.fillRect(size.width / 2 - 5, 0, 10, size.height);

		// Draw the ball
		ballView.paint(g);

		// Draw the player and comp
		playerView.paint(g);
		compView.paint(g);

		// Draw any message
		//
		Game.State state = game.getGameState();
		if (state == Game.State.PLAYING) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Helvetica", Font.BOLD, 12));
			g.drawString("Level: " + game.getLevel(), 10, 30);
		}
		if (state == Game.State.WON) {
			g.setColor(Color.red);
			g.setFont(new Font("Helvetica", Font.BOLD, 60));
			g.drawString("YOU WIN!", 100, 100);
		} else if (state == Game.State.NEW_LEVEL) {
			g.setColor(Color.red);
			g.setFont(new Font("Helvetica", Font.BOLD, 24));
			g.drawString("NEXT LEVEL!   LEVEL " + game.getLevel() + "!", 85,
					100);
			repaint();
		} else if (state == Game.State.LOST) {
			g.setColor(Color.red);
			g.setFont(new Font("Helvetica", Font.BOLD, 60));
			g.drawString("YOU LOSE!", 85, 100);
		} else if (state == Game.State.IDLE) {
			g.setColor(Color.red);
			g.setFont(new Font("Helvetica", Font.BOLD, 24));
			g.drawString("Press the mouse to begin!", 100, 100);
			repaint();
		}
	}
}
