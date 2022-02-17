package com.harrycampaz.redditapi.domain.repository

import com.harrycampaz.core.domain.DataPostsEntity
import kotlinx.coroutines.flow.Flow

interface IPostsDataRepository {
    suspend fun getPostsTop(restore: Boolean): Flow<Result<List<DataPostsEntity>>>
    suspend fun deleteAllPosts(): Int
    suspend fun deleteItemPosts(postsEntity: DataPostsEntity): Int
    suspend fun updatePosts(post: DataPostsEntity): Int
}