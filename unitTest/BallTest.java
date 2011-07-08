package unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class BallTest {


/**
 *  In diesem Test wird die Ball Position manuell gesetzt und danach mit einem indentischen Rechteck überprüft.
 *
 */


public void testStartNew()
	{
		Game game1 = new Game();
		Ball ball1 = new Ball(game1, 1, 1);
		ball1.restart(200, 200);
		assertEquals(new Rectangle(200,200,10,10), ball1.getArea());
	}

/**
 *  In diesem Test wird die Methode makeMove überprüft, in dem mehrmals makeMove() ausgefürt wird und danach die Position des 
 * Balles mit einem Vergleichsball überprüft wird.
 *
 */

public void testMakeMove()
	{
		Game game1 = new Game();
		Ball ball1 = new Ball(game1, 1, 1);
		ball1.makeMove();
		ball1.makeMove();
		ball1.makeMove();
		assertEquals(new Rectangle(4,4,10,10), ball1.getArea());
	}

}
