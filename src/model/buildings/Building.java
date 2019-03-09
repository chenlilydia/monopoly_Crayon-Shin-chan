package model.buildings;

import java.awt.Graphics;

import model.Port;
import model.PlayerModel;

import control.Control;

/**
 * 
 * ���ݳ�����
 * 
 */
public class Building{
	/**
	 * 
	 * ����ӵ����
	 * 
	 */
	protected PlayerModel owner = null;

	/**
	 * ��������
	 */
	protected String name;

	/**
	 * �ɹ�����
	 */
	protected boolean purchasability = false;

	/**
	 * ����յصļ۸�
	 */
	protected int price;
	/**
	 * ˰
	 */
	protected int revenue;
	/**
	 * ��ǰ���ݵȼ�
	 */
	protected int level;

	/**
	 * 
	 * ����
	 * 
	 */
	protected int posX;
	protected int posY;
	/**
	 * ���ȼ�
	 */
	protected int maxLevel;

	
	public Building(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public boolean isPurchasability() {
		return purchasability;
	}

	public void setPurchasability(boolean purchasability) {
		this.purchasability = purchasability;
	}

	/**
	 * �Ƿ��������
	 */
	public boolean canUpLevel() {
		return this.level < maxLevel;
	}

	public PlayerModel getOwner() {
		return owner;
	}

	public void setOwner(PlayerModel owner) {
		this.owner = owner;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}
	public String getUpName() {
		return name;
	}

	public int getUpLevelPrice() {
		return price;
	}
	/**
	 * 
	 * ��ȡ�����ܼ�ֵ
	 * 
	 */
	public int getAllPrice() {
		return 0;
	}
	public int getRevenue() {
		return revenue;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	/**
	 * �����¼�
	 */
	public int getEvent() { return 0;}
	
	/**
	 * ·���¼�
	 */
	public int passEvent() { return 0;}
	
	public void paint(Graphics g){}

	
}	
