package com.harrycampaz.redditapi.data.datasource

import com.harrycampaz.core.data.models.toListEntity
import com.harrycampaz.core.domain.DataPostsEntity
import com.harrycampaz.redditapi.data.PostsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Singleton

class RemotePostsDataSourceImpl(private val api: PostsApi): IRemotePostsDataSource {
    override suspend fun getPostsTop():  Flow<Result<List<DataPostsEntity>>> {
        return flow {
            emit(Result.success(api.getPostsTop().data.children.toListEntity()))
        }.catch {
            emit(Result.failure(RuntimeException("Some Error")))
        }
    }
}