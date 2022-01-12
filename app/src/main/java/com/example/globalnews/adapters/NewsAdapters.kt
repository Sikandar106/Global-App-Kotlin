package com.example.globalnews.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.globalnews.R
import com.example.globalnews.activities.WebViewDetailActivity
import com.example.globalnews.models.Articles

class NewsAdapters(private val context: Context, private val list: List<Articles>) : RecyclerView.Adapter<NewsAdapters.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val newsView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return NewsViewHolder(newsView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val articles = list[position]
        holder.newsTitle.text = articles.title
        holder.newsDesc.text = articles.description
        Glide.with(context).load(articles.urlToImage).into(holder.newImage)
        holder.itemView.setOnClickListener {
            val onWebView = Intent(context, WebViewDetailActivity::class.java)
            onWebView.putExtra("url", articles.url)
            context.startActivity(onWebView)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var newImage = itemView.findViewById<ImageView>(R.id.imageViewNews)
        var newsTitle = itemView.findViewById<TextView>(R.id.textViewHeadline)
        var newsDesc = itemView.findViewById<TextView>(R.id.textViewBody)
    }
}