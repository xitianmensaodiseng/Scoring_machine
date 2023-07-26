package com.example.jifenqi.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jifenqi.Activity.Score

class MyViewModel : ViewModel() {
   // 当前的分数
    val scoreLiveData = MutableLiveData<Score>()
    private val history = mutableListOf<Score>()

    init {
        reset()
    }

    fun add(homeFlag: Boolean, num: Int) {//对myViewModle 加方法的实现

        //将分数scoreLiveData的值取出来赋值给Score类定义的newScore
        val newScore = scoreLiveData.value!!.copy()

        //进行是主队还是客队得分，并对其值进行修改
        if (homeFlag) {
            newScore.homeScore += num
        } else {
            newScore.visitScore += num
        }
        //对历史值进行存储
        history.add(scoreLiveData.value!!)
        //将修改过后的值重新赋值给scoreLiveData,scoreLiveData监听值改变后自动修改内容
        scoreLiveData.value = newScore//
    }

    fun revert() {//对返回按钮功能进行实现，每点一次history数组中最后一位scoreLiveData.value会被去除
        if (history.isNotEmpty()) {
            scoreLiveData.value = history.removeLast()//
        }

    }

    fun reset() {//对重置按钮进行实现历史数据清零， scoreLiveData.value 清零
        history.clear()
        scoreLiveData.value = Score(0, 0)
        history.add(scoreLiveData.value!!)
    }
}


