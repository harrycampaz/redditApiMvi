package com.harrycampaz.redditapi.domain.usecase

import com.harrycampaz.core.domain.DataPostsEntity
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetDataPostsUseCase(
    private val repository: IPostsDataRepository
) {
    suspend fun invoke(restore: Boolean) : Result<List<DataPostsEntity>>? {
        return repository.getPostsTop(restore).firstOrNull()
    }
}