package com.knitterassignment.viewmodel.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.knitterassignment.repository.PostsRepository
import com.knitterassignment.repository.PostsRepositoryImpl
import com.knitterassignment.repository.db.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private var posts: MutableLiveData<List<Post>> = MutableLiveData()
    private val postsRepository: PostsRepository = PostsRepositoryImpl(getApplication())

    init {
        CoroutineScope(Dispatchers.Main).launch {
            getPost()
        }
    }

    private suspend fun getPost() {
//        var responses = listOf<Result>()
//        CoroutineScope(Dispatchers.IO).launch {
//            responses = PostsService.getPostsService().getPosts(1).result
//            withContext(Dispatchers.Main) {
//                posts.value = responses
//            }
//        }


        var responses = listOf<Post>()
        CoroutineScope(Dispatchers.IO).launch {
            postsRepository.getPosts().let {
                responses = it!!
            }

            withContext(Dispatchers.Main) {
                if (responses.isNotEmpty())
                posts.value = responses
            }
        }
    }

    fun getPosts(): LiveData<List<Post>> {
        return posts
    }

}