package com.harrycampaz.redditapi.data.repository

import com.harrycampaz.core.data.models.DataPostsModel
import com.harrycampaz.core.data.models.RedditPosts
import com.harrycampaz.core.data.models.toListEntity
import com.harrycampaz.core.domain.DataPostsEntity
import com.harrycampaz.redditapi.data.datasource.IRemotePostsDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException


class PostsDataRepositoryImplTest {

    private val exception = RuntimeException("Some error")

    private lateinit var repository: PostsDataRepositoryImpl
    private val remoteSource: IRemotePostsDataSource = mockk(relaxed = true)
    private val redditPosts: RedditPosts = mockk(relaxed = true)

    @Before
    fun setup(){
        repository = PostsDataRepositoryImpl(remoteSource)
    }

    @Test
    fun`WHEN repository getPostsTop THEN  remoteSource exec`(): Unit = runBlocking {

        coEvery { remoteSource.getPostsTop() } returns flow { emit(Result.success(redditPosts.data.children.toListEntity())) }

        repository.getPostsTop().first()

        coVerify (atLeast = 1) { remoteSource.getPostsTop()  }

    }

    @Test
    fun `WHEN repository getPostsTop() THEN remoteSource return flow DataPosts Entity`(): Unit = runBlocking{
        coEvery { remoteSource.getPostsTop() } returns flow { emit(Result.success(redditPosts.data.children.toListEntity())) }

        assertTrue(repository.getPostsTop().first().isSuccess)
        assertEquals(
            Result.success(redditPosts.data.children.toListEntity()),
            repository.getPostsTop().first()
        )
    }

    @Test
    fun `WHEN repository getPostsTop() THEN remoteSource return exception`(): Unit = runBlocking{
        coEvery { remoteSource.getPostsTop() } returns flow {
            emit(Result.failure<List<DataPostsEntity>>(exception))
        }

        repository.getPostsTop()

        assertTrue(repository.getPostsTop().first().isFailure)
        assertEquals(exception, repository.getPostsTop().first().exceptionOrNull())
    }

}