package com.knitterassignment.repository.network.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.knitterassignment.repository.db.posts.Post
import com.knitterassignment.repository.network.posts.PostsService
import com.knitterassignment.repository.network.model.posts.PostsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.subjects.ReplaySubject
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer

class PostsPageKeyedDataSource : PageKeyedDataSource<String, Post>() {
    private val TAG = "PostsPageKeyedDataSource"
    private val postsService =
        PostsService.getPostsService()
    private var postsObservable: ReplaySubject<Post> = ReplaySubject.create()


    override fun loadInitial(params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Post>) {
        Log.i(TAG, "Loading Initial, Count " + params.requestedLoadSize)

        var callBack: Call<PostsResponse> = postsService.getPagedPosts( 1)

            callBack.enqueue(object: Callback<PostsResponse> {
                override fun onFailure(call: Call<PostsResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                    if (response.isSuccessful) {
                        var postsMutableList = mutableListOf<Post>()
                        for (post in response.body()?.result!!) {
                            postsMutableList.add(Post(post.id, post.user_id, post.title, post.body))
                        }

                        callback.onResult(postsMutableList, Integer.toString(1),
                            2.toString()
                        )

                        postsMutableList.forEach(Consumer {
                            postsObservable.onNext(it)
                        })
                    }
                }

            })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Post>) {
        Log.i(TAG, "Loading page " + params.key )

        val page = AtomicInteger(0)
        try {
            page.set(params.key.toInt())
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        lateinit var callBack: Call<PostsResponse>

            callBack = postsService.getPagedPosts( page.get())

            callBack.enqueue(object: Callback<PostsResponse> {
                override fun onFailure(call: Call<PostsResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                    if (response.isSuccessful) {

                        var postsMutableList = mutableListOf<Post>()
                        for (post in response.body()?.result!!) {
                            postsMutableList.add(Post(post.id, post.user_id, post.title, post.body))
                        }

                        callback.onResult(postsMutableList, (page.get() + 1).toString())

                        postsMutableList.forEach(Consumer {
                            postsObservable.onNext(it)
                        })
                    }
                }

            })
    }


    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Post>) {

    }

    fun getPosts(): ReplaySubject<Post> {
        return postsObservable
    }


}