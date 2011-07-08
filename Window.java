import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

/**
 * Write a description of class Window here.
 * 
 * @Noortje de Haan
 */
public class Window extends JFrame {
	private Game game;

	/**
	 * Konstruktor der Klasse Window
	 */
	public Window() {
		this.setTitle("Pong");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container contentPane = new Container();
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		game = new Game();

		GameView gameView = new GameView(game);
		contentPane.add(gameView, BorderLayout.CENTER);

		this.pack();
	}
}
