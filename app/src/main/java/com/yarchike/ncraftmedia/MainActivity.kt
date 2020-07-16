package com.yarchike.ncraftmedia

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var post = Post(
        12, 1594585699570, "Мартынов Я.В.",
        "10 февраля, среда\n" +
                "\n" +
                "Львам так надоело терять людей! \n" +
                "Им нужен тот, кто придет в их жизнь \n" +
                "и скажет: 'Хочешь, не хочешь, а я остаюсь", 0,
        0, 5, isLike = false, isComment = false, isShare = false,
        adress = "Проспект Королева дом 5", coordinates = Pair(55.921606, 37.841268)
    )
    var postTemp = post.copy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init(post)


    }

    fun clicLike(view: View) {
        if (post.isLike) {
            likeImage.setImageResource(R.drawable.ic_no_like)
            likeText.setTextColor(Color.BLACK)
            val postTemp = post.copy(like = post.like - 1, isLike = false)
            init(postTemp)
        } else {
            likeImage.setImageResource(R.drawable.ic_like)
            likeText.setTextColor(Color.RED)
            val postTemp = post.copy(like = post.like + 1, isLike = true)
            init(postTemp)
        }

    }

    fun clicVideo(view: View) {

        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            setData(Uri.parse("https://www.youtube.com/watch?v=eBcOK3ay94A"))
        }
        startActivity(intent)
    }

    fun clicLocal(view: View) {
        val (lat, lng) = post.coordinates
        val geoUri = Uri.parse("geo:$lat,$lng")
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            setData(geoUri)
        }
        startActivity(intent)
    }


    fun istruth(post: Post) {
        if (post.isLike) {
            likeImage.setImageResource(R.drawable.ic_like)
            likeText.setTextColor(Color.RED)
        }
        if (post.isComment) {
            commentImage.setImageResource(R.drawable.ic_comment)
            commentText.setTextColor(Color.RED)
        }
        if (post.isShare) {
            shareImage.setImageResource(R.drawable.ic_share)
            shareText.setTextColor(Color.RED)
        }
    }

    fun init(post: Post) {
        this.post = post
        autorText.text = post.autor
        postText.text = post.postText
        if (post.like > 0) {
            likeText.text = post.like.toString()
        } else {
            likeText.text = " "
        }
        if (post.comments > 0) {
            commentText.text = post.comments.toString()
        } else {
            commentText.text = " "
        }
        if (post.share > 0) {
            shareText.text = post.share.toString()
        } else {
            shareText.text = " "
        }

        datePost.text = date(post)
        istruth(post)

    }

    fun date(post: Post): String {
        val publishedAgo = (System.currentTimeMillis() - post.data) / 1000
        val toMin = if (publishedAgo > 3599) {
            publishedAgo / 3600
        } else {
            publishedAgo / 60
        }
        val result = when (publishedAgo) {
            in 0..59 -> "менее минуты назад"
            in 60..179 -> "минуту назад"
            in 180..299 -> "$toMin минуты назад"
            in 300..3599 -> "$toMin минут назад"
            in 3600..17999 -> "$toMin часа назад"
            else -> "$toMin часов назад "
        }
        return result


    }


}






