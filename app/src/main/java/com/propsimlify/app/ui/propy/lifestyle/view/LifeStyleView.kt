package com.propsimlify.app.ui.propy.lifestyle.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.View
import com.propsimlify.R
import com.propsimlify.app.base.ConstraintLayoutWithLifecycle
import com.propsimlify.app.base.extension.getDrawable
import com.propsimlify.app.base.extension.subscribeAndObserveOnMainThread
import com.propsimlify.app.base.extension.throttleClicks
import com.propsimlify.app.model.HomePostCategoryInfo
import com.propsimlify.app.model.LifeStyleInfo
import com.propsimlify.databinding.ViewLifeStyleBinding
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class LifeStyleView(context: Context) : ConstraintLayoutWithLifecycle(context) {

    private lateinit var binding: ViewLifeStyleBinding
    private var lifeStyleInfo : LifeStyleInfo?= null
    private val lifeStyleClickSubject: PublishSubject<LifeStyleInfo> =
        PublishSubject.create()
    val lifeStyleClick: Observable<LifeStyleInfo> = lifeStyleClickSubject.hide()

    init {
        inflateUi()
    }

    private fun inflateUi() {
        val view = View.inflate(context, R.layout.view_life_style, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        binding = ViewLifeStyleBinding.bind(view)
        binding.liveDestinationBg.throttleClicks().subscribeAndObserveOnMainThread {
            lifeStyleInfo?.let { it1 -> lifeStyleClickSubject.onNext(it1) }
        }.autoDispose()
    }

    fun bind(liveDestinationInfo: LifeStyleInfo) {
        lifeStyleInfo = liveDestinationInfo
        binding.apply {
            ivPlace.setImageDrawable(getDrawable(context,liveDestinationInfo.image ?: "ic_investment"))
            tvName.text = liveDestinationInfo.name
            if (liveDestinationInfo.isSelected) {
                liveDestinationBg.setBackgroundDrawable(context.resources.getDrawable(R.drawable.bg_live_destination_selected, null))
                ivPlace.setColorFilter(context.resources.getColor(R.color.white, null), PorterDuff.Mode.SRC_IN);
                tvName.setTextColor(ColorStateList.valueOf(context.resources.getColor(R.color.white,null)))
            }else {
                liveDestinationBg.setBackgroundDrawable(context.resources.getDrawable(R.drawable.bg_lifestyle_view, null))
                ivPlace.setColorFilter(context.resources.getColor(R.color.colorPrimary, null), PorterDuff.Mode.SRC_IN);
                tvName.setTextColor(ColorStateList.valueOf(context.resources.getColor(R.color.colorPrimary,null)))
            }
        }
    }


}