package com.example.astroidfield;

public class Options {
    private int playerSkinIndex;
    private int volume;



    private boolean gameModeTilt;
    private int sensitivity;
    private int gameSpeed;

    //default options
    private static final boolean GAME_MODE_TILT = false;
    private static final int PLAYER_SKIN_INDEX = 0;
    private static final int  VOLUME = 50;
    private static final int SENSITIVITY = 5;
    private static final int GAME_SPEED = 2;

    public int getPlayerSkinIndex() {
        return playerSkinIndex;
    }
    public int getVolume() {
        return volume;
    }
    public boolean isGameModeTilt() {
        return gameModeTilt;
    }
    public int getSensitivity() {
        return sensitivity;
    }
    public int getGameSpeed() {
        return gameSpeed;
    }

    public Options setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
        return this;
    }
    public Options setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
        return this;
    }
    public Options setPlayerSkinIndex(int playerSkinIndex) {
        this.playerSkinIndex = playerSkinIndex;
        return this;
    }
    public Options setVolume(int volume) {
        this.volume = volume;
        return this;
    }
    public Options setGameModeTilt(boolean gameModeTilt) {
        this.gameModeTilt = gameModeTilt;
        return this;
    }

    public void setDefaultOptions() {
        this.setPlayerSkinIndex(PLAYER_SKIN_INDEX)
                .setVolume(VOLUME)
                .setGameModeTilt(GAME_MODE_TILT)
                .setGameSpeed(GAME_SPEED)
                .setSensitivity(SENSITIVITY);
    }
}
