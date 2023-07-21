package com.example.jifenqi

import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jzvd.jzvideo.TAG
import java.util.logging.Handler

class MyViewModle : ViewModel() {
    //当前的分数
    val scoreLiveData = MutableLiveData<Score>()
    private val history = mutableListOf<Score>()

    init {
        reset()
    }

    fun add(homeFlag: Boolean, num: Int) {
        val newScore = scoreLiveData.value!!.copy()
        if (homeFlag) {
            newScore.homeScore += num
        } else {
            newScore.visitScore += num
        }
        history.add(scoreLiveData.value!!)
        scoreLiveData.value = newScore//
    }

    fun revert() {
        if (history.isNotEmpty()) {
            scoreLiveData.value = history.removeLast()
        }

    }

    fun reset() {
        history.clear()
        scoreLiveData.value = Score(0, 0)
        history.add(scoreLiveData.value!!)
    }




//    var redfenshu = MutableLiveData<Int>()
//    var bulefenshu = MutableLiveData<Int>()
//
//    private var prevRedValue: Int = 0
//    private var prevBlueValue: Int = 0
//
//    init {
//        redfenshu.value = 0
//        bulefenshu.value = 0
//    }
//
//    private inner class OnButtonClickListener : View.OnClickListener {
//        override fun onClick(view: View) {
//            // 使用 when 判断不同按钮的点击事件
//            when (view.id) {
//                R.id.addbule1 -> bulefenshu.value=(bulefenshu.value)?.plus(1)
//                R.id.addbule2 -> bulefenshu.value=(bulefenshu.value)?.plus(2)
//                R.id.addbule3 -> bulefenshu.value=(bulefenshu.value)?.plus(3)
//                R.id.addred1 -> redfenshu.value=(redfenshu.value)?.plus(1)
//                R.id.addred2 -> redfenshu.value=(redfenshu.value)?.plus(2)
//                R.id.addred3 -> redfenshu.value=(redfenshu.value)?.plus(3)
//                R.id.fanhui -> cexiao()
//                R.id.chongzhi -> zhilin()
//                // 添加更多按钮的判断，按需设置即可
//            }
//
//        }
//    }
//    private val onButtonClickListener = OnButtonClickListener()
//
//    // 在 ViewModel 中创建函数作为点击事件监听
//    fun setButtonClickListeners(button1: View, button2: View,button3: View,
//                                button4: View,button5: View, button6: View,
//                                imagebutton1:View,imagebutton2:View) {
//        button1.setOnClickListener(onButtonClickListener)
//        button2.setOnClickListener(onButtonClickListener)
//        button3.setOnClickListener(onButtonClickListener)
//        button4.setOnClickListener(onButtonClickListener)
//        button5.setOnClickListener(onButtonClickListener)
//        button6.setOnClickListener(onButtonClickListener)
//        imagebutton1.setOnClickListener(onButtonClickListener)
//        imagebutton2.setOnClickListener(onButtonClickListener)
//        // 添加更多按钮的监听，按需设置即可
//    }
//
//     fun zhilin(){
//        redfenshu.value = 0
//        bulefenshu.value = 0
//        prevRedValue = 0
//        prevBlueValue = 0
//    }
//     fun cexiao(){
//        redfenshu.value = prevRedValue
//        bulefenshu.value = prevBlueValue
//        Log.d(TAG,"撤销按钮被点击")
//    }
}


