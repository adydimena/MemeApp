package com.example.ady.memesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ady.memesapp.pojo.Item;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Ady on 11/14/2017.
 */

public class Recycleadapter extends RecyclerView.Adapter<Recycleadapter.ViewHolder> {
    List<Item> list = new ArrayList<>();
    Context context;
    String TAG = "Recycle Adapter";


    public Recycleadapter(List<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleviewlayout,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list.get(position).getPagemap() != null) {
            Log.d(TAG, "onBindViewHolder: PAGEMap not empty");
            if (list.get(position).getPagemap().getCseImage() != null) {
                Log.d(TAG, "onBindViewHolder: Cse not empty");

                Glide.with(context)
                        .load(list.get(position).getPagemap().getCseImage().get(0).getSrc())
                        .into(holder.recycleDisplay);
            } else {
                Log.d(TAG, "onBindViewHolder: Cse empty on recycle");
            }
        }else {
            Log.d(TAG, "onBindViewHolder: PAGEMAP is empty");
        }
    }


    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView recycleDisplay;
        public ViewHolder(View itemView) {
            super(itemView);
            recycleDisplay = itemView.findViewById(R.id.imadisplay);
        }
    }

}
