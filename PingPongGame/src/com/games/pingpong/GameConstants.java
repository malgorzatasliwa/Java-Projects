package com.games.pingpong;

/**
 * This interface contains all definitions of the final variables that are used
 * in the game
 */
public interface GameConstants {
	// Size of the ping pong table
	public final int TABLE_WIDTH = 320; //blue field 
	public final int TABLE_HEIGHT = 220; // blue field
	public final int TABLE_TOP = 12; // upper range of the racket
	public final int TABLE_BOTTOM = 180; // lower range of the racket
	
	// Margin of the frame
	public final int FRAME_WIDTH_MARGIN = 25;
	public final int FRAME_HEIGHT_MARGIN = 70;

	// Ball movement increment in pixels
	public final int BALL_INCREMENT = 4; 

	// Maximum and minimum allowed ball coordinates
	public final int BALL_MIN_X = 1 + BALL_INCREMENT; 
	public final int BALL_MIN_Y = 1 + BALL_INCREMENT; 
	public final int BALL_MAX_X = TABLE_WIDTH - BALL_INCREMENT; 
	public final int BALL_MAX_Y = TABLE_HEIGHT - BALL_INCREMENT; 

	// Starting coordinates of the ball
	public final int BALL_START_X = TABLE_WIDTH / 2; 
	public final int BALL_START_Y = TABLE_HEIGHT / 2; 

	// Rackets' sizes, positions and movement increments
	public final int PLAYER_RACKET_X = 300; 
	public final int PLAYER_RACKET_Y_START = 100; 
	public final int COMPUTER_RACKET_X = 15;
	public final int COMPUTER_RACKET_Y_START = 100;
	public final int RACKET_INCREMENT = 2; 
	public final int RACKET_LENGTH = 30;
	public final int RACKET_WIDTH = 5;

	public final int WINNING_SCORE = 3;

	// Slow down fast computers - change the value if needed
	public final int SLEEP_TIME = 50; // time in milliseconds

}
