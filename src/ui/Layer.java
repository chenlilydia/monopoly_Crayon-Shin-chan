package ui;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Image;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import util.FileUtil;

public abstract class Layer extends JPanel{
	protected int x;
	protected int y;
	protected int w;//width
	protected int h;//height

	protected static final int PADDING = 5;
	protected static final int SIZE = 2;
	protected static Image WINDOW_IMG = new ImageIcon("images/window/window.png").getImage();
	protected static int WINDOW_W = WINDOW_IMG.getWidth(null);
	protected static int WINDOW_H = WINDOW_IMG.getHeight(null);

	protected Layer(int x, int y, int w, int h) {
		this.setBounds(x, y, w, h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void setLocation (int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public void createWindow(Graphics g) {
		float alpha = 0.2f;
		//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha)); 
		g.drawImage(WINDOW_IMG, 0, 0, 0 + SIZE, 0 + SIZE, 0, 0, SIZE, SIZE,
				null);
		g.drawImage(WINDOW_IMG, 0 + SIZE, 0, 0 + w - SIZE, 0 + SIZE, SIZE, 0,
				WINDOW_W - SIZE, SIZE, null);
		g.drawImage(WINDOW_IMG, 0 + w - SIZE, 0, 0 + w, 0 + SIZE, WINDOW_W
				- SIZE, 0, WINDOW_W, SIZE, null);
		g.drawImage(WINDOW_IMG, 0, 0 + SIZE, 0 + SIZE, 0 + h - SIZE, 0, SIZE,
				SIZE, WINDOW_H - SIZE, null);
		g.drawImage(WINDOW_IMG, 0 + SIZE, 0 + SIZE, 0 + w - SIZE, 0 + h - SIZE,
				SIZE, SIZE, WINDOW_W - SIZE, WINDOW_H - SIZE, null);
		g.drawImage(WINDOW_IMG, 0 + w - SIZE, 0 + SIZE, 0 + w, 0 + h - SIZE,
				WINDOW_W - SIZE, SIZE, WINDOW_W, WINDOW_H - SIZE, null);
		g.drawImage(WINDOW_IMG, 0, 0 + h - SIZE, 0 + SIZE, 0 + h, 0, WINDOW_H
				- SIZE, SIZE, WINDOW_H, null);
		g.drawImage(WINDOW_IMG, 0 + SIZE, 0 + h - SIZE, 0 + w - SIZE, 0 + h,
				SIZE, 50 - SIZE, WINDOW_W - SIZE, WINDOW_H, null);
		g.drawImage(WINDOW_IMG, 0 + w - SIZE, 0 + h - SIZE, 0 + w, 0 + h,
				WINDOW_W - SIZE, WINDOW_H - SIZE, WINDOW_W, WINDOW_H, null);
	}
	
	abstract public void paint(Graphics g);
	/**
	 * start game panel settings
	 */
	abstract public void startPanel();
}
