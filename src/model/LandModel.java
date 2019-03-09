package model;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import control.GameRunning;

/**
 * ����
 * 
 */
public class LandModel extends Tick implements Port {
	/**
	 * 
	 * ����ͼƬ
	 * 
	 */
	private Image landsIMG = null;
	/**
	 * �յ�
	 */
	public final static int SPACE = 1;

	/**
	 * ����
	 */
	public final static int NEWS = 2;
	/**
	 * ��԰
	 */
	public final static int PARK = 3;
	/**
	 * ���
	 */
	public final static int ORIGIN = 4;
	/**
	 * ����
	 */
	public final static int PRISON = 5;
	/**
	 * �����
	 */
	public final static int SHOP = 6;
	/**
	 * �޽���
	 */
	public final static int NULL_SET = 0;
	
	/**
	 * ������Ϣ
	 */
	public static Point prison = new Point(0, 0);

	/**
	 * ����
	 */
	public final static int LEFT = 1;
	/**
	 * ����
	 */
	public final static int 	RIGHT = 2;
	/**
	 * ����
	 */
	public final static int UP = 3;
	/**
	 * ����
	 */
	public final static int 	DOWN = 4;
	/**
	 * ���λ��
	 */
	public final static int 	RAN = 5;
	/**
	 * ��
	 */
	public final static int 	NULL_MOVE = 0;

	public LandModel() {
		this.landsIMG = new ImageIcon("images/window/land.jpg").getImage();
	}

	public Image getLandsIMG() {
		return landsIMG;
	}

	public void setLandsIMG(Image landsIMG) {
		this.landsIMG = landsIMG;
	}
	
	 protected int[][] land1 = {
	
/*
	{ ORIGIN, SPACE, SPACE, SPACE, SPACE, NEWS, NULL_SET, NEWS,
	     SPACE, SPACE, SPACE, SPACE, NEWS },
	{ SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET,
	    	 SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
	{ SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET,
		    	 SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
	{ PARK , NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET,
			    	 SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
	{ SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET,
				    	 SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, PARK },
	{ SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE, PRISON,
					    	 SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
	{ SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
		NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
	{ NEWS, SPACE, SPACE, SPACE, SPACE, SPACE, SPACE, SPACE,
		 SPACE, SPACE, SPACE, SPACE, NEWS }
*/

/*
			 { ORIGIN, SHOP, SHOP, SHOP, SHOP, SHOP, NULL_SET, NEWS,
					 SPACE, SPACE, SPACE, SPACE, NEWS },
			 { SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET,
					 SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
			 { SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET,
					 SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
			 { PARK , NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET,
					 SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
			 { SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET,
					 SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, PARK },
			 { SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE, PRISON,
					 SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
			 { SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,
					 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
			 { NEWS, SPACE, SPACE, SPACE, SPACE, SPACE, SPACE, SPACE,
					 SPACE, SPACE, SPACE, SPACE, NEWS }*/


			 { ORIGIN, SPACE, SPACE, SPACE, SPACE, SPACE, SHOP, SPACE,
					 SPACE, SPACE, SPACE, SPACE, PRISON },
			 { SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE,
					 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
			 { SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE,
					 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
			 { PARK, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NEWS,
					 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
			 { SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE,
					 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, PARK },
			 { SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE,
					 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
			 { SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE,
					 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
			 { PRISON, SPACE, SPACE, SPACE, SPACE, SPACE, SHOP, SPACE,
					 SPACE, SPACE, SPACE, SPACE, NEWS }
		 
	 };
	protected int[][] moveDirec1 = {/*
			{RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, DOWN, NULL_MOVE, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN, NULL_MOVE, UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN, NULL_MOVE, UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN, NULL_MOVE, UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN, NULL_MOVE, UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, RIGHT, RIGHT, UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT}*/
			{RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RAN, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, NULL_MOVE, DOWN},
			{UP, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, LEFT}
	};
	/**
	 * �ǿ���������
	 */
	protected int landTotalNum =  49;

	 protected int[][] land;
	 
	public int[][] getLand() {
		return land;
	}

	/**
	 *
	 * ���ص�num���ǿ����ص�(x,y)
	 */
	public int[] getLandPosi(int num){
		int posX = 0;
		int posY = 0;
		int[] pos = new int[2];
		for (int i = 0; i < num; ++i){
			System.out.println(moveDirec1[posX][posY]);
			System.out.println("X:"+posX+"Y:"+posY);
			switch (moveDirec1[posX][posY]){
				case RIGHT:
					posY++;
					break;
				case LEFT:
					posY--;
					break;
				case UP:
					posX--;
					break;
				case DOWN:
					posX++;
				case RAN:
					double random = Math.random();
					if (random < 0.5){
						posX++;
					}else
						posY++;
			}
		}
		pos[0] = posX;
		pos[1] = posY;
		return pos;
	}

	public int getLandTotalNum() { return landTotalNum; }


	/**
	 * 
	 * ��ʼ��Ϸ����
	 * 
	 */
	public void startGameInit() {
		if (GameRunning.MAP == 1){
			land = land1;
		}
	}

	@Override
	public void updata(long tick) {
		this.nowTick = tick;

	}
}
