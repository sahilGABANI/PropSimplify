package com.propsimlify.app.ui.propy.property_type

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.propsimlify.R
import com.propsimlify.app.base.BaseFragment
import com.propsimlify.app.model.LiveDestinationInfo
import com.propsimlify.app.model.getLiveDestinationList
import com.propsimlify.app.model.getPropertyTypeList
import com.propsimlify.app.ui.propy.livedestination.view.LiveDestinationAdapter
import com.propsimlify.app.utils.AlphaAndScalePageTransformer
import com.propsimlify.databinding.FragmentLiveDestinationBinding
import com.propsimlify.databinding.FragmentPropertyTypeBinding
import java.util.ArrayList

class PropertyTypeFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = PropertyTypeFragment()
    }
    private lateinit var listOfLiveDestinationInfo: ArrayList<LiveDestinationInfo>
    private var _binding: FragmentPropertyTypeBinding? = null
    private val binding get() = _binding!!
    private lateinit var liveDestinationAdapter: LiveDestinationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPropertyTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToViewEvents()
    }

    private fun listenToViewEvents() {
        initAdapter()
    }

    private fun initAdapter() {
        listOfLiveDestinationInfo = getPropertyTypeList()
        liveDestinationAdapter = LiveDestinationAdapter(requireContext())
        liveDestinationAdapter.listOfDataItems = listOfLiveDestinationInfo
        binding.cardViewPager.apply {
            adapter = liveDestinationAdapter
            offscreenPageLimit = 3
        }

        binding.cardViewPager.currentItem = 1
        binding.cardViewPager.setPageTransformer(AlphaAndScalePageTransformer())
//        binding.cardViewPager.setPadding(40)
        binding.cardViewPager.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                listOfLiveDestinationInfo.filter { it.isSelected == true }.forEach {
                    it.isSelected = false
                }
                listOfLiveDestinationInfo.get(position).apply {
                    isSelected = true
                }
                liveDestinationAdapter.listOfDataItems = listOfLiveDestinationInfo
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }


}