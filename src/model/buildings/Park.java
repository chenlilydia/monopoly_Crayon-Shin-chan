package model.buildings;

import java.awt.Image;

import javax.swing.ImageIcon;

import model.EventsModel;
import model.PlayerModel;
import context.GameState;
import control.Control;

/**
 * 
 * ��԰ ���������Ĺ�԰����ɫ������ʲô����Ҳ���ᷢ��
 * 
 * 
 */
public class Park extends Building {
	/*
	 * 
	 * �¼�ͼƬ
	 */
	private Image[] imgageEvents = { EVENT_PARK_1, EVENT_PARK_2, EVENT_PARK_3, EVENT_PARK_4};
	
	/**
	 * �¼�ͼƬ
	*/
	
	public static Image EVENT_PARK_1 = new ImageIcon("images/event/park1.png")
			.getImage();
	/**
	 * �¼�ͼƬ
	*/
	
	public static Image EVENT_PARK_2 = new ImageIcon("images/event/park2.png")
			.getImage();
	/**
	 * �¼�ͼƬ
	*/
	
	public static Image EVENT_PARK_3 = new ImageIcon("images/event/park3.png")
			.getImage();
	/**
	 * �¼�ͼƬ
	*/
	
	public static Image EVENT_PARK_4 = new ImageIcon("images/event/park4.png")
			.getImage();
    
	public Park(int posX, int posY) {
		super(posX, posY);
		this.name = "��԰";
	}
	
	
	public Image[] getImgageEvents() {
		return imgageEvents;
	}



	public int getEvent() {
		return GameState.PARK_EVENT;
	}


}
