package com.jga.flappy.game.enums;

public enum UtilityType {

    DECREASE_PLAYER,
    SHIELD;

    // == public methods ==
    public boolean isDecreasePlayer(){
        return this == DECREASE_PLAYER;
    }

    public boolean isShield(){
        return this == SHIELD;
    }

}
