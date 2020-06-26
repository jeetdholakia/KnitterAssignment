package com.knitterassignment.viewmodel.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.knitterassignment.network.PostsService
import com.knitterassignment.network.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private var posts: MutableLiveData<List<Result>> = MutableLiveData()

    init {
        getPost()
    }

    private fun getPost() {
        var responses = listOf<Result>()
        CoroutineScope(Dispatchers.IO).launch {
            responses = PostsService.getPostsService().getPosts().result
            withContext(Dispatchers.Main) {
                posts.value = responses
            }
        }
    }

    fun getPosts(): LiveData<List<Result>> {
      return posts
    }

}