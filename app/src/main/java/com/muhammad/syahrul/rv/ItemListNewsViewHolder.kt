package com.muhammad.syahrul.rv

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhammad.syahrul.databinding.ItemListNewsBinding
import com.muhammad.syahrul.remote.POJO.ArticlesItem
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

class ItemListNewsViewHolder(binding: ItemListNewsBinding)
    : RecyclerView.ViewHolder(binding.root) {
    private val image: ImageView = binding.imageView
    private val title: TextView = binding.txtTitle
    private val date: TextView = binding.txtDate
    private val root: MaterialCardView = binding.root

    fun bind(berita: ArticlesItem) {
        title.text = berita.title
        date.text = berita.publishedAt
        Glide.with(root.context).load(berita.urlToImage).into(image)
    }

}