package model.buildings;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import context.GameState;
import control.GameRunning;


import model.buildings.Building;
import model.card.*;

/**
 * 
 * 
 * �̵�ģ��
 * 
 */
public class Shop_ extends Building {

	/**
	 * 
	 * �����Ʒ����
	 * 
	 */
	public static int MAXITEMSIZE = 6;
	/**
	 * 
	 * ��Ʒ����
	 * <p>
	 * ������� 9
	 * 
	 */
	private List<Card> cards = new ArrayList<Card>(MAXITEMSIZE);

	private GameRunning running = null;

	private Shop_ shopUI;

	public Shop_(int posX, int posY) {
		super(posX, posY);
		this.name = "Ұԭ��֮���ӻ���Ŷ~";
	}

	@Override
	public int getEvent() {
		return GameState.SHOP_EVENT;
	}

	/**
	 * 
	 * Ϊ�̵�Ļ��ܴ���������Ʒ
	 * 
	 */
	public void createCards() {
		// ��ջ���
		this.cards = new ArrayList<Card>(MAXITEMSIZE);
		// ����µ�card
		for (int i = 0; i < MAXITEMSIZE; i++) {
			int random = (int) (Math.random() * 5);
			switch (random) {
			case 0:
				cards.add(new AddLevelCard(null));
				break;
			case 1:
				cards.add(new AveragerPoorCard(null));
				break;
			case 2:
				cards.add(new ControlDiceCard(null));
				break;
			case 3:
				cards.add(new TortoiseCard(null));
				break;
			case 4:
				cards.add(new GoodLuckCard(null));
				break;
			}
		}
	}
	public List<Card> getCards() {
		return cards;
	}
}
