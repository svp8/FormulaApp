package com.example.formulaapp.Practice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.ItemCont;
import com.example.formulaapp.R;

import java.util.ArrayList;
import java.util.List;

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.MyViewHolder> implements Filterable {
    private List<ItemCont> itemList;
    private List<ItemCont> exampleListFull;

    String[] array;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText1, myText2;
        ImageView myImage;
        ProgressBar progressBar;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            array = itemView.getResources().getStringArray(R.array.practice_content_array_1);
            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
            myImage = itemView.findViewById(R.id.myImageView);
            progressBar = itemView.findViewById(R.id.progressBar);
            progressBar.setMax(array.length);
progressBar.setVisibility(View.VISIBLE);
//            max = 0;
//            SharedPreferences mPrefs = context.getSharedPreferences("done", Context.MODE_PRIVATE);
//            switch (position) {
//                case 1:
//                    for (int i = 0; i < array.length; i++) {
//                        if (mPrefs.contains("ans" + 0 + i)) {
//                            max++;
//                        }
//                    }
//                    break;
//                case 2:
//                    for (int i = 0; i < array.length; i++) {
//                        if (mPrefs.contains("ans" + 1 + i)) {
//                            max++;
//                        }
//                    }
//                    break;
//            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    ItemCont itemCont = itemList.get(position);
                    int positionIndex = Integer.parseInt(itemCont.getText2());
                    Intent intent = new Intent(context, PracticeTasksActivity.class);
                    intent.putExtra("POS_KEY", positionIndex);
                    context.startActivity(intent);
                }
            });
        }
    }


    public PracticeAdapter(List<ItemCont> exampleList, Context cont) {
        this.context = cont;
        this.itemList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.practice_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemCont currentItem = itemList.get(position);
        array = context.getResources().getStringArray(R.array.practice_content_array_1);
        SharedPreferences mPrefs = context.getSharedPreferences("done", Context.MODE_PRIVATE);
       int  amount=0;

        switch (position) {
            case 0:
                for (int i = 0; i < array.length; i++) {
                    if (mPrefs.contains("ans" + 0 + i)) {
                        amount++;
                    }
                }
                break;
            case 1:
                for (int i = 0; i < array.length; i++) {
                    if (mPrefs.contains("ans" + 1 + i)) {
                        amount++;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < array.length; i++) {
                    if (mPrefs.contains("ans" + 2 + i)) {
                        amount++;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < array.length; i++) {
                    if (mPrefs.contains("ans" + 3 + i)) {
                        amount=1;
                    }
                }
                break;
        }
        holder.myText1.setText(currentItem.getText1());
        holder.myText2.setText(currentItem.getText2());

        holder.myImage.setImageResource(currentItem.getImageResource());
Log.i("kk",String.valueOf(amount));
if(amount==0){

   holder.progressBar.getProgressDrawable().setColorFilter(
           context.getResources().getColor(R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
}
else
       holder.progressBar.setProgress(amount);



    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<ItemCont> filteredList = new ArrayList<>();


            if (charSequence == null || charSequence.length() == 0) {

                filteredList.addAll(exampleListFull);
                // filteredList = data1copy.clone();
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (ItemCont item : exampleListFull) {
                    if (item.getText1().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
//data1=new String[data1.length];

            itemList.clear();
            itemList.addAll((List) results.values);
            notifyDataSetChanged();
            Log.i("ss", "123");


        }
    };


}
