package com.harrycampaz.redditapi.data.datasource

import com.harrycampaz.core.domain.DataPostsEntity
import kotlinx.coroutines.flow.Flow

interface IRemotePostsDataSource {
    suspend fun getPostsTop(): Flow<Result<List<DataPostsEntity>>>
}