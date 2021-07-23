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

import android.widget.SearchView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class TheoryFragment extends Fragment {

    String theoryArr[], s2[];
    int images[] = { R.color.design_default_color_primary,R.color.red,R.color.green,R.color.orange,R.color.yellow,R.color.purple,R.color.design_default_color_primary,R.color.design_default_color_on_secondary,R.color.red,R.color.green,R.color.orange,R.color.yellow,R.color.purple,R.color.design_default_color_primary,R.color.design_default_color_on_secondary,R.color.red,R.color.green,R.color.orange,R.color.yellow,R.color.purple};
    RecyclerView recyclerView;
     MyAdapter myAdapter;
     private List<String> favStatusList=new ArrayList<>();
    private List<ItemCont> exampleList;
    private AdView adView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.theory_fragment, container, false);
        s2 = getResources().getStringArray(R.array.description);
        theoryArr = getResources().getStringArray(R.array.theory_array);

        adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("ss", Context.MODE_PRIVATE);
        for( int position=0;position<s2.length;position++){

            if(mPrefs.contains("ID2 "+position)){

                favStatusList.add("1");
            }
            else{favStatusList.add("0");}

        }
        exampleList = new ArrayList<>();
        for (int i = 0; i < theoryArr.length; i++) {
            if(mPrefs.contains("ID2 "+i)){

                exampleList.add(new ItemCont(images[i], theoryArr[i], s2[i],"1",1));
            }
            else{exampleList.add(new ItemCont(images[i], theoryArr[i], s2[i],"0",1));}

        }
        //fillExampleList();
        getActivity().setTitle("Теория");
        theoryArr = getResources().getStringArray(R.array.theory_array);
        s2 = getResources().getStringArray(R.array.description);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

         myAdapter = new MyAdapter(exampleList,getActivity());
        recyclerView.setAdapter(myAdapter);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
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
 /*      ArrayList<String> data = new ArrayList<>();
//Заполнение аррея
        array = getResources().getStringArray(R.array.theory_array);
        for (int i = 0; i < array.length; i++) {
            data.add(array[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
        ListView lvData = (ListView) view.findViewById(R.id.listView1);
        lvData.setAdapter(adapter);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), TheoryContentActivity.class);
                intent.putExtra("POS_KEY", position);
                startActivity(intent);

            }
        });*/