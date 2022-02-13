package com.harrycampaz.redditapi.domain.repository

import com.harrycampaz.core.domain.DataPostsEntity
import kotlinx.coroutines.flow.Flow

interface IPostsDataRepository {
    suspend fun getPostsTop(): Flow<Result<List<DataPostsEntity>>>
}