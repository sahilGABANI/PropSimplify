package com.propsimlify.app.ui.propy.lifestyle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.propsimlify.R
import com.propsimlify.app.base.BaseFragment
import com.propsimlify.app.base.RxBus
import com.propsimlify.app.base.RxEvent
import com.propsimlify.app.base.extension.showToast
import com.propsimlify.app.base.extension.subscribeAndObserveOnMainThread
import com.propsimlify.app.model.LifeStyleInfo
import com.propsimlify.app.model.LiveDestinationInfo
import com.propsimlify.app.model.getLifeStyleList
import com.propsimlify.app.model.getLiveDestinationList
import com.propsimlify.app.ui.propy.livedestination.view.LiveDestinationAdapter
import com.propsimlify.app.ui.propy.lifestyle.view.LifeStyleAdapter
import com.propsimlify.app.utils.AlphaAndScalePageTransformer
import com.propsimlify.databinding.FragmentLifeStyleBinding
import com.propsimlify.databinding.FragmentLiveDestinationBinding
import java.util.ArrayList

class LifeStyleFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LifeStyleFragment()
    }

    private var listOfLifeStyleInfo: ArrayList<LifeStyleInfo> ?= arrayListOf()
    private var _binding: FragmentLifeStyleBinding? = null
    private val binding get() = _binding!!
    private lateinit var lifeStyleAdapter: LifeStyleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLifeStyleBinding.inflate(inflater, container, false)
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
        listOfLifeStyleInfo = getLifeStyleList()
        lifeStyleAdapter = LifeStyleAdapter(requireContext()).apply {
            lifeStyleClick.subscribeAndObserveOnMainThread { item ->
                val listofCategory = lifeStyleAdapter.listOfDataItems
                listofCategory?.find { it.id == item.id }?.apply {
                    isSelected = !isSelected
                }
                lifeStyleAdapter.listOfDataItems = listofCategory
            }.autoDispose()
        }
        lifeStyleAdapter.listOfDataItems = listOfLifeStyleInfo
        binding.rvLifeStyle.apply {
            adapter = lifeStyleAdapter
            layoutManager = GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
        }

    }

    override fun onResume() {
        super.onResume()
        RxBus.listen(RxEvent.CheckIsSelectAnyOptionInLifeStyle::class.java).subscribeAndObserveOnMainThread {
            val listOfLifeStyleInfo = lifeStyleAdapter.listOfDataItems
            listOfLifeStyleInfo?.firstOrNull { it.isSelected == true }.apply {
                if (this == null) {
                    showToast("please select your lifestyle.")
                } else {
                    RxBus.publish(RxEvent.NavigateBudgetScreen)
                }
            }
        }.autoDispose()
    }

}