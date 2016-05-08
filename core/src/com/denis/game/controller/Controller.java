package com.denis.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Textures;

public class Controller {

    private Viewport viewport;
    private Stage stage;

    private OrthographicCamera cam;

    public boolean upPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean shotPressed;

    public Controller(SpriteBatch sb) {

        cam = new OrthographicCamera();

        viewport = new FitViewport(Assets.V_WIDTH, Assets.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        Table leftTable = new Table();
        leftTable.left().bottom();

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

        // adding elements on the screen
        leftTable.add();
        leftTable.row().pad(5, 10, 10, 5).padTop(50);
        leftTable.add(leftImage).size(leftImage.getWidth(), leftImage.getHeight());
        leftTable.add();
        leftTable.add(rightImage).size(rightImage.getWidth(), rightImage.getHeight());
        leftTable.add().pad(0, 140, 0, 0);
        leftTable.add(upImage).size(upImage.getWidth(), upImage.getHeight());
        leftTable.add(shotImage).size(shotImage.getWidth(), shotImage.getHeight());
        leftTable.row();

        stage.addActor(leftTable);
    }

    public void draw() {
        stage.draw();
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

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void dispose() {
        stage.dispose();
    }
}
