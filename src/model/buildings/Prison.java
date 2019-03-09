package model.buildings;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import model.LandModel;
import model.PlayerModel;
import model.TextTipModel;

import context.GameState;
import control.Control;

/**
 * 
 * 监狱 玩家到这里可以入狱或者发生其他事件
 * 
 * 
 */
public class Prison extends Building {
	private PlayerModel player;
	/*
	 * 
	 * 事件图片
	 */
	private Image[] imgageEvents = { EVENT_PRISON_1, EVENT_PRISON_2, EVENT_PRISON_3};
	
	/**
	 * 事件图片
	*/
	
	public static Image EVENT_PRISON_1 = new ImageIcon("images/event/prison1.png")
			.getImage();
	/**
	 * 事件图片
	*/
	
	public static Image EVENT_PRISON_2 = new ImageIcon("images/event/prison2.png")
			.getImage();
	/**
	 * 事件图片
	*/
	
	public static Image EVENT_PRISON_3 = new ImageIcon("images/event/prison3.png")
			.getImage();
    
    /*
	private String[] events = { "去监狱看望美冴了哦~ ", "被风间冤枉入狱了哦~ ", "被监狱管理员松阪老师抓去打扫卫生了哦~ " };
	*/

	public Prison(int posX, int posY) {
		super(posX, posY);
		this.name = "监狱";
	}
	
	public Image[] getImgageEvents() {
		return imgageEvents;
	}
	/*
	public String[] getEvents() {
		return events;
	}
	*/

	@Override
	public int getEvent() {
		return GameState.PRISON_EVENT;
	}
}
