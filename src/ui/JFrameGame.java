package ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import util.FrameUtil;

@SuppressWarnings("serial")
public class JFrameGame extends JFrame {

	/**
	 * 
	 * Main panel
	 * 
	 * */

	private JPanelGame panelGame = null;
	
	public JFrameGame() {
		/* title setting */
		this.setTitle("Happy Monopoly Game!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/* window size */
		this.setSize(750 + 200, 650);
		this.setResizable(false);
		FrameUtil.setFrameCenter(this);
		this.panelGame = new JPanelGame();
		add(this.panelGame);
		this.setUndecorated(true);
	}

	public JPanelGame getPanelGame() {
		return panelGame;
	}
}
