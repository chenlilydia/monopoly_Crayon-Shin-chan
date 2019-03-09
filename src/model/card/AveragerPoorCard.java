package model.card;

import javax.swing.JOptionPane;

import context.GameState;

import model.PlayerModel;

/**
 * 
 * 有福同享卡，可以与选中玩家平分现金。
 * 
 */
public class AveragerPoorCard extends Card {

	public AveragerPoorCard(PlayerModel owner) {
		super(owner);
		this.name = "AveragerPoorCard";
		this.cName = "有福同享卡";
		this.price = 100;
	}

	@Override
	public int useCard() {
		return GameState.CARD_AVERAGERPOOR;
	}

}
