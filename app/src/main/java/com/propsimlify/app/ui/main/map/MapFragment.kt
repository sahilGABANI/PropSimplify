package com.propsimlify.app.ui.main.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.propsimlify.R
import com.propsimlify.app.base.BaseFragment
import com.propsimlify.app.ui.main.favourite.FavouriteFragment
import com.propsimlify.app.ui.main.map.view.MapTabAdapter
import com.propsimlify.databinding.ActivityWelcomeBinding
import com.propsimlify.databinding.FragmentBudgetBinding
import com.propsimlify.databinding.FragmentMapBinding

class MapFragment : BaseFragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    companion object {
        @JvmStatic
        fun newInstance() = MapFragment()
    }
    private lateinit var mapTabAdapter : MapTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToViewEvents()

    }

    private fun listenToViewEvents() {
        mapTabAdapter = MapTabAdapter(requireActivity())
        binding.viewPager.adapter = mapTabAdapter
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.currentItem = 0

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.label_maps)
                }
                1 -> {
                    tab.text = getString(R.string.label_ar)
                }
            }}.attach()
    }

}