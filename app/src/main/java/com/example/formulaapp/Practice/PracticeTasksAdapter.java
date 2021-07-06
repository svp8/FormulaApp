package com.example.formulaapp.Practice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.R;

import java.util.ArrayList;
import java.util.List;

public class PracticeTasksAdapter extends RecyclerView.Adapter<PracticeTasksAdapter.MyViewHolder>  {
    private List<PracticeTasksItem> itemList;
    private List<PracticeTasksItem> exampleListFull;


    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mText1, idText,difficultyText,mText2;
        ImageView imageStatus;

        public MyViewHolder(@NonNull View itemView) {
             super(itemView);


            mText1 = itemView.findViewById(R.id.myText1);
            mText2 = itemView.findViewById(R.id.myText2);
            idText = itemView.findViewById(R.id.idText);
            imageStatus=itemView.findViewById(R.id.imageStatus);
           difficultyText = itemView.findViewById(R.id.difText);







            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();


                   PracticeTasksItem itemCont= itemList.get(position);
                    int positionIndex = Integer.parseInt(itemCont.getId()) ;
                    Intent intent = new Intent(context, PracticeContentActivity.class);
                    intent.putExtra("POS_KEY", positionIndex);
                    intent.putExtra("CAT_KEY",itemCont.getCategory());
                    context.startActivity(intent);
                }
            });
        }
    }


    public PracticeTasksAdapter(List<PracticeTasksItem> exampleList, Context cont) {
        this.context=cont;
       this.itemList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.practice_tasks_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
PracticeTasksItem currentItem=itemList.get(position);

        if(currentItem.getStatus().equals("0")){
            holder.imageStatus.setImageResource(R.drawable.ic_check_blank);
        }
        else{ holder.imageStatus.setImageResource(R.drawable.ic_tcheck);}
        holder.mText1.setText(currentItem.getText1());
        holder.mText2.setText(currentItem.getText2());
        holder.idText.setText(currentItem.getId());
        Log.i("kek",currentItem.getDifficulty());
switch(currentItem.getDifficulty()){
    case "0":
        holder.difficultyText.setText("Легко");
        break;
    case "1":
        holder.difficultyText.setText("Средне");
        break;
    case "2":
        holder.difficultyText.setText("Сложно");
        break;
}


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

   /* @Override
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
    };*/


}
