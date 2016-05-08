package com.denis.game.controller;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.denis.game.model.Fixtures.Coin;
import com.denis.game.model.Fixtures.ExtraBullets;
import com.denis.game.model.Fixtures.Lava;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Fixtures.AbstractStopBlock;
import com.denis.game.model.Fixtures.Door;
import com.denis.game.model.Fixtures.FinishBlock;
import com.denis.game.model.Fixtures.Key;
import com.denis.game.model.Mobs.BlackSquare;
import com.denis.game.model.Resource.Bits;
import com.denis.game.view.PlayScreen;

public class B2WorldCreator {

    private World world;
    private TiledMap map;

    private BodyDef bdef;
    private PolygonShape shape;
    private FixtureDef fdef;
    private Body body;

    private Array<BlackSquare> goombas;

    public B2WorldCreator(PlayScreen screen) {

        world = screen.getWorld();
        map = screen.getMap();

        // box2d elements
        bdef = new BodyDef();
        shape = new PolygonShape();
        fdef = new FixtureDef();

        // Ground initialization
        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Assets.PPM, (rect.getY() + rect.getHeight() / 2) / Assets.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / Assets.PPM, rect.getHeight() / 2 / Assets.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = Bits.GROUND_BIT;
            body.createFixture(fdef);
        }

        // Goombas initialization
        goombas = new Array<BlackSquare>();

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            goombas.add(new BlackSquare(screen, rect.getX() / Assets.PPM, rect.getY() / Assets.PPM));
        }

        // Key initialization
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Key(screen, rect);
        }

        // Door initialization
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Door(screen, rect);
        }

        // Coin initialization
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(screen, rect);
        }

        // Abstract blocks initialization
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new AbstractStopBlock(screen, rect);
        }

        // Lava initialization
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Lava(screen, rect);
        }

        // FinishBlock initialization
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new FinishBlock(screen, rect);
        }

        // Abstract stop block initialization
        for(MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new AbstractStopBlock(screen, rect);
        }

        // Extra bullets blocks initialization
        for(MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new ExtraBullets(screen, rect);
        }
    }

    public Array<BlackSquare> getGoombas() {
        return goombas;
    }
}
