package com.harrycampaz.redditapi.domain.usecase

import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class DeleteAllPostsUseCaseTest {
    lateinit var useCase: DeleteAllPostsUseCase
    private val response = 1
    private val repository: IPostsDataRepository = mockk(relaxed = true)

    @Before
    fun setup(){
        useCase = DeleteAllPostsUseCase(repository)
    }

    @Test
    fun `WHEN useCase invoke THEN repository return Int `() = runBlocking {

        coEvery { repository.deleteAllPosts() } returns response

        assertEquals(
            response, useCase.invoke()
        )

        coVerify(atLeast = 1) {  repository.deleteAllPosts() }
    }

}