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
 * Das Gameview ist die grafische Darstellung des Game Objektes. Es gibt das Spiel auf
 *  dem Bildschirm wieder. 
 *
 * @Olivier Favre
 */
public class GameView extends JComponent implements MouseListener, KeyListener,
		MouseWheelListener {
	/**
	 * Dieses Thread läuft dauernd im Hintergrund und ruft die Methode repaint () 
	 * auf, damit das Spielfenster neu geladen wird und der Verlauf des Spieles sichtbar wird.
	 */
	private class AnimationThread extends Thread {
		/**
		 * Schleife in welcher sich das Spiel bin zum Ende befindet.
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
	 * Erstellt eine GameView um das Spiel zu sehen.
	 * 
	 * @param game
	 *            das zu sehende Spiel.
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
		// KeyEvent.VK_W
		case 87:
			game.setMoveplayer(-10);
			break;
		// KeyEvent.VK_S
		case 83:
			game.setMoveplayer(10);
			break;
		// KeyEvent.VK_UP
		case 38:
			game.setMoveplayer(-10);
			break;
		// KeyEvent.VK_DOWN
		case 40:
			game.setMoveplayer(10);
			break;
		}
	}

	/**
	 * Wenn eine Taste gedrückt wird wollen wir den Balken bewegen.
	 */
	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == 32) {
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
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	/**
	 * Wenn mit der Maus geklickt wird, sendet diese Mathode das "start" signal an die Klasse Spiel.
	 * Dies jedoch nur wenn das Spiel noch nicht gestartet ist.
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

	
	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();

		game.setMoveplayer(6 * notches);
	}

	/**
	 * Gibt das Spiel auf dem Bildschirm wieder
	 * 
	 * @param g
	 *            Das Grafikumfeld in welches gezeichnet werden soll.
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
