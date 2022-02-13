package com.harrycampaz.redditapi.data.repository

import com.harrycampaz.core.domain.DataPostsEntity
import com.harrycampaz.redditapi.data.datasource.IRemotePostsDataSource
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsDataRepositoryImpl @Inject constructor(private val dataSource: IRemotePostsDataSource): IPostsDataRepository {
    override suspend fun getPostsTop(): Flow<Result<List<DataPostsEntity>>> =
        dataSource.getPostsTop()
}