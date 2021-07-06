package com.example.formulaapp.Practice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formulaapp.MainActivity;
import com.example.formulaapp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class PracticeContentActivity extends AppCompatActivity {
    ActionBar actionBar;
    private int position;
    private TextView text_content;
    private String content[], ansArr[];
    private TextView text_ans, textAnsTitle;
    private EditText editText;
    private int category;
    private RewardedAd mRewardedAd;
    private Button rewardBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.practice_content);
        rewardBtn = findViewById(R.id.btn_show_answer);
        rewardBtn.setEnabled(false);
        loadAd();

        Intent intent = getIntent();
        actionBar = getSupportActionBar();
        text_ans = findViewById(R.id.text_answer);
        text_ans.setVisibility(View.GONE);
        textAnsTitle = findViewById(R.id.text_ans_title);
        textAnsTitle.setVisibility(View.GONE);
        text_content = findViewById(R.id.text_content);
        actionBar.setDisplayHomeAsUpEnabled(true);


        if (intent != null) {
            position = intent.getIntExtra("POS_KEY", 0);
            category = intent.getIntExtra("CAT_KEY", 0);
        }
        taskInit();
        int pos1 = position + 1;
        actionBar.setTitle("Задача " + pos1);

        text_content.setText(content[position]);

    }

    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917", adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error.
                Log.d("practice", loadAdError.getMessage());
                mRewardedAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                mRewardedAd = rewardedAd;
                Log.d("practice", "onAoLoaded");
                rewardBtn.setEnabled(true);
            }
        });
    }

    public void showAd() {
        if (mRewardedAd != null) {
            Activity activityContext = PracticeContentActivity.this;

            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d("practice", "The user earned the reward.");
                    text_ans.setVisibility(View.VISIBLE);
                    textAnsTitle.setVisibility(View.VISIBLE);
                    rewardBtn.setEnabled(false);
                }
            });
        } else {
            Log.d("practice", "The rewarded ad wasn't ready yet.");
        }
    }

    public void taskInit() {
        switch (category) {
            case 0:
                content = getResources().getStringArray(R.array.practice_content_array_1);
                ansArr = getResources().getStringArray(R.array.practice_ans_array);
                break;
            case 1:
                content = getResources().getStringArray(R.array.practice_content_array_2);
                ansArr = getResources().getStringArray(R.array.practice_ans_array);
                break;
        }


    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickShowAns(View view) {
        showAd();

    }

    public void onClickCheck(View view) {
        SharedPreferences mPrefs = this.getSharedPreferences("done", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = mPrefs.edit();
        editText = findViewById(R.id.editText);
        String input = editText.getText().toString().toLowerCase();
        String answer = ansArr[position];
        if (input.equals(answer)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Правильно!", Toast.LENGTH_SHORT);
            toast.show();
            ed.putString("ans" + category + position, "1");
        } else if (input.equals("")) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Неправильно!", Toast.LENGTH_SHORT);
            toast.show();
        }
        ed.apply();
    }
}
