package com.example.fragmentmusicplayer.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragmentmusicplayer.fragments.FirstFragment
import com.example.fragmentmusicplayer.fragments.SecondFragment

class ViewPager2Adapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0){
            return FirstFragment()
        }
        else if (position == 1){
            return SecondFragment()
        }
        return Fragment()
    }
}