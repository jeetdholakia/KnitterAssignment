package com.knitterassignment.viewmodel.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.knitterassignment.repository.CommentsRepository
import com.knitterassignment.repository.CommentsRepositoryImpl
import com.knitterassignment.repository.PostsRepository
import com.knitterassignment.repository.PostsRepositoryImpl
import com.knitterassignment.repository.db.comments.Comment
import com.knitterassignment.repository.db.posts.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private var posts: MutableLiveData<List<Post>> = MutableLiveData()
    private var comments: MutableLiveData<List<Comment>> = MutableLiveData()
    private val postsRepository: PostsRepository = PostsRepositoryImpl(getApplication())
    private val commentsRepository: CommentsRepository = CommentsRepositoryImpl(getApplication())

    init {
        CoroutineScope(Dispatchers.Main).launch {
            fetchPosts()
            fetchComments()
        }
    }

    private suspend fun fetchPosts() {
        var responses = listOf<Post>()
        CoroutineScope(Dispatchers.IO).launch {
            val result = postsRepository.getPosts()

            withContext(Dispatchers.Main) {
                if (result != null) {
                    if (result.isNotEmpty()) {
                        posts.value = result
                    }
                }
            }
        }
    }

    private suspend fun fetchComments() {
        var responses = listOf<Comment>()
        CoroutineScope(Dispatchers.IO).launch {
            val result = commentsRepository.getComments()
            withContext(Dispatchers.Main) {
                if (result != null) {
                    if (result.isNotEmpty()) {
                        comments.value = result
                    }
                }
            }
        }
    }

    fun getPosts(): LiveData<List<Post>> {
        return posts
    }

    fun getComments(): LiveData<List<Comment>> {
        return comments
    }
}