package com.example.jifenqi.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.jifenqi.Fragment.HomeFragment
import com.example.jifenqi.Fragment.MineFragment
import com.example.jifenqi.Fragment.ZoomOutPageTransformer
import com.example.jifenqi.R
import org.jzvd.jzvideo.TAG

open class FragmetDemoActivity() : AppCompatActivity() {

    val TAG = "ASDF"

    val fragmentList = mutableListOf<Fragment>()

    private lateinit var vp2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPaeger()
        radioButtonCheck()

    }

    private fun radioButtonCheck() {
        findViewById<RadioButton>(R.id.rb_msg).setOnClickListener{

            vp2.currentItem = 0 // 切换到首页 Fragment
            Log.d(TAG,"首页按钮被点击")
        }
        findViewById<RadioButton>(R.id.rb_people).setOnClickListener{

            vp2.currentItem = 1 // 切换到我的 Fragment
            Log.d(TAG,"我的按钮被点击")
        }
    }


    private fun initPaeger() {
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        vp2 = findViewById(R.id.Viewpager)
        vp2.adapter = pagerAdapter
        vp2.setPageTransformer(ZoomOutPageTransformer())
        fragmentList.add(HomeFragment())
        fragmentList.add(MineFragment())

        vp2.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                updateBottomNavIcon(position, positionOffset)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

    }

        private fun updateBottomNavIcon(position: Int, positionOffset: Float) {
            val homeIcon = findViewById<RadioButton>(R.id.rb_msg)
            val mineIcon = findViewById<RadioButton>(R.id.rb_people)


            if (position == 0) {
                // 当前显示的是 "首页" 页面
                homeIcon.isChecked=true
            } else if (position == 1) {
                // 当前显示的是 "我的" 页面
                mineIcon.isChecked=true
            }

            // 根据 positionOffset 来实现图标的动画效果
            // 假设底部导航栏图标在选中时要放大，未选中时要缩小
//            val scaleSelected = 1.2f
//            val scaleUnselected = 1.0f
//
//            homeIcon.scaleX = scaleUnselected + (scaleSelected - scaleUnselected) * positionOffset
//            homeIcon.scaleY = scaleUnselected + (scaleSelected - scaleUnselected) * positionOffset
//            mineIcon.scaleX = scaleSelected - (scaleSelected - scaleUnselected) * positionOffset
//            mineIcon.scaleY = scaleSelected - (scaleSelected - scaleUnselected) * positionOffset
        }

    override fun onBackPressed() {
        if (vp2.currentItem == 0) {
            super.onBackPressed()
        } else {
            vp2.currentItem = vp2.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment = fragmentList[position]

    }
}


