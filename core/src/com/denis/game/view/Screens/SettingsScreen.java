package com.denis.game.view.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denis.game.controller.ScreenControlls.SettingsControlls;
import com.denis.game.model.Resource.Settings;
import com.denis.game.model.Dates.WriteSettings;
import com.denis.game.model.Resource.Textures;
import com.denis.game.view.AbstractGameScreen;

import java.lang.Override;

public class SettingsScreen extends AbstractGameScreen {

    private SettingsControlls settingsControlls;

    private SpriteBatch batch;

    private float changedSound;
    private boolean isMusicOn;

    public SettingsScreen(Game game) {
        super(game);

        batch = new SpriteBatch();
        settingsControlls = new SettingsControlls(batch);

        settingsControlls.soundSlider.setValue(Settings.sound);
        settingsControlls.checkBox.setChecked(Settings.isMusicOn);

    }


    @Override
    public void render(float deltaTime) {

        // setting default background color
        Gdx.gl.glClearColor(Textures.colorR, Textures.colorG, Textures.colorB, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        settingsControlls.draw();

        // back to menu button pressing treatment
        if(settingsControlls.isBackButtonPressed())
            backToMenu();

        changedSound = SettingsControlls.soundSlider.getValue() / 100;
        isMusicOn = SettingsControlls.checkBox.isChecked();

    }

    public void backToMenu() {
        game.setScreen(new MenuScreen(game));

        // change volume of background music if it changed on settings screen
        if(Settings.sound != changedSound)
            MenuScreen.background.setVolume(MenuScreen.id, changedSound / 2);

        // change volume of background music in play screen if it changed on settings screen
        if((Settings.sound != changedSound) || (Settings.isMusicOn != isMusicOn)) {
            Settings.sound = changedSound;
            Settings.isMusicOn = isMusicOn;
            new WriteSettings();
        }
    }

    @Override
    public void resize(int width, int height) {
        settingsControlls.resize(width, height);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void dispose() {

    }
}
