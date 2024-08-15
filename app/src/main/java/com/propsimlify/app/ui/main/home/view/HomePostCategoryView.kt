package com.propsimlify.app.ui.main.home.view

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import com.propsimlify.app.base.extension.subscribeAndObserveOnMainThread
import com.propsimlify.app.base.extension.throttleClicks
import com.propsimlify.R
import com.propsimlify.app.base.ConstraintLayoutWithLifecycle
import com.propsimlify.app.model.HomePostCategoryInfo
import com.propsimlify.databinding.ViewHomePostCatgoryBinding
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class HomePostCategoryView(context: Context) : ConstraintLayoutWithLifecycle(context) {
    private lateinit var dataVideo: HomePostCategoryInfo

    private var binding: ViewHomePostCatgoryBinding? = null

    private val categorySubject: PublishSubject<HomePostCategoryInfo> =
        PublishSubject.create()
    val categoryClick: Observable<HomePostCategoryInfo> = categorySubject.hide()
    init {
        inflateUi()
    }

    private fun inflateUi() {
        val view = View.inflate(context, R.layout.view_home_post_catgory, this)
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        binding = ViewHomePostCatgoryBinding.bind(view)
        binding?.apply {
            rlVenueCategory.throttleClicks().subscribeAndObserveOnMainThread {
                categorySubject.onNext(dataVideo)
            }.autoDispose()
        }
    }

    fun bind(dataVideo: HomePostCategoryInfo, position: Int) {
        this.dataVideo = dataVideo
        binding?.apply {
            tvVenueCategoryName.text = dataVideo.name
            if (dataVideo.isSelected == true) {
                rlVenueCategory.setBackgroundDrawable(resources.getDrawable(R.drawable.category_selected_background, null))
                tvVenueCategoryName.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.white,null)))
                ivVenueCategoryImage.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.white,null))
            } else {
                tvVenueCategoryName.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.black,null)))
                ivVenueCategoryImage.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.grey_bottom_icon,null))
                rlVenueCategory.setBackgroundDrawable(resources.getDrawable(R.drawable.category_unselected_background, null))
            }
        }
    }
}