package com.example.astroidfield;

public class Options {
    private int playerSkinIndex;
    private int volume;
    private boolean gameModeTilt;

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
}
