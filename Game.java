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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
        int score1; // score for the player1
        int score2;//score for the player2
	Ball ball = new Ball(this); //creating an instance of Ball class
	Racquet racquet = new Racquet(this); //creating an instance of Racquet class
	int speed = 1; //setting the initial value of speed to 1
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		}); //creating the anonymous class addkeyListener where all the behaviors of the keys take place
		setFocusable(true); //focuses the command to specified keys which 
//                                     in turn performs the commands once the keys are clicked... not sure though :[
                Sound.BACK.loop(); //continues to play the sound until the Game ends;

	}

	private void move() { //allows the ball and and the racquets to move
//                              when this method is called in the main method
		ball.move(); //method move() from the ball is called
		racquet.move();//method move() from the racquet class is called
	}
        public int getSpeed(){//returns the current speed;
            return speed;
        }
        
	@Override
	public void paint(Graphics g) {
		super.paint(g); // calls the method paint from the superclass that continuosly cleans the 
                                //"canvas" as the ball's x and y changes 
		Graphics2D g2d = (Graphics2D) g; //casts the Graphics to Graphics2d
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON); //allows the objects drawn to have a smoother form
                g2d.setColor(Color.BLUE); //sets the color of the "to-be" drawn shape to blue
		ball.paint(g2d);//calls the paint method in paint filling the color of the ball to blue
                g2d.setColor(Color.GREEN);   //resets the color of the "to-be" drawn shape to green
		racquet.paint(g2d); //calls the paint method in paint filling the color of the racquets to green
		g2d.setColor(Color.BLUE);//again resets the color of the "to-be" drawn shape to blue which is applied to the String Drawn
		g2d.setFont(new Font("Verdana", Font.BOLD, 20));//sets the font of the String to drawn
		g2d.drawString("Player 2:" + String.valueOf(score2), 10, 30); //draws a new string
                g2d.drawString("Player 1: " +String.valueOf(score1), 10, 330);
	}
        //int restart = 1;
        public void newGame(){//creates the option to choose to restart game or not
            int x = JOptionPane.showConfirmDialog(this, "Great JOB!",
		"continue the Game?", JOptionPane.YES_NO_OPTION); //displays the box to choose from
                                                                  //if the user clicks the no button, its value is 1
                                                                  //if the user clicks the yes button, the value is 0
            if(x == 1){ // 
                System.exit(ABORT);
            }else if (x == 0){
                speed = 1;
                score1 = 0;
                score2 = 0;
            }
        }
        
	public void gameOver() { //displayed when one of the players gets 3 points
            Sound.BACK.stop();//stops the Back sound
            Sound.GAMEOVER.play(); //allows the sound for gameOver to be heard
            String win = "";
            if(score1 > score2) // command to choose whether who wins the game based on the scores
                win = "player1";
            else{
                win = "player2";
            }
            JOptionPane.showMessageDialog(this, "the winner is " + win,
		"Game Over", JOptionPane.YES_NO_OPTION); //displays an box that announces the winner
            newGame(); // calls the newGame() method which determines allows the user to choose whether
                        //to restart the game or to end it
        } 

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Pong"); //creates a frame named "Pong"
		Game game = new Game(); //initializes the class game
		frame.add(game); //allows the behaviors of the game to be placed on the frame
		frame.setSize(300, 400); //sets the size  length = 300, width = 400
		frame.setVisible(true); //allows the frame to be visible, if this is removed, we cannot see the frame nor the actions taking plce there
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//tells the program to close once the x button has been pressed
		while (true) {
			game.move();//the move function is continuosly called while the program is not yet stopped
			game.repaint(); // tells the awt program to run the paint() method as soon as possible since the paint() method is 
			Thread.sleep(10);//allows the canvas to be seen and run since the main thread takes control in the entire system and ignore the eventQueue thread
                                           //methods that will not implemented if thread.sleep() is not called 
                                           //this tells the main thread to stop for 10 ms that allows the eventQueue thread to execute
		}
	}
}
    
