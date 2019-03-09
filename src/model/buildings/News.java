package model.buildings;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.EventsModel;
import model.LandModel;
import model.PlayerModel;
import model.TextTipModel;

import context.GameState;
import control.Control;

import util.FileUtil;

/**
 * 
 * ���� �������������ʱ������������¼������Ҵ����������벻���ľ���
 * 
 * 
 */
public class News extends Building {
	/*
	 * 
	 * �¼�ͼƬ
	 */
	private Image[] imgageEvents = { /*EVENT_BAD_HOSPITAL_3_01,
			EVENT_BAD_HOSPITAL_3_02, EVENT_BAD_LOSE_1000_01,
			EVENT_BAD_LOSE_1000_02, EVENT_BAD_LOSE_1500_01,
			EVENT_BAD_LOSE_2000_01, EVENT_BAD_LOSE_300_01,
			EVENT_BAD_LOSE_300_02, EVENT_BAD_LOSE_400_01,
			EVENT_BAD_LOSE_40S_01, EVENT_BAD_LOSE_500_01,
			EVENT_LUCK_GAIN_1000_01, EVENT_LUCK_GAIN_2000_01,
			EVENT_LUCK_GAIN_2000_02, EVENT_LUCK_GAIN_3999_100S,
			EVENT_LUCK_GAIN_400S_01,EVENT_LUCK_LOSE_3000,*/
			
			EVENT_LUCK_GAIN_1000Y,EVENT_BAD_LOSE_400Y,EVENT_BAD_LOSE_200Y,
			EVENT_BAD_LOSE_1000Y,EVENT_LUCK_GAIN_600Y,EVENT_LUCK_FREE_HOUSE,
			EVENT_BAD_LOSE_3000Y,EVENT_LUCK_GAIN_2000Y,EVENT_BAD_INTO_PRISON,
			EVENT_LUCK_GAIN_1000Y_2,EVENT_BAD_LOSE_700Y,EVENT_BAD_STOP_ONCE
			};
	
	public static Image EVENT_LUCK_GAIN_1000Y = new ImageIcon(
			"images/event/luck_gain1000y.png").getImage();
	public static Image EVENT_BAD_LOSE_400Y = new ImageIcon(
			"images/event/bad_lose400y.png").getImage();
	public static Image EVENT_BAD_LOSE_200Y = new ImageIcon(
			"images/event/bad_lose200y.png").getImage();
	public static Image EVENT_BAD_LOSE_1000Y = new ImageIcon(
			"images/event/bad_lose1000y.png").getImage();
	public static Image EVENT_LUCK_GAIN_600Y = new ImageIcon(
			"images/event/luck_gain600y.png").getImage();
	public static Image EVENT_LUCK_FREE_HOUSE = new ImageIcon(
			"images/event/luck_freehouse.png").getImage();
	public static Image EVENT_BAD_LOSE_3000Y = new ImageIcon(
			"images/event/bad_lose3000y.png").getImage();
	public static Image EVENT_LUCK_GAIN_2000Y = new ImageIcon(
			"images/event/luck_gain2000y.png").getImage();
	public static Image EVENT_BAD_INTO_PRISON = new ImageIcon(
			"images/event/bad_intoprison.png").getImage();	
	public static Image EVENT_LUCK_GAIN_1000Y_2 = new ImageIcon(
			"images/event/luck_gain1000y2.png").getImage();
	public static Image EVENT_BAD_LOSE_700Y = new ImageIcon(
			"images/event/bad_lose700y.png").getImage();
	public static Image EVENT_BAD_STOP_ONCE = new ImageIcon(
			"images/event/bad_stoponce.png").getImage();
	/**
	 * �¼�ͼƬ
	 
	public static Image EVENT_LUCK_LOSE_3000 = new ImageIcon(
			"images/event/bad_lose3000.jpg").getImage();
	public static Image EVENT_LUCK_LOSE_3000_W = new ImageIcon(
			"images/event/bad_lose3000_w.jpg").getImage();
			
	public static Image EVENT_BAD_HOSPITAL_3_01 = new ImageIcon(
			"images/event/bad_hospital_3_01.jpg").getImage();

	public static Image EVENT_BAD_HOSPITAL_3_02 = new ImageIcon(
			"images/event/bad_hospital_3.jpg").getImage();

	public static Image EVENT_BAD_LOSE_1000_01 = new ImageIcon(
			"images/event/bad_lose1000_01.jpg").getImage();

	public static Image EVENT_BAD_LOSE_1000_02 = new ImageIcon(
			"images/event/bad_lose1000.jpg").getImage();

	public static Image EVENT_BAD_LOSE_1500_01 = new ImageIcon(
			"images/event/bad_lose1500.jpg").getImage();

	public static Image EVENT_BAD_LOSE_2000_01 = new ImageIcon(
			"images/event/bad_lose2000.jpg").getImage();

	public static Image EVENT_BAD_LOSE_300_01 = new ImageIcon(
			"images/event/bad_lose300_01.jpg").getImage();

	public static Image EVENT_BAD_LOSE_300_02 = new ImageIcon(
			"images/event/bad_lose300.jpg").getImage();

	public static Image EVENT_BAD_LOSE_400_01 = new ImageIcon(
			"images/event/bad_lose400.jpg").getImage();

	public static Image EVENT_BAD_LOSE_40S_01 = new ImageIcon(
			"images/event/bad_lose40s.jpg").getImage();

	public static Image EVENT_BAD_LOSE_500_01 = new ImageIcon(
			"images/event/bad_lose500.jpg").getImage();

	public static Image EVENT_LUCK_GAIN_1000_01 = new ImageIcon(
			"images/event/luck_gain1000.jpg").getImage();

	public static Image EVENT_LUCK_GAIN_2000_01 = new ImageIcon(
			"images/event/luck_gain2000_01.jpg").getImage();

	public static Image EVENT_LUCK_GAIN_2000_02 = new ImageIcon(
			"images/event/luck_gain2000.jpg").getImage();

	public static Image EVENT_LUCK_GAIN_3999_100S = new ImageIcon(
			"images/event/luck_gain3999_100.jpg").getImage();


	public static Image EVENT_LUCK_GAIN_400S_01 = new ImageIcon(
			"images/event/luck_gain400s.jpg").getImage();
	*/

	public News(int posX, int posY) {
		super(posX, posY);
		this.name = "����";
	}

	public Image[] getImgageEvents() {
		return imgageEvents;
	}
	/*
	public Image get3000() {
		//return EVENT_LUCK_LOSE_3000_W;
	}
	*/
	@Override
	public int getEvent() {
		return GameState.NEWS_EVENT;
	}
}
