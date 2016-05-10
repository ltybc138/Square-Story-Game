package com.denis.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denis.game.model.Dates.ReadSettings;
import com.denis.game.model.Dates.SettingsCache;
import com.denis.game.model.Resource.Settings;
import com.denis.game.view.Screens.MenuScreen;

import java.io.IOException;

public class Main extends Game {

	public SpriteBatch batch;
	public static BitmapFont font;

	@Override
	public void create () {

		SettingsCache settingsCache = new SettingsCache();

		if(Gdx.app.getType() == Application.ApplicationType.Desktop)
			try {
				new ReadSettings();
			} catch (IOException e) {
				e.printStackTrace();
			}

		Gdx.app.log("Music:", Boolean.toString(SettingsCache.getIsMusicOn()));
		Gdx.app.log("Sound:", Float.toString(SettingsCache.getSound()));

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal(Settings.font));

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
