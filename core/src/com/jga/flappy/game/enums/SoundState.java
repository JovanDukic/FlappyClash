package com.jga.flappy.game.enums;

public enum SoundState {

    SOUND_ON,
    SOUND_OFF;

    // == public methods ==
    public boolean isSoundOn() {
        return this == SOUND_ON;
    }

}
