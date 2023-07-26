package com.example.jifenqi.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.jifenqi.Activity.ScoreActivity
import com.example.jifenqi.R

class HomeFragment : Fragment() {
//创建好一个fragment碎片xml
    //创建一个Fragment子类
    //添加依赖
    override fun onCreateView(//在onCreateView中实现fragment_home xml 的创建图像化
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onResume() {//在onResume中实现用户与Fragment碎片的按钮与Activity的跳转
        super.onResume()
        val basketballButton = view?.findViewById<ImageButton>(R.id.fra_bu_basketball)
        if (basketballButton != null) {
            basketballButton.setOnClickListener{//跳转到ScoreActivity
                val intent = Intent(requireActivity(), ScoreActivity::class.java)
                // 执行跳转操作
                startActivity(intent)
            }
        }
    }
}