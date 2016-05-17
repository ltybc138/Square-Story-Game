package com.denis.game.controller.ScreenControlls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denis.game.Main;
import com.denis.game.model.Dates.SettingsCache;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Textures;

public class SettingsControls {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera cam;

    private BitmapFont font;
    private Label.LabelStyle labelStyle;
    private Label.LabelStyle errorLabelStyle;

    private Texture checkBoxOn;
    private Texture checkBoxOff;

    private Label screenLabel;
    private Label soundLabel;
    private Label onOffMusicLabel;
    private Label onOffGyroscope;
    private Label gyroscopeNotAvail;

    public static CheckBox musicCheckBox;
    public static CheckBox gyroscopeCheckBox;

    private TextButton backButton;

    public boolean backButtonPressed;
    private boolean gyroscopeAvail = Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope);

    private Texture buttonTexture;
    private TextButton.TextButtonStyle textButtonStyle;

    public SettingsControls(SpriteBatch sb) {
        cam = new OrthographicCamera();

        viewport = new FitViewport(Assets.V_WIDTH, Assets.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        font = Main.font;
        labelStyle = new Label.LabelStyle(font, Color.WHITE);
        if(!gyroscopeAvail)
            errorLabelStyle = new Label.LabelStyle(font, Color.GRAY);


        Table topTable = new Table();
        topTable.setPosition(Assets.V_WIDTH / 2, Assets.V_HEIGHT / 8 * 7);

        Table settingsTable = new Table();
        settingsTable.setPosition(Assets.V_WIDTH / 2, Assets.V_HEIGHT / 2);
        settingsTable.left();
        settingsTable.center();

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

        // create checkbox style
        checkBoxOn = new Texture(Gdx.files.internal(Textures.checkBoxOn));
        checkBoxOff = new Texture(Gdx.files.internal(Textures.checkBoxOff));
        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.checkboxOn = new TextureRegionDrawable(new TextureRegion(checkBoxOn));
        checkBoxStyle.checkboxOff = new TextureRegionDrawable(new TextureRegion(checkBoxOff));
        checkBoxStyle.font = font;
        checkBoxStyle.fontColor = Color.WHITE;

        // creating on/off music checkbox
        musicCheckBox = new CheckBox("", checkBoxStyle);
        musicCheckBox.setChecked(SettingsCache.getIsMusicOn());

        // creating music checkbox label
        onOffMusicLabel = new Label("Music: ", labelStyle);
        onOffMusicLabel.setSize(200 / Assets.CPPM, 66 / Assets.CPPM);

        // creating on/off gyroscope checkbox
        gyroscopeCheckBox = new CheckBox("", checkBoxStyle);
        if(gyroscopeAvail)
            gyroscopeCheckBox.setChecked(true);
        else gyroscopeCheckBox.setChecked(false);

        // creating gyroscope checkbox label
        onOffGyroscope = new Label("Gyroscope: ", labelStyle);
        onOffGyroscope.setSize(200 / Assets.CPPM, 66 / Assets.CPPM);

        // if gyroscope is not avail in your devise
        if(!gyroscopeAvail) {
            gyroscopeNotAvail = new Label("Not avail", errorLabelStyle);
            gyroscopeNotAvail.setSize(100 / Assets.CPPM, 33 / Assets.CPPM);
        }


        // adding elements on the screen
        topTable.row().colspan(3);
        topTable.add(backButton).size(backButton.getWidth(), backButton.getHeight()).padRight(50);
        topTable.add(screenLabel).padRight(30);
        topTable.add().pad(0, 30, 0, 30);
        topTable.debug(); // TODO delete later

        settingsTable.row();
        settingsTable.add(onOffMusicLabel);
        settingsTable.add(musicCheckBox);
        settingsTable.row();
        settingsTable.add(onOffGyroscope);
        settingsTable.add(gyroscopeCheckBox);
        if(!gyroscopeAvail)
            settingsTable.add(gyroscopeNotAvail);
        settingsTable.row();
        settingsTable.debug(); // TODO delete later

        stage.addActor(topTable);
        stage.addActor(settingsTable);
    }

    public static boolean getMusicCheckBox() {
        return musicCheckBox.isChecked();
    }

    public static boolean getGyroscopeCheckBox() {
        return gyroscopeCheckBox.isChecked();
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


