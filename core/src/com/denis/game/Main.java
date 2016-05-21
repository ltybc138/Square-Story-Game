package com.denis.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.denis.game.model.Dates.SettingsCache;
import com.denis.game.model.Resource.Settings;
import com.denis.game.view.Screens.MenuScreen;

public class Main extends Game {

	public SpriteBatch batch;
	public static BitmapFont font;

	@Override
	public void create () {

		new SettingsCache();

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
}
