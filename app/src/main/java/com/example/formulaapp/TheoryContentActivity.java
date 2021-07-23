package com.example.formulaapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

public class TheoryContentActivity extends AppCompatActivity {
    private int position;
    private TextView text_content;
    private TextView text_content2;

    private String[] array_theory;

    private ActionBar actionBar;
    private PageAdapter pAdapter;
    private int pos;
    private int min = 1;
    private int max = 3;
    private int random;
    private ArrayList<itemPage> arrayList;
    private Button button;
    private InterstitialAd mInterstitialAd;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                random = (int) (Math.random() * ((max - min) + 1)) + min;
                Log.i("lols", "r" + random);
                if (mInterstitialAd != null && random == 2) {
                    mInterstitialAd.show(TheoryContentActivity.this);

                }

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theory_content2);
        button = findViewById(R.id.button5);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        finish();
                        Log.i("TAG", "The ad was dismissed.");
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.i("TAG", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.i("TAG", "The ad was shown.");
                    }
                });
                Log.i("lols", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i("lols", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });


        arrayList = new ArrayList<>();

        text_content = findViewById(R.id.textView3);
        text_content2 = findViewById(R.id.textView5);
        Intent intent = getIntent();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (intent != null) {
            position = intent.getIntExtra("POS_KEY", 0);
        }
        changeContent();

    }

    private void changeContent() {
        array_theory = getResources().getStringArray(R.array.theory_array);
        boolean fileFound = true;
        arrayList = new ArrayList<>();
        String imageName = "theory_";
        pos = position + 1;
        findImage(pos);
        configureViewPager();
        actionBar.setTitle(array_theory[position]);
    }

    public void findImage(int pos) {
        String imageName = "theory_";
        for (int i = 1; i < 4; i++) {
            imageName = "theory_" + pos + "_";
            imageName += i;
            int imageId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());

            if (imageId != 0) {
                Log.i("test321", " " + imageId);
                arrayList.add(new itemPage(imageId));
            }

        }
    }

    private void configureViewPager() {

        pAdapter = new PageAdapter(this, arrayList);
        ViewPager2 pager = findViewById(R.id.activity_main_viewpager);
        pager.setFocusable(false);
        pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        pager.setAdapter(pAdapter);

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                pos = position + 1;
                text_content.setText(pos + "/" + arrayList.size());
                text_content2.setText(pos + "/" + arrayList.size());
            }
        });

    }


    public void onClickNext(View view) {
        random = (int) (Math.random() * ((max - min) + 1)) + min;
        if (mInterstitialAd != null && random == 1) {
            mInterstitialAd.show(TheoryContentActivity.this);

        } else {
            Log.i("TAG", "The interstitial ad wasn't ready yet.");
            finish();
        }
        Log.i("lolz", "1" + position);
    }
}
