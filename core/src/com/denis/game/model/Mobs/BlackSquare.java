package com.denis.game.model.Mobs;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Enemy;
import com.denis.game.model.Resource.Bits;
import com.denis.game.view.PlayScreen;

public class BlackSquare extends Enemy {

    private float stateTime;
    private Animation walkAnimation;
    private TextureRegion enemyTextureRegion;
    private Array<TextureRegion> frames;
    private boolean setToDestroy;
    private boolean destroyed;

    public BlackSquare(PlayScreen screen, float x, float y) {

        super(screen, x, y);

        frames = new Array<TextureRegion>();

        // creating goomba animation
        //for (int i = 0; i < 2; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("bad_sqare"), 0, 0, 16, 16));

        //setting default settings about goomba
        walkAnimation = new Animation(0.4f, frames);
        //enemyTextureRegion = new TextureRegion(screen.getAtlas().findRegion("enemy"));

        stateTime = 0;
        setBounds(getX(), getY(), 16 / Assets.PPM, 16 / Assets.PPM);
        setToDestroy = false;
        destroyed = false;
    }

    public void update(float dt) {
        stateTime += dt;

        // if goomba killed set its texture to died
        if (setToDestroy && !destroyed) {
            world.destroyBody(b2body);
            destroyed = true;
            setRegion(new TextureRegion(screen.getAtlas().findRegion("bad_sqare"), 0, 0, 16, 16));
            stateTime = 0;
        } else if (!destroyed) {
            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion(walkAnimation.getKeyFrame(stateTime, true));
        }

    }

    @Override
    protected void defineEnemy() {

        // initialize goomba like box2d element
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        // creating fixture definition on goomba
        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(6 / Assets.PPM, 6 / Assets.PPM);

        fdef.filter.categoryBits = Bits.ENEMY_BIT;
        fdef.filter.maskBits = Bits.GROUND_BIT |
                Bits.ABSTRACT_STOP_BLOCK_BIT |
                Bits.FINISH_BIT |
                Bits.ENEMY_BIT |
                Bits.OBJECT_BIT |
                Bits.PLAYER_BIT |
                Bits.FIREBALL_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void draw(Batch batch) {
        if(!destroyed || stateTime < 1)
            super.draw(batch);
    }

    @Override
    public void hitOnHead() {
        setToDestroy = true;
    }

}

