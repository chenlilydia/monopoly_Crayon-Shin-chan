package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import control.Control;


import model.DiceModel;


/**
 * 
 * Dice layer
 * 
 */
@SuppressWarnings("serial")
public class Dice extends Layer {

	private Control control;
	private DiceModel dice;
	private DiceButton diceButton;
	

	protected Dice(int x, int y, int w, int h, Control control) {
		super(x, y, w, h);
		setLayout(null);
		this.control = control;
		this.dice = control.getDice();
		this.diceButton = new DiceButton(control, 105, 32);
		add(diceButton);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		this.paintDice(g, -12, -15);
		this.showDice();
		diceButton.update(g);
	}

	/**
	 * 
	 * Dice paint
	 * 
	 */
	private void paintDice(Graphics g, int i, int j) {
		// Dice move settings
		if (dice.getStartTick() < dice.getNowTick()
				&& dice.getNextTick() >= dice.getNowTick()) {
			dice.setDiceState(DiceModel.DICE_RUNNING);
		} else {
			dice.setDiceState(DiceModel.DICE_POINT);
		}

		if (dice.getDiceState() == DiceModel.DICE_POINT) {
			this.paintPoint(g, i, j);
		} else if (dice.getDiceState() == DiceModel.DICE_RUNNING) {
			this.paintRunning(g, i, j, dice.getNowTick() % 4 == 0);
		}
		g.setColor(Color.DARK_GRAY);
		g.setFont(new java.awt.Font("ÐÂËÎÌå", Font.BOLD, 14));
		g.drawString(dice.getRunning().getNowPlayer().getName() + ":", i + 150,
				j + 40);
	}

	public DiceButton getDiceButton() {
		return diceButton;
	}

	/**
	 * 
	 * Moving status
	 * 
	 */
	public void paintRunning(Graphics g, int x, int y, boolean change) {
		if (change) {
			dice.addImgCount(1);
		}
		Image temp = dice.getNowImg();
		g.drawImage(temp, x + 20, y + 20, x + temp.getWidth(null) + 30,
				y + temp.getHeight(null) + 30, 0, 0, temp.getWidth(null),
				temp.getHeight(null), null);
	}

	/**
	 * 
	 * Display face value
	 * 
	 */
	public void paintPoint(Graphics g, int x, int y) {
		Image temp = dice.getDicePoints()[dice.getPoint()];
		g.drawImage(temp, x + 20, y + 20, x + temp.getWidth(null) + 30,
				y + temp.getHeight(null) + 30, 0, 0, temp.getWidth(null),
				temp.getHeight(null), null);
	}

	/**
	 * 
	 * Dice button
	 * 
	 */
	private void showDice() {
		diceButton.setEnabled(dice.isShowDiceLabel());
	}

	@Override
	public void startPanel() {
	}

}
