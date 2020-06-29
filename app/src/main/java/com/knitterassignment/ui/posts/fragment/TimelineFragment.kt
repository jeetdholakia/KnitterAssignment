package com.knitterassignment.ui.posts.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.knitterassignment.R
import com.knitterassignment.ui.posts.PostListAdapter
import com.knitterassignment.ui.posts.activity.MainActivity
import com.knitterassignment.viewmodel.main.MainViewModel
import com.orhanobut.logger.Logger
import com.payo.transactionanalysis.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_timeline.*
import javax.inject.Inject

class TimelineFragment : DaggerFragment() {

    private val TAG = "TimelineFragment"
    lateinit var postListAdapter: PostListAdapter

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timeline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(MainViewModel::class.java)

        postListAdapter = PostListAdapter(onClickListener = {
           // Open the comments fragment here
            val parent = activity as MainActivity
            parent.postFragmentClicked()
        })

        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        mainViewModel.getPosts().observe(viewLifecycleOwner, Observer {
            Logger.d("Posts received ${it.size}")
            postListAdapter.setPosts(it)
        })

        mainViewModel.getIsLoading().observe(viewLifecycleOwner, Observer {
            toggleProgressBar(it)
        })
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(activity)
        postsRecyclerview.layoutManager = layoutManager
        postsRecyclerview.adapter = postListAdapter

        val mScrollListener: RecyclerView.OnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val visibleItemCount: Int = layoutManager.childCount
                    val totalItemCount: Int = layoutManager.itemCount
                    val pastVisibleItems: Int = layoutManager.findFirstVisibleItemPosition()
                    if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                        //End of list, notify the viewModel to load more posts
                        mainViewModel.postsEndReached()
                    }
                }
            }

        postsRecyclerview.addOnScrollListener(mScrollListener)
    }

    private fun toggleProgressBar(shouldDisplay: Boolean) {
        if(shouldDisplay) {
            loadingProgressBar.visibility = View.VISIBLE
        } else {
            loadingProgressBar.visibility = View.INVISIBLE
        }
    }
}