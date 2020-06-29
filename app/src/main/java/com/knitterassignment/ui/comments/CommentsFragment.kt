package com.knitterassignment.ui.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.knitterassignment.R
import com.knitterassignment.viewmodel.main.MainViewModel
import com.orhanobut.logger.Logger
import com.payo.transactionanalysis.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_comments.*
import kotlinx.android.synthetic.main.fragment_timeline.loadingProgressBar
import javax.inject.Inject

class CommentsFragment : DaggerFragment() {

    private val TAG = "TimelineFragment"
    private lateinit var commentsListAdapter: CommentsListAdapter

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
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(MainViewModel::class.java)

        commentsListAdapter = CommentsListAdapter(onClickListener = {

        })

        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        mainViewModel.getComments().observe(viewLifecycleOwner, Observer {
            Logger.d("Comments received ${it.size}")
            commentsListAdapter.setComments(it)
        })

        mainViewModel.getIsLoading().observe(viewLifecycleOwner, Observer {
            toggleProgressBar(it)
        })
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(activity)
        commentsRecyclerview.layoutManager = layoutManager
        commentsRecyclerview.adapter = commentsListAdapter

        val mScrollListener: RecyclerView.OnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val visibleItemCount: Int = layoutManager.childCount
                    val totalItemCount: Int = layoutManager.itemCount
                    val pastVisibleItems: Int = layoutManager.findFirstVisibleItemPosition()
                    if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                        //End of list, notify the viewModel to load more posts
                        mainViewModel.commentsEndReached()
                    }
                }
            }

        commentsRecyclerview.addOnScrollListener(mScrollListener)
    }

    private fun toggleProgressBar(shouldDisplay: Boolean) {
        if(shouldDisplay) {
            loadingProgressBar.visibility = View.VISIBLE
        } else {
            loadingProgressBar.visibility = View.INVISIBLE
        }
    }
}