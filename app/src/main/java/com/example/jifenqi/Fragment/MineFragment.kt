package com.example.jifenqi.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jifenqi.R

class MineFragment : Fragment() {
//创建好一个fragment碎片xml
    //创建一个Fragment子类
    //添加依赖

    override fun onCreateView(//在onCreateView中实现fragment_home xml 的创建图像化
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

}