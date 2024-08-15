package com.propsimlify.app.ui.main.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.propsimlify.app.ui.main.favourite.FavouriteFragment
import com.propsimlify.app.ui.main.home.HomeFragment
import com.propsimlify.app.ui.main.map.MapFragment
import com.propsimlify.app.ui.main.message.MessageFragment
import com.propsimlify.app.ui.main.profile.ProfileFragment

class MainHomeTabAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment.newInstance()
            }
            1 -> {
                MapFragment.newInstance()
            }
            2-> {
                FavouriteFragment.newInstance()
            }
            3 -> {
                MessageFragment.newInstance()
            }
            4 -> {
                ProfileFragment.newInstance()
            }
            else -> {
                HomeFragment.newInstance()
            }
        }
    }
}