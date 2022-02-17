package com.harrycampaz.redditapi.domain.usecase

import com.harrycampaz.core.domain.DataPostsEntity
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository

class UpdateItemPostUseCase(
    private val repository: IPostsDataRepository
) {
    suspend fun invoke(dataPostsEntity: DataPostsEntity): Int {
        return repository.updatePosts(dataPostsEntity)
    }
}