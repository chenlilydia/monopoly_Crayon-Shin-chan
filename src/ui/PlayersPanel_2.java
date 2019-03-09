package ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.List;

import model.PlayerModel;
/**
 * 
 * Player2��Ϣ���ˢ��
 */

public class PlayersPanel_2 extends Layer {

	private List<PlayerModel> players = null;

	protected PlayersPanel_2(int x, int y, int w, int h, List<PlayerModel> players) {
		super(x, y, w, h);
		this.players = players;
	}

	/**
	 * 
	 * �����Ϣ��ʾ������
	 * 
	 */
	public void paintPlayerInformation(Graphics g) {
		int tempX = 80;
		paintPlayerPanel(players.get(1), g, tempX, 15);
	}

	/**
	 * 
	 * �����Ϣ������
	 * 
	 */
	private void paintPlayerPanel(PlayerModel player, Graphics g, int x,int y) {
		// �����Ϣ�ַ���
		String[] information = { player.getName(),
				Integer.toString(player.getCash()) + " Ԫ",
				//Integer.toString(player.getNx()) + " ��ȯ",
				Integer.toString(player.getBuildings().size()) + " С����"
				//Integer.toString(player.getCards().size()) + "��Ƭ" 
				};
		// ��ȡplayerͷ��
		g.drawImage(player.getIMG("mini_02"), x -26 + 15 , y - 10, x -26 + 15 +player.getIMG("mini_02").getWidth(null) ,
				 y - 10 +player
					.getIMG("mini_02").getHeight(null) , 0, 0, player.getIMG("mini_02").getWidth(null), player
						.getIMG("mini_02").getHeight(null), null);
		y += 70;
		//������ɫ 
		g.setColor(Color.DARK_GRAY);
		//����
		g.setFont(new Font(null,Font.BOLD,18));
		// ��Ϣ�ػ�
		FontMetrics fm = g.getFontMetrics();
		for (int k = 0; k < information.length; g.drawString(information[k], x
				+ (45 - fm.stringWidth(information[k])), y += 30), k++)
			;

	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		// �����Ϣ��ʾ����ػ�
		this.paintPlayerInformation(g);
		
	}

	@Override
	public void startPanel() {
	}


}
