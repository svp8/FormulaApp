package com.example.formulaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.app.DialogFragment;
import android.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.setBackgroundColor(Color.rgb(255, 255, 255));
        return view;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference myPref = (Preference) findPreference("login");
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                
                DialogFrag dialogFrag = new DialogFrag();
                Bundle bundle = new Bundle();
                bundle.putInt("message", 0);
                dialogFrag.setArguments(bundle);
                dialogFrag.show(getActivity().getSupportFragmentManager(), "dialog");

                return true;
            }
        });
        Preference mPref = (Preference) findPreference("fav");
        mPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                DialogFrag dialogFrag = new DialogFrag();
                Bundle bundle = new Bundle();
                bundle.putInt("message", 1);
                dialogFrag.setArguments(bundle);
                dialogFrag.show(getActivity().getSupportFragmentManager(), "dialog");

                return true;
            }
        });
    }

}