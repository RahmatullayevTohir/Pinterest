package com.example.pinterest.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterest.R
import com.example.pinterest.activity.DetailsPhotoActivity
import com.example.pinterest.activity.MainActivity
import com.example.pinterest.adapter.HomeAdapter
import com.example.pinterest.model.PhotoModel
import com.example.pinterest.network.retrofit.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment():Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeAdapter
    var a =1


    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HomeAdapter(requireContext())
        getPhotosViaApi(1)
    }

//    override fun onResume() {
//        super.onResume()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.home_fragment,container,false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                val into = intArrayOf(0,0)
//                val lastVisiblePosition = layoutManager.findLastVisibleItemPositions(into)
//                if(lastVisiblePosition[1] == layoutManager.itemCount-1||lastVisiblePosition[0] == layoutManager.itemCount-1)
//                    getPhotosViaApi(++a)
                if (!recyclerView.canScrollVertically(1)) {
                    getPhotosViaApi(++a)
                }
            }
        })
    }

    private fun getPhotosViaApi(page:Int) {
        RetrofitHttp.apiService.getPhotos(page = page, 20).enqueue(object :Callback<ArrayList<PhotoModel>>{
            override fun onResponse(
                call: Call<ArrayList<PhotoModel>>,
                response: Response<ArrayList<PhotoModel>>
            ) {
                if (response.isSuccessful){
                    adapter.items.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ArrayList<PhotoModel>>, t: Throwable) {

            }
        })

    }



}