package com.example.classworkonviewmodel.model

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*


class MainActivityViewModel : ViewModel(){
    //live data to store and update count
   private var _data:MutableLiveData<Int> = MutableLiveData()
    val data:LiveData<Int> = _data
//func to count
    fun counting(){
        viewModelScope.launch(Dispatchers.IO){
            for (i in 1..100){
                try {
                    Thread.sleep(1000)//delay count by 1 secs
                }catch (e: Exception){
                    e.printStackTrace()
                }
                withContext(Dispatchers.Main){
                    _data.value = i //update count value on main thread
                }
            }
        }

    }

}