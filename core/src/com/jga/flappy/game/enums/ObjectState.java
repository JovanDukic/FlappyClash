package com.jga.flappy.game.enums;

public enum ObjectState {

    JUMP,
    FALL;

    // == public methods ==
    public boolean isJumping() {
        return this == JUMP;
    }

    public boolean isFalling() {
        return this == FALL;
    }

}
