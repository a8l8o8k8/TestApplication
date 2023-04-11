package com.example.testapplication

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapplication.model.SwitchModel

class MainViewModel():ViewModel() {

    var list= mutableListOf<SwitchModel>()
    val observeResponse = MutableLiveData<Any>()


    fun addList() {
        list.clear()
        for (i in 0 until 10){
            list.add(i,SwitchModel(false,"Service is disabled","Turn Off switch to disable the Service"))
        }
        observeResponse.value=list
    }

}
