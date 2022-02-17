package com.harrycampaz.redditapi.domain.usecase

import com.harrycampaz.core.data.models.toEntity
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository
import com.harrycampaz.redditapi.utils.getFakeDataPostsModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class DeleteItemPostsUseCaseTest {
    lateinit var userCase: DeleteItemPostsUseCase
    private val response = 1
    private val repository: IPostsDataRepository = mockk(relaxed = true)

    @Before
    fun setup(){
        userCase = DeleteItemPostsUseCase(repository)
    }

    @Test
    fun `WHEN useCase invoke THEN repository return Int `() = runBlocking {
        val dataPosts = getFakeDataPostsModel().toEntity()

        coEvery { repository.deleteItemPosts(dataPosts) } returns response

        Assert.assertEquals(response, userCase.invoke(dataPosts))
    }

}