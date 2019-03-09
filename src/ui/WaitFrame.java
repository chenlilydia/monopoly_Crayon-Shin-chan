package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import util.FrameUtil;

/**
 * 
 * Wait Frame
 * 
 * */

public class WaitFrame extends JFrame {

	public WaitFrame() {
		/* title setting */
		this.setTitle("Happy Monopoly Game!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,100);
		this.setResizable(false);
		FrameUtil.setFrameCenter(this);
		add(new JLabel("LOADING ... ...",JLabel.CENTER));
		setVisible(true);
	}

}
