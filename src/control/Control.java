package control;

import java.applet.AudioClip;
import java.awt.Image;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

import model.BackgroundModel;
import model.BuildingsModel;
import model.DiceModel;
import model.EffectModel;
import model.EventsModel;
import model.LandModel;
import model.PlayerModel;
import model.Port;
import model.TextTipModel;
import model.buildings.Building;
import model.buildings.News;
import model.buildings.Origin;
import model.buildings.Park;
import model.buildings.Point;
import model.buildings.Prison;
import model.buildings.Shop_;
import model.card.Card;
import model.card.TortoiseCard;
import music.Music;
import ui.JPanelGame;
import util.FileUtil;
import util.MyThread;
import context.GameState;

/**
 * 
 * ��Ϸ�ܿ�����
 * 
 */
public class Control {
	/**
	 * 
	 * ��Ϸtickֵ
	 * 
	 */
	public static long tick;
	/**
	 * 
	 * ÿ�뻭��ˢ��Ƶ��
	 * 
	 */
	public static int rate = 30;
	/**
	 * 
	 * ��Ϸ�����
	 * 
	 */
	private JPanelGame panel;
	/**
	 * 
	 * ��Ϸ����
	 * 
	 */
	private GameRunning run = null;

	private List<Port> models = new ArrayList<Port>();
	private List<PlayerModel> players = null;
	private BuildingsModel building = null;
	private BackgroundModel background = null;
	private LandModel land = null;
	private TextTipModel textTip = null;
	private DiceModel dice = null;
	private EventsModel events = null;
	private EffectModel effect = null;

	private Music music = null;
	
	/**
	 * 
	 * ��Ϸ��ʱ��
	 * 
	 */
	private Timer gameTimer = null;

	public Control() {
		// ����һ����Ϸ״̬
		this.run = new GameRunning(this, players);
		// ��ʼ����Ϸ����
		this.initClass();
		// ����Ϸ״̬�м������ģ��
		this.run.setPlayers(players);
	}

	public void setPanel(JPanelGame panel) {
		this.panel = panel;
	}

	/**
	 * 
	 * ��ʼ����Ϸ����
	 * 
	 */
	private void initClass() {
		// ����һ���µ��¼�ģ��
		this.events = new EventsModel();
		this.models.add(events);
		// ����һ���µĳ���Ч��ģ��
		this.effect = new EffectModel();
		this.models.add(effect);
		// �����µı���ģ��
		this.background = new BackgroundModel();
		this.models.add(background);
		// �����µ�����ģ��
		this.land = new LandModel();
		this.models.add(land);
		// �����µ��ı���ʾģ��
		this.textTip = new TextTipModel();
		this.models.add(textTip);
		// ����һ���µĽ���ģ��
		this.building = new BuildingsModel(land);
		this.models.add(building);
		// ����һ���µ��������
		this.players = new ArrayList<PlayerModel>();
		this.players.add(new PlayerModel(1, this));
		this.players.add(new PlayerModel(2, this));
		this.models.add(players.get(0));
		this.models.add(players.get(1));
		// ����һ���µ�����ģ��
		this.dice = new DiceModel(run);
		this.models.add(dice);
		
		// ����һ��������
		this.music = new Music();
	}

