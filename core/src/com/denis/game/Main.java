package com.denis.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denis.game.model.Dates.ReadSettings;
import com.denis.game.model.Resource.Settings;
import com.denis.game.model.Resource.Sounds;
import com.denis.game.view.Screens.MenuScreen;

import java.io.IOException;

public class Main extends Game {

	public SpriteBatch batch;
	public static BitmapFont font;

	public static AssetManager manager;

	@Override
	public void create () {

		if(Gdx.app.getType() == Application.ApplicationType.Desktop)
			try {
				new ReadSettings();
			} catch (IOException e) {
				e.printStackTrace();
			}

		Gdx.app.log("Music:", Boolean.toString(Settings.isMusicOn));
		Gdx.app.log("Sound:", Float.toString(Settings.sound));

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal(Settings.font));

		manager = new AssetManager();
		manager.load(Sounds.music, Music.class);
		manager.finishLoading();

		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	public void dispose() {
		super.dispose();
		batch.dispose();
		font.dispose();
	}

	public void backToMenu() {
		this.setScreen(new MenuScreen(this));
	}
}
