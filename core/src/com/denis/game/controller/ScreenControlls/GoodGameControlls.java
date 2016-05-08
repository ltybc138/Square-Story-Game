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

public class GoodGameControlls {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera cam;

    private BitmapFont font;
    private Label.LabelStyle labelStyle;

    private Label label;

    private TextButton backButton;
    private TextButton nextLevelButton;

    public boolean backButtonPressed;
    public boolean nextLevelButtonPressed;

    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton.TextButtonStyle closedTextButtonStyle;
    private Texture buttonTexture;
    private Texture closedButtonTexture;


    public GoodGameControlls(SpriteBatch sb) {
        cam = new OrthographicCamera();

        viewport = new FitViewport(Assets.V_WIDTH, Assets.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        font = Main.font;
        labelStyle = new Label.LabelStyle(font, Color.WHITE);

        Table table = new Table();
        table.setPosition(Assets.V_WIDTH / 2, Assets.V_HEIGHT / 3 * 2);

        label = new Label("Well done!!!", labelStyle);
        label.setSize(300 / Assets.CPPM, 100 / Assets.CPPM);

        // create textButton style
        buttonTexture = new Texture(Gdx.files.internal(Textures.button));
        closedButtonTexture = new Texture(Gdx.files.internal(Textures.closedButton));

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        closedTextButtonStyle = new TextButton.TextButtonStyle();
        closedTextButtonStyle.font = font;
        closedTextButtonStyle.up = new TextureRegionDrawable(new TextureRegion(closedButtonTexture));

        // creating backButton and adding inputListener on it
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

        // creating nextLevelButton and adding inputListener on it
        if(Assets.nextLevel) {
            nextLevelButton = new TextButton("NextLevel", textButtonStyle);
            nextLevelButton.setSize(300 / Assets.CPPM, 70 / Assets.CPPM);
            nextLevelButton.addListener(new InputListener() {

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    nextLevelButtonPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    nextLevelButtonPressed = false;
                }
            });
        } else{
            nextLevelButton = new TextButton("NextLevel", closedTextButtonStyle);
            nextLevelButton.setSize(300 / Assets.CPPM, 70 / Assets.CPPM);
        }

        // adding elements on the screen
        table.add();
        //table.row().pad(50, 0, 0, 0);
        table.add(label).pad(50, 0, 0, 150);
        table.row().pad(50, 50, 50 ,50);
        table.add(backButton).size(backButton.getWidth(), backButton.getHeight());
        table.add(nextLevelButton).size(nextLevelButton.getWidth(), nextLevelButton.getHeight());
        table.row();

        stage.addActor(table);
    }

    public void draw() {
        stage.draw();
    }

    public boolean isBackButtonPressed() {
        return backButtonPressed;
    }

    public boolean isNextLevelButtonPressed() {
        return nextLevelButtonPressed;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void dispose() {
        stage.dispose();
    }
}



