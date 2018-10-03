package me.alfredobejarano.gridimages.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.alfredobejarano.gridimages.PictureViewHolder
import me.alfredobejarano.gridimages.R

/**
 * Simple [Adapter][RecyclerView.Adapter] class that displays a [List]
 * of Strings, (those String objects being pictures URLS) in a
 * [PictureViewHolder] object, this ViewHolders are displayed in a
 * RecyclerView.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 02/10/2018 - 03:47 PM
 */
class PicturesAdapter(private val urls: List<String>) : RecyclerView.Adapter<PictureViewHolder>() {

    /**
     * Creates a new ViewHolder that is going to be displayed in the RecyclerView.
     * @param parent The ViewGroup that this adapter belongs to.
     * @param viewType Layout Id for the created ViewHolder to render.
     */
    override fun onCreateViewHolder(parent: ViewGroup, @LayoutRes viewType: Int) =
            PictureViewHolder(LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false))

    /**
     * Defines how many elements are in the adapter available for display.
     * @return Size of the elements that the adapter has available for display.
     */
    override fun getItemCount() = urls.size

    /**
     * Uses the elements from the [urls] property at the given position
     * and sends it to the ViewHolder to be displayed in a SimpleDraweeView.
     * @param viewHolder The current ViewHolder the RecyclerView is inflating.
     * @param position The current position of the element being displayed.
     */
    override fun onBindViewHolder(viewHolder: PictureViewHolder, position: Int) {
        viewHolder.renderPicture(urls[position])
    }

    /**
     * Defines the layout id for this adapter elements.
     * @return Layout Id resource for the item_picture.xml file.
     */
    @LayoutRes
    override fun getItemViewType(position: Int) = R.layout.item_picture
}