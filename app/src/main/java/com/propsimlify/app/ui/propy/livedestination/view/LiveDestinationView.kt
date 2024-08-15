package com.propsimlify.app.ui.propy.livedestination.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.propsimlify.R
import com.propsimlify.app.base.ConstraintLayoutWithLifecycle
import com.propsimlify.app.base.extension.getDrawable
import com.propsimlify.app.model.HomePostInfo
import com.propsimlify.app.model.LiveDestinationInfo
import com.propsimlify.databinding.HomeItemViewBinding
import com.propsimlify.databinding.LiveDestinationViewBinding

class LiveDestinationView(context: Context) : ConstraintLayoutWithLifecycle(context)  {
    private lateinit var dataVideo: LiveDestinationInfo

    private var binding: LiveDestinationViewBinding? = null


    init {
        inflateUi()
    }

    private fun inflateUi() {


        val view = View.inflate(context, R.layout.live_destination_view, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        binding = LiveDestinationViewBinding.bind(view)



        binding?.apply {

        }
    }

    fun bind(dataVideo: LiveDestinationInfo) {
        binding?.apply {
            ivPlace.setImageDrawable(getDrawable(context,dataVideo.image ?: "ic_abu_dhabi"))
            if (dataVideo.isSelected == true) {
                liveDestinationBg.setBackgroundDrawable(context.resources.getDrawable(R.drawable.bg_live_destination_selected, null))
                ivPlace.setColorFilter(context.resources.getColor(R.color.white, null), PorterDuff.Mode.SRC_IN);
                tvName.text = dataVideo.name
                tvName.setTextColor(ColorStateList.valueOf(context.resources.getColor(R.color.white,null)))
            }else {
                liveDestinationBg.setBackgroundDrawable(context.resources.getDrawable(R.drawable.bg_live_destination_unselected, null))
                ivPlace.setColorFilter(context.resources.getColor(R.color.colorPrimary, null), PorterDuff.Mode.SRC_IN);
                tvName.text = dataVideo.name
                tvName.setTextColor(ColorStateList.valueOf(context.resources.getColor(R.color.colorPrimary,null)))
            }
        }
    }
}