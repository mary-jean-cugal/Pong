/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp4;

/**
 *
 * @author Mary Jean Cugal
 */
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.ABORT;

public class Racquet {
         
private static final int Y = 350;
private static final int Y2 = 0;
	private static final int WITH = 60;
	private static final int HEIGHT = 10;
	int x = 0;
        int x1 = 0;
	int xa = 0;
        int xa2 = 0;
	private Game game;

	public Racquet(Game game) {
		this.game = game;
	}

	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WITH) //applies to the first racquet
                                                                    //allows the racquet to move
			x = x + xa;
                if (x1 + xa2 > 0 && x1 + xa2 < game.getWidth() - WITH)//applies to the second racquet
			x1 = x1 + xa2;                              //allows the racquet to move
                
	}
        

	public void paint(Graphics2D g) {
		g.fillRect(x, Y, WITH, HEIGHT); //clears the last move of the racquet 1
                g.fillRect(x1, Y2, WITH, HEIGHT); //clears the last move og the racquet2
	}

	public void keyReleased(KeyEvent e) {
		xa = 0; //initialize xa and xa2 to zero
                xa2 = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) //checks if the key pressed is left arrow key
			xa = -game.getSpeed(); //assigns the xa in a negative answer to be used on how the ball moves 
                                                
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)//checks if the key pressed is left arrow key
			xa = game.getSpeed();
                if (e.getKeyCode() == KeyEvent.VK_Z)//checks if the key pressed is left arrow key
			xa2 = -game.getSpeed();
                if (e.getKeyCode() == KeyEvent.VK_X)//checks if the key pressed is left arrow key
			xa2 = game.getSpeed();
	}
//        getters and setters;

	public Rectangle getBoundsY1() {
		return new Rectangle(x, Y, WITH, HEIGHT);
	}
        public Rectangle getBoundsY2() {
		return new Rectangle(x1, Y2, WITH, HEIGHT);
	}

	public int getTopY1() {
		return Y - HEIGHT;
	}
        public int getTopY2() { //return the spot where the
		return Y2 - HEIGHT;
	}
}
