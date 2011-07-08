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

public void testSetLocation()
	{
		Game game2 = new Game();
		Paddle paddle3 = new Paddle(game2, 0, 0);
		paddle3.setPosition(1, 1);
		assertEquals(new Rectangle(1,1,10,40), paddle3.getArea());
	}

public void testMakeMove()
	{
		Game game2 = new Game();
		Paddle paddle2 = new Paddle(game2, 1, 1);
		paddle2.makeMove(1);
		assertEquals(new Rectangle(1,2,10,40), paddle2.getArea());
	}

}
