package com.wjxls.statudyrecyclerviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerview: BackgroundRecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById<BackgroundRecyclerView>(R.id.back_recyclerview);
        recyclerview.layoutManager = LinearLayoutManager(this);
        var list = arrayListOf<String>();
        for (i in 0..50){
            list.add("数字： ${i}" );
        }
        recyclerview.adapter = BackRecyclerViewAdapter(this,list);
    }
}