package com.example.fragmentmusicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.fragmentmusicplayer.adapters.ViewPager2Adapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPager2Adapter: ViewPager2Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2 = findViewById(R.id.viewPager)
        viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2.adapter = viewPager2Adapter

    }
}