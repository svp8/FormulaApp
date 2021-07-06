package com.example.formulaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class FormulaFragment extends Fragment {
    private String[] forArray;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    int images[] = {R.color.design_default_color_primary, R.color.red, R.color.green, R.color.orange, R.color.yellow, R.color.purple, R.color.design_default_color_primary, R.color.design_default_color_on_secondary, R.color.red, R.color.green, R.color.orange, R.color.yellow, R.color.purple, R.color.design_default_color_primary};
    private List<String> favStatusList = new ArrayList<>();
    private List<ItemCont> List;
    private AdView adView;

    @Override
    public void onStop() {

        super.onStop();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.formula_fragment, container, false);
        getActivity().setTitle("Формулы");
        adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        forArray = getResources().getStringArray(R.array.formula_array);
        fillList();


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new MyAdapter(List,getActivity());
        recyclerView.setAdapter(myAdapter);
        setHasOptionsMenu(true);

        return view;
    }

    //Заполнение list
    private void fillList() {
        List = new ArrayList<>();
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("ss", Context.MODE_PRIVATE);
        for (int i = 0; i < forArray.length; i++) {
            if (mPrefs.contains("ID3 " + i)) {

                List.add(new ItemCont(images[i], forArray[i], String.valueOf(i), "1",0));
            } else {
                List.add(new ItemCont(images[i], forArray[i], String.valueOf(i), "0",0));
            }

        }


    }
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.main,menu);
        MenuItem searchItem=menu.findItem(R.id.action_search);

        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Поиск...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                myAdapter.getFilter().filter(s);

                return false;
            }
        });

    }
}
