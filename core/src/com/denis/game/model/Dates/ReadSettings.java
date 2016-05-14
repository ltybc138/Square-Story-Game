package com.denis.game.model.Dates;

import com.badlogic.gdx.Gdx;
import com.denis.game.model.Resource.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.naming.Context;

public class ReadSettings {

    private static String sound;
    private static String isMusicOn;

    public ReadSettings() throws IOException {

        try {
            // reading music settings
            isMusicOn = Gdx.files.local(Settings.musicDestenition).readString();
            SettingsCache.setIsMusicOn(Boolean.parseBoolean(isMusicOn));

            // reading levels settings
        } catch(Exception e) {
            /*List<String> musicLines = Arrays.asList(Boolean.toString(Settings.defaultMusic));
            Path musicFile = Paths.get(Settings.musicDestenition);
            Files.write(musicFile, musicLines, Charset.forName("UTF-8"));

            List<String> soundLines = Arrays.asList(Float.toString(Settings.defaultSound));
            Path soundFile = Paths.get(Settings.settingsDestenition);
            Files.write(soundFile, soundLines, Charset.forName("UTF-8"));

            Settings.isMusicOn = Settings.defaultMusic;
            Settings.sound = Settings.defaultSound;*/


            // create settings file in android(Internal storage)

            // TODO realize creating new file in android
        }
    }
}
