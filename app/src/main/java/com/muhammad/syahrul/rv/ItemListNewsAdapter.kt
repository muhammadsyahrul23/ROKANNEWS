package com.muhammad.syahrul.rv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammad.syahrul.DetailActivity
import com.muhammad.syahrul.databinding.ItemListNewsBinding
import com.muhammad.syahrul.remote.POJO.ArticlesItem

class ItemListNewsAdapter : RecyclerView.Adapter<ItemListNewsViewHolder>() {
    private val listnews = ArrayList<ArticlesItem>()

    fun addData(data : List<ArticlesItem>) {
        listnews.clear()
        listnews.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListNewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =  ItemListNewsBinding.inflate(layoutInflater,parent, false)
        return ItemListNewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listnews.size
    }

    override fun onBindViewHolder(holder: ItemListNewsViewHolder, position: Int) {
        val data = listnews[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context, data.title, Toast.LENGTH_SHORT).show()
            val intent = Intent(it.context, DetailActivity::class.java).apply {
                putExtra("berita_item", data)
            }
            it.context.startActivity(intent)
        }
    }
}