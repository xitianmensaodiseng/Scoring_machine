package com.example.jifenqi.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.example.jifenqi.R

class Animation_activity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        val image: ImageView = findViewById(R.id.image)


//        image.setOnClickListener {
//            val hyperspaceJump: Animation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump)
//            image.startAnimation(hyperspaceJump)
//    }
        image.setOnClickListener{
            val Aset : AnimationSet= AnimationSet(true)
            val Apa : AlphaAnimation = AlphaAnimation(1.0f,0.5f)
            val Rotate : RotateAnimation = RotateAnimation(50f,0f,50,5f,50,5f)
            Aset.addAnimation(Apa)
            Aset.addAnimation(Rotate)
            Aset.duration=7000
            image.startAnimation(Aset)
            Aset.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    // 动画开始时的操作（可选）
                }

                override fun onAnimationEnd(animation: Animation?) {
                    // 动画结束时的操作（可选）
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    // 动画重复时的操作（可选）
                }
            })

        }



    }

}


