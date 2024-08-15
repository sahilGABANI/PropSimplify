package com.propsimlify.app.ui.propy.lifestyle.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.propsimlify.app.model.LifeStyleInfo
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class LifeStyleAdapter (
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var adapterItems = listOf<AdapterItem>()

    private val lifeStyleClickSubject: PublishSubject<LifeStyleInfo> =
        PublishSubject.create()
    val lifeStyleClick: Observable<LifeStyleInfo> = lifeStyleClickSubject.hide()

    var listOfDataItems: List<LifeStyleInfo>? = null
        set(listOfReelsInfo) {
            field = listOfReelsInfo
            updateAdapterItems()
        }

    @Synchronized
    private fun updateAdapterItems() {
        val adapterItems = mutableListOf<AdapterItem>()

        listOfDataItems?.forEach {
            adapterItems.add(AdapterItem.LifeStyleViewItem(it))
        }

        this.adapterItems = adapterItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.LifeStyleViewItemType.ordinal -> {
                LifeStyleAdapterViewHolder(LifeStyleView(context).apply {
                    lifeStyleClick.subscribe { lifeStyleClickSubject.onNext(it) }
                })
            }
            else -> throw IllegalArgumentException("Unsupported ViewType")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val adapterItem = adapterItems.getOrNull(position) ?: return
        when (adapterItem) {
            is AdapterItem.LifeStyleViewItem -> {
                (holder.itemView as LifeStyleView).bind(
                    adapterItem.liveDestinationInfo,
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

    private class LifeStyleAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view)

    sealed class AdapterItem(val type: Int) {
        data class LifeStyleViewItem(var liveDestinationInfo: LifeStyleInfo) :
            AdapterItem(ViewType.LifeStyleViewItemType.ordinal)
    }

    private enum class ViewType {
        LifeStyleViewItemType
    }
}