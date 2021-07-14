package com.jga.flappy.game.enums;

public enum GameState {

    PLAY,
    GAME_OVER;

    // == public methods ==
    public boolean isPlay() {
        return this == PLAY;
    }

    public boolean isGameOver() {
        return this == GAME_OVER;
    }

}
