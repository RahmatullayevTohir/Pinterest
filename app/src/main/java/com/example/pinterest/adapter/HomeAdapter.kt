package com.example.pinterest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterest.R
import com.example.pinterest.activity.MainActivity
import com.example.pinterest.fragment.DetailsFragment
import com.example.pinterest.model.PhotoModel
import com.squareup.picasso.Picasso

class HomeAdapter(var context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: ArrayList<PhotoModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_layout,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeViewHolder)
        holder.bind(position)
    }



    override fun getItemCount(): Int {
        return items.size
    }

    inner class HomeViewHolder(view: View):RecyclerView.ViewHolder(view){
        val iv_likes:ImageView = view.findViewById(R.id.iv_likes)
        val iv_photo :ImageView = view.findViewById(R.id.iv_photo)
        val tv_number:TextView = view.findViewById(R.id.tv_number)

        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int){
            iv_likes.visibility = View.GONE
            var photo = items[position]
            ViewCompat.setTransitionName(iv_photo,""+position)
            Picasso.get().load(photo.urls.small).into(iv_photo)
            tv_number.text = photo.user.name.toString()
            itemView.setOnClickListener {
                (context as MainActivity).replaceFragment(DetailsFragment(photo))
            }
        }
    }
}