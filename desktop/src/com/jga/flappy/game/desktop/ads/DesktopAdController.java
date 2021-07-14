package com.jga.flappy.game.desktop.ads;

import com.badlogic.gdx.utils.Logger;
import com.jga.flappy.game.ads.AdController;

public class DesktopAdController implements AdController {

    // == constants ==
    private static final Logger log = new Logger(DesktopAdController.class.getSimpleName());

    // == public methods ==
    @Override
    public void showBanner() {
        log.debug("show banner");
    }

    @Override
    public void showInterstitial() {
        log.debug("show interstitial");
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void setVisibleBanner() {
        log.debug("set visible banner");
    }

    @Override
    public void setInVisibleBanner() {
        log.debug("set invisible banner");
    }
}
