package com.jga.flappy.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jga.flappy.game.ads.AdController;
import com.jga.flappy.game.desktop.ads.DesktopAdController;
import com.jga.flappy.game.screen.FlappyGame;

public class DesktopLauncher {

    private static final AdController AD_CONTROLLER = new DesktopAdController();


    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new FlappyGame(AD_CONTROLLER), config);
    }
}
