package com.example.pinterest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pinterest.R
import com.example.pinterest.fragment.SearchFragment
import com.example.pinterest.model.SearchItem
import com.squareup.picasso.Picasso

class SearchAdapter(var context: SearchFragment, var items:ArrayList<SearchItem>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_layout,parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is SearchViewHolder){
            var iv_photo_search = holder.iv_photo_search
            var tv_photo_name = holder.tv_photo_name

            Glide.with(holder.itemView).load(item.image).placeholder(R.drawable.ic_launcher_background).into(iv_photo_search)
            //Picasso.get().load("https://images.unsplash.com/photo-1646764065835-917bed6cc0b8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyOXx8fGVufDB8fHx8&auto=format&fit=crop&w=300&q=60").into(iv_photo_search)
            tv_photo_name.text = item.text
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class SearchViewHolder(view: View):RecyclerView.ViewHolder(view){
        var iv_photo_search : ImageView
        var tv_photo_name : TextView

        init {
            iv_photo_search = view.findViewById(R.id.iv_photos_search)
            tv_photo_name = view.findViewById(R.id.tv_photo_name)
        }
    }
}