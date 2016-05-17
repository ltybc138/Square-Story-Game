package com.denis.game.model.Fixtures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.denis.game.controller.HUDWithControls;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.InteractiveTileObject;
import com.denis.game.model.Resource.Bits;
import com.denis.game.view.PlayScreen;

public class Key extends InteractiveTileObject {

    public Key(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Bits.KEY_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Key", "Collision");
        setCategoryFilter(Bits.DESTROYED_BIT);
        getCell().setTile(null);
        HUDWithControls.addScore(500);
        Assets.keysCollected++;
    }
}