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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onResume() {
        super.onResume()
        val basketballButton = view?.findViewById<ImageButton>(R.id.fra_bu_basketball)
        if (basketballButton != null) {
            basketballButton.setOnClickListener{
                val intent = Intent(requireActivity(), ScoreActivity::class.java)
                // 执行跳转操作
                startActivity(intent)
            }
        }
    }
}