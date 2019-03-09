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
 * ���� ��ҵ���������������߷��������¼�
 * 
 * 
 */
public class Prison extends Building {
	private PlayerModel player;
	/*
	 * 
	 * �¼�ͼƬ
	 */
	private Image[] imgageEvents = { EVENT_PRISON_1, EVENT_PRISON_2, EVENT_PRISON_3};
	
	/**
	 * �¼�ͼƬ
	*/
	
	public static Image EVENT_PRISON_1 = new ImageIcon("images/event/prison1.png")
			.getImage();
	/**
	 * �¼�ͼƬ
	*/
	
	public static Image EVENT_PRISON_2 = new ImageIcon("images/event/prison2.png")
			.getImage();
	/**
	 * �¼�ͼƬ
	*/
	
	public static Image EVENT_PRISON_3 = new ImageIcon("images/event/prison3.png")
			.getImage();
    
    /*
	private String[] events = { "ȥ��������������Ŷ~ ", "�����ԩ��������Ŷ~ ", "����������Ա������ʦץȥ��ɨ������Ŷ~ " };
	*/

	public Prison(int posX, int posY) {
		super(posX, posY);
		this.name = "����";
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
