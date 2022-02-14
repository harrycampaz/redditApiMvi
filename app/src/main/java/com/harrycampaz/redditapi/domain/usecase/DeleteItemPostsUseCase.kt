package com.harrycampaz.redditapi.domain.usecase

import com.harrycampaz.core.domain.DataPostsEntity
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository

class DeleteItemPostsUseCase(
    private val repository: IPostsDataRepository
) {
    suspend fun invoke(postsEntity: DataPostsEntity): Int =
        repository.deleteItemPosts(postsEntity)
}