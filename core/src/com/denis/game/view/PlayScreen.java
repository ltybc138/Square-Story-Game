package com.denis.game.view;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denis.game.Main;
import com.denis.game.controller.B2WorldCreator;
import com.denis.game.controller.Controller;
import com.denis.game.controller.WorldContactListener;
import com.denis.game.model.Dates.PlayerCache;
import com.denis.game.model.Dates.SettingsCache;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Enemy;
import com.denis.game.model.Resource.Levels;
import com.denis.game.model.Mobs.Player;
import com.denis.game.model.Resource.PlayerStatements;
import com.denis.game.model.Resource.Settings;
import com.denis.game.model.Resource.Sounds;
import com.denis.game.model.Resource.Textures;
import com.denis.game.view.Screens.GameOverScreen;
import com.denis.game.view.Screens.GoodGameScreen;
import com.denis.game.view.Screens.MenuScreen;


public class PlayScreen extends AbstractGameScreen implements Screen {

    private SpriteBatch batch;

    private TextureAtlas atlas;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    public Controller controller;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    public B2WorldCreator creator;

    private Music music;

    private Player player;

    private WorldContactListener listener;

    private static AssetManager manager;

    public static int howManyBalls = 0;

    public PlayScreen(Game game) {
        super(game);

        batch = new SpriteBatch();

        atlas = new TextureAtlas(Textures.mobsData);

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Assets.V_WIDTH / Assets.PPM, Assets.V_HEIGHT / Assets.PPM, gamecam);

        hud = new Hud(batch);

        mapLoader = new TmxMapLoader();
        setLevel(Assets.map);

        renderer = new OrthogonalTiledMapRenderer(map, 1 / Assets.PPM);
        gamecam.position.set(2 + 1 / Assets.CamConst, gamePort.getWorldHeight(), 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this);

        player = new Player(this);

        world.setContactListener(new WorldContactListener());

        controller = new Controller(batch);

        Gdx.app.log("Music:", Boolean.toString(SettingsCache.getIsMusicOn()));
        Gdx.app.log("Sound:", Float.toString(SettingsCache.getSound()));

