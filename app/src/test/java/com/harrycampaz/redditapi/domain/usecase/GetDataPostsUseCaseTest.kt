package com.harrycampaz.redditapi.domain.usecase

import com.harrycampaz.core.data.models.RedditPosts
import com.harrycampaz.core.data.models.toListEntity
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetDataPostsUseCaseTest {
    lateinit var useCase: GetDataPostsUseCase

    private val repository: IPostsDataRepository = mockk(relaxed = true)
    private val redditPosts: RedditPosts = mockk(relaxed = true)


    @Before
    fun setup(){
        useCase = GetDataPostsUseCase(repository)
    }

    @Test
    fun `WHEN useCase invoke THEN repository return `() = runBlocking{

        coEvery { repository.getPostsTop() } returns  flow { emit(Result.success(redditPosts.data.children.toListEntity()))}

        assertEquals(
            Result.success(redditPosts.data.children.toListEntity()),
            useCase.invoke()
        )

        coVerify (atLeast = 1) { repository.getPostsTop() }

    }
}