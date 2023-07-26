package com.example.jifenqi.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.jifenqi.Activity.ScoreActivity
import com.example.jifenqi.Http.WeatherCallback
import com.example.jifenqi.Http.WeatherData
import com.example.jifenqi.Http.WeatherHttp
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

    @SuppressLint("CutPasteId")
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

        val conditionToday = view?.findViewById<TextView>(R.id.conditionToday)


        val cityName = "成都"
        WeatherHttp.fetchWeather(cityName,object : WeatherCallback{
            @SuppressLint("SetTextI18n")
            override fun onSuccess(weatherData: WeatherData) {
                val city = weatherData.city
                val wea = weatherData.wea
                val win = weatherData.win
                val update_time = weatherData.update_time
                val wea_img = weatherData.wea_img
                val tem = weatherData.tem
                val tem_day = weatherData.tem_day
                val tem_night = weatherData.tem_night
                val win_speed = weatherData.win_speed
                val win_meter = weatherData.win_meter
                val air = weatherData.air

                activity?.runOnUiThread {

                    conditionToday?.isSelected = true
                    conditionToday?.text = "城市:$city   今日天气: $wea   风向：$win   更新时间：$update_time  " +
                            "天气对应图标：$wea_img  实时温度:$tem℃   白天温度(高温): $tem_day℃  白天温度(低温)：$tem_night℃ " +
                            "   风力等级：$win_speed  风速：$win_meter  空气质量：$air"
                    conditionToday?.setTextColor(Color.RED)
                }
            }

            override fun onError(errorMessage: String) {
                Log.d("conditionToday","conditionToday数据请求失败")
            }
        })


    }
}