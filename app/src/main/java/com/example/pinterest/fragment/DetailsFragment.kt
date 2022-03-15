package com.example.pinterest.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterest.R
import com.example.pinterest.activity.MainActivity
import com.example.pinterest.adapter.DetailsAdapter
import com.example.pinterest.model.PhotoModel
import com.example.pinterest.model.ReletedPhotos
import com.example.pinterest.network.retrofit.RetrofitHttp
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment(var photo: PhotoModel) : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: DetailsAdapter
    lateinit var iv_back: ImageView
    lateinit var imageView: ImageView
    lateinit var tv_profile_name: TextView
    lateinit var shiv_profile: ShapeableImageView
    lateinit var tv_followers: TextView
    var photosRelated: ArrayList<PhotoModel> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        requireActivity().window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("@@@PhotoInfo", photo.toString())
        (requireContext() as MainActivity).hideBottomNavigation()
    }

    override fun onResume() {
        super.onResume()
        getPhotosRelatedViaApi()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.details_fragment, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        tv_followers = view.findViewById(R.id.tv_followers)
        imageView = view.findViewById(R.id.iv_detailed_photo)
        tv_profile_name = view.findViewById(R.id.tv_profile_name)
        shiv_profile = view.findViewById(R.id.shiv_profile)
        Picasso
            .get()
            .load(photo.urls.small)
            .into(imageView)
        Picasso
            .get()
            .load(photo.urls.small)
            .into(shiv_profile)
        tv_profile_name.text = photo.user.name
        tv_followers.text = photo.user.totalLikes.toString()
        recyclerView = view.findViewById(R.id.recyclerView_details)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        refreshAdpter(requireContext())

        iv_back = view.findViewById(R.id.iv_back)
        iv_back.setOnClickListener {

        }
    }

    private fun refreshAdpter(items: Context) {
        adapter = DetailsAdapter(requireContext() as MainActivity)
        recyclerView.adapter = adapter
    }

    private fun getPhotosRelatedViaApi() {
        RetrofitHttp.apiService.getRelatedPhotos(photo.id)
            .enqueue(object : Callback<ReletedPhotos?> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<ReletedPhotos?>,
                    response: Response<ReletedPhotos?>
                ) {
                    photosRelated = response.body()!!.result!!
                    adapter.items!!.addAll(photosRelated)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ReletedPhotos?>, t: Throwable) {

                }
            })

    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }


}