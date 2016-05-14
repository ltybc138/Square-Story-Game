package com.denis.game.model.Fixtures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.denis.game.model.Dates.PlayerCache;
import com.denis.game.model.InteractiveTileObject;
import com.denis.game.model.Resource.Bits;
import com.denis.game.model.Resource.PlayerStatements;
import com.denis.game.view.PlayScreen;

public class Lava extends InteractiveTileObject{

    public Lava(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Bits.LAVA_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Lava", "Collision");
        PlayerCache.setIsPlayerDied(true);
    }
}
