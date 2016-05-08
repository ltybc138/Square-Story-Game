package com.denis.game.model.Fixtures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.InteractiveTileObject;
import com.denis.game.model.Resource.Bits;
import com.denis.game.view.PlayScreen;

public class Door extends InteractiveTileObject{
    public Door(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Bits.DOOR_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Door", "Collision");
        if(Assets.keysCollected > 2) {
            setCategoryFilter(Bits.DESTROYED_BIT);
            getCell().setTile(null);
        }
        //Hud.addScore(1000);
    }
}

