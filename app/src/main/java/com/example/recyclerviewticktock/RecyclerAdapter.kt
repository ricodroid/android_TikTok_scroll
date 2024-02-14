package com.example.recyclerviewticktock

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.common.reflect.Reflection.getPackageName

class RecyclerAdapter(private val context: Context): RecyclerView.Adapter<ViewHolderItem>()  {
    private val videoList = listOf(
        "android.resource://" + context.packageName + "/" + R.raw.bigbuckbunny,
        "android.resource://" + context.packageName + "/" + R.raw.move1,
        "android.resource://" + context.packageName + "/" + R.raw.bigbuckbunny,
        "android.resource://" + context.packageName + "/" + R.raw.move1,
        "android.resource://" + context.packageName + "/" + R.raw.bigbuckbunny,
        "android.resource://" + context.packageName + "/" + R.raw.move1,
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