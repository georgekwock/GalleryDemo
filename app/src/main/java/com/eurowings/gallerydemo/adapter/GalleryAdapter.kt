package com.eurowings.gallerydemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eurowings.gallerydemo.Image
import com.eurowings.gallerydemo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview.view.*

class GalleryAdapter(ctx: Context) : PagedListAdapter<Image, GalleryAdapter.CustomViewHolder>(DIFF_CALLBACK) {
    private val context: Context

    init {
        context = ctx
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.cardview, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val image = getItem(position)

        Picasso.with(context).load(image!!.link.toString())
            .into(holder?.itemView?.gallery_imageview)
        holder.IMAGE_URL = image?.link
        holder.title.text = image?.title
        holder.id = image?.id
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(
                oldConcert: Image,
                newConcert: Image
            ): Boolean =
                oldConcert.id == newConcert.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldConcert: Image,
                newConcert: Image
            ): Boolean =
                oldConcert == newConcert
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.gallery_imageview)
        var title: TextView = itemView.findViewById(R.id.cardText)
        var IMAGE_URL: String? = null
        var id: String? = null
    }
}