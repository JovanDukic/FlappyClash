package com.jga.flappy.game.enums;

public enum PlayerSkin {

    A(false, false),
    B(false, false),
    C(false, false),
    D(false, false),
    E(false, false),
    F(false, false),
    G(false, false),
    H(false, false),
    I(false, false),
    J(false, false),
    K(false, false),
    L(false, false),
    M(false, false),
    N(false, false),
    O(false, false),
    P(false, false),
    Q(false, false),
    R(false, false),
    DEFAULT(true, true);

    PlayerSkin(boolean posses, boolean play) {
        this.posses = posses;
        this.play = play;
    }

    private boolean posses;
    private boolean play;

    public boolean isPosses() {
        return posses;
    }

    public boolean isPlay(){
        return play;
    }

    public void setPosses(boolean posses) {
        this.posses = posses;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }
}
