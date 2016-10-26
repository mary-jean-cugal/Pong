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

public class Ball {
	private static final int DIAMETER = 30;
	int x = 0; //initial position x of the ball
	int y = 30;//initial position y of the ball
	int xa = 1;//to be used to add to the speed of the ball
	int ya = 1;//to be used to add to the speed of the ball
        int edgeDown = 330;//the edge of or the last x that appear in the frame
        int edgeUp = 0;//edge on the upper portion of the canvass
	private Game game; //declaring an "instance" game
        int score1 = 0;
        int score2 = 0;

	public Ball(Game game) { //constructor
		this.game= game;
	}
        
	void move() {
		boolean changeDirection = true;
		if (x + xa < edgeUp)//if the ball is about to go beyond the declared width of the frame
			xa = game.getSpeed(); //this allows to ball to move in an increasing value of x along
                                                //the screen also in a changeable speed
		else if (x + xa > game.getWidth() - DIAMETER)//checks if the ball is about to go beyond the alloted width of frame
			xa = -game.getSpeed();//this allows to ball to move in an increasing value of x along
                                                //the screen also in a changeable speed
                else if (y + ya > edgeDown){//checks if the ball is about to go beyond the alloted height of frame
                    ya = -game.getSpeed();//behaves the same way as the xa behave
                    if(y == edgeDown)
                        game.score2++;
                }else if (y + ya < edgeUp){//checks if the ball is about to go beyond the alloted height of frame
			ya = game.getSpeed();
                        if ((y == edgeUp) && !collisionY2()){ //if the ball bounces to the place of the player, the pther player scores
                            System.out.println("yeyeyey");
                            game.score1++;
                        }if (y == game.getHeight() && !collisionY1())
                            game.score2++;
                }else if (game.score1 == 3 || game.score2 == 3)//stops the game
			game.gameOver();
		else if (collisionY1() && !collisionY2()){ //adds score to the racquet that has been bounced by the ball
                        game.speed++;//adds the speed in every collision or if the ball is int he lower or upper edge
                        game.score1++;
			ya = -game.getSpeed();
			y = game.racquet.getTopY1() - DIAMETER;
                }else if (collisionY2() && !collisionY1()){
                        game.score2++;
                        game.speed++;
			ya = game.getSpeed();
			y = game.racquet.getTopY2() + DIAMETER;	
		} else 
			changeDirection = false;
		if (changeDirection)
                    Sound.BALL.play();
		x = x + xa;
		y = y + ya;
	}

	private boolean collisionY1() {//checks if the ball and the racquet collides
		return game.racquet.getBoundsY1().intersects(getBounds());
	}
        private boolean collisionY2() {
		return game.racquet.getBoundsY2().intersects(getBounds());
	}
         
	public void paint(Graphics2D g) {//creates a 2d onject that is to be filled once there is a color
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() { //gets the dimensions of the ball enclosed in a small square;
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

}