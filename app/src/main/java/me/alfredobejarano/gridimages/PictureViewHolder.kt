package me.alfredobejarano.gridimages

import android.support.v7.widget.RecyclerView
import android.view.View
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Simple [ViewHolder][RecyclerView.ViewHolder] class
 * that displays a picture in a RecyclerView.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 02/10/2018 - 03:59 PM
 */
class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * Reference to the SimpleDraweeView in the ViewHolder's layout,
     * it can be null so the null-safe (?) operator is used.
     */
    private val picture: SimpleDraweeView? = itemView.findViewById(R.id.picture_view)

    /**
     * Receives an image URL as a String object and
     * sends it to the SimpleDraweeView to be fetched
     * and displayed, image caching is handled by Fresco.
     * @param url The image URL to be displayed.
     */
    fun renderPicture(url: String) = picture?.setImageURI(url)
}