package com.example.recyclerviewticktock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewticktock.databinding.OneLayoutBinding

class ViewHolderItem(private val binding: OneLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(videoPath: String) {
        // VideoViewの設定
        binding.iv.setVideoPath(videoPath)
        binding.iv.setOnPreparedListener { mp ->
            // ビデオが準備できた後に再生を開始する場合はコメントを外してください
            // mp.start()
        }
    }

    companion object {
        // ViewHolderのインスタンスを作成するためのメソッド
        fun create(parent: ViewGroup): ViewHolderItem {
            val inflater = LayoutInflater.from(parent.context)
            val binding = OneLayoutBinding.inflate(inflater, parent, false)
            return ViewHolderItem(binding)
        }
    }
}