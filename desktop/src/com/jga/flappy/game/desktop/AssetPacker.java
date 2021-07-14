package com.jga.flappy.game.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {

    private static final String RAW_ASSETS_PATH = "desktop/assets";
    private static final String ASSETS_PATH = "android/assets";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.pot = true;

        TexturePacker.process(
                settings,
                RAW_ASSETS_PATH,
                ASSETS_PATH + "/utils",
                "utils");
    }
}
