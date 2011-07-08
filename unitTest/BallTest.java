package unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class BallTest {

	@Test
	public final void testBall() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetArea() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testMakeMove() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRestart() {
		fail("Not yet implemented"); // TODO
	}
public void testStartNew()
	{
		Game game1 = new Game();
		Ball ball1 = new Ball(game1, 1, 1);
		ball1.restart(200, 200);
		assertEquals(new Rectangle(200,200,10,10), ball1.getArea());
	}

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
