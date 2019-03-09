package main;

import javax.swing.JFrame;
import javax.swing.UIManager;

import ui.JFrameGame;
import ui.WaitFrame;
import ui.config.FrameConfig;

public class Main {

	static {
		/* 设置样式 */
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			/* 使用JTatto皮肤包 */
			//UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e1) {
			
		}
	}

	public static void main(String[] args) {
		/* 建立等待界面 */
		WaitFrame wFrame = new WaitFrame();
		/* 建立游戏主窗口 */
		JFrameGame frame = new JFrameGame();
		/* 建立游戏配置窗口 */
		new FrameConfig(wFrame,frame);
	}
}
