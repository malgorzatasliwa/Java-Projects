package com.games.pingpong;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * This class is a mouse and keyboard listener. It calculates ball and racket
 * movements, changes their coordinates.
 */
public class PingPongGameEngine implements Runnable, MouseMotionListener,
		KeyListener, GameConstants {

	private PingPongBlueTable table; 
	private int playerRacket_Y = PLAYER_RACKET_Y_START;
	private int computerRacket_Y = COMPUTER_RACKET_Y_START;
	private int playerScore;
	private int computerScore;
	private int ballX; // ball's X position
	private int ballY; // ball's Y position
	private boolean movingLeft = true;
	private volatile boolean ballServed = false;

	// Value in pixels of the vertical ball movement
	private int verticalSlide;
	Thread worker;

	// Constructor. Stores a reference to the table
	public PingPongGameEngine(PingPongBlueTable blueTable) {
		table = blueTable;
		worker = new Thread(this);
		worker.start(); // after start() program goes to run()
						
	}

	// Methods required by MouseMotionListener
	// interface (some of them are empty, but must be
	// included in the class anyway)

	public void mouseDragged(MouseEvent e) { // override
	}

	public void mouseMoved(MouseEvent e) { // override

		int mouse_Y = e.getY();

		// If a mouse is above the player's racket
		// and the racket did not go over the table top
		// move it up, otherwise move it down
		if (mouse_Y < playerRacket_Y && playerRacket_Y > TABLE_TOP) {
			playerRacket_Y -= RACKET_INCREMENT;
		} else if (playerRacket_Y < TABLE_BOTTOM) {
			playerRacket_Y += RACKET_INCREMENT;
		}

		// Set the new position of the racket on the table
		table.setPlayerRacket_Y(playerRacket_Y);
	}

	// Methods required by KeyListener interface
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();

		if ('n' == key || 'N' == key) {
			startNewGame();
		} else if ('q' == key || 'Q' == key) {
			endGame();
		} else if ('s' == key || 'S' == key) {
			playerServe();
		}
	}

	public void keyReleased(KeyEvent e) { //override

	}

	public void keyTyped(KeyEvent e) { //override
	}

	// Start a new Game
	public void startNewGame() {
		computerScore = 0;
		playerScore = 0;
		table.setMessageText("Score Computer: 0  Player: 0");
		playerServe();
	}

	// End the game
	public void endGame() {
		System.exit(0);
	}

	// Method run() is required by Runnable interface
	public void run() { //override

		boolean canBounce = false;
		while (true) { // main loop, after pressing S, the flag in line 100 changes to true

			if (ballServed) { // if ball is moving

				System.out.println("In run() 1");

				// Step 1. Is ball moving o the left?
				if (movingLeft && ballX > BALL_MIN_X) {

					canBounce = (ballY >= computerRacket_Y
							&& ballY < (computerRacket_Y + RACKET_LENGTH) ? true
							: false);
					ballX -= BALL_INCREMENT;

					// Add up or down slide to any left/right ball
					// movement
					ballY -= verticalSlide;

					table.setBallPosition(ballX, ballY);
					// Can bounce?
					if (ballX <= COMPUTER_RACKET_X && canBounce) {
						movingLeft = false;
					}
				}

				System.out.println("In run() 2");

				// Step 2. Is ball moving to the right?
				if (!movingLeft && ballX <= BALL_MAX_X) {
					canBounce = (ballY >= playerRacket_Y
							&& ballY < (playerRacket_Y + RACKET_LENGTH) ? true
							: false);

					ballX += BALL_INCREMENT;
					table.setBallPosition(ballX, ballY);
					// Can bounce?
					if (ballX >= PLAYER_RACKET_X && canBounce) {
						movingLeft = true;
					}
				}

				// Step 3. Move computer's racket up or down
				// to block the ball

				System.out.println("In run() 3");

				if (computerRacket_Y < ballY && computerRacket_Y < TABLE_BOTTOM) {
					computerRacket_Y += RACKET_INCREMENT;
				} else if (computerRacket_Y > TABLE_TOP) {
					computerRacket_Y -= RACKET_INCREMENT;
				}
				table.setComputerRacket_Y(computerRacket_Y);

				System.out.println("In run() 4");

				// Step 4. Sleep a little
				try {
					System.out.println("Sleeping for " + SLEEP_TIME);
					Thread.sleep(SLEEP_TIME);
					System.out.println("Woke up");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Step 5. Update the score if the ball is in the
				// blue area but is not moving
				if (isBallOnTheTable()) {
					if (ballX > BALL_MAX_X) {
						computerScore++;
						displayScore();
					} else if (ballX < BALL_MIN_X) {
						playerScore++;
						displayScore();
					} 
				} else {
					System.out.println("OUT");
					ballServed = false; // loop stops and you need to serve
				
						table.setMessageText( "Out! Serve Again");
								
					}
			} // End if ballServed
		} // End while
	}// End run()

	// Serve from the current position of the player's racket
	private void playerServe() {
		System.out.println("In playerServe() 1");
		ballServed = true;
		ballX = PLAYER_RACKET_X - 1;
		ballY = playerRacket_Y;

		if (ballY > TABLE_HEIGHT / 2) {
			verticalSlide = -1;
		} else {
			verticalSlide = 1;
		}

		table.setBallPosition(ballX, ballY);
		table.setPlayerRacket_Y(playerRacket_Y);
	}

	private void displayScore() {
		System.out.println("In displayScore() ");
		ballServed = false; // loop stops and toy need to serve

		if (computerScore == WINNING_SCORE) {
			table.setMessageText("Computer won! " + computerScore + ":"
					+ playerScore);
		} else if (playerScore == WINNING_SCORE) {
			table.setMessageText("You won! " + playerScore + ":" + computerScore);
		} else {
			table.setMessageText("Computer: " + computerScore + " Player: "
					+ playerScore);
		}
	}

	// check if ball did not cross the top or bottom
	// borders of the table
	private boolean isBallOnTheTable() {
		if (ballY >= BALL_MIN_Y && ballY <= BALL_MAX_Y) {
			return true;
		} else {
			return false;
		}
	}
}
