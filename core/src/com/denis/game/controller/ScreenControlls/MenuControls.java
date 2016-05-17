package com.denis.game.controller.ScreenControlls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denis.game.Main;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Levels;
import com.denis.game.model.Resource.Textures;

public class MenuControls {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera cam;

    private BitmapFont font;

    public boolean settingsPressed;
    public boolean startPressed;
    public boolean levelsMenuPressed;

    private Label.LabelStyle labelStyle;

    private Label label;
    private TextButton startGame;
    private TextButton settings;
    private TextButton levelsMenu;

    private Texture buttonTexture;
    private Texture closedButtonTexture;

    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton.TextButtonStyle closedTextButtonStyle;

    public MenuControls(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(Assets.V_WIDTH, Assets.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        font = Main.font;
        labelStyle = new Label.LabelStyle(font, Color.WHITE);

        Table table = new Table();
        table.setPosition(Assets.V_WIDTH / 2, Assets.V_HEIGHT / 3 * 2);

        label = new Label("Menu screen", labelStyle);
        label.setSize(300 / Assets.CPPM, 100 / Assets.CPPM);

        // creating button style
        buttonTexture = new Texture(Gdx.files.internal(Textures.button));
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        closedButtonTexture = new Texture(Gdx.files.internal(Textures.closedButton));
        closedTextButtonStyle = new TextButton.TextButtonStyle();
        closedTextButtonStyle.font = font;
        closedTextButtonStyle.up = new TextureRegionDrawable(new TextureRegion(closedButtonTexture));

        // creating startGame button and adding inputListener on it
        startGame = new TextButton("Start Game", textButtonStyle);
        startGame.setSize(300 / Assets.CPPM, 75 / Assets.CPPM);
        startGame.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                startPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                startPressed = false;
            }
        });

        // creating level settings button and adding inputListener on it
        settings = new TextButton("Settings", textButtonStyle);
        settings.setSize(300 / Assets.CPPM, 75 / Assets.CPPM);
        settings.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                settingsPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                settingsPressed = false;
            }
        });

        // creating level levelsMenu button and adding inputListener on it
        if(Levels.isLevelsOpened)
            levelsMenu = new TextButton("Levels", textButtonStyle);
        else
            levelsMenu = new TextButton("Levels", closedTextButtonStyle);
        levelsMenu.setSize(300 / Assets.CPPM, 75 / Assets.CPPM);
        levelsMenu.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                levelsMenuPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                levelsMenuPressed = false;
            }
        });

        // adding elements on the screen
        table.add();
        table.row().pad(50, 0, 0, 0);
        table.add(label);
        table.row().padBottom(10);
        table.add(startGame).size(startGame.getWidth(), startGame.getHeight());
        table.row().padBottom(10);
        table.add(levelsMenu).size(levelsMenu.getWidth(), levelsMenu.getHeight());
        table.row().padBottom(10);
        table.add(settings).size(settings.getWidth(), settings.getHeight());
        table.row().padBottom(10);
        table.debug(); // TODO delete later

        stage.addActor(table);
    }

    public void draw() {
        stage.draw();
    }

    public boolean isStartPressed() {
        return startPressed;
    }

    public boolean isSettingsPressed() {
        return settingsPressed;
    }

    public boolean isLevelsMenuPressed() {
        return levelsMenuPressed;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void dispose() {
        stage.dispose();
    }
}
