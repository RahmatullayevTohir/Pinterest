package com.example.pinterest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterest.R
import com.example.pinterest.adapter.SearchAdapter
import com.example.pinterest.model.SearchItem

class SearchFragment:Fragment() {

    lateinit var recyclerView_ideas: RecyclerView
    lateinit var recyclerView_popular: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.search_fragment,container,false)
        initViews(view)
        return view
    }

    fun initViews(view: View){
        recyclerView_ideas = view.findViewById(R.id.recyclerView_ideas)
        recyclerView_popular = view.findViewById(R.id.recyclerView_popular)

        recyclerView_ideas.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerView_popular.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        refreshAdapterIdeas(getAllSearchIdeas())
        refreshAdapterPopular(getAllSearchPopular())
    }

    fun refreshAdapterIdeas(items:ArrayList<SearchItem>){
        val adapter = SearchAdapter(this,items)
        recyclerView_ideas!!.adapter = adapter
    }

    fun refreshAdapterPopular(items: ArrayList<SearchItem>){
        val adapter = SearchAdapter(this,items)
        recyclerView_popular!!.adapter = adapter
    }

    fun getAllSearchIdeas():ArrayList<SearchItem>{
        val items: ArrayList<SearchItem> = ArrayList<SearchItem>()
        items.add(SearchItem("https://images.unsplash.com/photo-1646764065835-917bed6cc0b8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyOXx8fGVufDB8fHx8&auto=format&fit=crop&w=300&q=60","Ranaldo"))
        items.add(SearchItem("https://images.unsplash.com/photo-1646764065835-917bed6cc0b8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyOXx8fGVufDB8fHx8&auto=format&fit=crop&w=300&q=60","Ranaldo"))

        return items
    }

    fun getAllSearchPopular():ArrayList<SearchItem>{
        val items: ArrayList<SearchItem> = ArrayList<SearchItem>()
        items.add(SearchItem("https://images.unsplash.com/photo-1556962021-9d0303621643?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8cmVhbCUyMG1hZHJpZHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=400&q=60","Real Madrid"))
        items.add(SearchItem("https://images.unsplash.com/photo-1556962021-9d0303621643?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8cmVhbCUyMG1hZHJpZHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=400&q=60","Real Madrid"))
        items.add(SearchItem("https://images.unsplash.com/photo-1556962021-9d0303621643?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8cmVhbCUyMG1hZHJpZHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=400&q=60","Real Madrid"))
        items.add(SearchItem("https://images.unsplash.com/photo-1556962021-9d0303621643?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8cmVhbCUyMG1hZHJpZHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=400&q=60","Real Madrid"))
        items.add(SearchItem("https://images.unsplash.com/photo-1556962021-9d0303621643?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8cmVhbCUyMG1hZHJpZHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=400&q=60","Real Madrid"))
        items.add(SearchItem("https://images.unsplash.com/photo-1556962021-9d0303621643?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8cmVhbCUyMG1hZHJpZHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=400&q=60","Real Madrid"))

        return items
    }

}