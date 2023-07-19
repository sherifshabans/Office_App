package com.android.kotlinmvvmtodolist.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.kotlinmvvmtodolist.List2
import com.android.kotlinmvvmtodolist.ui.task.TaskFragment

internal class AdabterForTab(var context: Context ,fm:FragmentManager,var totalTabs:Int ):FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
return  when(position){
    0->{
        TaskFragment()
    }
    1->{
        List2()
    }
    else -> getItem(position)


     }

    }
    override fun getCount(): Int {
    return totalTabs
    }


}