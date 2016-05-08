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
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denis.game.Main;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Settings;
import com.denis.game.model.Resource.Textures;

public class SettingsControlls {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera cam;

    private BitmapFont font;
    private Label.LabelStyle labelStyle;

    private Texture slider_background;
    private Texture slider_arrow;
    private Texture checkBoxOn;
    private Texture checkBoxOff;

    public static Slider soundSlider;

    private Label screenLabel;
    private Label soundLabel;
    private Label onOffMusicLabel;

    public static CheckBox checkBox;

    private TextButton backButton;

    public boolean backButtonPressed;

    private Texture buttonTexture;
    private TextButton.TextButtonStyle textButtonStyle;

    public SettingsControlls(SpriteBatch sb) {
        cam = new OrthographicCamera();

        viewport = new FitViewport(Assets.V_WIDTH, Assets.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        font = Main.font;
        labelStyle = new Label.LabelStyle(font, Color.WHITE);

        Table topTable = new Table();
        topTable.setPosition(Assets.V_WIDTH / 2, Assets.V_HEIGHT / 8 * 7);

        Table settingsTable = new Table();
        settingsTable.setPosition(Assets.V_WIDTH / 2, Assets.V_HEIGHT / 2);
        settingsTable.left();
        settingsTable.center();

        // creating sound slider
        slider_background = new Texture(Gdx.files.internal(Textures.slider_background));
        slider_arrow = new Texture(Gdx.files.internal(Textures.slider_arrow));
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = new TextureRegionDrawable(new TextureRegion(slider_background));
        sliderStyle.knob = new TextureRegionDrawable(new TextureRegion(slider_arrow));
        soundSlider = new Slider(1f, 100f, 1f, false, sliderStyle);
        soundSlider.setValue(Settings.sound);

        // creating on/off music checkbox
        checkBoxOn = new Texture(Gdx.files.internal(Textures.checkBoxOn));
        checkBoxOff = new Texture(Gdx.files.internal(Textures.checkBoxOff));
        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.checkboxOn = new TextureRegionDrawable(new TextureRegion(checkBoxOn));
        checkBoxStyle.checkboxOff = new TextureRegionDrawable(new TextureRegion(checkBoxOff));
        checkBoxStyle.font = font;
        checkBoxStyle.fontColor = Color.WHITE;
        checkBox = new CheckBox("", checkBoxStyle);
        checkBox.setChecked(Settings.isMusicOn);

        // creating music checkbox label
        onOffMusicLabel = new Label("Music: ", labelStyle);
        onOffMusicLabel.setSize(200 / Assets.CPPM, 66 / Assets.CPPM);

        // creating sound label
        soundLabel = new Label("Sound:", labelStyle);
        soundLabel.setSize(200 / Assets.CPPM, 66 / Assets.CPPM);

        // creating settings screen label
        screenLabel = new Label("Settings screen", labelStyle);
        screenLabel.setSize(300 / Assets.CPPM, 100 / Assets.CPPM);

        // creating  backButton and adding inputListener on it
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
        topTable.row().colspan(3);
        topTable.add(backButton).size(backButton.getWidth(), backButton.getHeight()).padRight(50);
        topTable.add(screenLabel).padRight(30);
        topTable.add().pad(0, 30, 0, 30);

        settingsTable.row();
        settingsTable.add(soundLabel);
        settingsTable.add(soundSlider);
        settingsTable.row();
        settingsTable.add(onOffMusicLabel);
        settingsTable.add(checkBox);
        settingsTable.row();

        stage.addActor(topTable);
        stage.addActor(settingsTable);
    }

    public static boolean getCheckbox() {
        return checkBox.isChecked();
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


