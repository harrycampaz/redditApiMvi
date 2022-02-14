package com.harrycampaz.redditapi.data.repository

import com.harrycampaz.core.data.datasource.ILocalPostsDataSource
import com.harrycampaz.core.data.models.toDb
import com.harrycampaz.core.data.models.toListEntity
import com.harrycampaz.core.domain.DataPostsEntity
import com.harrycampaz.redditapi.data.datasource.IRemotePostsDataSource
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

class PostsDataRepositoryImpl(
    private val dataSource: IRemotePostsDataSource,
    private val localSource: ILocalPostsDataSource
) : IPostsDataRepository {
    override suspend fun getPostsTop(restore: Boolean): Flow<Result<List<DataPostsEntity>>> {
        if (restore) {
            dataSource.getPostsTop().first().getOrNull()?.map {
                localSource.upsert(it.toDb())
            }
        }
        return flow {
            emit(Result.success(localSource.getPostsTop().toListEntity()))
        }
    }

    override suspend fun deleteAllPosts(): Int {
        return localSource.deleteAllItemDataPosts()
    }

    override suspend fun deleteItemPosts(postsEntity: DataPostsEntity): Int {
        return localSource.deleteItemDataPosts(postsEntity.toDb())
    }

}