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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Textures;

public class PlayScreenButtons {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera cam;

    private Texture buttonTexture;
    private Button.ButtonStyle buttonStyle;
    private Button button;

    public static boolean isButtonPressed;

    public PlayScreenButtons(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(Assets.V_WIDTH, Assets.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setPosition(Assets.V_WIDTH / 2, Assets.V_HEIGHT / 3 * 2);

        // creating button style
        buttonTexture = new Texture(Gdx.files.internal(Textures.button));
        buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        // creating level levelsMenu button and adding inputListener on it
        button = new Button(buttonStyle);
        button.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        button.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isButtonPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isButtonPressed = false;
            }
        });

        // adding elements on the screen

        table.add(button).top().padLeft(Assets.V_WIDTH - Assets.V_WIDTH / 15).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        stage.addActor(table);
    }

    public void draw() {
        stage.draw();
    }

    public boolean isButtonPressed() {
        return isButtonPressed;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void dispose() {
        stage.dispose();
    }
}
