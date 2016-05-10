package com.denis.game.model.Dates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SettingsCache {

    private static Preferences prefs = Gdx.app.getPreferences("My Preferences");

    public SettingsCache() {
        prefs.putBoolean("isMusicOn", true); // set music looping to true
        prefs.putFloat("sound", 0.5f); // set sound in a half
        prefs.flush();
    }

    // setters
    public static void setSound(float sound) {
        prefs.putFloat("sound", sound);
        prefs.flush();
    }

    public static void setIsMusicOn(boolean isMusicOn) {
        prefs.putBoolean("isMusicOn", isMusicOn);
        prefs.flush();
    }

    // getters
    public static float getSound() {
        return prefs.getFloat("sound");
    }

    public static boolean getIsMusicOn() {
        return prefs.getBoolean("isMusicOn");
    }
}