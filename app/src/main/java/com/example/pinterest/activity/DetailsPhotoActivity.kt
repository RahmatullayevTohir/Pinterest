package com.example.pinterest.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.pinterest.R
import com.example.pinterest.model.PhotoModel
import com.example.pinterest.utils.setOnDoubleClickListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DetailsPhotoActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var lottieAnimationView: LottieAnimationView
    lateinit var iv_back:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_photo)

        initViewsDetails()

        supportPostponeEnterTransition()
        val extras = intent.extras
        val photoItem: PhotoModel = extras!!.getSerializable("photoTester") as PhotoModel


        Log.d("@@@",photoItem.toString())

        imageView = findViewById(R.id.iv_detailed_photo)
        lottieAnimationView = findViewById(R.id.lottie_animation)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName =
                extras.getString("transitionName")
            imageView.transitionName = imageTransitionName
        }

        Picasso.get().load(photoItem.urls.small).into(imageView, object : Callback {
            override fun onSuccess() {
                supportStartPostponedEnterTransition()
            }

            override fun onError(e: Exception?) {
                supportStartPostponedEnterTransition();
            }
        })

        imageView.setOnDoubleClickListener {
            lottieAnimationView.setAnimation("like-animation.json")
            lottieAnimationView.playAnimation()
            object : CountDownTimer(2000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    lottieAnimationView.visibility = View.GONE
                    Toast.makeText(
                        this@DetailsPhotoActivity,
                        "Image saved to Favourites",
                        Toast.LENGTH_SHORT
                    ).show()
                    lottieAnimationView.pauseAnimation()
                }
            }.start()


        }
    }

    private fun initViewsDetails() {
        iv_back = findViewById(R.id.iv_back)
        iv_back.setOnClickListener {
            finish()
        }
    }

}