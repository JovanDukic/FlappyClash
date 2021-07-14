package com.jga.flappy.game;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.jga.flappy.game.ads.AdController;
import com.jga.flappy.game.ads.AdUnitIds;
import com.jga.flappy.game.ads.AdUtils;
import com.jga.flappy.game.screen.FlappyGame;

public class AndroidLauncher extends AndroidApplication implements AdController {

	private AdView bannerAdView;
	private InterstitialAd interstitialAd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initAds();
		initUi();
	}

	private void initAds() {
		bannerAdView = new AdView(this);
		bannerAdView.setAdUnitId(AdUnitIds.BANNER_ID);
		bannerAdView.setAdSize(AdSize.SMART_BANNER);

		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId(AdUnitIds.INTERSTITIAL_ID);

		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				loadInterstitial();
			}
		});

		loadInterstitial();
	}

	private void initUi() {
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView = initializeForView(new FlappyGame(this), config);

		RelativeLayout layout = new RelativeLayout(this);

		// ad view params
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT
		);

		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		// game view params
		RelativeLayout.LayoutParams gameParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT
		);

//        gameParams.addRule(RelativeLayout.ABOVE, bannerAdView.getId());

		// adParams and gameParams must be passed
		layout.addView(gameView, gameParams);
		layout.addView(bannerAdView, adParams);

		// must be called otherwise nothing will be seen
		setContentView(layout);
	}

	@Override
	protected void onResume() {
		super.onResume();
		bannerAdView.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		bannerAdView.pause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		bannerAdView.destroy();
	}

	@Override
	public void showBanner() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				loadBanner();
			}
		});
	}

	private void loadBanner() {
		if (isNetworkConnected()) {
			bannerAdView.loadAd(AdUtils.buildRequest());
		}
	}

	@Override
	public void showInterstitial() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				showInterstitialInternal();
			}
		});
	}

	private void showInterstitialInternal() {
		if (interstitialAd.isLoaded()) {
			interstitialAd.show();
		}
	}

	private void loadInterstitial() {
		if (isNetworkConnected()) {
			interstitialAd.loadAd(AdUtils.buildRequest());
		}
	}

	@Override
	public void setVisibleBanner() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAdView.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public void setInVisibleBanner() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAdView.setVisibility(View.INVISIBLE);
			}
		});
	}

	@Override
	public boolean isNetworkConnected() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}
}
