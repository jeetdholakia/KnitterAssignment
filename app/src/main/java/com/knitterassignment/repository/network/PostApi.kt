package com.knitterassignment.repository.network

import com.knitterassignment.repository.network.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    // TODO: 6/26/20 Add the token via http interceptor later on
    @GET("posts")
    suspend fun getPosts(@Query("page") pageNumber: Int): Response

}