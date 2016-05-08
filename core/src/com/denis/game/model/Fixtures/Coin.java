package com.denis.game.model.Fixtures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.denis.game.model.InteractiveTileObject;
import com.denis.game.model.Resource.Bits;
import com.denis.game.model.Resource.PlayerStatements;
import com.denis.game.view.Hud;
import com.denis.game.view.PlayScreen;

public class Coin extends InteractiveTileObject {

    public Coin(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Bits.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin", "Collision");
        setCategoryFilter(Bits.DESTROYED_BIT);
        getCell().setTile(null);
        PlayerStatements.coinCount++;
        Hud.addScore(1000);
    }
}
