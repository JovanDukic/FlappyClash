package com.jga.flappy.game.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jga.flappy.game.assets.AssetDescriptors;
import com.jga.flappy.game.config.GameConfig;
import com.jga.flappy.game.menu.MainMenu;
import com.jga.flappy.game.utils.GdxUtils;

public class LoadingScreen extends ScreenAdapter {

    private static final float PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH / 2f;
    private static final float PROGRESS_BAR_HEIGHT = 60f;

    private final FlappyGame game;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private float progress;
    private float waitTime = 0.75f;

    private boolean changeScreen;

    public LoadingScreen(FlappyGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        assetManager.load(AssetDescriptors.RENDERER_FONT);
        assetManager.load(AssetDescriptors.FONT);
        assetManager.load(AssetDescriptors.TITLE_FONT);
        assetManager.load(AssetDescriptors.FONT_32);
//        assetManager.load(AssetDescriptors.MENU_FONT_1);
        assetManager.load(AssetDescriptors.MENU_FONT_2);

        assetManager.load(AssetDescriptors.TEXTURES);
        assetManager.load(AssetDescriptors.UTILITY_ATLAS);

        assetManager.load(AssetDescriptors.UI_SKIN);
        assetManager.load(AssetDescriptors.WINDOW_SKIN);
        assetManager.load(AssetDescriptors.MERCH_SKIN);
        assetManager.load(AssetDescriptors.MERCH_SKIN_1);

//        assetManager.load(AssetDescriptors.COIN_SOUND);
//        assetManager.load(AssetDescriptors.JUMP_SOUND);
//        assetManager.load(AssetDescriptors.GAME_OVER_SOUND);
    }

    @Override
    public void render(float delta) {
        update(delta);

        GdxUtils.clearScreen();
        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.setAutoShapeType(true);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        draw();

        renderer.end();

        if (changeScreen) {
            game.setScreen(new MainMenu(game));
        }
    }

    private void update(float delta) {
        progress = assetManager.getProgress();

        if (assetManager.update()) {
            waitTime = waitTime - delta;

            if (waitTime <= 0) {
                changeScreen = true;
            }
        }
    }

    private void draw() {
        float progressBarX = (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f;
        float progressBarY = (GameConfig.HUD_HEIGHT - PROGRESS_BAR_HEIGHT) / 2f;

        renderer.setColor(Color.WHITE);
        renderer.rect(progressBarX, progressBarY,
                progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);

        renderer.setColor(Color.GREEN);
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.rect(progressBarX - 2.5f, progressBarY - 2.5f,
                PROGRESS_BAR_WIDTH + 5f, PROGRESS_BAR_HEIGHT + 5f);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
