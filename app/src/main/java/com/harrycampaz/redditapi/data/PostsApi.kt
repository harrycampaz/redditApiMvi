package com.harrycampaz.redditapi.data

import com.harrycampaz.core.data.models.RedditPosts
import retrofit2.http.GET

interface PostsApi {
    @GET("android/hot.json")
    suspend fun getPostsTop(): RedditPosts

}