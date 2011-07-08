/**
 * 
 */
package unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Michael
 *
 */
public class PaddleTest {

/**
 *  Dieser Test testet die Methode setLocation, mit welcher man das Paddle auf eine gewünschte Position des Festers     
 *  setzten kann. Um das zu testen wird die Position des Paddle-Rechtecks mit der eines neuen Rechtecks verglichen.
 *
 */



public void testSetLocation()
	{
		Game game2 = new Game();
		Paddle paddle3 = new Paddle(game2, 0, 0);
		paddle3.setPosition(1, 1);
		assertEquals(new Rectangle(1,1,10,40), paddle3.getArea());
	}

/**
 *  Dieser Test testet die Methode makeMove. Damit kann man das Paddle nach oben oder unten verschieben.
 *  Getestet wird so, dass man das Paddle zuerst bewegt und dann mit einem neuen Rechteck abgleicht, ob sich das Paddle bewegt 
 *  hat.
 *
 */
public void testMakeMove()
	{
		Game game2 = new Game();
		Paddle paddle2 = new Paddle(game2, 1, 1);
		paddle2.makeMove(1);
		assertEquals(new Rectangle(1,2,10,40), paddle2.getArea());
	}

}
