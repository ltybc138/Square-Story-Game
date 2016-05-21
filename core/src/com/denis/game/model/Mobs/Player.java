package com.denis.game.model.Mobs;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.denis.game.model.Dates.PlayerCache;
import com.denis.game.model.Fixtures.FireBall;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Bits;
import com.denis.game.model.Resource.PlayerStatements;
import com.denis.game.view.PlayScreen;

public class Player extends Sprite {

    // initialize peoples states
    public enum State {
        FALLING, JUMPING, STANDING, RUNNING
    }
    private State currentState;
    private State previousState;

    private PlayScreen screen;
    private World world;
    public Body b2body;
    private TextureRegion playerStand;
    private TextureRegion platerTextureRegion;

    private Animation playerRun;
    private Animation playerJump;

    private float stateTimer;
    private boolean runningRight;

    private Array<FireBall> fireballs;

    public Player(PlayScreen screen) {

        super(screen.getAtlas().findRegion("player"));

        this.world = screen.getWorld();
        this.screen = screen;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        // creating players texture
        platerTextureRegion = new TextureRegion(getTexture(), 16, 0, 16, 16);

        // setting players default settings
        definePlayer();
        setBounds(0, 0, 16 / Assets.PPM, 16 / Assets.PPM);
        //setRegion(playerStand);
        setRegion(platerTextureRegion);

        fireballs = new Array<FireBall>();
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));

        for(FireBall  ball : fireballs) {
            ball.update(dt);
            if(ball.isDestroyed())
                fireballs.removeValue(ball, true);
        }
    }

    private TextureRegion getFrame(float dt) {
        TextureRegion region;

        region = platerTextureRegion;

        // inverting players animation(textures) when he turned on the axis OY
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING) || b2body.getLinearVelocity().y < 0)
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else return State.STANDING;
    }

    private void definePlayer() {

        // initialize player like box2d element
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Assets.PPM, 32/ Assets.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.gravityScale = 1f;
        b2body = world.createBody(bdef);

        // creating fixture definition on player

        PolygonShape shape = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2(-6, 6).scl(1 / Assets.PPM); // -6, 0
        vertices[1] = new Vector2(6, 6).scl(1 / Assets.PPM); // 6, 0
        vertices[2] = new Vector2(5, -6).scl(1 / Assets.PPM);
        vertices[3] = new Vector2(-5, -6).scl(1 / Assets.PPM);
        shape.set(vertices);

        FixtureDef fdef = new FixtureDef();
        fdef.filter.categoryBits = Bits.PLAYER_BIT;
        fdef.filter.maskBits = Bits.GROUND_BIT |
                Bits.FINISH_BIT |
                Bits.ENEMY_BIT |
                Bits.OBJECT_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef);

        // creating players bitFixture
        PolygonShape bitFixture = new PolygonShape();

        Vector2[] bitFixtureVertice = new Vector2[4];
        bitFixtureVertice[0] = new Vector2(-3, 6).scl(1 / Assets.PPM); // -3, 0
        bitFixtureVertice[1] = new Vector2(3, 6).scl(1 / Assets.PPM); // 3, 0
        bitFixtureVertice[2] = new Vector2(2, -6).scl(1 / Assets.PPM);
        bitFixtureVertice[3] = new Vector2(-2, -6).scl(1 / Assets.PPM);

        bitFixture.set(bitFixtureVertice);
        fdef.shape = bitFixture;
        fdef.isSensor = true;

        // adding players bitFixture in his fixture
        b2body.createFixture(fdef).setUserData("bitFixture");
    }

    public void fire() {
        fireballs.add(new FireBall(screen, b2body.getPosition().x, b2body.getPosition().y, runningRight ? true : false));
    }

    public void draw(Batch batch) {
        super.draw(batch);
        for(FireBall ball : fireballs)
            ball.draw(batch);
    }
}
