package com.example.formulaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {
    ArrayList<String> FavList = new ArrayList<>();
    int images[] = {R.color.design_default_color_primary, R.color.red, R.color.green, R.color.orange, R.color.yellow, R.color.purple, R.color.design_default_color_primary, R.color.design_default_color_on_secondary, R.color.red, R.color.green, R.color.orange, R.color.yellow, R.color.purple, R.color.design_default_color_primary};
    private List<ItemCont> favList = new ArrayList<>();
    ;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    private List<String> favStatusList = new ArrayList<>();
    ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourites_fragment, container, false);
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("ss", Context.MODE_PRIVATE);
        getActivity().setTitle("Избранное");
        AdView adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        for (int position = 0; position < 10; position++) {

            if (mPrefs.contains("ID2 " + position)) {

                favList.add(new ItemCont(images[position], mPrefs.getString("ID " + position, "default"), String.valueOf(position), mPrefs.getString("ID2 " + position, "0"), 1));
                Log.i("kk", mPrefs.getString("ID " + position, "default"));
            }
        }
        for (int position = 0; position < 10; position++) {
            if (mPrefs.contains("ID3 " + position)) {

                favList.add(new ItemCont(images[position], mPrefs.getString("IDText " + position, "default"), String.valueOf(position), mPrefs.getString("ID3 " + position, "0"), 0));
            }
        }
        recyclerView = view.findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, FavList);
        // ListView lvData = (ListView) view.findViewById(R.id.listView2);
        //  lvData.setAdapter(adapter);
        //Toast toast=Toast.makeText(getContext(),favItem,Toast.LENGTH_SHORT);
        // toast.show();
        myAdapter = new MyAdapter(favList, getActivity());
        recyclerView.setAdapter(myAdapter);
        setHasOptionsMenu(true);

        recyclerView.getAdapter().notifyDataSetChanged();

        return view;

    }

    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.main,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Поиск...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.i("ss", "CHANGE");
                myAdapter.getFilter().filter(s);

                return false;
            }
        });

    }
}
