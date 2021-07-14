package com.jga.flappy.game.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> RENDERER_FONT =
            new AssetDescriptor<BitmapFont>(AssetPaths.RENDERER_FONT, BitmapFont.class);

    public static final AssetDescriptor<BitmapFont> FONT =
            new AssetDescriptor<BitmapFont>(AssetPaths.FONT, BitmapFont.class);

    public static final AssetDescriptor<BitmapFont> TITLE_FONT =
            new AssetDescriptor<BitmapFont>(AssetPaths.TITLE_FONT, BitmapFont.class);

    public static final AssetDescriptor<BitmapFont> FONT_32 =
            new AssetDescriptor<BitmapFont>(AssetPaths.FONT_32, BitmapFont.class);

    public static final AssetDescriptor<BitmapFont> MENU_FONT_1 =
            new AssetDescriptor<BitmapFont>(AssetPaths.MENU_FONT_1, BitmapFont.class);

    public static final AssetDescriptor<BitmapFont> MENU_FONT_2 =
            new AssetDescriptor<BitmapFont>(AssetPaths.MENU_FONT_2, BitmapFont.class);

    public static final AssetDescriptor<TextureAtlas> TEXTURES =
            new AssetDescriptor<TextureAtlas>(AssetPaths.TEXTURES, TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> UTILITY_ATLAS =
            new AssetDescriptor<>(AssetPaths.UTILITY_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<Skin> UI_SKIN =
            new AssetDescriptor<Skin>(AssetPaths.UI_SKIN, Skin.class);

    public static final AssetDescriptor<Skin> WINDOW_SKIN =
            new AssetDescriptor<Skin>(AssetPaths.WINDOW_SKIN, Skin.class);

    public static final AssetDescriptor<Skin> MERCH_SKIN =
            new AssetDescriptor<Skin>(AssetPaths.MERCH_SKIN, Skin.class);

    public static final AssetDescriptor<Skin> MERCH_SKIN_1 =
            new AssetDescriptor<Skin>(AssetPaths.MERCH_SKIN_1, Skin.class);

    /*public static final AssetDescriptor<Sound> COIN_SOUND =
            new AssetDescriptor<Sound>(AssetPaths.COIN_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> JUMP_SOUND =
            new AssetDescriptor<Sound>(AssetPaths.JUMP_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> GAME_OVER_SOUND =
            new AssetDescriptor<Sound>(AssetPaths.GAME_OVER_SOUND, Sound.class);*/

    private AssetDescriptors() {

    }
}
