package Files;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;

public class MainPanel extends JPanel implements ActionListener{

	final static int WIDTH = 1200;
	final static int HEIGHT = 700;
	final static int BALL_SIZE = 60;
	final static int sqRADIUS = (BALL_SIZE/2)*(BALL_SIZE/2);
	
	int time = 0;
	int ballX = 30;
	int ballY = 40;
	int speed = 20;
	boolean xx = true;
	boolean yy = true;
	boolean running = false;
	boolean started = false;
	
	Timer timer;
	Random random;
	MainPanel(){
		timer = new Timer(1, this);
		random = new Random();
		startGame();
		
		this.addKeyListener(new MyKeyAdapter());
		this.addMouseListener(new MyMouseAdapter());
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	public void startGame() {
		
		ballX = random.nextInt(WIDTH - BALL_SIZE);
		ballY = random.nextInt(HEIGHT - BALL_SIZE);
		
		time = 0;
		if(!started) {
			timer.start();
			running = true;
			started = true;
		}else {
			running = true;
			timer.restart();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(new Font("calibri", Font.PLAIN, 30));
		g.drawString("Time : " + time, WIDTH/2 - 80, 20);
		
		if(running) {
						
			g.setFont(new Font("Cambria", Font.BOLD, 60));
			g.setColor(Color.GRAY);
			g.drawString("Catch the ball", WIDTH/2 - 200, HEIGHT/2 - 30);
			
			g.setColor(Color.RED);
			g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
		}else {
			g.setFont(new Font("Cambria", Font.BOLD, 60));
			g.setColor(Color.GREEN);
			g.drawString("You catched it!", WIDTH/2 - 200, HEIGHT/2-30);
			g.setFont(new Font("calibri", Font.ITALIC, 30));
			g.drawString("Press enter to play again", WIDTH/2 - 150, HEIGHT/2+40);
			repaint();
			timer.stop();
		}
	}
	public void changePosition() {
		if(xx) {
			ballX += 2*speed;
			if(ballX +BALL_SIZE >= WIDTH) {
				xx = false;
			}
		}else {
			ballX -= 2*speed;
			if(ballX <= 0) {
				xx = true;
			}
		}
		if(yy) {
			ballY += speed;
			if(ballY +BALL_SIZE >= HEIGHT) {
				yy = false;
			}
		}else {
			ballY -= speed;
			if(ballY <= 0) {
				yy = true;
			}
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(running) {
			changePosition();
			time += 1;
		}
		repaint();
	}
	
	class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			x = (ballX + BALL_SIZE/2) - x; // Getting position with respect to origin
			y = (ballY + BALL_SIZE/2) - y; // Getting position with respect to origin
			
			if((x*x + y*y) <= sqRADIUS) {
				running = false;
			}
		}
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(running == false && e.getKeyCode() == KeyEvent.VK_ENTER) {
				startGame();
			}
		}
	}
	
}
