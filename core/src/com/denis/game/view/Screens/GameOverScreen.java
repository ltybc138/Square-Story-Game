package com.denis.game.view.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denis.game.controller.ScreenControlls.GameOverControls;
import com.denis.game.model.Resource.Textures;
import com.denis.game.view.AbstractGameScreen;

import java.lang.Override;

public class GameOverScreen extends AbstractGameScreen {

    private GameOverControls gameOverControlls;
    private SpriteBatch batch;

    public GameOverScreen(Game game) {
        super(game);

        batch = new SpriteBatch();
        gameOverControlls = new GameOverControls(batch);
    }


    @Override
    public void render(float deltaTime) {

        //setting default background color
        Gdx.gl.glClearColor(Textures.colorR, Textures.colorG, Textures.colorB, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameOverControlls.draw();

        // back to menu button pressing treatment
        if(gameOverControlls.isBackButtonPressed())
            backToMenu();

    }

    public void backToMenu() {
        game.setScreen(new MenuScreen(game));
    }

    @Override
    public void resize(int width, int height) {
        gameOverControlls.resize(width, height);
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
