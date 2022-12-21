package com.example.doodleandroid

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ui_screens.FragGroups
import com.example.ui_screens.FragIndividuals
import com.example.ui_screens.User

internal class TabAdaptor(fm:FragmentManager,var totalTabs:Int,var fragIndAdap:FragIndividuals,val lifecycle: Lifecycle):FragmentStateAdapter(fm,lifecycle) {



    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                fragIndAdap

            }
            1->{
                FragGroups()

            }
            else -> createFragment(position)
        }
    }

}
