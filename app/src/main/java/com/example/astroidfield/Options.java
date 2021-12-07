package com.example.astroidfield;

public class Options {
    private int playerSkinIndex;
    private int volume;
    private boolean gameModeTilt;

    //default options
    private static final boolean GAME_MODE_TILT = false;
    private static final int PLAYER_SKIN_INDEX = 0;
    private static final int  VOLUME = 50;

    public int getPlayerSkinIndex() {
        return playerSkinIndex;
    }
    public int getVolume() {
        return volume;
    }
    public boolean isGameModeTilt() {
        return gameModeTilt;
    }

    public void setPlayerSkinIndex(int playerSkinIndex) {
        this.playerSkinIndex = playerSkinIndex;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public void setGameModeTilt(boolean gameModeTilt) {
        this.gameModeTilt = gameModeTilt;
    }

    public void setDefaultOptions() {
        this.setPlayerSkinIndex(PLAYER_SKIN_INDEX);
        this.setVolume(VOLUME);
        this.setGameModeTilt(GAME_MODE_TILT);
    }
}
