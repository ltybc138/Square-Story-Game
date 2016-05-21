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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denis.game.Main;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Settings;
import com.denis.game.model.Resource.Textures;

public class GameOverControls {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera cam;

    private BitmapFont font;
    private Label.LabelStyle labelStyle;

    private Label label;

    private TextButton backButton;
    private Texture buttonTexture;
    private TextButton.TextButtonStyle textButtonStyle;

    public boolean backButtonPressed;

    public GameOverControls(SpriteBatch sb) {
        cam = new OrthographicCamera();

        viewport = new FitViewport(Assets.V_WIDTH, Assets.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        font = Main.font;
        labelStyle = new Label.LabelStyle(font, Color.WHITE);

        Table table = new Table();
        table.setPosition(Assets.V_WIDTH / 2, Assets.V_HEIGHT / 3 * 2);

        label = new Label("Game Over", labelStyle);
        label.setSize(300 / Assets.CPPM, 100 / Assets.CPPM);

        // creating backButton and adding inputListener on it
        buttonTexture = new Texture(Gdx.files.internal(Textures.button));
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        backButton = new TextButton("Back", textButtonStyle);
        backButton.setSize(150 / Assets.CPPM, 70 / Assets.CPPM);
        backButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backButtonPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                backButtonPressed = false;
            }
        });

        // adding elements on the screen
        table.add();
        table.row().pad(20, 0, 0, 0);
        table.add(label);
        table.row().pad(50);
        table.add(backButton).size(backButton.getWidth(), backButton.getHeight());
        table.row();

        stage.addActor(table);
    }

    public void draw() {
        stage.draw();
    }

    public boolean isBackButtonPressed() {
        return backButtonPressed;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void dispose() {
        stage.dispose();
    }
}