	/**
	 * 
	 * ��Ϸ��ʱ��
	 * 
	 */
	private void createGameTimer() {
		this.gameTimer = new Timer();
		this.gameTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				tick++;
				// ���¸�����
				for (Port temp : models) {
					temp.updata(tick);
				}
				// UI����
				panel.repaint();
			}
		}, 0, (1000 / rate));
	}

	/**
	 * 
	 * ����������
	 * 
	 */
	public void start() {
		// ����һ����ʱ��
		this.createGameTimer();
		// ˢ�¶����ʼ����
		for (Port temp : this.models) {
			temp.startGameInit();
		}
		// ��Ϸ������ʼ
		this.run.startGameInit();
		// panel ��ʼ��
		this.panel.startGamePanelInit();
		// ��Ϸ��������
		this.startMusic();
		// ��Ϸ��ʼ������ͼЧ��
		this.effect.showImg("start");
	}

	
	/**
	 * 
	 * ��Ϸ��������
	 * 
	 */
	private void startMusic() {
		music.start();
	}

	public List<PlayerModel> getPlayers() {
		return players;
	}

	public BuildingsModel getBuilding() {
		return building;
	}

	public BackgroundModel getBackground() {
		return background;
	}

	public LandModel getLand() {
		return land;
	}

	public EffectModel getEffect() {
		return effect;
	}

	public TextTipModel getTextTip() {
		return textTip;
	}

	public GameRunning getRunning() {
		return run;
	}

	public DiceModel getDice() {
		return dice;
	}

	public EventsModel getEvents() {
		return events;
	}

	public JPanelGame getPanel() {
		return panel;
	}

	/**
	 * 
	 * 
	 * ��������
	 * 
	 * 
	 */
	public void pressButton() {
		PlayerModel player = this.run.getNowPlayer();
		if (player.getInStop() > 0) {
			this.run.nextState();
			this.textTip.showTextTip(player, player.getName() + "��ס��.", 1);
			this.run.nextState();
		}else if (player.getInPrison() > 0) {
			this.run.nextState();
			if (player.getInPrison() > 0) {
				Image INPRISON = new ImageIcon("images/event/INPRISON.png")
						.getImage();
				Image[] primage = {INPRISON}; 
				this.events.showImg(primage[0], 1 , new Point(
						420, 160, 0));
				this.textTip.showTextTip(player, player.getName() + "�׼���.", 1);
			}
			this.run.nextState();
		} else {
			// �������Ӷ���ʼת��ʱ��
			this.dice.setStartTick(Control.tick);
			// �������Ӷ������ת��ʱ��
			this.dice.setNextTick(this.dice.getStartTick()
					+ this.dice.getLastTime());
			// �����ж�������������Ӷ���
			this.dice.setPoint(this.run.getPoint());
			// ת��״̬�����ƶ�״̬��
			this.run.nextState();
			// ����ת����Ϻ�����ƶ�
			this.run.getNowPlayer().setStartTick(this.dice.getNextTick() + 10);
			this.run.getNowPlayer().setNextTick(
					this.run.getNowPlayer().getStartTick()
							+ this.run.getNowPlayer().getLastTime()
							* (this.run.getPoint() + 1));
		}
	}

	/**
	 * 
	 * 
	 * ����ƶ�
	 * 
	 * 
	 */
	public void movePlayer() {
		// �����˶�
		for (int i = 0; i < (60 / this.run.getNowPlayer().getLastTime()); i++) {
			// �ƶ����
			if (GameRunning.MAP == 1) {
				this.move02();
			}
		}
	}

	/**
	 * 
	 * �����;·������
	 * 
	 */
	public void prassBuilding() {
		// ��ǰ���
		PlayerModel player = this.run.getNowPlayer();
		// �õص㷿��
		Building building = this.building.getBuilding(player.getY() / 60,
				player.getX() / 60);
		if (building != null && player.getX() % 60 == 0
				&& player.getY() % 60 == 0) {
			// �������ݷ����¼�
			int event = building.passEvent();
			// ���뾭�������¼�����
			disposePassEvent(building, event, player);
		}
	}

	/**
	 * 
	 * ���������¼�����
	 * 
	 */
	private void disposePassEvent(Building b, int event, PlayerModel player) {
		switch (event) {
		case GameState.ORIGIN_PASS_EVENT:
			// ��;����ԭ��
			passOrigin(b, player);
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * ��;����ԭ��
	 * 
	 */
	private void passOrigin(Building b, PlayerModel player) {
		this.textTip.showTextTip(player, player.getName() + " ·��ԭ����Ŷ~ ���� "
				+ ((Origin) b).getPassReward() + "����.", 3);
		player.setNx(player.getNx() + ((Origin) b).getPassReward());
	}

	/*
	*
	* ����ƶ�
	*
	 */
	private void move01() {
		int dice = this.run.getPoint() + 1;
		PlayerModel p = this.run.getNowPlayer();
		// ��λ�ƶ�����
		int movePixel = 1;
		Boolean turn = dice % 2 != 0;
		if (p.getY() == 0) {
			// ����
			if (p.getX() == 5 * 60 || p.getX() == 12 * 60) {
				// ����·��
				p.setY(p.getY() + movePixel);
			} else {
				p.setX(p.getX() + movePixel);
			}
		} else if (p.getX() == 5 * 60 && p.getY() >= 0 && p.getY() < 5 * 60) {
			// [0,9]
			// ��
			p.setY(p.getY() + movePixel);
		} else if (p.getX() >= 5 * 60  && p.getX() < 7 * 60 && p.getY() == 5 * 60) {
			// ��
			p.setX(p.getX() + movePixel);
		} else if (p.getX() == 7 * 60 && p.getY() <= 5 * 60
				&& p.getY() > 0) {
			// ��
			p.setY(p.getY() - movePixel);
		} else if (p.getX() == 12 * 60 && p.getY() >= 0 && p.getY() < 7 * 60) {
			// ��
			p.setY(p.getY() + movePixel);
		} else if (p.getY() == 7 * 60 && p.getX() > 0) {
			// ��
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 0 && p.getY() > 0 && p.getY() <= 7 * 60) {
			// ��
			p.setY(p.getY() - movePixel);
		}
	}

	private void move02() {
		int dice = this.run.getPoint() + 1;
		PlayerModel p = this.run.getNowPlayer();
		// ��λ�ƶ�����
		int movePixel = 1;
		Boolean turn = dice % 2 != 0;
		if (p.getY() == 0) {
			// ����
			double random = Math.random();
			if ((p.getX() == 6 * 60 && random < 0.5) || p.getX() == 12 * 60) {
				// ����·��
				p.setY(p.getY() + movePixel);
			} else {
				p.setX(p.getX() + movePixel);
			}
		} else if (p.getX() == 6 * 60 && p.getY() > 0 && p.getY() < 7 * 60) {
			// [0,9]
			// ��
			p.setY(p.getY() + movePixel);
		} else if (p.getX() == 12 * 60 && p.getY() > 0 && p.getY() < 7 * 60) {
			// ��
			p.setY(p.getY() + movePixel);
		} else if (p.getY() == 7 * 60 && p.getX() > 0) {
			// ��
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 0 && p.getY() > 0 && p.getY() <= 7 * 60) {
			// ��
			p.setY(p.getY() - movePixel);
		}
	}
	
	/**
	 * 
	 * ����ƶ���ϣ�ͣ���ж�
	 * 
	 */
	public void playerStopJudge() {
		// ��ǰ���
		PlayerModel player = this.run.getNowPlayer();
		if (player.getInPrison() > 0) {
			this.textTip.showTextTip(player, player.getName() + "��ǰ�ڼ���,�����ƶ�.",
					2);
			// �������״̬
			this.run.nextState();
		} else{
			// ������Ҳ������� �¼��ȣ�
			this.playerStop();
		}
	}

	/**
	 * 
	 * ����ƶ���ϣ�ͣ�²���
	 * 
	 */
	public void playerStop() {
		// ��ǰ���
		PlayerModel player = this.run.getNowPlayer();
		// �õص㷿��
		Building building = this.building.getBuilding(player.getY() / 60,
				player.getX() / 60);
		//����¼�����������������0.1
		double random = Math.random();
		if (random < 0.05){
			// ���¼�����ʾ�¼�
			this.events.showImg(new ImageIcon("images/event/randomEvent_overspeed.png").getImage(), 2 , new Point(
					420, 160, 0));
			new Thread(new MyThread(run, 2)).start();
			player.setInPrison(player.getInPrison() + 2);
			// ���λ���л�������λ��
			if (LandModel.prison != null) {
				player.setX(LandModel.prison.x);
				player.setY(LandModel.prison.y);
			}
		}else {
			if (building != null) {// ��ȡ����
				int event = building.getEvent();
				// ����������Ϣ
				disposeStopEvent(building, event, player);

			}
		}
	}

	/**
	 * 
	 * ͣ�������¼�����
	 * 
	 * 
	 */
	private void disposeStopEvent(Building b, int event, PlayerModel player) {
		switch (event) {
		
		case GameState.HUOSE_EVENT:
			// ͣ���ڿɲ�������
			stopInHouse(b, player);
			break;	
		case GameState.NEWS_EVENT:
			// ͣ�������ŵ���
			stopInNews(b, player);
			break;
		case GameState.ORIGIN_EVENT:
			// ͣ����ԭ��
			stopInOrigin(b, player);
			break;
		case GameState.PARK_EVENT:
			// ͣ���ڹ�԰
			stopInPack(b, player);
			break;
		case GameState.PRISON_EVENT:
			// ͣ���ڼ���
			stopInPrison(b, player);
			break;
		case GameState.LOTTERY_EVENT:
			// ͣ������͸����
			stopInLottery(b, player);
			break;
		case GameState.SHOP_EVENT:
			// ͣ�����̵�
			stopInShop(b, player);
			break;
		}
	}

	/**
	 *
	 * ͣ�����̵�
	 *
	 */
	private void stopInShop(Building b, PlayerModel player) {
		if (player.getNx() > 0){
			// Ϊ�̵�Ļ�������������Ʒ
			((Shop_) b).createCards();
			// Ϊ�̵��������µĿ�Ƭ��Ʒ
			this.panel.getShop().addCards((Shop_) b);
			// ���̵�����������
			this.panel.getShop().moveToFront();
		} else {
			this.run.nextState();
		}
	}

	/**
	 * 
	 * ͣ���ڼ���
	 * 
	 */
	private void stopInPrison(Building b, PlayerModel player) {
		int random = (int) (Math.random() * ((Prison) b).getImgageEvents().length);
		switch (random) {
		case 0:
			player.setInPrison(1 + 1);
			break;
		case 1:
			player.setInPrison(2 + 1);
			break;
		case 2:
			player.setInPrison(3 + 1);
			break;
		}
		this.events.showImg(((Prison) b).getImgageEvents()[random], 2, new Point(
				420, 160, 0));
		new Thread(new MyThread(run, 2)).start();
	}

	/**
	 *
	 * ͣ����ԭ��
	 *
	 */
	private void stopHold(Building b, PlayerModel player) {
		player.setInStop(1 + 2);
		new Thread(new MyThread(run, 2)).start();
	}

	/**
	 * 
	 * ͣ���ڹ�԰
	 * 
	 */
	private void stopInPack(Building b, PlayerModel player) {
		int random = (int) (Math.random() * ((Park) b).getImgageEvents().length);
		this.events.showImg(((Park) b).getImgageEvents()[random], 2, new Point(
				320, 160, 0));
		switch (random) {
		case 0:
			break;
		}
		new Thread(new MyThread(run, 2)).start();
	}

	/**
	 * 
	 * ͣ����ԭ��
	 * 
	 */
	private void stopInOrigin(Building b, PlayerModel player) {
		this.textTip.showTextTip(player, player.getName() + " �����ͣ����Ŷ~ ������ "
				+ ((Origin) b).getReward() + "����.", 3);
		player.setNx(player.getNx() + ((Origin) b).getReward());
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * ͣ�������ŵ���
	 * 
	 */
	private void stopInNews(Building b, PlayerModel player) {
		int random = (int) (Math.random() * ((News) b).getImgageEvents().length);
		switch (random) {
		case 0:
			player.setCash(player.getCash() + 1000);
			break;
		case 1:
			player.setCash(player.getCash() - 400);
			break;
		case 2:
			player.setCash(player.getCash() - 200);
			break;
		case 3:
			player.setCash(player.getCash() - 1000);
			break;
		case 4: 
			player.setCash(player.getCash() + 600);
			break;
		case 5://free house
			
			break;
		case 6:
			player.setCash(player.getCash() -3000);
			break;
		case 7:
			player.setCash(player.getCash() + 2000);
			break;
		case 8://into prison
			player.setInPrison(player.getInPrison() + 2);
			// ���λ���л�������λ��
			if (LandModel.prison != null) {
				player.setX(LandModel.prison.x);
				player.setY(LandModel.prison.y);
			}
			break;
		case 9:
			player.setCash(player.getCash() + 1000);
			break;
		case 10:
			player.setCash(player.getCash() - 700);
			break;
		case 11: //stop once
			this.run.setNowPlayerState(GameRunning.PLAYER_STOP_START);
			player.setInStop(player.getInStop() + 2);
			break;
		}
		// ���¼�����ʾ�¼�
		this.events.showImg(((News) b).getImgageEvents()[random], 2 , new Point(
				420, 160, 0));
		new Thread(new MyThread(run, 2)).start();
	}
	
	/**
	 * 
	 * 
	 * ͣ���ڿɲ�������
	 * 
	 * 
	 */
	private void stopInHouse(Building b, PlayerModel player) {
		if (b.isPurchasability()) {// ��ҷ���
			if (b.getOwner() == null) { // ���˷���
				// ִ���򷿲���
				this.buyHouse(b, player);
			} else {// ���˷���
				if (b.getOwner().equals(player)) {// �Լ�����
					// ִ���������ݲ���
					this.upHouseLevel(b, player);
				} else {// ���˷���
					// ִ�н�˰����
					this.giveTax(b, player);
				}
			}
		}
	}
	
	/**
	 * 
	 * ͣ������͸����
	 * 
	 */
	private void stopInLottery(Building b, PlayerModel player) {
		// δ����
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * ִ�н�˰����
	 * 
	 * 
	 */
	private void giveTax(Building b, PlayerModel player) {
		int revenue = b.getRevenue();
		// ����Ҽ��ٽ��
		player.setCash(player.getCash() - revenue);
		// ҵ���õ����
		b.getOwner().setCash(b.getOwner().getCash() + revenue);
		// �����ı���ʾ
		this.textTip.showTextTip(player, player.getName() + "����"
				+ b.getOwner().getName() + "�ĵ��̣���·��:" + revenue + "Ԫ.", 3);
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * ִ���������ݲ���
	 * 
	 */
	private void upHouseLevel(Building b, PlayerModel player) {
		if (b.canUpLevel()) {
			// ��������
			int price = b.getUpLevelPrice();
			String name = b.getName();
			String upName = b.getUpName();
			int choose = JOptionPane.showConfirmDialog(null,
					"Oops:" + player.getName() + "�����������Ŷ~" + "\r\n" + "�Ƿ��������أ�\r\n" + name
							+ "��" + upName + "\r\n" + "�۸�" + price + " Ԫ.");
			if (choose == JOptionPane.OK_OPTION) {
				if (player.getCash() >= price) {
					b.setLevel(b.getLevel() + 1);
					// ������Ҫ�Ľ��
					player.setCash(player.getCash() - price);
					// �����ı���ʾ
					this.textTip.showTextTip(player, player.getName() + " �� "
							+ name + " ������ " + upName + ".������ " + price
							+ "Ԫ. ", 3);
				} else {
					// �����ı���ʾ
					this.textTip.showTextTip(player, player.getName()
							+ "�ֽ���,����ʧ��. ", 3);
				}
			}
		}
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * ִ���򷿲���
	 * 
	 * 
	 */
	private void buyHouse(Building b, PlayerModel player) {
		int price = b.getUpLevelPrice();
		int choose = JOptionPane.showConfirmDialog(
				null,
				"Oops:" + player.getName() + "����������Ŷ~" + "\r\n" + "�Ƿ��������أ�\r\n"
						+ b.getName() + "��" + b.getUpName() + "\r\n" + "�۸�"
						+ price + " Ԫ.");

		if (choose == JOptionPane.OK_OPTION) {
			// ����
			if (player.getCash() >= price) {
				b.setOwner(player);
				b.setLevel(1);
				// ���÷��ݼ��뵱ǰ��ҵķ����б���
				player.getBuildings().add(b);
				// ������Ҫ�Ľ��
				player.setCash(player.getCash() - price);
				this.textTip.showTextTip(player, player.getName()
						+ " ������һ��յ�.������: " + price + "Ԫ. ", 3);
			} else {
				this.textTip.showTextTip(player, player.getName()
						+ " �ֽ���,����ʧ��. ", 3);
			}
		}
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * ��ƬЧ������
	 * 
	 */
	public void cardsBuff() {
		List<Card>delete = new ArrayList<Card>();
		for (Card a : this.run.getNowPlayer().getEffectCards()) {
			int buff = a.cardBuff();
			cardBuff(a, buff,delete);
		}
		this.run.getNowPlayer().getEffectCards().removeAll(delete);
		this.run.nextState();
	}

	/**
	 * 
	 * ��ƬЧ������
	 * 
	 * 
	 */
	private void cardBuff(Card card, int buff,List<Card>delete) {
		switch (buff) {
		case GameState.CARD_BUFF_TORTOISE:
			// �ڹ꿨BUff
			buffTortoiseCard((TortoiseCard) card,delete);
			break;
		case GameState.CARD_BUFF_STOP:
			// ͣ����Buff
			buffStopCard(card,delete);
			break;
		}
	}

	/**
	 * 
	 * ͣ����Buff
	 * 
	 * 
	 */
	private void buffStopCard(Card card,List<Card>delete) {
		// �����ı���ʾ
		this.textTip.showTextTip(card.geteOwner(), card.geteOwner().getName()
				+ " ��\"ͣ����\" ���ã������ƶ�.. ", 2);
		// �Ƴ���Ƭ
		delete.add(card);
		this.run.nextState();
		new Thread(new MyThread(run, 1)).start();
	}
	

	/**
	 * 
	 * �ڹ꿨BUff
	 * 
	 */

	private void buffTortoiseCard(TortoiseCard card,List<Card>delete) {
		if (card.getLife() <= 0) {
			delete.add(card);
			return;
		} else {
			card.setLife(card.getLife() - 1);
		}
		this.textTip.showTextTip(card.geteOwner(), card.geteOwner().getName()
				+ " ��\"�ڹ꿨\" ���ã�ֻ���ƶ�һ��.. ", 2);
		this.run.setPoint(0);
	}

	/**
	 * 
	 * ʹ�ÿ�Ƭ
	 * 
	 */
	public void useCards() {
		PlayerModel p = this.run.getNowPlayer();
		while (true) {
			if (p.getCards().size() == 0) {
				// �޿�Ƭ�������׶�
				this.run.nextState();
				break;
			} else {
				Object[] options = new Object[p.getCards().size() + 1];
				int i;
				for (i = 0; i < p.getCards().size(); i++) {
					options[i] = p.getCards().get(i).getcName() + "\r\n";
				}
				options[i] = "����,��ʹ��";
				int response = JOptionPane.showOptionDialog(null,
						" " + p.getName() + "��ѡ����Ҫʹ�õĿ�Ƭ", "��Ƭʹ�ý׶�.",
						JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);
				if (response != i && response != -1) {
					// ��ÿ�Ƭ
					int th = p.getCards().get(response).useCard();
					// ʹ�ÿ�Ƭ
					useCard(p.getCards().get(response), th);
				} else {
					// ��ʹ�ã������׶�.
					this.run.nextState();
					break;
				}
			}
		}
	}

	/**
	 * 
	 * ʹ�ÿ�Ƭ
	 * 
	 */
	private void useCard(Card card, int th) {
		switch (th) {
		case GameState.CARD_ADDLEVEL:
			// ʹ�üӸǿ�
			useAddLevelCard(card);
			break;
		case GameState.CARD_AVERAGERPOOR:
			// ʹ�þ�ƶ��
			useAveragerPoorCard(card);
			break;
		case GameState.CARD_CONTROLDICE:
			// ʹ��ң�����ӿ�
			useControlDiceCard(card);
			break;
		case GameState.CARD_HAVE:
			// ʹ�ù��ؿ�
			useHaveCard(card);
			break;
		case GameState.CARD_TORTOISE:
			// ʹ���ڹ꿨
			useTortoiseCard(card);
			break;
		case GameState.CARD_GOODLUCK:
			//ʹ�ú��˿�
			useGoodLuckCard(card);
			break;
		}
	}

	/**
	 * 
	 * ʹ���ڹ꿨
	 * 
	 * 
	 */
	private void useTortoiseCard(Card card) {
		Object[] options = { card.getOwner().getName(),
				card.getOwner().getOtherPlayer().getName(), "����ѡ��" };
		int response = JOptionPane.showOptionDialog(null,
				" ��ѡ��Ŀ����ң�������\"�ڹ꿨\".", "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response == 0) {
			card.getOwner().getEffectCards().add(card);
			card.seteOwner(card.getOwner());
			// �����ı���ʾ
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ���Լ�ʹ����\"�ڹ꿨\". ", 2);
			card.getOwner().getCards().remove(card);
		} else if (response == 1) {
			card.getOwner().getOtherPlayer().getEffectCards().add(card);
			card.seteOwner(card.getOwner().getOtherPlayer());
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ��\"" + card.getOwner().getOtherPlayer().getName()
					+ "\"ʹ����\"�ڹ꿨\". ", 2);
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * ʹ�ù��ؿ�
	 * 
	 */
	private void useHaveCard(Card card) {
		// �õص㷿��
		Building building = this.building.getBuilding(
				card.getOwner().getY() / 60, card.getOwner().getX() / 60);
		if (building.getOwner() != null
				&& building.getOwner().equals(card.getOwner().getOtherPlayer())) {// �ǶԷ��ķ���
			Object[] options = { "ȷ��ʹ��", "����ѡ��" };
			int response = JOptionPane.showOptionDialog(null,
					"ȷ��ʹ��\"���ؿ�\"���˵��չ�����Ҫ���ѣ�" + building.getAllPrice() + " ���.",
					"��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			if (response == 0) {
				if (card.getOwner().getCash() >= building.getAllPrice()) {
					// ��ҽ���
					building.getOwner().setCash(
							building.getOwner().getCash()
									+ building.getAllPrice());
					card.getOwner().setCash(
							card.getOwner().getCash() - building.getAllPrice());
					building.setOwner(card.getOwner());
					// �����ı���ʾ
					this.textTip.showTextTip(card.getOwner(), card.getOwner()
							.getName() + " ʹ���� \"���ؿ�\"���չ�����˸�����. ", 2);
					// ����ȥ��Ƭ
					card.getOwner().getCards().remove(card);
				} else {
					Object[] options1 = { "����ѡ��" };
					JOptionPane.showOptionDialog(null, " ��Ҳ��㣬�޷�������!",
							"��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options1,
							options1[0]);
				}
			}
		} else {
			Object[] options1 = { "����ѡ��" };
			JOptionPane.showOptionDialog(null, "�˷����޷�ʹ�øÿ�Ƭ.", "��Ƭʹ�ý׶�.",
					JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					options1, options1[0]);
		}
	}

	/**
	 * 
	 * 
	 * ʹ��ң�����ӿ�
	 * 
	 * 
	 */
	private void useControlDiceCard(Card card) {
		Object[] options = { "1��", "2��", "3��", "4��", "5��", "6��", "����ѡ��" };
		int response = JOptionPane.showOptionDialog(null,
				"ȷ��ʹ��\"ң�����ӿ�\"ң�����ӵ���?", "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response == -1 || response == 6) {
			return;
		} else {
			// ʹ��
			this.run.setPoint(response);
			// �����ı���ʾ
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ʹ���� \"ң�����ӿ�\".", 2);
			// ����ȥ��Ƭ
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * ʹ�þ�ƶ��
	 * 
	 */
	private void useAveragerPoorCard(Card card) {
		Object[] options = { "ȷ��ʹ��", "����ѡ��" };
		int response = JOptionPane.showOptionDialog(null,
				"ȷ��ʹ��\"��ƶ��\"�����ƽ���ֽ�?", "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response == 0) {
			// ʹ��
			int money = (int) (card.getOwner().getCash() + card.getOwner()
					.getOtherPlayer().getCash()) / 2;
			card.getOwner().setCash(money);
			card.getOwner().getOtherPlayer().setCash(money);
			// �����ı���ʾ
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ʹ���� \"��ƶ��\"�������ƽ�����ֽ�,����˫���ֽ���Ϊ:" + money + " ���. ", 2);

			// ����ȥ��Ƭ
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * ʹ�üӸǿ�
	 * 
	 */

	private void useAddLevelCard(Card card) {
		Building building = this.building.getBuilding(
				card.getOwner().getY() / 60, card.getOwner().getX() / 60);
		if (building.getOwner() != null
				&& building.getOwner().equals(card.getOwner())) {// ���Լ��ķ���
			if (building.canUpLevel()) { // ������
				// ����
				building.setLevel(building.getLevel() + 1);
				// �����ı���ʾ
				this.textTip.showTextTip(card.getOwner(), card.getOwner()
						.getName() + " ʹ���� \"�Ӹǿ�\"�������ݵȼ�����һ��. ", 2);
				// ����ȥ��Ƭ
				card.getOwner().getCards().remove(card);
			} else {
				// �޷�ʹ��,��������
				Object[] options = { "����ѡ��" };
				JOptionPane.showOptionDialog(null, " ��ǰ���ݲ�������.", "��Ƭʹ�ý׶�.",
						JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);
			}
		} else {
			// �޷�ʹ��.
			Object[] options = { "����ѡ��" };
			JOptionPane.showOptionDialog(null, " ��ǰ���ݲ���ʹ�øÿ�Ƭ.", "��Ƭʹ�ý׶�.",
					JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					options, options[0]);
		}
	}

	/**
	 *
	 * ʹ�ú��˿�
	 *
	 */
	private void useGoodLuckCard(Card card) {
		Object[] options = { card.getOwner().getName(),
				card.getOwner().getOtherPlayer().getName(), "����ѡ��" };
		int response = JOptionPane.showOptionDialog(null,
				" ��ѡ��Ŀ����ң�������\"���˿�\".", "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		int randomLand = (int)(Math.random() * this.getLand().getLandTotalNum());
		System.out.println(randomLand);
		int[] pos = this.getLand().getLandPosi(randomLand);
		if (response == 0) {
			// �����ı���ʾ
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ���Լ�ʹ����\"���˿�\". ", 2);
			PlayerModel player = this.run.getNowPlayer();
			player.setX(pos[1] * 60);
			player.setY(pos[0] * 60);
			card.getOwner().getCards().remove(card);
		} else if (response == 1) {
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " ��\"" + card.getOwner().getOtherPlayer().getName()
					+ "\"ʹ����\"���˿�\". ", 2);
			PlayerModel player = this.run.getNowPlayer().getOtherPlayer();
			player.setX(pos[1] * 60);
			player.setY(pos[0] * 60);
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * �˳��̵�
	 * 
	 */
	public void exitShop() {
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * �̵�����Ƭ����
	 * 
	 * 
	 */
	public void buyCard(Shop_ shop) {
		int chooseCard = this.panel.getShop().getChooseCard();
		if (chooseCard >= 0
				&& this.panel.getShop().getCard().get(chooseCard) != null) {
			// ����Ƭ �������ɹ�
			if (this.buyCard(shop, chooseCard)) {
				// �գ���ȥ��Ƭ
				this.panel.getShop().getCard().get(chooseCard).setEnabled(false);
				// ��ʼ����ѡ��Ƭ
				this.panel.getShop().setChooseCard(-1);
			}
		}
	}

	/**
	 * 
	 * ����Ƭ
	 * 
	 * 
	 */
	public boolean buyCard(Shop_ shop, int p) {
		if (this.panel.getShop().getCard().get(p) != null) {
			if (this.run.getNowPlayer().getCards().size() >= PlayerModel.MAX_CAN_HOLD_CARDS) {
				JOptionPane.showMessageDialog(null, "�����ɳ���:"
						+ PlayerModel.MAX_CAN_HOLD_CARDS + "�ſ�Ƭ,Ŀǰ�Ѿ������ٹ�����!");
				return false;
			}
			if (this.run.getNowPlayer().getNx() < shop.getCards().get(p)
					.getPrice()) {
				JOptionPane.showMessageDialog(null, "��ǰ��Ƭ��Ҫ:"
						+ shop.getCards().get(p).getPrice() + "��ȯ,��ĵ�ȯ��������");
				return false;
			}
			// ���ÿ�Ƭӵ����
			shop.getCards().get(p).setOwner(this.run.getNowPlayer());
			// ����ҿ�Ƭ������ӿ�Ƭ
			this.run.getNowPlayer().getCards().add(shop.getCards().get(p));
			// ��ȥ��Ӧ��ȯ
			this.run.getNowPlayer().setNx(
					this.run.getNowPlayer().getNx()
							- shop.getCards().get(p).getPrice());
		}
		return true;
	}


	/**
	 * 
	 * ��Ϸ����~
	 * 
	 *
	 * @param winer
	 */
	public void gameOver () {
		this.run.setNowPlayerState(GameRunning.GAME_STOP);
		this.panel.getBackgroundUI().moveToFront();
		this.panel.getRunning().moveToFront();
		this.panel.getPlayerInfo().moveToFront();
		this.panel.getEffect().moveToFront();
		this.music.gameOver();
		this.effect.showImg("timeover2");
		
	}
}
