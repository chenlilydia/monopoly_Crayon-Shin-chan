package model.card;

import javax.swing.JOptionPane;

import context.GameState;

import model.PlayerModel;

/**
 * 
 * 
 * 遥控骰子,使用遥控骰子，可以自由控制下一次骰子点数。
 *
 */
public class ControlDiceCard extends Card{

	int diceNumber;
	
	public ControlDiceCard(PlayerModel owner) {
		super(owner);
		this.name = "ControlDiceCard";
		this.cName = "遥控骰子卡";
		this.price = 30;
	}

	@Override
	public int useCard() {
		return GameState.CARD_CONTROLDICE;
	}
}
