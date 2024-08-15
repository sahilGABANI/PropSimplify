package com.propsimlify.app.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.propsimlify.app.base.extension.subscribeAndObserveOnMainThread
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.propsimlify.app.base.BaseFragment
import com.propsimlify.app.base.extension.throttleClicks
import com.propsimlify.app.model.HomePagePostInfoState
//import com.propsimlify.app.model.getHomeFreshPostList
import com.propsimlify.app.model.getHomePostCategoryList
import com.propsimlify.app.model.getHomePostList
import com.propsimlify.app.ui.main.home.view.HomePostAdapter
import com.propsimlify.app.ui.main.home.view.HomePostCategoryAdapter
import com.propsimlify.app.ui.propy.PropyActivity
import com.propsimlify.app.utils.ViewPagerLayoutManager
import com.propsimlify.databinding.FragmentHomeBinding
import com.yuyakaido.android.cardstackview.*

class HomeFragment : BaseFragment(), CardStackListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homePostAdapter: HomePostAdapter
    private lateinit var homePostCategoryAdapter: HomePostCategoryAdapter

    private val manager by lazy { CardStackLayoutManager(requireContext(), this) }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        listenToViewEvents()

    }

    private fun initAdapter() {
        homePostAdapter = HomePostAdapter(requireContext()).apply {
            postViewClicks.subscribeAndObserveOnMainThread { state ->
                when (state) {
                    is HomePagePostInfoState.UserLikeClick -> {
//                        val listOfDataVideo = homePostAdapter.listOfDataItems
//                        listOfDataVideo?.find { it.id == state.postInfo.id }?.apply {
//                            isLiked  = !isLiked
//                        }
//                        homePostAdapter.listOfDataItems = listOfDataItems
                    }
                    else -> {

                    }
                }
            }.autoDispose()
        }
        binding.homeRecyclerView.apply {
            layoutManager = manager
            adapter = homePostAdapter
        }
        homePostCategoryAdapter = HomePostCategoryAdapter(requireContext()).apply {
            categoryClick.subscribeAndObserveOnMainThread { item ->
                val listofCategory = homePostCategoryAdapter.listOfDataItems
                listofCategory?.filter { it.isSelected == true }?.forEach {
                    it.isSelected = false
                }
                listofCategory?.find { it.id == item.id }?.apply {
                    isSelected = true
                }
                homePostCategoryAdapter.listOfDataItems = listofCategory
            }.autoDispose()
        }
        val listOfHomeCategory = getHomePostCategoryList()
        homePostCategoryAdapter.listOfDataItems = listOfHomeCategory
        binding.rvVenueCategoryList.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = homePostCategoryAdapter
        }
    }

    private fun listenToViewEvents() {
        val listOfPost = getHomePostList()
        homePostAdapter.listOfDataItems = listOfPost
        setupCardStackView()

        binding.swipeRefreshLayout.refreshes().subscribeAndObserveOnMainThread {
            binding.swipeRefreshLayout.isRefreshing = false
            reset()
        }.autoDispose()

        binding.ivAIButton.throttleClicks().subscribeAndObserveOnMainThread {
            startActivity(PropyActivity.getIntent(requireContext()))
        }.autoDispose()
    }

    private fun setupCardStackView() {
        initialize()
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(9.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.5f)
        manager.setMaxDegree(20.0f)

        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(false)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        Log.d("CardStackView", "onCardSwiped: homePostAdapter.itemCount = ${homePostAdapter.itemCount}")
        if (manager.topPosition == homePostAdapter.itemCount - 3) {
            paginate()
        }
    }

    private fun paginate() {
        val listOfPost = homePostAdapter.listOfDataItems as ArrayList
        listOfPost.addAll(getHomePostList())
        homePostAdapter.listOfDataItems = listOfPost
    }

    private fun reset() {
        val listOfPost = getHomePostList()
        homePostAdapter.listOfDataItems = listOfPost
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
//        val textView = view.findViewById<TextView>(com.yuyakaido.android.cardstackview.R.id.item_name)
        Log.d("CardStackView", "onCardAppeared: ($position) ")
    }

    override fun onCardDisappeared(view: View, position: Int) {
//        val textView = view.findViewById<TextView>(com.yuyakaido.android.cardstackview.R.id.item_name)
        Log.d("CardStackView", "onCardDisappeared: ($position) ")
    }

}