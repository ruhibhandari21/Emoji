package com.maxamtech.emoji.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.maxamtech.emoji.R

class CommonStickerAdapter: RecyclerView.Adapter<CommonStickerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var itemView: View = LayoutInflater.from(p0.context).
                inflate(R.layout.sticker_tag_item, p0, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }

}