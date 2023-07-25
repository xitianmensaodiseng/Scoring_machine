package com.example.jifenqi.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.jifenqi.Fragment.HomeFragment
import com.example.jifenqi.Fragment.MineFragment
import com.example.jifenqi.Fragment.ZoomOutPageTransformer
import com.example.jifenqi.R

class FragmetDemoActivity() : AppCompatActivity() {

    val fragmentList = mutableListOf<Fragment>()

    private lateinit var vp2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPaeger()

    }


    private fun initPaeger() {
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        vp2 = findViewById(R.id.Viewpager)
        vp2.adapter =pagerAdapter
        vp2.setPageTransformer(ZoomOutPageTransformer())
        fragmentList.add(HomeFragment())
        fragmentList.add(MineFragment())
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


