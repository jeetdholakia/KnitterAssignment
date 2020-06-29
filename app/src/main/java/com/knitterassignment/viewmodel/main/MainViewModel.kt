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
    private var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        CoroutineScope(Dispatchers.Main).launch {
            fetchPosts()
            fetchComments()
            isLoading.value = true
        }
    }

    private suspend fun fetchPosts() {
        listOf<Post>()
        CoroutineScope(Dispatchers.IO).launch {
            val result = postsRepository.getPosts()

            withContext(Dispatchers.Main) {
                if (result != null) {
                    if (result.isNotEmpty()) {
                        posts.value = result
                        isLoading.value = false
                    }
                }
            }
        }
    }

    private suspend fun fetchComments() {
        listOf<Comment>()
        CoroutineScope(Dispatchers.IO).launch {
            val result = commentsRepository.getComments()
            withContext(Dispatchers.Main) {
                if (result != null) {
                    if (result.isNotEmpty()) {
                        comments.value = result
                        isLoading.value = false
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

    fun postsEndReached() {
        CoroutineScope(Dispatchers.Main).launch {
            isLoading.value = true
            fetchPosts()
        }
    }

    fun commentsEndReached() {
        CoroutineScope(Dispatchers.Main).launch {
            isLoading.value = true
            fetchComments()
        }
    }

    fun getIsLoading(): MutableLiveData<Boolean> {
        return isLoading
    }
}