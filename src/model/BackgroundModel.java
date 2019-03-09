package model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import control.GameRunning;

import ui.JPanelGame;
import util.FileUtil;

/**
 * 
 * Background Model
 *
 */
public class BackgroundModel extends Tick implements Port{
	/**
	 * back ground image
	 */
	private Image bg = null;
	public BackgroundModel (){
	}
	
	public Image getBg() {
		return bg;
	}
	
	public void setBg(Image bg) {
		this.bg = bg;
	}

	/**
	 * 
	 * start Game Initialization
	 * 
	 */
	public void startGameInit (){
		this.bg = new ImageIcon("images/background/bg_0"+GameRunning.MAP+".jpg").getImage();
	}
	@Override
	public void updata(long tick) {
		this.nowTick = tick;
	}
}
