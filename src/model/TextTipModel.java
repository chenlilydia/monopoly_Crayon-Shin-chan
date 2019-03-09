package model;

import java.awt.Color;
import java.awt.Graphics;

import ui.JPanelGame;
import ui.TextTip;
import control.Control;

/**
 * 
 * ������ʾ����
 * 
 */
public class TextTipModel extends Tick implements Port{
	
	private PlayerModel player = null;
	
	private String tipString = "GAME BEGINS!";
	
	public TextTipModel (){
	}

	public  String getTipString() {
		return tipString;
	}

	public void setTipString(String tipString) {
		this.tipString = tipString;
	}
	
	
	/**
	 * 
	 * ��ʼ��Ϸ����
	 * 
	 */
	public void startGameInit (){}

	@Override
	public void updata(long tick) {
		this.nowTick = tick;
	}

	
	public PlayerModel getPlayer() {
		return player;
	}

	/**
	 * ��ʾ������ʾ
	 * 
	 * 
	 * */
	public void showTextTip(PlayerModel player,String str, int time) {
		this.player = player;
		this.setTipString(str);
		this.setStartTick(this.nowTick);
		this.setNextTick(this.nowTick + time * Control.rate);
	}
}
