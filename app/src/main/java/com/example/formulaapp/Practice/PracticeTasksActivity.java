package com.example.formulaapp.Practice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PracticeTasksActivity extends AppCompatActivity {
    private String content[], difArr[];
    RecyclerView recyclerView;
    List<PracticeTasksItem> pracList = new ArrayList<>();
    PracticeTasksAdapter myAdapter;
    private int category;
    private int key=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.practice_tasks_activity);

        Intent intent = getIntent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Задачи");
        Toolbar toolbar=findViewById(R.id.toolbar);
        if (intent != null) {
            category = intent.getIntExtra("POS_KEY", 0);
        }

        fillPracList();
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new PracticeTasksAdapter(pracList, this);
        recyclerView.setAdapter(myAdapter);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_2, menu);
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        fillPracList();
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new PracticeTasksAdapter(pracList, this);
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    protected void onPause() {

        super.onPause();

    }


    private void fillPracList() {
        pracList.clear();
        SharedPreferences mPrefs = this.getSharedPreferences("done", Context.MODE_PRIVATE);

        switch(category){
            case 0:
                content = getResources().getStringArray(R.array.practice_content_array_1);
                difArr = getResources().getStringArray(R.array.practice_dif_array);
                for (int i = 0, j = 1; i < content.length; i++, j++) {

                    pracList.add(new PracticeTasksItem("Задача" + j, content[i], "" + i,  mPrefs.getString("ans"+ category +i,"0"), difArr[i], category));
                }
                break;
            case 1:
                content = getResources().getStringArray(R.array.practice_content_array_2);
                difArr = getResources().getStringArray(R.array.practice_dif_array);
                for (int i = 0, j = 1; i < content.length; i++, j++) {

                    pracList.add(new PracticeTasksItem("Задача" + j, content[i], "" + i, mPrefs.getString("ans"+ category +i,"0"), difArr[i], category));
                }
                break;
        }



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.sort:

                if (key==0){
                Collections.sort(pracList,PracticeTasksItem.myName);
//                recyclerView = findViewById(R.id.RecyclerView);
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setLayoutManager(new LinearLayoutManager(this));
//                myAdapter = new PracticeTasksAdapter(pracList, this);
//                recyclerView.setAdapter(myAdapter);
                    recyclerView.getAdapter().notifyDataSetChanged();
                key=1;
                Log.i("ss","CHANGE");}
                else{
                    Collections.sort(pracList,PracticeTasksItem.myName2);
//                recyclerView = findViewById(R.id.RecyclerView);
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setLayoutManager(new LinearLayoutManager(this));
//                    myAdapter = new PracticeTasksAdapter(pracList, this);
//                    recyclerView.setAdapter(myAdapter);
                    recyclerView.getAdapter().notifyDataSetChanged();
                    key=0;
                }
               // fillPracList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
