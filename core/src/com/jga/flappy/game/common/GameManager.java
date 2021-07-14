package com.jga.flappy.game.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.jga.flappy.game.config.GameConfig;
import com.jga.flappy.game.enums.PlayerSkin;
import com.jga.flappy.game.enums.SoundState;
import com.jga.flappy.game.screen.FlappyGame;

public class GameManager {

    // == constants ==
    public static final GameManager INSTANCE = new GameManager();

    private static final String HIGH_SCORE_KEY = "highScore";
    private static final String COIN_SUM_KEY = "coins";

    private static final int SKIN_PRICE = 500;

    // == attributes ==
    private int score;
    private int totalScore;
    private int highScore;
    private int displayScore;
    private int displayHighScore;

    private int coins;
    private int coinSum;
    private int multiple = GameConfig.STARTING_MULTIPLE;

    private Preferences prefs;

    private PlayerSkin playerSkin = PlayerSkin.DEFAULT;
    private SoundState soundState = SoundState.SOUND_ON;

    // == constructor ==
    public GameManager() {
        prefs = Gdx.app.getPreferences(FlappyGame.class.getName());

        highScore = prefs.getInteger(HIGH_SCORE_KEY, 0);
        coinSum = prefs.getInteger(COIN_SUM_KEY, 0);

        displayHighScore = highScore;

        loadSkins();
    }

    public void restart() {
        score = 0;
        displayScore = 0;
        totalScore = 0;
        coins = 0;
    }

    public void addCoins() {
        coins++;
    }

    public void updateHighScore() {
        totalScore = score + coins * GameConfig.COIN_MULTIPLE;

        if (totalScore < highScore) {
            return;
        }

        highScore = totalScore;
        displayHighScore = highScore;

        prefs.putInteger(HIGH_SCORE_KEY, highScore);
        prefs.flush();
    }

    public void updateScore(float delta) {
        score = score + (int) (multiple * delta);

        if (highScore < score) {
            highScore = score;
        }

        if (displayScore < score) {
            displayScore = score;
        }

        if (displayHighScore < highScore) {
            displayHighScore = highScore;
        }
    }

    public void updateCoins() {
        coinSum = coinSum + coins;

        prefs.putInteger(COIN_SUM_KEY, coinSum);
        prefs.flush();
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public int getDisplayHighScore() {
        return displayHighScore;
    }

    public String getDisplayHighScoreText() {
        return String.valueOf(displayHighScore);
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getCoins() {
        return String.valueOf(coins);
    }

    public int getCoinSumNumber() {
        return coinSum;
    }

    public String getCoinSum() {
        return String.valueOf(coinSum);
    }

    public void updateGameManager() {
        updateHighScore();
        updateCoins();
    }

    public void confirmPurchase(PlayerSkin skin) {
        coinSum = coinSum - SKIN_PRICE;
        skin.setPosses(true);
        prefs.putBoolean(skin.toString(), true);
        prefs.flush();
    }

    public PlayerSkin getPlayerSkin() {
        return playerSkin;
    }

    public void setPlayerSkin(PlayerSkin playerSkin) {
        this.playerSkin = playerSkin;
        saveSkin();
    }

    public void saveSkin() {
        resetSkins();
        playerSkin.setPlay(true);
        prefs.putBoolean(playerSkin.toString() + " ", playerSkin.isPlay());
        prefs.flush();
    }

    private void resetSkins() {
        for (PlayerSkin skin : PlayerSkin.values()) {
            skin.setPlay(false);
            prefs.putBoolean(skin.toString() + " ", skin.isPlay());
        }
    }

    private void loadSkins() {
        for (PlayerSkin skin : PlayerSkin.values()) {
            skin.setPosses(prefs.getBoolean(skin.toString(), false));

            skin.setPlay(prefs.getBoolean(skin.toString() + " ", false));

            if (skin.isPlay()) {
                playerSkin = skin;
            }
        }
    }

    public SoundState getSoundState() {
        return soundState;
    }

    public void setSoundState(SoundState soundState) {
        this.soundState = soundState;
    }
}
