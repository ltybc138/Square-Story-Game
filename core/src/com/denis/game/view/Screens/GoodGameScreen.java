package com.denis.game.view.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denis.game.controller.ScreenControlls.GoodGameControlls;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Textures;
import com.denis.game.view.AbstractGameScreen;
import com.denis.game.view.PlayScreen;

import java.lang.Override;

public class GoodGameScreen extends AbstractGameScreen {

    private GoodGameControlls goodGameControlls;
    private SpriteBatch batch;

    public GoodGameScreen(Game game) {
        super(game);

        batch = new SpriteBatch();
        goodGameControlls = new GoodGameControlls(batch);

        if(Assets.map > Assets.lastMap) {
            Assets.map = 1;
            Gdx.app.log("Game", "End");
        }
    }


    @Override
    public void render(float deltaTime) {

        // setting default background color
        Gdx.gl.glClearColor(Textures.colorR, Textures.colorG, Textures.colorB, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        goodGameControlls.draw();

        // back to menu button pressing treatment
        if(goodGameControlls.isBackButtonPressed())
            backToMenu();

        // new level button pressing treatment
        if(goodGameControlls.isNextLevelButtonPressed())
            newLevel();

    }

    public void newLevel() {
        game.setScreen(new PlayScreen(game));
        dispose();
    }

    public void backToMenu() {
        game.setScreen(new MenuScreen(game));
        dispose();
    }

    @Override
    public void resize(int width, int height) {
        goodGameControlls.resize(width, height);
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
