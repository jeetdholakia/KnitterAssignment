package com.knitterassignment.repository.network.posts

import com.knitterassignment.repository.network.HTTPClient.HTTPClient


object PostsService {

    fun getPostsService(): PostApi {
       return HTTPClient.getNetworkService().create(PostApi::class.java)
    }
}