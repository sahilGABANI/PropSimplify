package com.propsimlify.app.ui.main.home.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.propsimlify.app.model.HomePagePostInfoState
import com.propsimlify.app.model.HomePostInfo
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class HomeDetailsAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val postViewClicksSubject: PublishSubject<HomePagePostInfoState> =
        PublishSubject.create()
    val postViewClicks: Observable<HomePagePostInfoState> = postViewClicksSubject.hide()

    private var adapterItems = listOf<AdapterItem>()

    var listOfDataItems: List<HomePostInfo>? = null
        set(listOfReelsInfo) {
            field = listOfReelsInfo
            updateAdapterItems()
        }

    @SuppressLint("NotifyDataSetChanged")
    @Synchronized
    private fun updateAdapterItems() {
        val adapterItems = mutableListOf<AdapterItem>()

        listOfDataItems?.forEach {
            adapterItems.add(AdapterItem.HomePostViewItem(it))
        }

        this.adapterItems = adapterItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.HomePostViewItemType.ordinal -> {
                HomePostAdapterViewHolder(HomeDetailsView(context).apply {
//                    postViewClicks.subscribe { postViewClicksSubject.onNext(it) }
                })
            }
            else -> throw IllegalArgumentException("Unsupported ViewType")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val adapterItem = adapterItems.getOrNull(position) ?: return
        when (adapterItem) {
            is AdapterItem.HomePostViewItem -> {
                (holder.itemView as HomeDetailsView).bind(
                    adapterItem.dataVideo,
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return adapterItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return adapterItems[position].type
    }

    private class HomePostAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view)

    sealed class AdapterItem(val type: Int) {
        data class HomePostViewItem(var dataVideo: HomePostInfo) :
            AdapterItem(ViewType.HomePostViewItemType.ordinal)
    }

    private enum class ViewType {
        HomePostViewItemType
    }
}