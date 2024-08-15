package com.propsimlify.app.ui.main.map.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.propsimlify.app.ui.main.map.mapscreen.MapScreenFragment
import com.propsimlify.app.ui.main.map.arscreen.ARFragment

class MapTabAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                MapScreenFragment.newInstance()
            }
            1 -> {
                ARFragment.newInstance()
            }
            else -> {
                MapScreenFragment.newInstance()
            }
        }
    }
}