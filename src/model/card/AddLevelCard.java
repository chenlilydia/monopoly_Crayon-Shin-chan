package model.card;

import javax.swing.JOptionPane;

import context.GameState;

import model.PlayerModel;
import model.buildings.Building;

/**
 * 
 * 加盖卡，可以使当前房屋加盖一层
 * 
 */
public class AddLevelCard extends Card {

	public AddLevelCard(PlayerModel owner) {
		super(owner);
		this.name = "AddLevelCard";
		this.cName = "加盖卡";
		this.price = 30;

	}

	@Override
	public int useCard() {
		return GameState.CARD_ADDLEVEL;
	}

}
