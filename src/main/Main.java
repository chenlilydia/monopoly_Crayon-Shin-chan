package main;

import javax.swing.JFrame;
import javax.swing.UIManager;

import ui.JFrameGame;
import ui.WaitFrame;
import ui.config.FrameConfig;

public class Main {

	static {
		/* ������ʽ */
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			/* ʹ��JTattoƤ���� */
			//UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e1) {
			
		}
	}

	public static void main(String[] args) {
		/* �����ȴ����� */
		WaitFrame wFrame = new WaitFrame();
		/* ������Ϸ������ */
		JFrameGame frame = new JFrameGame();
		/* ������Ϸ���ô��� */
		new FrameConfig(wFrame,frame);
	}
}
