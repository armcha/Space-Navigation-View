package com.luseen.spacenavigationview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private List<String> colorList;

    private RecyclerClickListener recyclerClickListener;

    public RecyclerAdapter(List<String> colorList) {
        this.colorList = colorList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.simple_view, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        String color = colorList.get(position);
        ((RecyclerViewHolder) holder).itemView1.setBackgroundColor(Color.parseColor(color));
        ((RecyclerViewHolder) holder).itemView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerClickListener != null) {
                    recyclerClickListener.onClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout itemView1;

        public RecyclerViewHolder(View v) {
            super(v);
            itemView1 = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
        }
    }

    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener) {
        this.recyclerClickListener = recyclerClickListener;
    }

    public interface RecyclerClickListener {
        void onClick(int position);
    }
}
