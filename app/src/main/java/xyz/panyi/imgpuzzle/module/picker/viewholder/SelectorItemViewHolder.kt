package xyz.panyi.imgpuzzle.module.picker.viewholder

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.panyi.imgpuzzle.R
import xyz.panyi.imgpuzzle.model.SelectFileWrap
import java.io.File

class SelectorItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView
    init {
        imageView = itemView.findViewById(R.id.item_image)
    }

    fun bind(pos:Int , itemData:SelectFileWrap){
        Glide.with(imageView.context).load(Uri.fromFile(File(itemData.data?.path))).into(imageView)
    }
}