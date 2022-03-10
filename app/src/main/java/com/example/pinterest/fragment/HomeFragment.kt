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
import com.example.pinterest.adapter.HomeAdapter
import com.example.pinterest.model.PhotoModel
import com.example.pinterest.network.retrofit.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment:Fragment() {

    lateinit var recyclerView: RecyclerView
    var photoModel: ArrayList<PhotoModel> = ArrayList()
    lateinit var adapter: HomeAdapter
    var a =1


    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.home_fragment,container,false)
        initViews(view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        getPhotosViaApi(a)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val into = intArrayOf(0,0)
                val lastVisiblePosition = layoutManager.findLastVisibleItemPositions(into)
                if(lastVisiblePosition[1] == layoutManager.itemCount-1||lastVisiblePosition[0] == layoutManager.itemCount-1)
                    getPhotosViaApi(++a)
            }
        })
    }

    private fun refreshAdapter(photos: ArrayList<PhotoModel>) {
        adapter = HomeAdapter(this, photos) { pos, photo, image ->
            sendPhotoToActivity(pos as Int, photo as PhotoModel, image as ImageView)
        }
        recyclerView.adapter = adapter

    }

    private fun getPhotosViaApi(page:Int): List<PhotoModel> {

        var retrofitData = RetrofitHttp.apiService.getPhotos(page, 20)
        retrofitData.enqueue(object : Callback<ArrayList<PhotoModel>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ArrayList<PhotoModel>?>,
                response: Response<ArrayList<PhotoModel>?>
            ) {
                if (!response.isSuccessful) {
                    Log.d("@@@", "code: " + response.code())
                }
//                photoHome.clear()
                if (a==1){
                    refreshAdapter(response.body()!!)
                }else{
                    adapter.items.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()
                }
//                photoHome.addAll(response.body() as ArrayList<_root_ide_package_.com.example.pinterest.model.PhotoModel>)
//                 refreshAdapter(photoHome)
            }

            override fun onFailure(call: Call<ArrayList<PhotoModel>?>, t: Throwable) {
                Log.d("@@@", t.message.toString())
            }
        })
        return photoModel

    }

    private fun sendPhotoToActivity(pos: Int, photo: PhotoModel, image: ImageView) {
        val intent = Intent(context, DetailsPhotoActivity::class.java)
        intent.putExtra("photoTester", photo)
        intent.putExtra("transitionName", ViewCompat.getTransitionName(image))
        val options = ActivityOptions.makeSceneTransitionAnimation(
            context as Activity?,
            image,
            ViewCompat.getTransitionName(image)
        )
        startActivity(intent, options.toBundle())
    }


}