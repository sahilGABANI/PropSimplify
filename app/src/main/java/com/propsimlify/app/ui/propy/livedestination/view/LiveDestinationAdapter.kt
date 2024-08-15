package com.propsimlify.app.ui.propy.livedestination.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.propsimlify.app.model.HomePostInfo
import com.propsimlify.app.model.LiveDestinationInfo

class LiveDestinationAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var adapterItems = listOf<AdapterItem>()

    var listOfDataItems: List<LiveDestinationInfo>? = null
        set(listOfReelsInfo) {
            field = listOfReelsInfo
            updateAdapterItems()
        }

    @Synchronized
    private fun updateAdapterItems() {
        val adapterItems = mutableListOf<AdapterItem>()

        listOfDataItems?.forEach {
            adapterItems.add(AdapterItem.LiveDestinationViewItem(it))
        }

        this.adapterItems = adapterItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.LiveDestinationViewItemType.ordinal -> {
                HomePostAdapterViewHolder(LiveDestinationView(context).apply {
                })
            }
            else -> throw IllegalArgumentException("Unsupported ViewType")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val adapterItem = adapterItems.getOrNull(position) ?: return
        when (adapterItem) {
            is AdapterItem.LiveDestinationViewItem -> {
                (holder.itemView as LiveDestinationView).bind(
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

    private class HomePostAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view)

    sealed class AdapterItem(val type: Int) {
        data class LiveDestinationViewItem(var liveDestinationInfo: LiveDestinationInfo) :
            AdapterItem(ViewType.LiveDestinationViewItemType.ordinal)
    }

    private enum class ViewType {
        LiveDestinationViewItemType
    }
}