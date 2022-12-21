package com.example.ui_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.doodleandroid.TabAdaptor
import com.google.android.material.tabs.TabLayout


class FragGroups() : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_frag_groups, container, false)
        tabLayout=view.findViewById(R.id.tabnest_layout)
        viewPager=view.findViewById(R.id.nesttabs)
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.tSecret)).setIcon(R.drawable.ic_baseline_person_24))
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.tPrivate)).setIcon(R.drawable.ic_baseline_group_24))
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.tHosting)).setIcon(R.drawable.ic_baseline_person_24))
        tabLayout.tabGravity=TabLayout.GRAVITY_FILL
        val adaptor= NestAdaptor(requireActivity().supportFragmentManager,tabLayout.tabCount)
        viewPager.adapter=adaptor
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        return view


    }

}