package com.propsimlify.app.ui.propy.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.propsimlify.app.ui.propy.budget.BudgetFragment
import com.propsimlify.app.ui.propy.lifestyle.LifeStyleFragment
import com.propsimlify.app.ui.propy.livedestination.LiveDestinationFragment
import com.propsimlify.app.ui.propy.property_type.PropertyTypeFragment

class PropyNavigationAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                LiveDestinationFragment.newInstance()
            }
            1 -> {
                PropertyTypeFragment.newInstance()
            }
            2 -> {
                LifeStyleFragment.newInstance()
            }
            3 -> {
                BudgetFragment.newInstance()
            }
            else -> {
                LiveDestinationFragment.newInstance()
            }
        }
    }
}