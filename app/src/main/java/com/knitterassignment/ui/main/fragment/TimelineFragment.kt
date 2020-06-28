package com.knitterassignment.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.knitterassignment.R
import com.knitterassignment.viewmodel.main.MainViewModel
import com.orhanobut.logger.Logger
import com.payo.transactionanalysis.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TimelineFragment : DaggerFragment() {

    private val TAG = "TimelineFragment"
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

        setupObservers()
    }

    private fun setupObservers(){
     mainViewModel.getPosts().observe(viewLifecycleOwner, Observer {
         Logger.d("Posts received ${it.size}")
     })
    }
}