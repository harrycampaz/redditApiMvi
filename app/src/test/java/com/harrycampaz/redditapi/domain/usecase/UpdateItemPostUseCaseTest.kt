package com.harrycampaz.redditapi.domain.usecase

import com.harrycampaz.core.data.models.toEntity
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository
import com.harrycampaz.redditapi.utils.getFakeDataPostsModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class UpdateItemPostUseCaseTest {
    lateinit var userCase: UpdateItemPostUseCase
    private val response = 1
    private val repository: IPostsDataRepository = mockk(relaxed = true)

    @Before
    fun setup(){
        userCase = UpdateItemPostUseCase(repository)
    }

    @Test
    fun `WHEN useCase invoke THEN repository return Int `() = runBlocking {

        val dataPosts = getFakeDataPostsModel().toEntity()

        coEvery { repository.updatePosts(dataPosts) } returns response

        assertEquals(response, userCase.invoke(dataPosts))
    }


    }