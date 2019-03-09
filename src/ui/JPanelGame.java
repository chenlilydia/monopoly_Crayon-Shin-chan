package ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import ui.massage.MassageOk;
import ui.massage.MassageSimple;
import ui.massage.MassageYesNo;
import control.Control;

@SuppressWarnings("serial")

/**
 * 
 * JPanel Game
 * 
 * */

public class JPanelGame extends JPanel{

	private JFrameGame gameFrame = null;
	private JLayeredPane layeredPane;

	private List<Layer> lays = null;
	private Background backgroundUI = null;
	private Lands landsUI = null;
	private Buildings buildingsUI = null;
	private Players playersUI = null;
	private TextTip textTip = null;
	private PlayersPanel layerPlayersPanel = null;
	private PlayersPanel_2 layerPlayersPanel_2 = null;
	private Dice dice = null;
	private Event event = null;
	private Running running = null;
	private Effect effect = null;
	private Shop shop = null;
	private PlayerInfo playerInfo = null;
	
	private MassageYesNo massageYesNo = null;
	private MassageOk massageOk = null;
	private MassageSimple massageSimple = null;

	private Control control = null;

	/**
	 * Board position X
	 */
	public int posX = 100;
	/**
	 * Board position Y
	 * */
	public int posY = 100;

	public JPanelGame() {
		setLayout(new BorderLayout());
		/* Game initialization*/
		initGame();
	}

	/**
	 * 
	 * Game initialization
	 * 
	 */
	private void initGame() {
		// 添加控制器
		control = new Control();
		// 初始化UI
		initUI();
		// panel 传入控制器
		control.setPanel(this);
	}

	public Control getControl() {
		return control;
	}

	/**
	 * 
	 * 初始化UI
	 * 
	 */
	private void initUI() {
		// backgroundUI
		this.backgroundUI = new Background(0, 0, 950, 650,
				control.getBackground(),this);
		// landsUI
		this.landsUI = new Lands(posX, posY, 950, 650, control.getLand());
		// buildingsUI
		this.buildingsUI = new Buildings(posX, posY, 950, 650,
				control.getBuilding());
		// playersUI
		this.playersUI = new Players(posX, posY, 950, 650,control.getRunning(), control.getPlayers());
		// layerPlayersPanel
		this.layerPlayersPanel = new PlayersPanel(posX + 75, posY + 66, 200,
				250, control.getPlayers());
		// layerPlayersPanel_2
		this.layerPlayersPanel_2 = new PlayersPanel_2(posX + 500, posY + 66, 200,
				250, control.getPlayers());
		// ShopUI
		this.shop = new Shop(0, 0, 1000, 520, control, this);
		// textTip
		this.textTip = new TextTip(0,0,950,650,control.getTextTip());
		// diceUI
		this.dice = new Dice(posX + 75, posY + 270, 220, 150, control);
		// eventUI
		this.event = new Event(0, 0, 950, 650, control.getEvents());
		// runningUI
		this.running = new Running(780, 0, 200, 80, control.getRunning(),this);
		// effectUI
		this.effect = new Effect(0, 0, 950, 650, control.getEffect(),this);
		// playerInfo
		this.playerInfo = new PlayerInfo(control.getPlayers(),this);
		// massageYesNoUI
		this.massageYesNo = new MassageYesNo("选择框", "创建一个对话框", this);
		// massageOkUI
		this.massageOk = new MassageOk("确定框", "创建一个对话框", this);
		// massageSimpleUI
		this.massageSimple = new MassageSimple("多选框", "创建一个对话框", this);

		// lays存放所有panel组件
		lays = new ArrayList<Layer>();
		lays.add(backgroundUI);
		lays.add(dice);
		lays.add(playersUI);
        //lays.add(textTip);
		lays.add(layerPlayersPanel);
		lays.add(layerPlayersPanel_2);
		lays.add(buildingsUI);
		lays.add(landsUI);
		lays.add(backgroundUI);
		lays.add(running);
		lays.add(effect);
		lays.add(shop);
		// lays.add(massageYesNo);

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);

		int add = 1;
		//layeredPane.add(this.massageOk, add++);
		layeredPane.add(this.event, add++);
		layeredPane.add(this.effect, add++);
		layeredPane.add(this.textTip, add++);
		layeredPane.add(this.dice, add++);
		layeredPane.add(this.playersUI, add++);
		layeredPane.add(this.layerPlayersPanel, add++);
		layeredPane.add(this.layerPlayersPanel_2, add++);
		layeredPane.add(this.buildingsUI, add++);
		layeredPane.add(this.landsUI, add++);
		layeredPane.add(this.running, add++);
		layeredPane.add(this.backgroundUI, add++);
		layeredPane.add(this.playerInfo,add++);
		layeredPane.add(this.shop, add++);

		
		//layeredPane.add(this.massageYesNo, add++);
		//layeredPane.add(this.massageSimple, add++);
		
		add(layeredPane);
	}

	
	public MassageYesNo getMassageYesNo() {
		return massageYesNo;
	}

	public MassageOk getMassageOk() {
		return massageOk;
	}

	public MassageSimple getMassageSimple() {
		return massageSimple;
	}

	public Running getRunning() {
		return running;
	}

	public Dice getDice() {
		return dice;
	}

	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	public Background getBackgroundUI() {
		return backgroundUI;
	}

	public Effect getEffect() {
		return effect;
	}
	
	public Shop getShop() {
		return this.shop;
	}

	public JFrameGame getGameFrame() {
		return gameFrame;
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public void setGameFrame(JFrameGame gameFrame) {
		this.gameFrame = gameFrame;
	}

	/**
	 * 
	 * Game initialization
	 * 
	 */
	public void startGamePanelInit() {
		for (Layer temp : this.lays) {
			// refresh window
			temp.startPanel();
		}
	}

}
