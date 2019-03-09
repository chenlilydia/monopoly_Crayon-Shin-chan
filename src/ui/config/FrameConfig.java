package ui.config;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.PlayerModel;
import ui.JFrameGame;
import ui.WaitFrame;
import util.FrameUtil;
import control.Control;
import control.GameRunning;

/**
 * 
 * Get player settings
 * 
 * */
public class FrameConfig extends JFrame {

	private JButton jbnStart = new JButton("Start Game");
	private JButton jbnCancel = new JButton("Resetting");

	private JButton jbnPlayer01 = new JButton("1P character confirming");
	private JLabel jbnPlayerNameLabel01 = new JLabel("Name:");
	private JTextField jbnPlayerNameField01 = new JTextField(12);
	private JButton jbnPlayerName01 = new JButton("1P name confirming");

	private JButton jbnPlayer02 = new JButton("2P character confirming");
	private JLabel jbnPlayerNameLabel02 = new JLabel("Name:");
	private JTextField jbnPlayerNameField02 = new JTextField(12);
	private JButton jbnPlayerName02 = new JButton("2P name confirming");

	private JTabbedPane tabs;
	private ImageIcon[] img = Photo.PLAYER_CHOOSE;
	/**
	 * Player01
	 **/
	private JLabel jlPlayer01Choose = null;
	private final JLabel jlPlayer01Selected = new JLabel(
			Photo.PLAYER_01_SELECTED);
	private JButton leftButton01;
	private JButton rightButton01;

	/**
	 * Player02
	 **/
	private JLabel jlPlayer02Choose = null;
	private final JLabel jlPlayer02Selected = new JLabel(
			Photo.PLAYER_02_SELECTED);
	private JButton leftButton02;
	private JButton rightButton02;
	/**
	 * 1P 2P choices
	 */
	private int[] choices = { 0, 0 };
	/**
	 * 1P 2P selected character
	 */
	private int[] selected = { -1, -2 };
	/**
	 * 1P 2P selected name
	 */
	private String[] selectedName = { "", "" };

	/**
	 * 
	 * FrameConfig
	 * 
	 * */
	private JFrameGame jFrameGame;

	public FrameConfig(WaitFrame wFrame,JFrameGame jFrameGame) {
		wFrame.setVisible(false);
		this.jFrameGame = jFrameGame;
		setTitle("Players settings");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		this.add(this.createMainPanel(), BorderLayout.CENTER);
		
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		this.setResizable(false);
		this.setSize(450, 470);
		FrameUtil.setFrameCenter(this);
		setVisible(true);
	}

	/**
	 * createMainPanel
	 */
	private JTabbedPane createMainPanel() {
		this.tabs = new JTabbedPane();
		this.tabs.setOpaque(false);
		this.tabs.add("Players settings", this.createPlayerSelectPanel());
		this.tabs.setToolTipTextAt(0, "Complete players settings");
		return tabs;
	}

