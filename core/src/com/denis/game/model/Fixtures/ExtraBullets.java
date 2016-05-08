package com.denis.game.model.Fixtures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.denis.game.model.InteractiveTileObject;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Bits;
import com.denis.game.view.PlayScreen;

public class ExtraBullets extends InteractiveTileObject{
    public ExtraBullets(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Bits.EXTRA_BULLET);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Extra bullet block", "Collision");
        Assets.maxBalls++;
        setCategoryFilter(Bits.DESTROYED_BIT);
        getCell().setTile(null);
    }
}
