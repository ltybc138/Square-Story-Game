package com.denis.game.model.Fixtures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.InteractiveTileObject;
import com.denis.game.model.Resource.Bits;
import com.denis.game.view.PlayScreen;

public class AbstractStopBlock extends InteractiveTileObject {

    public AbstractStopBlock(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Bits.ABSTRACT_STOP_BLOCK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Abstract block", "Collision");
    }
}