	/**
	 * createPlayerSelectPanel
	 * */
	private JPanel createPlayerSelectPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(235,236,237));
		addPlayer01Config(12, 0, jp);
		addPlayer02Config(212, 0, jp);
		addCancelButton(jp);
		return jp;
	}

	private void addCancelButton(JPanel panel) {
		jbnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					reLoad();
			}

			/**
			 * reLoad
			 */
			private void reLoad() {
				leftButton01.setEnabled(true);
				rightButton01.setEnabled(true);
				jbnPlayer01.setEnabled(true);
				jlPlayer01Selected.setVisible(false);
				jlPlayer01Choose.setIcon(img[0]);
				jbnPlayerNameField01.setText("");
				jbnPlayerNameField01.setEditable(true);
				jbnPlayerName01.setEnabled(true);
				selected[0] = -1;
				choices[0] = 0;

				leftButton02.setEnabled(true);
				rightButton02.setEnabled(true);
				jbnPlayer02.setEnabled(true);
				jlPlayer02Selected.setVisible(false);
				jlPlayer02Choose.setIcon(img[0]);
				jbnPlayerNameField02.setText("");
				jbnPlayerNameField02.setEditable(true);
				jbnPlayerName02.setEnabled(true);
				selected[1] = -2;
				choices[1] = 0;
				repaint();
			}
		});
		jbnCancel.setBounds(350, 330, 80, 30);
		panel.add(jbnCancel);
	}

	/**
	 * addPlayer01Config
	 */
	private void addPlayer01Config(int x, int y, JPanel jp) {
		jlPlayer01Choose = new JLabel(img[choices[0]]);
		jlPlayer01Choose.setBounds(20, y, 128, 128);

		jlPlayer01Selected.setBounds(20, y, 128, 128);
		jlPlayer01Selected.setVisible(false);

		leftButton01 = this.createButton(x, 92 + y, Photo.BUTTON_LEFT, 'a');
		leftButton01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (choices[0] <= 0) {
					choices[0] = img.length;
				}
				jlPlayer01Choose.setIcon(img[--choices[0]]);
			}
		});

		jp.add(leftButton01);
		rightButton01 = this.createButton(128 + x, 92 + y, Photo.BUTTON_RIGHT,
				'd');
		rightButton01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (choices[0] >= img.length - 1) {
					choices[0] = -1;
				}
				jlPlayer01Choose.setIcon(img[++choices[0]]);
			}
		});
		jp.add(rightButton01);
		jbnPlayer01.setBounds( x, 128 + y, 160, 30);
		jbnPlayer01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((choices[0] != selected[1])) {

					leftButton01.setEnabled(false);
					rightButton01.setEnabled(false);
					jbnPlayer01.setEnabled(false);

					jlPlayer01Selected.setVisible(true);
					selected[0] = choices[0];
				}
			}
		});
		jp.add(jbnPlayer01);
		jp.add(jlPlayer01Selected);
		jp.add(jlPlayer01Choose);
		
		jbnPlayerNameLabel01.setBounds(x + 200 + 50 , y + 40, 50, 30);
		jbnPlayerNameField01.setBounds(x + 245 + 50, y + 40, 120 - 30, 30);
		jbnPlayerName01.setBounds(x +185 + 50, y + 36 + 40, 120 + 30, 30);

		jbnPlayerName01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!jbnPlayerNameField01.getText().equals("")) {
					selectedName[0] = jbnPlayerNameField01.getText();
					jbnPlayerNameField01.setEditable(false);
					jbnPlayerName01.setEnabled(false);

				}

			}
		});
		jp.add(jbnPlayerNameLabel01);
		jp.add(jbnPlayerNameField01);
		jp.add(jbnPlayerName01);
	}

	/**
	 * addPlayer02Config
	 */
	private void addPlayer02Config(int x, int y, JPanel jp) {

		jlPlayer02Choose = new JLabel(img[choices[1]]);
		jlPlayer02Choose.setBounds(20, y+180, 128, 128);

		jlPlayer02Selected.setBounds(20, y+180, 128, 128);
		/*jlPlayer02Choose.setBounds(x + 8, y, 128, 128);

		jlPlayer02Selected.setBounds(x + 8, y, 128, 128);*/
		jlPlayer02Selected.setVisible(false);

		leftButton02 = this.createButton(15, 275 + y, Photo.BUTTON_LEFT, 'a');
		leftButton02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (choices[1] <= 0) {
					choices[1] = img.length;
				}
				jlPlayer02Choose.setIcon(img[--choices[1]]);
			}
		});

		jp.add(leftButton02);
		rightButton02 = this.createButton(145, 275 + y, Photo.BUTTON_RIGHT,
				'd');
		rightButton02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (choices[1] >= img.length - 1) {
					choices[1] = -1;
				}
				jlPlayer02Choose.setIcon(img[++choices[1]]);
			}
		});

		jp.add(rightButton02);
		jbnPlayer02.setBounds(10, 310 + y, 160, 30);
		jbnPlayer02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selected[0] != choices[1]) {
					/* 设置不能点击 */
					leftButton02.setEnabled(false);
					rightButton02.setEnabled(false);
					jbnPlayer02.setEnabled(false);
					/* 增加选择图片 */
					jlPlayer02Selected.setVisible(true);
					selected[1] = choices[1];
				}
			}
		});
		jp.add(jbnPlayer02);
		jp.add(jlPlayer02Selected);
		jp.add(jlPlayer02Choose);
		/* 增加名字框 */
		jbnPlayerNameLabel02.setBounds(x  + 50, y + 128 + 36 + 10 + 50, 50, 30);
		jbnPlayerNameField02.setBounds(x + 12 + 30 + 50, y + 128 + 36 + 10 + 50, 120 - 30, 30);
		jbnPlayerName02.setBounds(x - 10 + 50, y + 128 + 36 + 36 + 10 + 50, 120+30, 30);
		/* 按钮添加监听 */
		jbnPlayerName02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!jbnPlayerNameField02.getText().equals("")) {
					selectedName[1] = jbnPlayerNameField02.getText();
					jbnPlayerNameField02.setEditable(false);
					jbnPlayerName02.setEnabled(false);

				}

			}
		});
		jp.add(jbnPlayerNameLabel02);
		jp.add(jbnPlayerNameField02);
		jp.add(jbnPlayerName02);
	}

	/**
	 * 
	 * createButton
	 * 
	 * */
	public JButton createButton(int x, int y, ImageIcon[] img, char keyLinstenr) {
		JButton add = new JButton("", img[0]);
		add.setPressedIcon(img[3]);
		add.setRolloverIcon(img[2]);
		add.setMnemonic(keyLinstenr);
		add.setBounds(x, y, img[0].getIconWidth(), img[0].getIconHeight());
		return add;
	}

	/**
	 * createButtonPanel
	 */
	private JPanel createButtonPanel() {
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		jbnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected[0] < 0 || selected[1] < 0) {
					Object[] options ={ "OK" }; 
					JOptionPane.showOptionDialog(null, "Please finish character setting!", "Oops",JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
				} else if (selectedName[0].equals("")
						|| selectedName[1].equals("")) {
					Object[] options ={ "OK" }; 
					JOptionPane.showOptionDialog(null, "Please finish name setting!", "Oops",JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
				} else {
					Object[] options ={ "Yes", "No", "cancel"}; 
					int response=JOptionPane.showOptionDialog(null, "R u ready?", "You've completed selection!",JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (response==0){
						startGame();
					}
				}
			}

			/**
			 * start Game
			 * */
			private void startGame() {
				setVisible(false);
				jFrameGame.setVisible(true);
				Control control = jFrameGame.getPanelGame().getControl();
				/* 处理玩家数据配置 */
				dealPlayers(control);
				/* 控制器启动 */
				control.start();
			}

			/**
			 * deal Players' settings 
			 */
			private void dealPlayers(Control control) {
				List<PlayerModel> tempPlayer = control.getPlayers();
				/* get name */
				tempPlayer.get(0).setName(selectedName[0]);
				tempPlayer.get(1).setName(selectedName[1]);
				/* get part */
				tempPlayer.get(0).setPart(selected[0]);
				tempPlayer.get(1).setPart(selected[1]);
				/* get other part */
				tempPlayer.get(0).setOtherPlayer(tempPlayer.get(1));
				tempPlayer.get(1).setOtherPlayer(tempPlayer.get(0));
			}

		});

		jp.add(jbnStart);
		return jp;
	}
}
