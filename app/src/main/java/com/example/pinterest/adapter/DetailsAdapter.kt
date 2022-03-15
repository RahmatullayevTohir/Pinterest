package com.example.pinterest.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterest.R
import com.example.pinterest.activity.MainActivity
import com.example.pinterest.model.PhotoModel
import com.example.pinterest.model.ReletedPhotos
import com.example.pinterest.network.retrofit.RetrofitHttp
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Callback
import retrofit2.Response

class DetailsAdapter( var context: MainActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: ArrayList<PhotoModel> = ArrayList()
    private lateinit var recyclerView: RecyclerView


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_layout, parent, false)
        return DetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DetailsViewHolder)
            holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    inner class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var adapter: DetailsAdapter
        val photos: ImageView = view.findViewById(R.id.iv_photo)
        val shiv_profile: ShapeableImageView = view.findViewById(R.id.shiv_profile)
        val profileName: TextView = view.findViewById(R.id.tv_profile_name)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        init {
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        fun bind(position: Int) {
            var photo = items?.get(adapterPosition)
            Picasso
                .get()
                .load(photo!!.urls.small)
                .placeholder(ColorDrawable(Color.parseColor(photo!!.color)))
                .into(photos)
            profileName.text = photo!!.user.name

            recyclerView.adapter = adapter
            apiRelatedPhotos(photo!!.id)
        }



        fun apiRelatedPhotos(id: String) {
            RetrofitHttp.apiService.getRelatedPhotos(id).enqueue(object : Callback<ReletedPhotos> {
                override fun onResponse(
                    call: retrofit2.Call<ReletedPhotos>,
                    response: Response<ReletedPhotos>
                ) {
                    Log.d("@@@DetailsD", response.body().toString())
                    if (response.isSuccessful) {
                        refreshAdapter(response.body()!!.result!!)
                    }
                }

                override fun onFailure(call: retrofit2.Call<ReletedPhotos>, t: Throwable) {
                    Log.d("@@@DetailsE", t.message.toString())
                }
            })
        }

    }
    fun refreshAdapter(items: ArrayList<PhotoModel>) {
        val adapter = DetailsAdapter(context)
        adapter.items!!.addAll(items)
        adapter.items = items
        recyclerView.adapter = adapter
    }
}