        // creating game music
        if(SettingsCache.getIsMusicOn()) {

            manager = new AssetManager();
            manager.load(Sounds.music, Music.class);
            manager.finishLoading();

            music = manager.get(Sounds.music, Music.class);
            music.setLooping(true);
            music.setVolume(SettingsCache.getSound());
            music.play();
        }
    }

    public void setLevel(int level) {
        switch(level) {
            case 1:
                map = mapLoader.load(Levels.level1);
                break;
            case 2:
                map = mapLoader.load(Levels.level2);
                break;
            case 3:
                map = mapLoader.load(Levels.level3);
                break;
            case 4:
                map = mapLoader.load(Levels.level4);
                break;
            case 5:
                map = mapLoader.load(Levels.level5);
                break;
            case 6:
                map = mapLoader.load(Levels.level6);
                break;
            case 7:
                map = mapLoader.load(Levels.level7);
                break;
            case 8:
                map = mapLoader.load(Levels.level8);
                break;
            case 9:
                map = mapLoader.load(Levels.level9);
                break;
            case 10:
                map = mapLoader.load(Levels.level10);
                break;
            case 11:
                map = mapLoader.load(Levels.level11);
                break;
            case 12:
                map = mapLoader.load(Levels.level12);
                break;
            case 13:
                map = mapLoader.load(Levels.level13);
                break;
            case 14:
                map = mapLoader.load(Levels.level14);
                break;
            case 15:
                map = mapLoader.load(Levels.level15);
                break;
        }
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    private void handleInput(float dt) {

        // set the controller settings based on devise
        if(Gdx.app.getType() == Application.ApplicationType.Desktop) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.getState() != Player.State.JUMPING)
                player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
                player.b2body.applyLinearImpulse(new Vector2(0.07f, 0), player.b2body.getWorldCenter(), true);
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.07f, 0), player.b2body.getWorldCenter(), true);
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                howManyBalls++;
                if(howManyBalls == 1)
                    player.fire();
            } else howManyBalls = 0;

        }else {
            if(controller.isUpPressed() && player.getState() != Player.State.JUMPING)
                player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
            if(controller.isRightPressed() && player.b2body.getLinearVelocity().x <= 2)
                player.b2body.applyLinearImpulse(new Vector2(0.07f, 0), player.b2body.getWorldCenter(), true);
            if(controller.isLeftPressed() && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.07f, 0), player.b2body.getWorldCenter(), true);
            if(controller.isShotPressed()) {
                howManyBalls++;
                if(howManyBalls == 1)
                    player.fire();
            } else howManyBalls = 0;
        }
        if(Assets.isPlayerHaveBeated) {
            player.b2body.applyLinearImpulse(new Vector2(0, 3f), player.b2body.getWorldCenter(), true);
            Assets.isPlayerHaveBeated = false;
            hud.downHealth(25);
        }

    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        hud.update(dt);
        for(Enemy enemy : creator.getGoombas())
            enemy.update(dt);
        player.update(dt);

        // setting camera position based on players position
        //if(player.b2body.getPosition().x > (2 + 1 / Assets.CamConst) && player.b2body.getPosition().x < ((2 + 1 / Assets.CamConst) + 2 / Assets.CPPM - 5 / Assets.PPM))
        if(player.b2body.getPosition().x > (2 + 1 / Assets.CamConst))
            gamecam.position.x = player.b2body.getPosition().x;

        //gamecam.position.y = gamePort.getWorldHeight() / 2;
        gamecam.position.y = player.b2body.getPosition().y + Assets.V_HEIGHT / Assets.PPM / 6;
        gamecam.update();


        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        //Gdx.gl.glClearColor(0.9725f, 0.6941f, 0.5843f, 0);
        Gdx.gl.glClearColor(0.2078f, 0.3608f, 0.4902f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        renderer.render();

        b2dr.render(world, gamecam.combined);

        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();
        player.draw(batch);
        for(Enemy enemy : creator.getGoombas())
            enemy.draw(batch);
        // TODO Random level
        batch.end();

        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(Hud.isBackButtonPressed())
            Gdx.app.log("Play Screen Button", "Pressed");

        // creating android control buttons
        if(Gdx.app.getType() == Application.ApplicationType.Android)
            controller.draw();

        // good end level
        if(Assets.isFinishBlockBroke) {
            Assets.nextLevel = false;
            if(Assets.map < Assets.lastMap) {
                Assets.map++;
                Assets.nextLevel = true;
            }
            if(!Levels.isLevelsOpened)
                Levels.isLevelsOpened = true;
            openLevel();
            Gdx.app.log("Map statement = ", Integer.toString(Assets.map));
            Assets.isFinishBlockBroke = false;
            goodGame();
        }

        // bad end game
        if(hud.worldTimer <= 0 || hud.health <=0 || PlayerCache.getIsPlayerDied()) {
            gameOver();
            PlayerCache.setIsPlayerDied(false);
        }

        // back button pressed
        if(hud.isBackButtonPressed()){
            backToMenu();
        }
    }

    public void goodGame() {
        game.setScreen(new GoodGameScreen(game));
        if(SettingsCache.getIsMusicOn()) {
            music.dispose();
            manager.dispose();
        }
        Assets.keysCollected = 0;
        PlayerCache.setBallsCount(0);
        dispose();
    }

    public void gameOver() {
        game.setScreen(new GameOverScreen(game));
        if(SettingsCache.getIsMusicOn()) {
            music.dispose();
            manager.dispose();
        }
        Assets.keysCollected = 0;
        PlayerCache.setBallsCount(0);
        dispose();
    }

    public void backToMenu() {
        game.setScreen(new MenuScreen(game));
        if(SettingsCache.getIsMusicOn()) {
            music.dispose();
            manager.dispose();
        }
        Assets.keysCollected = 0;
        PlayerCache.setBallsCount(0);
        dispose();
    }

    public void openLevel() {
        switch(Assets.map) {
            case 1:
                Levels.is1LevelOpen = true;
                break;
            case 2:
                Levels.is2LevelOpen = true;
                break;
            case 3:
                Levels.is3LevelOpen = true;
                break;
            case 4:
                Levels.is4LevelOpen = true;
            // TODO more levels
        }
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        controller.resize(width, height);
    }

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.stage.dispose();
        controller.dispose();
        batch.dispose();
    }
}
