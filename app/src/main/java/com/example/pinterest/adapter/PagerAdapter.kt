package com.example.pinterest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pinterest.fragment.MessageFragment
import com.example.pinterest.fragment.MessageVPFragment
import com.example.pinterest.fragment.UpdatesFragment

class PagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 ->{
                return UpdatesFragment()
            }
            1 ->{
                return MessageVPFragment()
            }
            else ->{
                return UpdatesFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {
                return "Updates"
            }
            1 -> {
                return "Message"
            }
        }
        return super.getPageTitle(position)
    }
}