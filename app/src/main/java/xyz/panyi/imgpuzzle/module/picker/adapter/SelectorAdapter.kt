package xyz.panyi.imgpuzzle.module.picker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.panyi.imgpuzzle.R
import xyz.panyi.imgpuzzle.model.SelectFileWrap
import xyz.panyi.imgpuzzle.module.picker.viewholder.SelectorItemViewHolder

class SelectorAdapter(
    val ctx: Context,
    val dataList:ArrayList<SelectFileWrap>
) : RecyclerView.Adapter<SelectorItemViewHolder>() {
    private var layoutInflater:LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(ctx)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectorItemViewHolder {
        val itemView = layoutInflater.inflate(R.layout.view_selector_item , parent , false)
        return SelectorItemViewHolder(itemView , ctx)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: SelectorItemViewHolder, position: Int) = holder.bind(position, dataList[position])
}