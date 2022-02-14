package com.harrycampaz.redditapi.domain.usecase

import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository

class DeleteAllPostsUseCase(
    private val repository: IPostsDataRepository
) {
    suspend fun invoke(): Int = repository.deleteAllPosts()
}