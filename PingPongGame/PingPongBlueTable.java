package com.games.pingpong;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;

/**
 * This class paints the blue ping pong table, ball, rackets and displays the
 * score
 */
public class PingPongBlueTable extends JPanel implements GameConstants {
	
	private JLabel label;

	private int computerRacket_Y = COMPUTER_RACKET_Y_START; 
	private int playerRacket_Y = PLAYER_RACKET_Y_START;
	private int ballX = BALL_START_X;
	private int ballY = BALL_START_Y;

	// czemu nie private?
	Dimension preferredSize = new Dimension(TABLE_WIDTH, TABLE_HEIGHT);

	// This method sets the size of the frame.
	// It's called by JVM
	public Dimension getPreferredSize() {
		return preferredSize; 
	}

	// Constructor. Creates a listener for mouse events
	PingPongBlueTable() {
		// pamietaj o finalach
		PingPongGameEngine gameEngine = new PingPongGameEngine(this);
		// Listen to mouse movements to move the rackets
		addMouseMotionListener(gameEngine);
		// Listen to the keyboard events
		addKeyListener(gameEngine);
	}

	// Add a panel with a JLabel to the frame
	void addPaneltoFrame(Container container) {
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(this);
		label = new JLabel("Press N for a new game, S to serve or Q to quit");
		container.add(label);
	}

	// to jest na pewno w documentacji jdk ;) A jesli chcesz tego typu koment, to lepiej chyba javadoc, czylo /** tekst */
	// repaint the window. This method is called by JVM
	// when it needs to refresh the screen or when a
	// method repaint() is called from PingPointGameEngine
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		drawTable(g);
		
		drawPlayerRacket(g);
		
		drawComputerRacket(g);		
		
		drawBall(g);
		
		drawWhiteLine(g);
		
		// Set the focus to the table, so the key
		// listener will send commands to the table
		requestFocus();
	}

	private void drawTable(Graphics g) {
		g.setColor(Color.CYAN);
		// paint the table cyan
		g.fillRect(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
	}
	private void drawPlayerRacket(Graphics g) {
		g.setColor(Color.magenta);
		// paint the player's racket
		g.fillRect(PLAYER_RACKET_X, playerRacket_Y, RACKET_WIDTH, RACKET_LENGTH);
		
	}	
	private void drawComputerRacket(Graphics g) {
		g.setColor(Color.blue);
		// paint the computer's racket
		g.fillRect(COMPUTER_RACKET_X, computerRacket_Y, RACKET_WIDTH,
				RACKET_LENGTH);
	}
	private void drawWhiteLine(Graphics g) {
		// draw the white lines
		g.setColor(Color.white);
		g.drawRect(10, 10, 300, 200);
		g.drawLine(160, 10, 160, 210);
	}
	private void drawBall(Graphics g) {
		// paint the ball
		g.setColor(Color.red);
		g.fillOval(ballX - 5, ballY - 5, 10, 10);
	}
	// Set the current position of player's racket
	public void setPlayerRacket_Y(int yCoordinate) { 
		this.playerRacket_Y = yCoordinate;
		repaint();
	}
	// Return current posiition of player's racket
	public int getPlayerRacket_Y() {
		return playerRacket_Y;
	}
	// Set the current position of the computer's racket
	public void setComputerRacket_Y(int yCoordinate) {
		this.computerRacket_Y = yCoordinate;
		repaint();
	}
	// Set the game's message
	public void setMessageText(String text) {
		label.setText(text);
		repaint();
	}
	// Set the game's message
	public void setBallPosition(int xPos, int yPos) {
		ballX = xPos;
		ballY = yPos;
		repaint();
	}
	public static void main(String[] args) {

		// Create an instance of the frame // wiem, powtarzam sie ;) Ale ten koment jest zbedny. Natomiast nigdy nie nazywaj zmiennych jak ponizej.
		JFrame f = new JFrame("Ping Pong Blue Table"); // bo za kilkadziesiat linii trzeba bedzie rozkminiac, czym jest f;)

		// Ensure that the window can be closed
		// by pressing a little cross in the corner
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		PingPongBlueTable table = new PingPongBlueTable();
		table.addPaneltoFrame(f.getContentPane());

		// Set the frame's size and make it visible
		f.setBounds(0, 0, TABLE_WIDTH + FRAME_WIDTH_MARGIN, TABLE_HEIGHT + FRAME_HEIGHT_MARGIN);
		f.setVisible(true);
	}
}
