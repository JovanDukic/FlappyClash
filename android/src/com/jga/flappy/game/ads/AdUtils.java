package com.jga.flappy.game.ads;

import com.google.android.gms.ads.AdRequest;

public class AdUtils {

    private static final String TEST_DEVICE = "78F90FCE4CB9659A431FF081A240E056"; // HUAWEI

    public static AdRequest buildRequest() {
        return new AdRequest.Builder()
//                .addTestDevice(TEST_DEVICE)
                .build();
    }

    private AdUtils() {

    }
}
