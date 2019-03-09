package model.card;

import context.GameState;
import model.PlayerModel;

/**
 *
 *
 *���˿�,����ʹ���ȥ���κεط���
 *
 *
 */

public class GoodLuckCard extends Card{

    public GoodLuckCard(PlayerModel owner) {
        super(owner);
        this.name = "GoodLuckCard";
        this.cName = "���˿�";
        this.price = 50;
    }

    @Override
    public int useCard() {
        return GameState.CARD_GOODLUCK;
    }

}
