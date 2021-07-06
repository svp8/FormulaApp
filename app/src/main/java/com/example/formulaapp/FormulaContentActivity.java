package com.example.formulaapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;

public class FormulaContentActivity extends AppCompatActivity {
    private int position;
    private TextView text_content;
    private ImageView imageView;
    private String[] array_formula;
    private int[] array_img = {R.drawable.formula_table, R.drawable.formula_img01, R.drawable.formula_img1, R.drawable.formula_img2, R.drawable.formula_img3, R.drawable.formula_img4, R.drawable.formula_img5, R.drawable.formula_img6, R.drawable.formula_img7, R.drawable.formula_img8, R.drawable.formula_img9};
    private ActionBar actionBar;
    private boolean adLoaded;
    private AdLoader adLoader;
    private TemplateView template;
    private ColorDrawable background;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formula_content);
        text_content = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView3);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

//        adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110").forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//
//
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//            @Override
//            public void onNativeAdLoaded(NativeAd nativeAd) {
//                NativeTemplateStyle styles = new NativeTemplateStyle.Builder().withMainBackgroundColor(background).build();
//                template = findViewById(R.id.nativeTemplateView);
//                template.setStyles(styles);
//
//                if (isDestroyed()) {
//                    nativeAd.destroy();
//                    return;
//                }
//                adLoaded = true;
//                Log.d("adFormula", "Native Ad is loaded, now you can show the native ad");
//            }
//
//        }).build();
//        loadNativeAd();
//        showNativeAd();
        Intent intent = getIntent();
        array_formula = getResources().getStringArray(R.array.formula_array);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (intent != null) {
            position = intent.getIntExtra("POS_KEY", 0);
        }
        switch (position) {
            case 0:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
            case 1:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            case 2:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
            case 3:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
            case 4:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
            case 5:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
            case 6:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
            case 7:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
            case 8:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
            case 9:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
            case 10:
                text_content.setText(array_formula[position]);
                actionBar.setTitle(array_formula[position]);
                imageView.setImageResource(array_img[position]);
        }


    }

//    private void loadNativeAd() {
//        // Creating  an Ad Request
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        // load Native Ad with the Request
//        adLoader.loadAd(adRequest);
//
//        // Showing a simple Toast message to user when Native an ad is Loading
//        Log.d("adFormula", "ad is loaded");
//
//    }
//    private void showNativeAd()
//    {
//        if ( adLoaded )
//        {
//            template.setVisibility( View.VISIBLE) ;
//            // Showing a simple Toast message to user when an Native ad is shown to the user
//            Log.d("adFormula","Native Ad  is loaded and Now showing ad ");
//        }
//        else
//        {
//            //Load the Native ad if it is not loaded
//            loadNativeAd() ;
//
//            // Showing a simple Toast message to user when Native ad is not loaded
//            Log.d("adFormula","Native Ad is not Loaded ");
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
