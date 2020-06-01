package com.example.mywechat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyHolder extends RecyclerView.ViewHolder {
    private View mView;
    public MyHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;
    }
    public View finViewById(int id){
        return mView.findViewById(id);
    }
}