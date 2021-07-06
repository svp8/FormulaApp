package com.example.formulaapp.Practice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.ItemCont;
import com.example.formulaapp.Practice.PracticeAdapter;
import com.example.formulaapp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class PracticeFragment extends Fragment {
    RecyclerView recyclerView;
    PracticeAdapter pAdapter;
    List<ItemCont> pList = new ArrayList<>();
    private String[] pArray;
    private String[] pId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.practice_fragment, container, false);
        getActivity().setTitle("Практика");
        AdView adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        fillPracticeList();
        recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pAdapter = new PracticeAdapter(pList, getActivity());

        recyclerView.setAdapter(pAdapter);
        setHasOptionsMenu(true);


        return view;
    }

    private void fillPracticeList() {
        pArray = getResources().getStringArray(R.array.practice_array);
        pId = getResources().getStringArray(R.array.practice_id);
pList.clear();

        for (int i = 0; i < 4; i++) {
            pList.add(new ItemCont(R.color.red, pArray[i], pId[i], "0",3));
        }
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

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
                Log.i("ss","CHANGE");
                pAdapter.getFilter().filter(s);

                return false;
            }
        });

    }
}
