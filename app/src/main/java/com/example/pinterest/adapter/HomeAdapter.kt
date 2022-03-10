package com.example.pinterest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pinterest.R
import com.example.pinterest.fragment.HomeFragment
import com.example.pinterest.model.PhotoModel

class HomeAdapter(
    var context: HomeFragment,
    var items: ArrayList<PhotoModel>,
    param: (Any, Any, Any) -> Unit
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_layout,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is HomeViewHolder){
            var iv_photo = holder.iv_photo
            var tv_number = holder.tv_number
            Log.d("@@@",item.urls.small)
//            Picasso.get().load(item.image).into(iv_photo)
            Glide.with(holder.itemView).load(item.urls.small).into(iv_photo)
            tv_number.text = item.likes.toString()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class HomeViewHolder(view: View):RecyclerView.ViewHolder(view){
        var iv_photo :ImageView
        var tv_number:TextView

        init {
            iv_photo = view.findViewById(R.id.iv_photo)
            tv_number = view.findViewById(R.id.tv_number)
//            Glide.with(view).load(items[1].image).placeholder(R.drawable.ic_launcher_background).into(iv_photo)
        }
    }
}