package com.example.paul.googleadmobad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class LevelOneActivity extends AppCompatActivity {

    private Button mLevelTwoButton;
    private InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;
    private AdView mBannerAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        mLevelTwoButton = ((Button) findViewById(R.id.level_two_button));
        mBannerAd = (AdView) findViewById(R.id.banner_adView);
        mLevelTextView = (TextView) findViewById(R.id.level_text_view);
        mLevelTwoButton.setEnabled(true);
        mLevelTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showIntAdd();
            }
        });
        mInterstitialAd = createNewIntAd();
        loadIntAdd();
        showBannerAd();
    }

    private void showBannerAd() {

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("754DB6521943676637AE86202C5ACE52")
                .build();
        mBannerAd.loadAd(adRequest);
    }

    private InterstitialAd createNewIntAd() {
        InterstitialAd intAd = new InterstitialAd(this);
        intAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        intAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mLevelTwoButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mLevelTwoButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                levelTwo();
            }
        });
        return intAd;
    }

    private void showIntAdd() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            levelTwo();
        }

    }

    private void loadIntAdd() {
        mLevelTwoButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("754DB6521943676637AE86202C5ACE52")
                .build();
        mInterstitialAd.loadAd(adRequest);

    }

    private void levelTwo() {
        mLevelTwoButton.setVisibility(View.INVISIBLE);
        mLevelTextView.setText("Level Two");
        Toast t = Toast.makeText(this, "you have reached level two", Toast.LENGTH_LONG);
        t.show();

    }


}























