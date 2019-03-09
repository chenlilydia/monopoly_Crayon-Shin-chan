package ui;

import java.awt.Graphics;
import java.awt.Image;

import util.FileUtil;

import model.BackgroundModel;


/**
 * 
 * Background layer
 * 
 */
public class Background extends Layer {

	private Image backg = null;
	private BackgroundModel background = null;
	private JPanelGame panel;
	protected Background(int x, int y, int w, int h,
			BackgroundModel background,JPanelGame panel) {
		super(x, y, w, h);
		this.background = background;
		this.panel = panel;
	}

	public void paint(Graphics g) {
		this.paintBg(g);
	}
	public void moveToBack() {
		/* move to lowest layer */
		this.panel.getLayeredPane().moveToBack(this);
	}
	public void moveToFront() {
		/* move to highest layer */
		this.panel.getLayeredPane().moveToFront(this);
	}
	
	public void paintBg(Graphics g){
		g.drawImage(this.backg, 0, 0, this.backg.getWidth(null),
				this.backg.getHeight(null), 0, 0, this.backg.getWidth(null),
				this.backg.getHeight(null), null);
	}

	@Override
	public void startPanel() {
		this.backg = background.getBg();
	}

}
