package com.knitterassignment.repository.network.comments

import com.knitterassignment.repository.network.HTTPClient.HTTPClient

object CommentsService {
    fun getCommentsService(): CommentsApi {
        return HTTPClient.getNetworkService().create(CommentsApi::class.java)
    }
}