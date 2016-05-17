package com.denis.game.view.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denis.game.controller.HUDWithControls;
import com.denis.game.controller.ScreenControlls.LevelsControls;
import com.denis.game.model.Dates.SettingsCache;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Levels;
import com.denis.game.model.Resource.Textures;
import com.denis.game.view.AbstractGameScreen;
import com.denis.game.view.PlayScreen;

public class LevelsScreen extends AbstractGameScreen {

    private LevelsControls levelsControlls;

    private SpriteBatch batch;
    private HUDWithControls hud;

    public LevelsScreen(Game game) {
        super(game);

        batch = new SpriteBatch();
        levelsControlls = new LevelsControls(batch);
    }


    @Override
    public void render (float deltaTime) {

        // setting default background
        Gdx.gl.glClearColor(Textures.colorR, Textures.colorG, Textures.colorB, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        levelsControlls.draw();

        // back to menu button pressing treatment
        if(levelsControlls.isBackButtonPressed()) {
            backToMenu();
            dispose();
        }

        // level 1 button pressing treatment
        if(levelsControlls.isLevel1Pressed() && Levels.is1LevelOpen) {
            Assets.map = 1;
            runGame();
            dispose();
        }

        // level 2 button pressing treatment
        if(levelsControlls.isLevel2Pressed() && Levels.is2LevelOpen) {
            Assets.map = 2;
            runGame();
            dispose();
        }

        // level 3 button pressing treatment
        if(levelsControlls.isLevel3Pressed() && Levels.is3LevelOpen) {
            Assets.map = 3;
            runGame();
            dispose();
        }

        // level 4 button pressing treatment
        if(levelsControlls.isLevel4Pressed() && Levels.is4LevelOpen) {
            Assets.map = 4;
            runGame();
            dispose();
        }

        // level 5 button pressing treatment
        if(levelsControlls.isLevel5Pressed() && Levels.is5LevelOpen) {
            Assets.map = 5;
            runGame();
            dispose();
        }

        // level 6 button pressing treatment
        if(levelsControlls.isLevel6Pressed() && Levels.is6LevelOpen) {
            Assets.map = 6;
            runGame();
            dispose();
        }

        // level 7 button pressing treatment
        if(levelsControlls.isLevel7Pressed() && Levels.is7LevelOpen) {
            Assets.map = 7;
            runGame();
            dispose();
        }

        // level 8 button pressing treatment
        if(levelsControlls.isLevel8Pressed() && Levels.is8LevelOpen) {
            Assets.map = 8;
            runGame();
            dispose();
        }
        // level 9 button pressing treatment
        if(levelsControlls.isLevel9Pressed() && Levels.is9LevelOpen) {
            Assets.map = 9;
            runGame();
            dispose();
        }

        // level 10 button pressing treatment
        if(levelsControlls.isLevel10Pressed() && Levels.is10LevelOpen) {
            Assets.map = 10;
            runGame();
            dispose();
        }

        // level 11 button pressing treatment
        if(levelsControlls.isLevel11Pressed() && Levels.is11LevelOpen) {
            Assets.map = 11;
            runGame();
            dispose();
        }

        // level 12 button pressing treatment
        if(levelsControlls.isLevel12Pressed() && Levels.is12LevelOpen) {
            Assets.map = 12;
            runGame();
            dispose();
        }

        // level 13 button pressing treatment
        if(levelsControlls.isLevel13Pressed() && Levels.is13LevelOpen) {
            Assets.map = 13;
            runGame();
            dispose();
        }

        // level 14 button pressing treatment
        if(levelsControlls.isLevel14Pressed() && Levels.is14LevelOpen) {
            Assets.map = 14;
            runGame();
            dispose();
        }

        // level 15 button pressing treatment
        if(levelsControlls.isLevel15Pressed() && Levels.is15LevelOpen) {
            Assets.map = 15;
            runGame();
            dispose();
        }
    }

    public void runGame() {
        game.setScreen(new PlayScreen(game));
    }

    public void backToMenu() {
        game.setScreen(new MenuScreen(game));
    }

    @Override
    public void resize (int width, int height) {
        levelsControlls.resize(width, height);
    }

    @Override
    public void show() { }

    @Override
    public void hide() { }

    @Override
    public void pause() { }

    @Override
    public void dispose() {
        if(SettingsCache.getIsMusicOn())
            MenuScreen.musicDispose();
    }
}