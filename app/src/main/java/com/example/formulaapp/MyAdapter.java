package com.example.formulaapp;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {
    private List<ItemCont> itemList;
    private List<ItemCont> exampleListFull;


    Context context;
    SharedPreferences sPref;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText1, myText2;
        ImageView myImage;
        Button favBtn;
        LikeButton likeButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
            myImage = itemView.findViewById(R.id.myImageView);
            favBtn = itemView.findViewById(R.id.button);
            likeButton = itemView.findViewById(R.id.star_button);
//            likeButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    ItemCont itemCont = itemList.get(position);
//                    sPref = context.getSharedPreferences("ss", MODE_PRIVATE);
//                    SharedPreferences.Editor ed = sPref.edit();
//
//                    if (itemCont.getFavStatus().equals("0")) {
//                        itemCont.setFavStatus("1");
//
//
//                        if (itemCont.getThemeId() == 1) {
//                            ed.putInt("ID1 " + itemCont.getText2(), position);
//                            ed.putString("ID2 " + itemCont.getText2(), itemCont.getFavStatus());
//                            ed.putString("ID " + itemCont.getText2(), itemCont.getText1());
//                        } else {
//                            ed.putString("ID3 " + itemCont.getText2(), itemCont.getFavStatus());
//                            ed.putString("IDText " + itemCont.getText2(), itemCont.getText1());
//                            ed.putInt("IDpos " + itemCont.getText2(), position);
//                        }
//
//                        ed.apply();
//                        likeButton.setLiked(true);
//                        favBtn.setBackgroundResource(R.mipmap.ic_star_full);
//                    } else {
//
//                        if (itemCont.getThemeId() == 1) {
//                            ed.remove("ID " + itemCont.getText2());
//                            ed.remove("ID1 " + itemCont.getText2());
//                            ed.remove("ID2 " + itemCont.getText2());
//                        } else {
//                            ed.remove("IDText " + itemCont.getText2());
//                            ed.remove("IDpos " + itemCont.getText2());
//                            ed.remove("ID3 " + itemCont.getText2());
//                        }
//                        itemCont.setFavStatus("0");
//                        favBtn.setBackgroundResource(R.drawable.button);
//                        // Log.i("TAG4",String.valueOf();
//                        likeButton.setLiked(false);
//                        ed.apply();
//                    }
//                }
//            });
            likeButton.setOnLikeListener(new OnLikeListener() {

                @Override
                public void liked(LikeButton likeButton) {
                    int position = getAdapterPosition();
                    ItemCont itemCont = itemList.get(position);
                    sPref = context.getSharedPreferences("ss", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    itemCont.setFavStatus("1");
                    if (itemCont.getThemeId() == 1) {
                        ed.putInt("ID1 " + itemCont.getText2(), position);
                        ed.putString("ID2 " + itemCont.getText2(), itemCont.getFavStatus());
                        ed.putString("ID " + itemCont.getText2(), itemCont.getText1());
                    } else {
                        ed.putString("ID3 " + itemCont.getText2(), itemCont.getFavStatus());
                        ed.putString("IDText " + itemCont.getText2(), itemCont.getText1());
                        ed.putInt("IDpos " + itemCont.getText2(), position);
                    }

                    ed.apply();
                }


                @Override
                public void unLiked(LikeButton likeButton) {
                    int position = getAdapterPosition();
                    ItemCont itemCont = itemList.get(position);
                    sPref = context.getSharedPreferences("ss", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    if (itemCont.getThemeId() == 1) {
                        ed.remove("ID " + itemCont.getText2());
                        ed.remove("ID1 " + itemCont.getText2());
                        ed.remove("ID2 " + itemCont.getText2());
                    } else {
                        ed.remove("IDText " + itemCont.getText2());
                        ed.remove("IDpos " + itemCont.getText2());
                        ed.remove("ID3 " + itemCont.getText2());
                    }
                    itemCont.setFavStatus("0");
                    ed.apply();
                }
            });
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    ItemCont itemCont = itemList.get(position);
                    sPref = context.getSharedPreferences("ss", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();

                    if (itemCont.getFavStatus().equals("0")) {
                        itemCont.setFavStatus("1");


                        if (itemCont.getThemeId() == 1) {
                            ed.putInt("ID1 " + itemCont.getText2(), position);
                            ed.putString("ID2 " + itemCont.getText2(), itemCont.getFavStatus());
                            ed.putString("ID " + itemCont.getText2(), itemCont.getText1());
                        } else {
                            ed.putString("ID3 " + itemCont.getText2(), itemCont.getFavStatus());
                            ed.putString("IDText " + itemCont.getText2(), itemCont.getText1());
                            ed.putInt("IDpos " + itemCont.getText2(), position);
                        }

                        ed.apply();

                        favBtn.setBackgroundResource(R.mipmap.ic_star_full);
                    } else {

                        if (itemCont.getThemeId() == 1) {
                            ed.remove("ID " + itemCont.getText2());
                            ed.remove("ID1 " + itemCont.getText2());
                            ed.remove("ID2 " + itemCont.getText2());
                        } else {
                            ed.remove("IDText " + itemCont.getText2());
                            ed.remove("IDpos " + itemCont.getText2());
                            ed.remove("ID3 " + itemCont.getText2());
                        }
                        itemCont.setFavStatus("0");
                        favBtn.setBackgroundResource(R.drawable.button);
                        // Log.i("TAG4",String.valueOf();

                        ed.apply();
                    }

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    ItemCont itemCont = itemList.get(position);
                    int positionIndex = Integer.parseInt(itemCont.getText2());

                    if (itemCont.getThemeId() == 1) {
                        Intent intent = new Intent(context, TheoryContentActivity.class);
                        intent.putExtra("POS_KEY", positionIndex);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, FormulaContentActivity.class);
                        intent.putExtra("POS_KEY", positionIndex);
                        context.startActivity(intent);
                    }
                }
            });
        }

    }


    public MyAdapter(List<ItemCont> exampleList, Context cont) {
        this.context = cont;
        this.itemList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }
   /* public MyAdapter(Context ct, String s1[], String s2[], int[] i) {
        context = ct;
        data1 = s1;
        listDataFull= new ArrayList<String>(Arrays.asList(s1));
        data2 = s2;
        images = i;
        listData = new ArrayList<String>(Arrays.asList(s1));
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemCont currentItem = itemList.get(position);
        if (currentItem.getFavStatus().equals("0")) {
            holder.favBtn.setBackgroundResource(R.drawable.ic_star_empty);
            holder.likeButton.setLiked(false);
        } else {
            holder.favBtn.setBackgroundResource(R.mipmap.ic_star_full);
            holder.likeButton.setLiked(true);
        }

        holder.myText1.setText(currentItem.getText1());
        holder.myText2.setText(currentItem.getText2());
        holder.myImage.setImageResource(currentItem.getImageResource());

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
