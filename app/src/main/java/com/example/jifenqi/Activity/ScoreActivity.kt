package com.example.jifenqi.Activity

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.jifenqi.R
import com.example.jifenqi.ViewModel.MyViewModel
import com.example.jifenqi.databinding.ActivityScoreBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScoreActivity : AppCompatActivity() {
    //binding的对象确定
    lateinit var binding: ActivityScoreBinding
    //ViewModel的对象确定
    private lateinit var myViewModle: MyViewModel

    // 全局变量，用于表示计时器是否正在运行
    var isTimerRunning  :Boolean = false

    // 全局变量，用于持有计时器协程的引用
    private var timerJob: Job? = null

    // 全局变量，用于记录计时器开始的时间戳
    private var startTime = 0L

    // 全局变量，用于记录已经流逝的时间
    private var elapsedTime = 0L
//    private var bar_count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_score)
        //将定义的binding与activity_score联系起来
        binding = DataBindingUtil.setContentView(this, R.layout.activity_score)

        //将定义的myViewModle与自定义的MyViewModel这个类联系起来
        myViewModle = ViewModelProvider(this).get(MyViewModel::class.java)

        //调用observe方法设置myViewModle的LiveData的监听
        myViewModle.scoreLiveData.observe(this) {
            //当visitScore（主场分数）或homeScore（客场分数）发生变化是LiveData监听会对两队分数text进行修改
            binding.landuifenshu.text = it.visitScore.toString()
            binding.hongduifenshu.text = it.homeScore.toString()
        }
        //初始化按钮并进行监听，给其被点击时附加功能
       initbindingbutton()

    }

    private fun initbindingbutton() {
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


    fun playingtime() {//判断是否开始比赛
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

    @OptIn(DelicateCoroutinesApi::class)
    private fun startTimer() {
        // 将计时器状态标记为正在运行中
        isTimerRunning = true
        // 记录计时器开始的时间戳
        startTime = System.currentTimeMillis() - elapsedTime
        // 使用协程创建一个计时器，将其绑定到主线程上运行
        timerJob = GlobalScope.launch(Dispatchers.Main) {
            // 循环更新计时器
            while (isTimerRunning) {
               // 获取当前时间的时间戳
                val currentTime = System.currentTimeMillis()
                // 计算已经流逝的时间
                elapsedTime = currentTime - startTime
                // 更新计时器显示文本
                updateTimerText()
                // 延迟一秒，等待下一次更新
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


    private fun updateTimerText() { // 在这里更新计时器的显示文本或其他视图
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

data class Score @JvmOverloads constructor(
    var homeScore: Int = 0,//主场分数
    var visitScore: Int = 0//客场分数
)