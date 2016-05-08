package com.denis.game.view.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denis.game.controller.ScreenControlls.MenuControlls;
import com.denis.game.model.Resource.Levels;
import com.denis.game.model.Resource.Settings;
import com.denis.game.model.Resource.Sounds;
import com.denis.game.model.Resource.Textures;
import com.denis.game.view.AbstractGameScreen;
import com.denis.game.view.PlayScreen;

import java.lang.Override;

public class MenuScreen extends AbstractGameScreen {

    private MenuControlls menuControlls;

    private SpriteBatch batch;

    public static Sound background;
    public static long id;

    public MenuScreen(Game game) {
        super(game);

        batch = new SpriteBatch();
        menuControlls = new MenuControlls(batch);

        if(!Settings.isBackgroundMusicOn) {
            Settings.isBackgroundMusicOn = true;
            background = Gdx.audio.newSound(Gdx.files.internal(Sounds.welcomeMusic));
            id = background.play(Settings.sound / 2);
        }
    }


    @Override
    public void render (float deltaTime) {

        // setting default background color
        Gdx.gl.glClearColor(Textures.colorR, Textures.colorG, Textures.colorB, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        menuControlls.draw();

        // back to menu button pressing treatment
        if(menuControlls.isStartPressed()) {
            goToPlayScreen();
            dispose();
        }

        // settings button pressing treatment
        if(menuControlls.isSettingsPressed())
            goToSettings();

        // levels screen button pressing treatment
        if(menuControlls.isLevelsMenuPressed() && Levels.isLevelsOpened)
            goToLevelsScreen();
    }

    public void goToLevelsScreen() {
        game.setScreen(new LevelsScreen(game));
    }

    public void goToPlayScreen() {
        game.setScreen(new PlayScreen(game));
    }

    public void goToSettings() {
        game.setScreen(new SettingsScreen(game));
    }

    @Override
    public void resize (int width, int height) {
        menuControlls.resize(width, height);
    }

    @Override
    public void show() { }

    @Override
    public void hide() { }

    @Override
    public void pause() { }

    @Override
    public void dispose() {
        if(Settings.isBackgroundMusicOn) {
            background.dispose();
            Settings.isBackgroundMusicOn = false;
        }
    }
}