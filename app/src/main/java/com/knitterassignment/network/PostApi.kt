package com.knitterassignment.network

import com.knitterassignment.network.model.Response
import retrofit2.http.GET

interface PostApi {

    // TODO: 6/26/20 Add the token via http interceptor later on
    @GET("posts?format=json&access-token=dv8RtNyd5a6ubIJhHlPCNkIVyedc8hx4RnGD?page=1")
    suspend fun getPosts(): Response

}