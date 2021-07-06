package com.example.formulaapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


public class DialogFrag extends AppCompatDialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        int flag = getArguments().getInt("message");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (flag == 0) {
            builder.setMessage("Вы действительно хотите сбросить весь прогресс?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SharedPreferences mPrefs = getActivity().getSharedPreferences("done", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed = mPrefs.edit();
                            ed.clear();
                            ed.commit();
                        }
                    })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

        } else {
            builder.setMessage("Вы действительно хотите сбросить избранное?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SharedPreferences mPrefs = getActivity().getSharedPreferences("ss", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed = mPrefs.edit();
                            ed.clear();
                            ed.commit();
                        }
                    })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
        }
        return builder.create();
    }
}