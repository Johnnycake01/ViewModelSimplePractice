package com.example.classworkonviewmodel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.classworkonviewmodel.R
import com.example.classworkonviewmodel.model.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var showCount:TextView
    lateinit var addButton: Button
    lateinit var mvcBtButton:Button
    lateinit var tvCount:TextView
    lateinit var tvAdd:TextView
    lateinit var btAdd:Button
    lateinit var viewModelInatsnce:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews() //initialise views



        //viewModel
        viewModelInatsnce = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModelInatsnce.data.observe(this, {
            showCount.text = it.toString()
        })


        addButton.setOnClickListener {

            viewModelInatsnce.counting()
            addButton.isClickable = false
        }

        //mvc
        mvcBtButton.setOnClickListener {
            mvcCount()
        }

        //add
        tvAdd.text = viewModelInatsnce.count.toString()
        btAdd.setOnClickListener {
            viewModelInatsnce.addOne()
            tvAdd.text = viewModelInatsnce.count.toString()
        }

    }

    private fun initViews() {
        showCount = findViewById(R.id.tvText)
        addButton = findViewById(R.id.tvButton)
        mvcBtButton = findViewById(R.id.btCount)
        tvCount = findViewById(R.id.tvCount)
        tvAdd = findViewById(R.id.tvAdd)
        btAdd = findViewById(R.id.btAdd)
    }

    private fun mvcCount() {
        Thread(Runnable {

            for (i in 0 until 100) {
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    mvcBtButton.isClickable = false
                    tvCount.text = i.toString()
                }
            }
        }).start()
    }
}