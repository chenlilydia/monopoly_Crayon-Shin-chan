package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import control.GameRunning;

import model.EffectModel;

public class Effect extends Layer {

	private EffectModel effect = null;
	/**
	 * 
	 * panel center
	 * 
	 */
	private Point middle = new Point((x + w) / 2, (y + h) / 2);
	private JPanelGame panel;
	protected Effect(int x, int y, int w, int h, EffectModel effect,JPanelGame panel) {
		super(x, y, w, h);
		this.effect = effect;
		this.panel = panel;
	}
	/* move to lowest */
	public void moveToBack() {
		this.panel.getLayeredPane().moveToBack(this);
	}
	/* move to highest */
	public void moveToFront() {
		this.panel.getLayeredPane().moveToFront(this);
	}
	
	@Override
	public void paint(Graphics g) {
		if (effect.getStartTick() < effect.getNowTick()
				&& effect.getNextTick() >= effect.getNowTick()) {
			int pos = (int) ((effect.getNowTick() - effect.getStartTick()) / effect
					.getImageShowGap());
			if (pos < effect.getImg().length) {
				Image tempIMG = effect.getImg()[pos];
				Point imgGiddle = new Point(tempIMG.getWidth(null) / 2,
						tempIMG.getHeight(null) / 2);
				Point position = new Point(middle.x - imgGiddle.x, middle.y
						- imgGiddle.y);
				g.drawImage(tempIMG, position.x, position.y, position.x
						+ tempIMG.getWidth(null),
						position.y + tempIMG.getHeight(null), 0, 0,
						tempIMG.getWidth(null), tempIMG.getHeight(null), null);
			}
		}
	}

	@Override
	public void startPanel() {

	}

}
