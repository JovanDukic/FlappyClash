package com.jga.flappy.game.ads;

public interface AdController {

    void showBanner();

    void setVisibleBanner();

    void setInVisibleBanner();

    void showInterstitial();

    boolean isNetworkConnected();

}
