package com.denis.game.view.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denis.game.controller.ScreenControlls.SettingsControls;
import com.denis.game.model.Dates.SettingsCache;
import com.denis.game.model.Resource.Textures;
import com.denis.game.view.AbstractGameScreen;

import java.lang.Override;

public class SettingsScreen extends AbstractGameScreen {

    private SettingsControls settingsControlls;

    private SpriteBatch batch;

    private boolean isMusicOn;
    private boolean isGyroscopeOn;

    public SettingsScreen(Game game) {
        super(game);

        batch = new SpriteBatch();
        settingsControlls = new SettingsControls(batch);

        settingsControlls.musicCheckBox.setChecked(SettingsCache.getIsMusicOn());
        settingsControlls.gyroscopeCheckBox.setChecked(SettingsCache.getIsGyroscopeOn());
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

        isMusicOn = SettingsControls.getMusicCheckBox();
        isGyroscopeOn = SettingsControls.getGyroscopeCheckBox();

    }

    public void backToMenu() {
        // change volume of background music in play screen if it changed on settings screen
        if(SettingsCache.getIsMusicOn() != isMusicOn) {
            SettingsCache.setIsMusicOn(isMusicOn);
            if(!isMusicOn)
                MenuScreen.musicDispose();
        }
        SettingsCache.setIsGyroscopeOn(isGyroscopeOn);
        game.setScreen(new MenuScreen(game));
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
