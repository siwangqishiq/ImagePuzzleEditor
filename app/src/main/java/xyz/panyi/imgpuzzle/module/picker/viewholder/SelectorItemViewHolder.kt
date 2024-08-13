package xyz.panyi.imgpuzzle.module.picker.viewholder

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.panyi.imgpuzzle.R
import xyz.panyi.imgpuzzle.model.SelectFileWrap
import xyz.panyi.imgpuzzle.module.picker.ImageSelectorActivity
import java.io.File

class SelectorItemViewHolder(itemView: View,val ctx: Context) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView
    var selectCountText: TextView

    init {
        imageView = itemView.findViewById(R.id.item_image)
        selectCountText =  itemView.findViewById(R.id.select_count_text)
    }

    fun bind(pos:Int , itemData:SelectFileWrap){
        Glide.with(imageView.context).load(Uri.fromFile(File(itemData.data?.path))).into(imageView)
        if(itemData.selectedCount > 0){
            selectCountText.visibility = View.VISIBLE
            selectCountText.text = itemData.selectedCount.toString()
        }else{
            selectCountText.visibility = View.INVISIBLE
        }

        itemView.setOnClickListener {
            if(ctx is ImageSelectorActivity){
                ctx.onItemSelected(itemData , pos)
            }//end if
        }
    }
}