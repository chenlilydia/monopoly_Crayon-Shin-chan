package model.card;

import javax.swing.JOptionPane;

import context.GameState;

import model.PlayerModel;

/**
 * 
 * �ڹ꿨,�Զ��ֻ��Լ�ʹ���ڹ꿨�����ö��ֻ��Լ���������ֻ��һ����
 *
 * 
 */
public class TortoiseCard extends Card {

	private int life = 3;

	public TortoiseCard(PlayerModel owner) {
		super(owner);
		this.name = "TortoiseCard";
		this.cName = "�ڹ꿨";
		this.price = 50;
	}

	@Override
	public int useCard() {
		return GameState.CARD_TORTOISE;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * 
	 * ��Ƭ����Ч��
	 * 
	 */
	@Override
	public int cardBuff() {
		return GameState.CARD_BUFF_TORTOISE;
	}
}
