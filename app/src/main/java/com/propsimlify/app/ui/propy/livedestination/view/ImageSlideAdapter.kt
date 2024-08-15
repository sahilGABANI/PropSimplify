package com.propsimlify.app.ui.propy.livedestination.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.propsimlify.R
import com.propsimlify.app.base.extension.getDrawable
import com.propsimlify.app.model.LiveDestinationInfo
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ImageSlideAdapter(private val context: Context, private var imageList: ArrayList<LiveDestinationInfo>) :
    PagerAdapter() {

    private val postViewClicksSubject: PublishSubject<Int> =
        PublishSubject.create()
    val postViewClicks: Observable<Int> = postViewClicksSubject.hide()

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.live_destination_view,
                container,
                false
            )
        val ivImages = view.findViewById<AppCompatImageView>(R.id.ivPlace)
        val tvName = view.findViewById<AppCompatTextView>(R.id.tvName)
        val liveDestinationBg = view.findViewById<LinearLayout>(R.id.liveDestinationBg)
        ivImages.setImageDrawable(getDrawable(context,imageList.get(position).image ?: "ic_abu_dhabi"))
        if (imageList.get(position).isSelected == true) {
            liveDestinationBg.setBackgroundDrawable(context.resources.getDrawable(R.drawable.bg_live_destination_selected, null))
            ivImages.setColorFilter(context.resources.getColor(R.color.white, null), PorterDuff.Mode.SRC_IN);
            tvName.text = imageList.get(position).name
            tvName.setTextColor(ColorStateList.valueOf(context.resources.getColor(R.color.white,null)))
        }else {
            liveDestinationBg.setBackgroundDrawable(context.resources.getDrawable(R.drawable.bg_live_destination_unselected, null))
            ivImages.setColorFilter(context.resources.getColor(R.color.colorPrimary, null), PorterDuff.Mode.SRC_IN);
            tvName.text = imageList.get(position).name
            tvName.setTextColor(ColorStateList.valueOf(context.resources.getColor(R.color.colorPrimary,null)))
        }



        view.setOnClickListener {
            postViewClicksSubject.onNext(position)
        }

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view


//        val inflate: View = LayoutInflater.from(container.getContext()).inflate(R.layout.live_destination_view, container, false)
//        val imageView1 = inflate.findViewById<View>(R.id.ivPlace) as ImageView
////        val textView = inflate.findViewById<View>(R.id.title_card_item) as TextView
//
////        Glide.with(mContext).load(mData.get(position).getImg_url()).into(imageView1)
////        textView.setText(mData.get(position).getTilte_text() + "")
//        container.addView(inflate)
//        return inflate
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}