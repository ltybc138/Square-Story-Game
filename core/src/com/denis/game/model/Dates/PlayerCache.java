package com.denis.game.model.Dates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PlayerCache {

    private static Preferences prefs = Gdx.app.getPreferences("Player preferences");

    public PlayerCache() {
        // setting default player settings
        prefs.putBoolean("isPlayerDied", false);
        prefs.putFloat("gravityScale", 1f);
        prefs.putInteger("coinCount", 0);
        prefs.putInteger("ballsCount", 0);
        prefs.putInteger("maxBalls", 10);
        prefs.putInteger("howManyBalls", 0);
        prefs.flush();
    }

    // getters
    public static Boolean getIsPlayerDied() {
        return prefs.getBoolean("isPlayerDied");
    }

    public static Float getGravityScale() {
        return prefs.getFloat("gravityScale");
    }

    public static Integer getCoinCount() {
        return prefs.getInteger("coinCount");
    }

    public static Integer getBallsCount() {
        return prefs.getInteger("ballsCount");
    }

    public static Integer getMaxBalls() {
        return prefs.getInteger("maxBalls");
    }

    public static Integer getHowManyBalls() {
        return prefs.getInteger("howManyBalls");
    }

    // setters
    public static void setIsPlayerDied(boolean isPlayerDied) {
        prefs.putBoolean("isPlayerDied", isPlayerDied);
    }

    public static void setGravityScale(float gravityScale) {
        prefs.putFloat("gravityScale", gravityScale);
    }

    public static void setCoinCount(int coinCount) {
        prefs.putInteger("coinCount", coinCount);
    }

    public static void setBallsCount(int ballsCount) {
        prefs.putInteger("ballsCount", ballsCount);
    }

    public static void setMaxBalls(int maxBalls) {
        prefs.putInteger("maxBalls", maxBalls);
    }

    public static void setHowManyBalls(int howManyBalls) {
        prefs.putInteger("howManyBalls", howManyBalls);
    }

}
