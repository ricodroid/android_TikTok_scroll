package com.example.recyclerviewticktock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter:RecyclerView.Adapter<ViewHolderItem>() {
    val nameList = listOf("My Name0","My Name1","My Name2","My Name3","My Name4","My Name5")
//    private val messageList = listOf("メッセージ０","メッセージ１","メッセージ２","メッセージ３","メッセージ４","メッセージ５")
//    private val imgList = listOf(
//        R.drawable.img0,R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5)

    private val videoList = listOf(
        "android.resource://your_package_name/" + R.raw.bigbuckbunny,
        "android.resource://your_package_name/" + R.raw.bigbuckbunny,
        "android.resource://your_package_name/" + R.raw.bigbuckbunny,
        "android.resource://your_package_name/" + R.raw.bigbuckbunny,
        "android.resource://your_package_name/" + R.raw.bigbuckbunny,
        "android.resource://your_package_name/" + R.raw.bigbuckbunny
        // 他のmp4ファイルのパスを追加
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        val itemXml = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_layout,parent,false)
        return ViewHolderItem(itemXml)
    }

    // position番目のデータをレイアウト(xml)に表示するようセット
    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
//        holder.tvNameHolder.text = nameList[position]
//        holder.tvMessageHolder.text = messageList[position]
//        holder.ivHolder.setImageResource(imgList[position])
        val videoPath = videoList[position]
        holder.ivHolder.setVideoPath(videoPath)
        holder.ivHolder.setOnPreparedListener { mp ->
            // ビデオが準備できた後に再生を開始する場合はコメントを外してください
             mp.start()
        }
    }

}