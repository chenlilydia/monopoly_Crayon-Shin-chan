package model.card;

import context.GameState;
import model.PlayerModel;

/**
 *
 *
 *好运卡,可以使玩家去到任何地方。
 *
 *
 */

public class GoodLuckCard extends Card{

    public GoodLuckCard(PlayerModel owner) {
        super(owner);
        this.name = "GoodLuckCard";
        this.cName = "好运卡";
        this.price = 50;
    }

    @Override
    public int useCard() {
        return GameState.CARD_GOODLUCK;
    }

}
