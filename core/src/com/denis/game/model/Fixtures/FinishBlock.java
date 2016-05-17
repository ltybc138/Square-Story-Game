package com.denis.game.model.Fixtures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.denis.game.Main;
import com.denis.game.model.Dates.PlayerCache;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.InteractiveTileObject;
import com.denis.game.model.Resource.Bits;
import com.denis.game.view.PlayScreen;

public class FinishBlock extends InteractiveTileObject {
    public Main game;

    public FinishBlock(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Bits.FINISH_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Finish block", "Collision");
        Gdx.app.log("Coins: ", Float.toString(PlayerCache.getCoinCount()));
        PlayerCache.setCoinCount(0);
        Assets.isFinishBlockBroke = true;
    }
}

