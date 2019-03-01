package com.example.stone.demoapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stone.demoapplication.R;
import com.example.stone.demoapplication.entity.HomeItemBean;

import java.util.List;


public class HomeMainAdapter extends RecyclerView.Adapter<HomeMainAdapter.MyViewHolder> {


    private List<HomeItemBean> dataList;
    private Activity context;

    public HomeMainAdapter(List<HomeItemBean> dataList, Activity context) {
        this.dataList = dataList;
        this.context = context;
    }

    private void removeItem(int position) {

        dataList.remove(position);
        notifyItemRemoved(position);
    }

    private void jumpToActivity(Class<?> activityName) {
        Intent intent = new Intent();
        intent.setClass(context, activityName);
        context.startActivity(intent);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item_layout, viewGroup, false));
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int i) {

        final HomeItemBean homeItemBean = dataList.get(i);
        holder.name.setText(homeItemBean.getName() + i);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToActivity(homeItemBean.getActivityName());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.delete.setVisibility(View.VISIBLE);
                return true;
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //渠道当前Item的所在位置，不能直接使用position
                removeItem(holder.getLayoutPosition());
            }
        });

        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = holder.getLayoutPosition()%2==1?getPixelsFromDp(75):getPixelsFromDp(50);
        holder.itemView.setLayoutParams(layoutParams);
    }

    private int getPixelsFromDp(int size){

        DisplayMetrics metrics =new DisplayMetrics();

        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return(size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}

