package com.example.recyclerviewticktock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter: RecyclerView.Adapter<ViewHolderItem>()  {
    private val videoList = listOf(
        "android.resource://your_package_name/" + R.raw.bigbuckbunny,
        "android.resource://your_package_name/" + R.raw.bigbuckbunny,
        "android.resource://your_package_name/" + R.raw.bigbuckbunny,
        "android.resource://your_package_name/" + R.raw.bigbuckbunny,
        "android.resource://your_package_name/" + R.raw.bigbuckbunny,
        "android.resource://your_package_name/" + R.raw.bigbuckbunny
        // 他のビデオパスを追加
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        return ViewHolderItem.create(parent)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        val videoPath = videoList[position]
        holder.bind(videoPath)
    }

}