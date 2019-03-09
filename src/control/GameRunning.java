package control;

import java.util.List;

import model.Port;
import model.PlayerModel;
import model.Tick;

import ui.JPanelGame;

/**
 * 
 * ��Ϸ��ת����
 * 
 */
public class GameRunning {

	/**
	 * ����б�
	 */
	private List<PlayerModel> players = null;

	/**
	 * ��ǰ�������
	 */
	private PlayerModel nowPlayer = null;

	/**
	 * ���ӵ�ǰ����
	 */
	private int point;

	/**
	 * ���ʹ�ÿ�Ƭ״̬
	 */
	public static int STATE_CARD = 1;
	/**
	 * ��ҿ�Ƭ����״̬
	 */
	public static int STATE_CARD_EFFECT = 2;
	/**
	 * �������״̬
	 */
	public static int STATE_THROWDICE = 3;
	/**
	 * ����ƶ�״̬
	 */
	public static int STATE_MOVE = 4;
	/**
	 * 
	 * ��Ϸ��ֹ״̬
	 * 
	 */

	public static int GAME_STOP = 5;
	/**
	 *
	 * ��ҿ�ʼ��ͣ״̬
	 *
	 */
	public static int PLAYER_STOP_START = 6;
	/**
	 *
	 * ��ҽ�����ͣ״̬
	 *
	 */
	public static int PLAYER_STOP_END = 7;
	/**
	 * 
	 * ���Ŀǰ״̬
	 * 
	 */
	private int nowPlayerState;

	/**
	 * 
	 * ��Ϸ��������
	 * 
	 */
	public static int day = 1;

	/**
	 * 
	 * ��ǰ��ͼ����
	 * 
	 */
	public static int MAP = 1;
	/**
	 * 
	 * ��Ϸ�������� - 1Ϊ������
	 * 
	 */
	public static int GAME_DAY = -1;
	/**
	 * 
	 * ��Ϸ��Ǯ���ߣ���ʤ��������-1Ϊ������
	 * 
	 */
	public static int MONEY_MAX = -1;

	/**
	 * 
	 * ��ʼ����ҳ�ʼ��Ǯ
	 * 
	 */
	public static int PLAYER_CASH = 5000;
	/**
	 *
	 * ��ʼ����ҳ�ʼ����
	 *
	 */
	public static int PLAYER_NX = 200;

	private Control control;

	public GameRunning(Control control, List<PlayerModel> players) {
		this.control = control;
		this.players = players;
	}

	/**
	 * 
	 * ��õ�ǰ���״̬
	 * 
	 */
	public int getNowPlayerState() {
		return this.nowPlayerState;
	}

	/**
	 * 
	 * ת�����״̬
	 * 
	 */
	public void nextState() {
		// �ж���Ϸ�Ƿ�ó����
		if (gameContinue()) {
			if (this.nowPlayerState == STATE_CARD) {
				// ��ʹ�ÿ�Ƭ״̬��
				this.nowPlayerState = STATE_CARD_EFFECT;
				// ��ƬBUFF
				this.control.cardsBuff();
			} else if (this.nowPlayerState == STATE_CARD_EFFECT) {
				// ����Ƭ��Ч״̬��
				this.nowPlayerState = STATE_THROWDICE;
			} else if (this.nowPlayerState == PLAYER_STOP_START) {
				// ��ʼֹͣ״̬
				this.nowPlayerState = STATE_CARD;
				this.nextPlayer();
				// ����һ������
				this.setPoint((int) (Math.random() * 6));
				// ��Ϻ�ִ����һ����ҵĶ��� - STATE_CARD
				this.control.useCards();
			} else if (this.nowPlayerState == PLAYER_STOP_END) {
				// ����ֹͣ״̬
				this.nowPlayerState = STATE_THROWDICE;
			} else if (this.nowPlayerState == STATE_THROWDICE) {
				// �ƶ�״̬
				this.nowPlayerState = STATE_MOVE;
			} else if (this.nowPlayerState == STATE_MOVE) {
				// ���˲���
				this.nowPlayerState = STATE_CARD;
				this.nextPlayer();
				// ����һ������
				this.setPoint((int) (Math.random() * 6));
				// ��Ϻ�ִ����һ����ҵĶ��� - STATE_CARD
				this.control.useCards();
			}
		}
	}

	/**
	 * 
	 * ��ȡ��ǰ���
	 * 
	 */
	public PlayerModel getNowPlayer() {
		return this.nowPlayer;
	}

	public void setNowPlayerState(int nowPlayerState) {
		this.nowPlayerState = nowPlayerState;
	}

	/**
	 * 
	 * ��ȡ�ǵ�ǰ���
	 * 
	 */
	public PlayerModel getNotNowPlayer() {
		return this.nowPlayer.equals(this.players.get(0)) ? this.players.get(1)
				: this.players.get(0);
	}

	/**
	 * ���˲���
	 */
	private void nextPlayer() {
		// ����ʱ��
		if (this.nowPlayer.getInPrison() > 0) {
			this.nowPlayer.setInPrison(this.nowPlayer.getInPrison() - 1);
		}
		if (this.nowPlayer.getInHospital() > 0) {
			this.nowPlayer.setInHospital(this.nowPlayer.getInHospital() - 1);
		}
		if (this.nowPlayer.getInStop() > 0) {
			this.nowPlayer.setInStop(this.nowPlayer.getInStop() - 1);
		}
		// ����
		if (this.nowPlayer.equals(this.players.get(0))) {
			this.nowPlayer = this.players.get(1);
		} else {
			this.nowPlayer = this.players.get(0);
			// ��������Ϸ��������
			day++;
		}
	}

	/**
	 * 
	 * �ж���Ϸ�Ƿ����
	 * 
	 * 
	 */
	public boolean gameContinue() {
		PlayerModel p1 = this.nowPlayer;
		PlayerModel p2 = this.nowPlayer.getOtherPlayer();
		// ����
		if (GAME_DAY > 0 && day >= GAME_DAY) {
			this.control.gameOver();
			return false;
		}
		// ����Ǯ
		if (MONEY_MAX > 0 && p1.getCash() >= MONEY_MAX) {
			this.control.gameOver();
			return false;
		} else if (MONEY_MAX > 0 && p2.getCash() >= MONEY_MAX) {
			this.control.gameOver();
			return false;
		}
		// �Ʋ�
		if (p1.getCash() < 0) {
			this.control.gameOver();
			return false;
		} else if (p2.getCash() < 0) {
			this.control.gameOver();
			return false;
		}
		return true;
	}

	public void setPlayers(List<PlayerModel> players) {
		this.players = players;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getDay() {
		return day;
	}

	/**
	 * 
	 * ��ʼ��Ϸ����
	 * 
	 */
	public void startGameInit() {
		// �趨��ǰ��Ϸ���
		this.nowPlayer = this.players.get(0);
		// �趨��ǰ���״̬Ϊ��ʹ�ÿ�Ƭ��
		this.nowPlayerState = STATE_THROWDICE;
		// ����趨����
		this.setPoint((int) (Math.random() * 6));
		// �׸����ʹ�ÿ�Ƭ
		//this.control.useCards();
	}

}
