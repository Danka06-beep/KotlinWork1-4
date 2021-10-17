package com.example.kotlinwork1_4

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var post = Post(1,"Dan",1634045445262,"la la la", like = false, comment = false, share = false,0,0,0 ,"Дементьева 12",coordinates = Pair(55.84058,38.20251))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inthat(post)
        likeImgBtn.setOnClickListener {
            if (post.like) {
                likeImgBtn.setImageResource(R.drawable.ic_baseline_favorite_disabled)
                likeTxt.setTextColor(Color.BLACK)
                post = post.copy(likeTxt = post.likeTxt - 1, like = false)
            } else {
                likeImgBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                likeTxt.setTextColor(Color.RED)
                post = post.copy(likeTxt = post.likeTxt + 1, like = true)
            }
            inthat(post)
        }
        coordBtn.setOnClickListener {
            val (lat, lng) = post.coordinates
            val geo = Uri.parse("geo:$lat,$lng")
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = geo
            }
            startActivity(intent)
        }
        imageButtonplay.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://www.youtube.com/watch?v=AM_WxaR6Spc")
                Log.d("MyLog","Video Play")
            }
            startActivity(intent)
        }
    }
     fun isTrue(post : Post) {
        if (post.like) {
            likeImgBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
        if (post.comment) {
            commentImgBtn.setImageResource(R.drawable.ic_baseline_comment_24)
        }
        if (post.share) {
            shareImgBtn.setImageResource(R.drawable.ic_baseline_share_24)
        }

    }

    fun inthat(post: Post) {

        authorxt.text = post.author
        txt.text = post.txt
        if (post.likeTxt > 0) {
            likeTxt.text = post.likeTxt.toString()
        }else{
            likeTxt.text = " "
        }
        if (post.commentTxt > 0) {
            commentTxt.text = post.commentTxt.toString()
        }else{
            commentTxt.text = " "
        }
        if (post.shareTxt > 0) {
            shareTxt.text = post.shareTxt.toString()
        }else{
            shareTxt.text = " "
        }

        datetxt.text = date(post)
        isTrue(post)

    }

    fun date(post: Post): String {
        val publishedAgo =  (System.currentTimeMillis() - post.data) / 1000
        val toMin = if (publishedAgo > 3599) {
            publishedAgo / 3600
        } else {
            publishedAgo / 60
        }
        return when (publishedAgo) {
            in 0..59 -> "менее минуты назад"
            in 60..179 -> "минуту назад"
            in 180..299 -> "$toMin минуты назад"
            in 300..3599 -> "$toMin минут назад"
            in 3600..17999 -> "$toMin часа назад"
            else -> "$toMin часов назад "
        }
    }
}


