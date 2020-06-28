package com.knitterassignment.repository.network

import com.knitterassignment.repository.network.model.comments.CommentsResponse
import com.knitterassignment.util.Constants.Companion.commentsEndpoint
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentsApi {
    @GET(commentsEndpoint)
    suspend fun getComments(@Query("page") pageNumber: Int): CommentsResponse
}