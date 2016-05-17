package com.denis.game.controller;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denis.game.model.Dates.SettingsCache;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.PlayerStatements;
import com.denis.game.model.Resource.Textures;

public class HUDWithControls {

    private Viewport viewport;
    private Stage stage;

    private OrthographicCamera cam;

    public Integer worldTimer;
    public float timeCount;
    public static Integer score;
    public static Integer health;
    public static String level = "1";

    private Label countdownLabel;
    public static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label playerLabel;
    private Label healthLabel;
    public static Label healthCountLabel;

    public boolean upPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean shotPressed;
    public boolean backPressed;

    public HUDWithControls(SpriteBatch sb) {

        cam = new OrthographicCamera();

        viewport = new FitViewport(Assets.V_WIDTH, Assets.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        worldTimer = 200;
        timeCount = 0;
        score = 0;
        health = PlayerStatements.health;

        Gdx.input.setInputProcessor(stage);

        // creating a hud table
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Table healthTable = new Table();
        Table scoreTable = new Table();
        Table timeTable = new Table();

        // creating controls table
        Table controlsTable = new Table();
        controlsTable.left().bottom();

        // creating upButton and adding inputListener on it
        final Image upImage = new Image(new Texture(Textures.upControls));
        upImage.setSize(75 / Assets.CPPM, 75 / Assets.CPPM);
        upImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = true;
                upImage.setSize(90 / Assets.CPPM, 90 / Assets.CPPM);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
                upImage.setSize(75 / Assets.CPPM, 75 / Assets.CPPM);
            }
        });

        // creating rightButton and adding inputListener on it
        final Image rightImage = new Image(new Texture(Textures.rightControls));
        rightImage.setSize(75 / Assets.CPPM, 75 / Assets.CPPM);
        rightImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                rightImage.setSize(90 / Assets.CPPM, 90 / Assets.CPPM);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
                rightImage.setSize(75 / Assets.CPPM, 75 / Assets.CPPM);
            }
        });

        // creating leftButton and adding inputListener on it
        final Image leftImage = new Image(new Texture(Textures.leftControls));
        leftImage.setSize(75 / Assets.CPPM, 75 / Assets.CPPM);
        leftImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                leftImage.setSize(90 / Assets.CPPM, 90 / Assets.CPPM);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
                leftImage.setSize(75 / Assets.CPPM, 75 / Assets.CPPM);
            }
        });

        // creating leftButton and adding inputListener on it
        final Image shotImage = new Image(new Texture(Textures.shotControls));
        shotImage.setSize(75 / Assets.CPPM, 75 / Assets.CPPM);
        shotImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                shotPressed = true;
                shotImage.setSize(90 / Assets.CPPM, 90 / Assets.CPPM);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                shotPressed = false;
                shotImage.setSize(75 / Assets.CPPM, 75 / Assets.CPPM);
            }
        });

        // creating backButton and adding inputListener on it
        final Image backImage = new Image(new Texture(Textures.shotControls));
        backImage.setSize(100 / Assets.CPPM, 100 / Assets.CPPM);
        backImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                backPressed = false;
            }
        });

        healthLabel = new Label("HEALTH", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthCountLabel = new Label(String.format("%03d", health), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        healthTable.add(healthLabel);
        healthTable.row();
        healthTable.add(healthCountLabel);

        scoreTable.add(playerLabel);
        scoreTable.row();
        scoreTable.add(scoreLabel);

        timeTable.add(timeLabel);
        timeTable.row();
        timeTable.add(countdownLabel);

        table.row().colspan(4);
        table.add(healthTable).expandX();
        table.add(scoreTable).expandX();
        table.add(timeTable).expandX();
        table.add(backImage).expandX().right().padRight(10).size(60 / Assets.CPPM, 60 / Assets.CPPM);
        table.row();
        table.debug();

        stage.addActor(table);

        if(Gdx.app.getType() == Application.ApplicationType.Android && !SettingsCache.getIsGyroscopeOn()) {
            controlsTable.row().pad(5, 10, 10, 5).padTop(50);
            controlsTable.add(leftImage).size(leftImage.getWidth(), leftImage.getHeight());
            controlsTable.add(rightImage).size(rightImage.getWidth(), rightImage.getHeight());
            controlsTable.add().pad(0, 140, 0, 0);
            controlsTable.add(upImage).size(upImage.getWidth(), upImage.getHeight());
            controlsTable.add(shotImage).size(shotImage.getWidth(), shotImage.getHeight());
            controlsTable.row();

            stage.addActor(controlsTable);
        }
    }

    public void draw() {
        stage.draw();
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }

        if(backPressed)
            Gdx.app.log("Button", "Pressed");
    }

    public static void changeLevel(String newLevel) {
        level = newLevel;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isShotPressed() {
        return shotPressed;
    }

    public boolean isBackPressed() {
        return backPressed;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public static void addScore(int count) {
        score += count;
    }

    public static void downHealth(int hp) {
        health -= hp;
    }

    public void dispose() {
        stage.dispose();
    }
}
