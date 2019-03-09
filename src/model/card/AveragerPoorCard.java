package model.card;

import javax.swing.JOptionPane;

import context.GameState;

import model.PlayerModel;

/**
 * 
 * �и�ͬ����������ѡ�����ƽ���ֽ�
 * 
 */
public class AveragerPoorCard extends Card {

	public AveragerPoorCard(PlayerModel owner) {
		super(owner);
		this.name = "AveragerPoorCard";
		this.cName = "�и�ͬ��";
		this.price = 100;
	}

	@Override
	public int useCard() {
		return GameState.CARD_AVERAGERPOOR;
	}

}
