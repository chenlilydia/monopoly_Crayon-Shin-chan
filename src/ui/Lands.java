package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.LandModel;


/**
 * Lands refreshment
 * 
 */
public class Lands extends Layer {

	/**
	 *	LandModel 
	 */
	private LandModel land = null;
	/**
	 * LandImage
	 */
	private  Image landsIMG = null;
	
	protected Lands(int x, int y, int w, int h,LandModel land) {
		super(x, y, w, h);
		this.land =land;
		this.landsIMG = this.land.getLandsIMG();
	}

	public void paint(Graphics g) {
		this.paintLands(g);
	}
	
	/**
	 * 
	 * PaintLands
	 * 
	 */
	private void paintLands(Graphics g) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < land.getLand().length; i++) {
			for (int j = 0; j < land.getLand()[i].length; j++) {
				if (land.getLand()[i][j] != 0) {
					g.drawImage(landsIMG, x + j * 60, y + i * 60, x
							+ (j + 1) * 60, y + (i + 1) * 60,
							(land.getLand()[i][j] - 1) * 60, 0, land.getLand()[i][j] * 60, 60, null);
				}
			}
		}
	}

	@Override
	public void startPanel() {
	}

}
