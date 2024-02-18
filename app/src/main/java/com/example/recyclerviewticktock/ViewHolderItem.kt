package com.example.recyclerviewticktock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewticktock.databinding.OneLayoutBinding

class ViewHolderItem(private val binding: OneLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var beforeText: String
    private lateinit var replaceText: String
    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var text3: TextView
    private lateinit var mottoMiruButton: TextView
    private lateinit var shukushouButton: TextView
    private lateinit var icon1: ImageView
    private lateinit var icon2: ImageView
    private lateinit var icon3: ImageView
    private lateinit var icon4: ImageView
    private lateinit var icon5: ImageView

    enum class IconButtonState {
        TAPPED,
        UN_TAPPED
    }

    private var currentHumanState = IconButtonState.UN_TAPPED
    private var currentStarState = IconButtonState.UN_TAPPED
    private var currentGoodState = IconButtonState.UN_TAPPED
    private var currentBadState = IconButtonState.UN_TAPPED
    private var currentSearchState = IconButtonState.UN_TAPPED

    fun bind(videoPath: String) {
        // VideoViewの設定
        binding.iv.setVideoPath(videoPath)
        binding.iv.setOnPreparedListener { mp ->
            // ビデオが準備できた後に再生を開始する場合はコメントを外してください
            mp.start()
        }
        text1 = binding.textView1
        text2 = binding.textView2
        text3 = binding.textView3
        text1.text = "雨ニモ負ケズ"
        beforeText = text3.text.toString()
        mottoMiruButton = binding.mottoMiruButton
        shukushouButton = binding.shukushouButton

        /** いいねボタンとかのアイコン類 */
        icon1 = binding.icon1
        icon2 = binding.icon2
        icon3 = binding.icon3
        icon4 = binding.icon4
        icon5 = binding.icon5

        if (isLongText(text3.text)) {
            // 13文字以上なら、もっと見るボタンを表示する
            toggleMottoMiruButtonVisibility(mottoMiruButton, View.VISIBLE)
            toggleMottoMiruButtonVisibility(shukushouButton, View.INVISIBLE)
            replaceText = replaceText(text3).toString()
            text3.text = replaceText(text3)
        } else {
            toggleMottoMiruButtonVisibility(mottoMiruButton, View.INVISIBLE)
            toggleMottoMiruButtonVisibility(shukushouButton, View.VISIBLE)
        }

        // ViewHolderItemのクラスに
        // クリックリスナーを継承させる方法わからなかったので、個別で対応。未熟者
        mottoMiruButton.setOnClickListener { v ->
            onClick(v)
        }
        shukushouButton.setOnClickListener { v ->
            onClick(v)
        }
        icon1.setOnClickListener { v ->
            onClick(v)
        }
        icon2.setOnClickListener { v ->
            onClick(v)
        }
        icon3.setOnClickListener { v ->
            onClick(v)
        }
        icon4.setOnClickListener { v ->
            onClick(v)
        }
        icon5.setOnClickListener { v ->
            onClick(v)
        }
    }

    /**
     * テキストの最初の13文字だけを取り出して、
     * 末尾に「・・・」をつける
     *
     * @return String
     */
    private fun replaceText(text: TextView): StringBuffer {
        // 最初から13文字を取得
        val shortenedText = text.text.take(9)
        // 末尾に「・・・」を追加
        return StringBuffer(shortenedText).append("・・・")
    }

    /**
     * 13文字以上ならtrue
     * 13文字未満ならfalse
     * @return Boolean
     */
    private fun isLongText(text: CharSequence): Boolean {
        return text.length >= 9
    }

    private fun toggleMottoMiruButtonVisibility(textType: TextView, viewStatus: Int) {
        textType.visibility = viewStatus
    }

    companion object {
        // ViewHolderのインスタンスを作成するためのメソッド
        fun create(parent: ViewGroup): ViewHolderItem {
            val inflater = LayoutInflater.from(parent.context)
            val binding = OneLayoutBinding.inflate(inflater, parent, false)
            return ViewHolderItem(binding)
        }
    }

    private fun onClick(v: View?) {
        when (v) {
            mottoMiruButton -> {
                println("もっと見るテキストがクリックされました！")
                text3.text = beforeText
                toggleMottoMiruButtonVisibility(mottoMiruButton, View.INVISIBLE)
                toggleMottoMiruButtonVisibility(shukushouButton, View.VISIBLE)
                mottoMiruButton.text = "縮小表示に戻す"
            }

            shukushouButton -> {
                println("縮小テキストがクリックされました！")
                text3.text = replaceText
                toggleMottoMiruButtonVisibility(shukushouButton, View.INVISIBLE)
                toggleMottoMiruButtonVisibility(mottoMiruButton, View.VISIBLE)
                mottoMiruButton.text = "...もっと見る"
            }

            icon1 -> {
                // 人型アイコン
                println("アイコン1がクリックされました！")
            }

            icon2 -> {
                // スターアイコン
                println("アイコン2がクリックされました！")
                toggleIconState(currentStarState, icon2)
                updateStarButtonImage(icon2)
            }

            icon3 -> {
                // グッドアイコン
                println("アイコン3がクリックされました！")
                toggleIconState(currentGoodState, icon3)// icon3はcurrentGoodStateがTAPPEDになる

                updateGoodButtonImage(icon3)// icon3はTAPPEDのアイコンになる
                // icon4がTAPPEDだったら、icon4をUNTAPPEDにする
                if (currentGoodState == IconButtonState.TAPPED && currentBadState == IconButtonState.TAPPED) {
                    toggleIconState(currentBadState, icon4)// badのステータスUN_TAPPED
                    updateBadButtonImage(icon4)
                }
            }

            icon4 -> {
                // バッドアイコン
                println("アイコン4がクリックされました！")
                toggleIconState(currentBadState, icon4)

                updateBadButtonImage(icon4)
                // icon3がTAPPEDだったら、icon3をUNTAPPEDにする
                if (currentGoodState == IconButtonState.TAPPED && currentBadState == IconButtonState.TAPPED) {
                    toggleIconState(currentGoodState, icon3)
                    updateGoodButtonImage(icon3)
                }
            }

            icon5 -> {
                // サーチアイコン
                println("アイコン5がクリックされました！")
            }
        }
    }

    private fun toggleIconState(state: IconButtonState, button: ImageView) {
        // アイコンの状態をトグルする
        when (button) {
            icon2 -> {
                currentStarState =
                    when (state) {
                        IconButtonState.UN_TAPPED -> IconButtonState.TAPPED
                        IconButtonState.TAPPED -> IconButtonState.UN_TAPPED
                    }
            }

            icon3 -> {
                currentGoodState =
                    when (state) {
                        IconButtonState.UN_TAPPED -> IconButtonState.TAPPED
                        IconButtonState.TAPPED -> IconButtonState.UN_TAPPED
                    }
            }

            icon4 -> {
                currentBadState =
                    when (state) {
                        IconButtonState.UN_TAPPED -> IconButtonState.TAPPED
                        IconButtonState.TAPPED -> IconButtonState.UN_TAPPED
                    }
            }
        }

    }

    private fun updateGoodButtonImage(button: ImageView) {
        // Goodボタン：現在の状態に応じて画像を切り替える
        val imageResource = when (currentGoodState) {
            IconButtonState.UN_TAPPED -> R.drawable.baseline_thumb_up_off_alt_24
            IconButtonState.TAPPED -> R.drawable.icon_good_tapped
        }
        button.setImageResource(imageResource)
    }

    private fun updateBadButtonImage(button: ImageView) {
        // Badボタン：現在の状態に応じて画像を切り替える
        val imageResource = when (currentBadState) {
            IconButtonState.UN_TAPPED -> R.drawable.icon_bad
            IconButtonState.TAPPED ->  R.drawable.icon_bad_tapped
        }
        button.setImageResource(imageResource)
    }

    private fun updateStarButtonImage(button: ImageView) {
        // アップデートボタン：現在の状態に応じて画像を切り替える
        val imageResource = when (currentStarState) {
            IconButtonState.UN_TAPPED -> R.drawable.icon_star
            IconButtonState.TAPPED -> R.drawable.icon_star_tapped
        }
        button.setImageResource(imageResource)
    }
}