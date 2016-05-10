package com.denis.game.model.Dates;

import com.badlogic.gdx.Gdx;
import com.denis.game.model.Resource.Settings;

public class WriteSettings {

    public WriteSettings() {

        Gdx.files.local(Settings.musicDestenition).writeString(String.valueOf(SettingsCache.getIsMusicOn()), false);
        Gdx.files.local(Settings.settingsDestenition).writeString(String.valueOf(SettingsCache.getSound()), false);

    }
}
