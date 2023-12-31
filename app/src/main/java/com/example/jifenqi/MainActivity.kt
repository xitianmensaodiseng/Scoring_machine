package com.example.jifenqi

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jifenqi.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var myViewModle: MyViewModle
    var isTimerRunning  :Boolean = false
    private var timerJob: Job? = null
    private var startTime = 0L
    private var elapsedTime = 0L
    private var bar_count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        myViewModle = ViewModelProvider(this).get(MyViewModle::class.java)
//        myViewModle.setButtonClickListeners(binding.addred1,binding.addred2,binding.addred3,binding.addbule1,
//            binding.addbule2,binding.addbule3,binding.chongzhi,binding.fanhui)
//
//        myViewModle.redfenshu.observe(this, Observer {
//            binding.hongduifenshu.text=it.toString()
//        })
//        myViewModle.bulefenshu.observe(this, Observer {
//            binding.landuifenshu.text=it.toString()
//        })
        myViewModle.scoreLiveData.observe(this) {
            binding.landuifenshu.text = it.visitScore.toString()
            binding.hongduifenshu.text = it.homeScore.toString()
        }
        binding.addred1.setOnClickListener {
            myViewModle.add(true, 1)
        }
        binding.addred2.setOnClickListener {
            myViewModle.add(true, 2)
        }
        binding.addred3.setOnClickListener {
            myViewModle.add(true, 3)
        }

        binding.addbule1.setOnClickListener {
            myViewModle.add(false, 1)
        }
        binding.addbule2.setOnClickListener {
            myViewModle.add(false, 2)
        }
        binding.addbule3.setOnClickListener {
            myViewModle.add(false, 3)
        }

        binding.fanhui.setOnClickListener {
            myViewModle.revert()
        }

        binding.chongzhi.setOnClickListener {
            myViewModle.reset()
            stopTimer()
        }
        binding.startPause.setOnClickListener{
            isTimerRunning=!isTimerRunning
            playingtime()
        }

    }


    fun playingtime() {
        if (isTimerRunning){
            startTimer()
        }else {
            pauseTimer()
        }
    }

    private fun pauseTimer() {
        isTimerRunning = false
        timerJob?.cancel()
    }

    private fun startTimer() {
        isTimerRunning = true
        startTime = System.currentTimeMillis() - elapsedTime
        timerJob = GlobalScope.launch(Dispatchers.Main) {
            while (isTimerRunning) {
                val currentTime = System.currentTimeMillis()
                elapsedTime = currentTime - startTime
                updateTimerText()
                delay(1000)
            }
        }
    }

    private fun stopTimer() {
        isTimerRunning = false
        timerJob?.cancel()
        elapsedTime = 0
        updateTimerText()
    }


    private fun updateTimerText() {
        val seconds = (elapsedTime / 1000) % 60
        val minutes = (elapsedTime / (1000 * 60)) % 60
        val hours = elapsedTime / (1000 * 60 * 60)
        binding.playingtime.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        Log.d(TAG, " 线程进行了：$hours: $minutes: $seconds ")
        if(minutes==0L && seconds==1L){
            binding.Subsection.text="第一小节"
        }else if (minutes==12L && seconds==1L){
            binding.Subsection.text="第二小节"
        }
        else if (minutes==24L && seconds==1L){
            binding.Subsection.text="第三小节"
        }
        else if (minutes==36L && seconds==1L){
            binding.Subsection.text="第四小节"
        }else if(minutes==48L && seconds==0L){
            binding.Subsection.text="比赛结束"
            pauseTimer()
        }
    }
}

data class Score(
    var homeScore: Int = 0,//主场分数
    var visitScore: Int = 0//客场分数
)