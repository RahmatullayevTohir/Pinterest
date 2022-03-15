package com.example.pinterest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.example.pinterest.R
import com.example.pinterest.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout

class MessageFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  view: View = inflater.inflate(R.layout.message_fragment,container,false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = PagerAdapter(requireActivity().supportFragmentManager)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
    }


}