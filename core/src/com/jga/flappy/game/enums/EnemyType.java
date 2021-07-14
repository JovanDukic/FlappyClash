package com.jga.flappy.game.enums;

public enum EnemyType {

    ENEMY,
    ENE;

    // == public methods
    public boolean isEnemy() {
        return this == ENEMY;
    }

    public boolean isEne() {
        return this == ENE;
    }

}
