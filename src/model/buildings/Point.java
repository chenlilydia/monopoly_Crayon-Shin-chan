package model.buildings;

import model.PlayerModel;
import model.TextTipModel;
import context.GameState;
import control.Control;

public class Point extends Building {

	private int point;


	public Point(int posX, int posY, int point) {
		super(posX, posY);
		this.name = point + "µãÈ¯Î»";
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

	@Override
	public int getEvent() {
		return GameState.POINT_EVENT;
	}
}
