package com.example.ui_screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

internal class NestAdaptor(fm: FragmentManager, var totalTabs:Int): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                grpSecretFrag

            }
            1->{
                FragIndividuals()

            }
            2->{
                FragIndividuals()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }

}