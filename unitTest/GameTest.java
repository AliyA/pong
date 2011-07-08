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
public class GameTest {

public void testGameBounds()
    {
        Game game1 = new Game();
        assertEquals(new Rectangle(0,0,500,500), game1.getBounds());
    }



public void testLevelAtGameStart()
    {
        Game game1 = new Game();
        assertEquals(1, game1.getLevel());
    }


public void testSetLevel()
    {
        Game game1 = new Game();
        game1.setLevel(5);
        assertEquals(5, game1.getLevel());
    }

public void testSetMoveplayerOneAndTick()
    {
        Game game1 = new Game();
        game1.setMoveplayer(1);
        game1.tick();
        assertEquals(new Rectangle(480,231,10,40), game1.getPlayer().getArea());
    }
    
public void testSetMoveplayerMinusOneAndTick()
    {
        Game game1 = new Game();
        game1.setMoveplayer(-1);
        game1.tick();
        assertEquals(new Rectangle(480,229,10,40), game1.getPlayer().getArea());
    }

public void testBallPosition()
    {
        Game game1 = new Game();
        assertEquals(new Rectangle(30,245,10,10), game1.getBall().getArea());
    }

public void testPaddlePlayerPosition()
    {
        Game game1 = new Game();
        assertEquals(new Rectangle(480,230,10,40), game1.getPlayer().getArea());
    }
    
public void testIsWithinBounds1()
    {
        Game game1 = new Game();
        Paddle testpaddle = new Paddle(game1,0,0);
        testpaddle.setPosition(-1,0);
        assertEquals(1, game1.isWithinBounds(testpaddle.getArea()));
        
    }
    
public void testIsWithinBounds2()
    {
        Game game1 = new Game();
        Paddle testpaddle = new Paddle(game1,0,0);
        testpaddle.setPosition(0,-1);
        assertEquals(2, game1.isWithinBounds(testpaddle.getArea()));
        
    }
public void testIsWithinBounds3()
    {
        Game game1 = new Game();
        Paddle testpaddle = new Paddle(game1,0,0);
        testpaddle.setPosition(501,0);
        assertEquals(3, game1.isWithinBounds(testpaddle.getArea()));
        
    }
public void testIsWithinBounds4()
    {
        Game game1 = new Game();
        Paddle testpaddle = new Paddle(game1,0,0);
        testpaddle.setPosition(0,501);
        assertEquals(4, game1.isWithinBounds(testpaddle.getArea()));
        
    }  
public void testIsWithinBounds5()
    {
        Game game1 = new Game();
        Paddle testpaddle = new Paddle(game1,0,0);
        testpaddle.setPosition(232,400);
        assertEquals(0, game1.isWithinBounds(testpaddle.getArea()));
        
    }   

}
