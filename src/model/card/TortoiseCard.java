package model.card;

import javax.swing.JOptionPane;

import context.GameState;

import model.PlayerModel;

/**
 * 
 * 乌龟卡,对对手或自己使用乌龟卡，会让对手或自己连续三次只走一步。
 *
 * 
 */
public class TortoiseCard extends Card {

	private int life = 3;

	public TortoiseCard(PlayerModel owner) {
		super(owner);
		this.name = "TortoiseCard";
		this.cName = "乌龟卡";
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
	 * 卡片持续效果
	 * 
	 */
	@Override
	public int cardBuff() {
		return GameState.CARD_BUFF_TORTOISE;
	}
}
