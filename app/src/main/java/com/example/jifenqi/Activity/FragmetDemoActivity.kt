package com.example.jifenqi.Activity

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.jifenqi.Fragment.HomeFragment
import com.example.jifenqi.Fragment.MineFragment
import com.example.jifenqi.Fragment.ZoomOutPageTransformer
import com.example.jifenqi.R

@Suppress("DEPRECATION")
open class FragmetDemoActivity() : AppCompatActivity() {

    val TAG = "ASDF"

    val fragmentList = mutableListOf<Fragment>()

    private lateinit var vp2: ViewPager2  //定义一个viewpager全局变量

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPaeger()
        radioButtonCheck()


    }

    private fun radioButtonCheck() {//设置按钮监听，实现按钮页面滑动
        findViewById<RadioButton>(R.id.rb_msg).setOnClickListener{

            vp2.currentItem = 0 // 切换到首页 Fragment
            Log.d(TAG,"首页按钮被点击")
        }
        findViewById<RadioButton>(R.id.rb_people).setOnClickListener{

            vp2.currentItem = 1 // 切换到我的 Fragment
            Log.d(TAG,"我的按钮被点击")
        }
    }


    private fun initPaeger() {//初始化程序
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        vp2 = findViewById(R.id.Viewpager)
        vp2.adapter = pagerAdapter
        //设置界面滑动切换的效果
        vp2.setPageTransformer(ZoomOutPageTransformer())

        //添加Fragment组件到数组中，在ScreenSlidePagerAdapter适配器中 override fun createFragment实现Fragment与viewpager2的联系
        fragmentList.add(HomeFragment())
        fragmentList.add(MineFragment())
//Viewpager2滑动监听
        vp2.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback(){
            //当滑动界面是的方法
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                updateBottomNavIcon(position)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

    }

        private fun updateBottomNavIcon(position: Int) {//实现按钮点亮与滑动界面的联动
            val homeIcon = findViewById<RadioButton>(R.id.rb_msg)
            val mineIcon = findViewById<RadioButton>(R.id.rb_people)


            if (position == 0) {
                // 当前显示的是 "首页" 页面
                homeIcon.isChecked=true
            } else if (position == 1) {
                // 当前显示的是 "我的" 页面
                mineIcon.isChecked=true
            }


        }


    @Deprecated("Deprecated in Java")
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


