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
            scoreLiveData.value = history.removeLast()//
        }

    }

    fun reset() {
        history.clear()
        scoreLiveData.value = Score(0, 0)
        history.add(scoreLiveData.value!!)
    }
}


