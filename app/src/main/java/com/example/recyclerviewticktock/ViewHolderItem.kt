package com.example.recyclerviewticktock

import android.view.View
import android.widget.Toast
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivHolder: VideoView = itemView.findViewById(R.id.iv)
//    val tvNameHolder: TextView =itemView.findViewById(R.id.tvName)
//    val tvMessageHolder: TextView =itemView.findViewById(R.id.tvMessage)

    private val recyclerAdapter = RecyclerAdapter()
    private val nameList = recyclerAdapter.nameList

    init {
        itemView.setOnClickListener {
            val position: Int = adapterPosition

            Toast.makeText(itemView.context, "${nameList[position]}さんです", Toast.LENGTH_SHORT).show()
        }
    }
}