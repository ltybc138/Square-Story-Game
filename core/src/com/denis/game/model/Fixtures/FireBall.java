package com.denis.game.model.Fixtures;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Bits;
import com.denis.game.view.PlayScreen;

public class FireBall extends Sprite {

    private PlayScreen screen;
    private World world;

    public Array<TextureRegion> frames;
    public Animation fireAnimation;

    public float stateTime;
    public boolean destroyed;
    public boolean setToDestroy;
    public boolean fireRight;

    private Body b2body;

    public FireBall(PlayScreen screen, float x, float y, boolean fireRight){

        this.fireRight = fireRight;
        this.screen = screen;
        this.world = screen.getWorld();

        frames = new Array<TextureRegion>();

        //for(int i = 0; i < 1; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("player"), 0, 0, 8, 8));

        //fireAnimation = new Animation(0.2f, frames);
        fireAnimation = new Animation(0, frames);
        setRegion(fireAnimation.getKeyFrame(0));
        setBounds(x, y, 8 / Assets.PPM, 5 / Assets.PPM);

        defineFireBall();
    }

    public void defineFireBall() {

        BodyDef bdef = new BodyDef();
        bdef.position.set(fireRight ? getX() + 12 / Assets.PPM : getX() - 12 / Assets.PPM, getY());
        bdef.type = BodyDef.BodyType.DynamicBody;

        if(!world.isLocked())
            b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(3 / Assets.PPM);
        fdef.filter.categoryBits = Bits.FIREBALL_BIT;
        fdef.filter.maskBits = Bits.GROUND_BIT |
                Bits.FINISH_BIT |
                Bits.OBJECT_BIT |
                Bits.ENEMY_BIT |
                Bits.DOOR_BIT |
                Bits.LAVA_BIT;

        fdef.shape = shape;
        fdef.restitution = 1;
        fdef.friction = 0;
        b2body.createFixture(fdef).setUserData(this);
        b2body.setLinearVelocity(new Vector2(fireRight ? 2 : -2, 3.5f));
    }

    public void update(float dt){

        stateTime += dt;

        setRegion(fireAnimation.getKeyFrame(stateTime, true));
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        if((stateTime > 5 || setToDestroy) && !destroyed) {
            world.destroyBody(b2body);
            destroyed = true;
        }

        if(b2body.getLinearVelocity().y > 1.5f)
            b2body.setLinearVelocity(b2body.getLinearVelocity().x, 1.5f);

        if((fireRight && b2body.getLinearVelocity().x < 0) || (!fireRight && b2body.getLinearVelocity().x > 0))
            setToDestroy();
    }

    public void setToDestroy(){
        setToDestroy = true;
    }

    public boolean isDestroyed(){
        return destroyed;
    }
}
