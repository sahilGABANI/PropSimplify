package com.propsimlify.app.ui.main.home.view

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.propsimlify.R
import com.propsimlify.app.base.ConstraintLayoutWithLifecycle
import com.propsimlify.app.base.extension.getDrawable
import com.propsimlify.app.base.extension.subscribeAndObserveOnMainThread
import com.propsimlify.app.base.extension.throttleClicks
import com.propsimlify.app.model.HomePagePostInfoState
import com.propsimlify.app.model.HomePostInfo
import com.propsimlify.app.model.getHomePostList
import com.propsimlify.databinding.HomeItemViewBinding
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.text.NumberFormat
import java.util.*

class HomeViewItem(context: Context) : ConstraintLayoutWithLifecycle(context) {
    private lateinit var dataVideo: HomePostInfo

    private var binding: HomeItemViewBinding? = null


    private val postViewClicksSubject: PublishSubject<HomePagePostInfoState> = PublishSubject.create()
    val postViewClicks: Observable<HomePagePostInfoState> = postViewClicksSubject.hide()


    init {
        inflateUi()
    }

    private lateinit var homeDetailsAdapter: HomeDetailsAdapter

    private fun inflateUi() {


        val view = View.inflate(context, R.layout.home_item_view, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        binding = HomeItemViewBinding.bind(view)



        binding?.apply {
            ivLike.throttleClicks().subscribeAndObserveOnMainThread {
                dataVideo.isLiked = !dataVideo.isLiked
                updateLikeIcon()
                postViewClicksSubject.onNext(HomePagePostInfoState.UserLikeClick(dataVideo))
            }.autoDispose()

            ivDoubleHeart.throttleClicks().subscribeAndObserveOnMainThread {
                dataVideo.isDoubleHeart = !dataVideo.isDoubleHeart
                updateDoubleHeartIcon()
                postViewClicksSubject.onNext(HomePagePostInfoState.UserDoubleHeartClick(dataVideo))
            }.autoDispose()

            ivHeartBreak.throttleClicks().subscribeAndObserveOnMainThread {
                dataVideo.isHeartBreak = !dataVideo.isHeartBreak
                updateHeartBreakIcon()
                postViewClicksSubject.onNext(HomePagePostInfoState.UserHeartBreakClick(dataVideo))
            }.autoDispose()

            ivDoubleHeartBreak.throttleClicks().subscribeAndObserveOnMainThread {
                dataVideo.isDoubleHeartBreak = !dataVideo.isDoubleHeartBreak
                updateDoubleHeartBreakIcon()
                postViewClicksSubject.onNext(HomePagePostInfoState.UseDoublerHeartBreakClick(dataVideo))
            }.autoDispose()
        }
    }

    private fun updateLikeIcon() {
        binding?.apply {
            if (dataVideo.isLiked) {
                ivLike.setImageDrawable(resources.getDrawable(R.drawable.ic_fill_heart, null))
            } else {
                ivLike.setImageDrawable(resources.getDrawable(R.drawable.ic_like, null))
            }
        }
    }

    private fun updateHeartBreakIcon() {
        binding?.apply {
            if (dataVideo.isHeartBreak) {
                ivHeartBreak.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary, null))
            } else {
                ivHeartBreak.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.white, null))
            }
        }
    }

    private fun updateDoubleHeartBreakIcon() {
        binding?.apply {
            if (dataVideo.isDoubleHeartBreak) {
                ivDoubleHeartBreak.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary, null))
            } else {
                ivDoubleHeartBreak.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.white, null))
            }
        }
    }

    private fun updateDoubleHeartIcon() {
        binding?.apply {
            if (dataVideo.isDoubleHeart) {
                ivDoubleHeart.setImageDrawable(resources.getDrawable(R.drawable.ic_double_like_selected, null))
            } else {
                ivDoubleHeart.setImageDrawable(resources.getDrawable(R.drawable.ic_double_like, null))
            }
        }
    }

    fun bind(dataVideo: HomePostInfo) {
        this.dataVideo = dataVideo
        homeDetailsAdapter = HomeDetailsAdapter(context)
        binding?.apply {
            rvDetails.apply {
                adapter = homeDetailsAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            }
            homeDetailsAdapter.listOfDataItems = getHomePostList()
            tvPropertyName.text = dataVideo.name
            tvPropertyNameInDetails.text = dataVideo.name
            tvPropertyAmount.text = dataVideo.totalAmount
            tvPropertyLocation.text = dataVideo.address
            tvPropertyLocationInDetails.text = dataVideo.address
            tvBedCountInCard.text = dataVideo.bedrooms.toString()
            tvBedCount.text = dataVideo.bedrooms.toString()
            tvBathRoomCountInCard.text = dataVideo.bathroom.toString()
            tvBathRoom.text = dataVideo.bathroom.toString()
            tvPropertySize.text = NumberFormat.getNumberInstance(Locale.US).format(dataVideo.sqft)
            tvPropertySizeInCard.text = NumberFormat.getNumberInstance(Locale.US).format(dataVideo.sqft)
            tvAvgRentalIncome.text = dataVideo.avgRentalIncome.toString()
            tvPropertyTotalAmount.text = dataVideo.totalAmount.toString()
            tvPropertyDescritption.originalText = dataVideo.description.toString()
            tvPropSimplifyAwards.text = dataVideo.rating.toString()
            tvApartmentRatting.text = dataVideo.apartmentRating.toString()
            tvRoi.text = dataVideo.roi.toString()
//            Glide.with(context).load(dataVideo.image).placeholder(R.drawable.welcome_screen_background).error(R.drawable.welcome_screen_background)
//                .into(ivBackground)
            ivBackground.setImageDrawable(getDrawable(context, dataVideo.image ?: "first_card_bg"))
            if (dataVideo.isLiked) {
                ivLike.setImageDrawable(resources.getDrawable(R.drawable.ic_fill_heart, null))
            } else {
                ivLike.setImageDrawable(resources.getDrawable(R.drawable.ic_like, null))
            }
        }
    }
}