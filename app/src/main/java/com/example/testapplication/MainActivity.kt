package com.example.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.model.SwitchModel

class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding

    val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        viewModel.addList()

        observerResponse()

    }

    private fun observerResponse() {
        viewModel.observeResponse.observe(this, Observer {
            when (it) {
               is List<*> -> {
                  viewModel.observeResponse.value = null
                           initilizeRecyclerView()

               }
            }
        })
    }


    fun initilizeRecyclerView() {
        mBinding.switchRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val switchAdapter = SwitchAdapter(this, viewModel.list)
        mBinding.switchRecycler.adapter = switchAdapter

    }
}