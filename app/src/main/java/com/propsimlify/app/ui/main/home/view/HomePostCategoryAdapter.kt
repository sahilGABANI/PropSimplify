package com.propsimlify.app.ui.main.home.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.propsimlify.app.model.HomePostCategoryInfo
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class HomePostCategoryAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var adapterItems = listOf<AdapterItem>()

    private val categorySubject: PublishSubject<HomePostCategoryInfo> =
        PublishSubject.create()
    val categoryClick: Observable<HomePostCategoryInfo> = categorySubject.hide()

    var listOfDataItems: List<HomePostCategoryInfo>? = null
        set(listOfReelsInfo) {
            field = listOfReelsInfo
            updateAdapterItems()
        }

    @Synchronized
    private fun updateAdapterItems() {
        val adapterItems = mutableListOf<AdapterItem>()

        listOfDataItems?.forEach {
            adapterItems.add(AdapterItem.HomePostCategoryViewItem(it))
        }

        this.adapterItems = adapterItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.HomePostCategoryViewItemType.ordinal -> {
                HomePostCategoryAdapterViewHolder(HomePostCategoryView(context).apply {
                    categoryClick.subscribe { categorySubject.onNext(it)  }
                })
            }
            else -> throw IllegalArgumentException("Unsupported ViewType")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val adapterItem = adapterItems.getOrNull(position) ?: return
        when (adapterItem) {
            is AdapterItem.HomePostCategoryViewItem -> {
                (holder.itemView as HomePostCategoryView).bind(
                    adapterItem.dataVideo, position
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

    private class HomePostCategoryAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view)

    sealed class AdapterItem(val type: Int) {
        data class HomePostCategoryViewItem(var dataVideo: HomePostCategoryInfo) : AdapterItem(ViewType.HomePostCategoryViewItemType.ordinal)
    }

    private enum class ViewType {
        HomePostCategoryViewItemType
    }

}