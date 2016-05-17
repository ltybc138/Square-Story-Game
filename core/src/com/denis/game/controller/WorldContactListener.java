package com.denis.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Enemy;
import com.denis.game.model.InteractiveTileObject;
import com.denis.game.model.Resource.Bits;
import com.denis.game.view.PlayScreen;

public class WorldContactListener implements ContactListener{

    private PlayScreen screen;

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        // checking square hit
        if(fixA.getUserData() == "square" || fixB.getUserData() == "square") {
            Fixture head = fixA.getUserData() == "square" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }

        // doing something if square hit
        switch (cDef) {
            // enemy rotation
            case Bits.ENEMY_BIT | Bits.GROUND_BIT:
                if(fixA.getFilterData().categoryBits == Bits.ENEMY_BIT)
                    ((Enemy) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy) fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Bits.ENEMY_BIT | Bits.ENEMY_BIT | Bits.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == Bits.ENEMY_BIT)
                    ((Enemy) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy) fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Bits.ENEMY_BIT | Bits.DOOR_BIT:
                if(fixA.getFilterData().categoryBits == Bits.ENEMY_BIT)
                    ((Enemy) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy) fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Bits.ENEMY_BIT | Bits.ABSTRACT_STOP_BLOCK_BIT:
                if(fixA.getFilterData().categoryBits == Bits.ENEMY_BIT)
                    ((Enemy) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy) fixB.getUserData()).reverseVelocity(true, false);
                break;

            // killing player by enemy
            case Bits.PLAYER_BIT | Bits.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == Bits.ENEMY_BIT) {
                    ((Enemy) fixA.getUserData()).reverseVelocity(true, false);
                    Assets.isPlayerHaveBeated = true;
                Gdx.app.log("PLAYER", "DIED");}
                break;

            // key collision
            case Bits.PLAYER_BIT | Bits.KEY_BIT:
                break;

            // lava collision
            case Bits.PLAYER_BIT | Bits.LAVA_BIT:
                break;

            // coin collision
            case Bits.PLAYER_BIT | Bits.COIN_BIT:
                break;

            // killing enemies using fire balls
            case Bits.FIREBALL_BIT | Bits.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == Bits.ENEMY_BIT) {
                    ((Enemy) fixA.getUserData()).hitOnHead();
                    HUDWithControls.addScore(1000);
                } else {
                    ((Enemy) fixB.getUserData()).hitOnHead();
                    HUDWithControls.addScore(1000);
                }
                break;

            case Bits.PLAYER_BIT | Bits.EXTRA_BULLET:
                if(fixA.getFilterData().categoryBits == Bits.EXTRA_BULLET) {
                    ((Enemy) fixA.getUserData()).hitOnHead();
                    HUDWithControls.addScore(1000);
                } else {
                    ((Enemy) fixB.getUserData()).hitOnHead();
                    HUDWithControls.addScore(1000);
                }


            /*case Bits.FIREBALL_BIT | Bits.ENEMY_HEAD_BIT:
                if (fixA.getFilterData().categoryBits == Bits.ENEMY_HEAD_BIT) {
                    ((Enemy) fixA.getUserData()).hitOnHead();
                    HUDWithControls.addScore(1000);
                } else {
                    ((Enemy) fixB.getUserData()).hitOnHead();
                    HUDWithControls.addScore(1000);
                }
                break;*/
        }

        Fixture fixC = contact.getFixtureA();
        Fixture fixD = contact.getFixtureB();

        int cDef1 = fixC.getFilterData().categoryBits | fixD.getFilterData().categoryBits;

        // checking bitFixture hit
        if(fixC.getUserData() == "bitFixture" || fixD.getUserData() == "bitFixture") {
            Fixture bitFixture = fixC.getUserData() == "bitFixture" ? fixC : fixD;
            Fixture player = bitFixture == fixC ? fixD : fixC;

            if (player.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(player.getUserData().getClass())) {
                ((InteractiveTileObject) player.getUserData()).onHeadHit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
