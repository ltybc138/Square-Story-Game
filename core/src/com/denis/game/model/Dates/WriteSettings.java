package com.denis.game.model.Dates;

import com.badlogic.gdx.Gdx;
import com.denis.game.model.Resource.Settings;

public class WriteSettings {

    public WriteSettings() {

        Gdx.files.local(Settings.musicDestenition).writeString(String.valueOf(Settings.isMusicOn), false);
        Gdx.files.local(Settings.settingsDestenition).writeString(String.valueOf(Settings.sound), false);

    }
}
