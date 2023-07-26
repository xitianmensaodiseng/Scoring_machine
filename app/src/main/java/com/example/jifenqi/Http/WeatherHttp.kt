package com.example.jifenqi.Http

import android.util.Log
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object WeatherHttp {
    private val client = OkHttpClient()

    // 定义天气 API 的基本 URL
    private const val BASE_URL = "https://v0.yiketianqi.com/free/day"

    // 用你的 OpenWeatherMap API 密钥替换下面的 "YOUR_API_KEY_HERE"
    private const val API_KEY = "63758555"
    private const val appsecret = "ul9f8UDE"

    fun fetchWeather(cityName: String, callback: WeatherCallback) {
        val url = "$BASE_URL?unescape=1&city=$cityName&appid=$API_KEY&appsecret=$appsecret"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                Log.d("conditionToday1","conditionToday数据请求成功")
                val responseBody = response.body?.string()
                if (response.isSuccessful && responseBody != null) {
                    // 解析天气数据并将其传递到回调函数中
                    val weatherData = parseWeatherData(responseBody)
                    callback.onSuccess(weatherData)
                } else {
                    callback.onError("无法获取天气数据")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                callback.onError("网络请求失败")
                Log.d("conditionToday2","conditionToday数据请求失败")
            }
        })
    }

    private fun parseWeatherData(response: String): WeatherData {
        try {
            val jsonObject = JSONObject(response)
            val city = jsonObject.getString("city")
            val wea = jsonObject.getString("wea")
            val win = jsonObject.getString("win")
            val tem = jsonObject.getString("tem")
            val tem_day = jsonObject.getString("tem_day")
            val tem_night = jsonObject.getString("tem_night")
            val win_speed = jsonObject.getString("win_speed")
            val win_meter = jsonObject.getString("win_meter")
            val air = jsonObject.getString("air")
            val update_time = jsonObject.getString("update_time")
            val wea_img = jsonObject.getString("wea_img")


            Log.d("conditionToday1","conditionToday数据请求成功1")
            // 构建 WeatherData 对象
            return WeatherData(city, wea, win,tem,tem_day,tem_night,win_speed,
                win_meter,air,update_time,wea_img)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return WeatherData("未知城市", "未知天气","未知风向","未知风向","未知风向",
            "未知风向","未知风向","未知风向","未知风向","未知风向","未知风向")
    }
}

// 定义 WeatherData 类来保存解析后的天气数据
data class WeatherData(val city: String, val wea: String,val win:String,val tem:String,val tem_day:String
                       ,val tem_night:String,val win_speed:String,val win_meter:String,val air:String,val update_time:String,
                       val wea_img:String)

// 定义回调接口来处理天气数据的响应
interface WeatherCallback {
    fun onSuccess(weatherData: WeatherData)
    fun onError(errorMessage: String)
}
