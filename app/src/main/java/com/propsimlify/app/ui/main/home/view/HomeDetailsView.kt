package com.propsimlify.app.ui.main.home.view

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.propsimlify.R
import com.propsimlify.app.base.ConstraintLayoutWithLifecycle
import com.propsimlify.app.base.extension.subscribeAndObserveOnMainThread
import com.propsimlify.app.base.extension.throttleClicks
import com.propsimlify.app.model.HomePagePostInfoState
import com.propsimlify.app.model.HomePostInfo
import com.propsimlify.databinding.HomeItemViewBinding
import com.propsimlify.databinding.ViewDetailsBinding
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class HomeDetailsView(context: Context) : ConstraintLayoutWithLifecycle(context) {
    private lateinit var dataVideo: HomePostInfo

    private var binding: ViewDetailsBinding? = null



    init {
        inflateUi()
    }

    private fun inflateUi() {


        val view = View.inflate(context, R.layout.view_details, this)
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        binding = ViewDetailsBinding.bind(view)



        binding?.apply {

        }
    }

    private fun updateLikeIcon() {
        binding?.apply {

        }
    }

    fun bind(dataVideo: HomePostInfo) {
        this.dataVideo = dataVideo

    }
}