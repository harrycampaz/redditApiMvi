package com.harrycampaz.redditapi.data.repository

import com.harrycampaz.core.data.datasource.ILocalPostsDataSource
import com.harrycampaz.core.data.models.DataPostsModel
import com.harrycampaz.core.data.models.RedditPosts
import com.harrycampaz.core.data.models.toListDb
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
    var restore = false
    private lateinit var repository: PostsDataRepositoryImpl
    private val remoteSource: IRemotePostsDataSource = mockk(relaxed = true)
    private val localSource: ILocalPostsDataSource = mockk(relaxed =  true)
    private val redditPosts: RedditPosts = mockk(relaxed = true)

    @Before
    fun setup(){
        repository = PostsDataRepositoryImpl(remoteSource, localSource)
    }

    @Test
    fun`WHEN repository getPostsTop THEN  localSource exec`(): Unit = runBlocking {

        coEvery { localSource.getPostsTop() } returns redditPosts.data.children.toListDb()

        repository.getPostsTop(restore).first()

        coVerify { localSource.getPostsTop()  }

    }

    @Test
    fun `WHEN repository getPostsTop() THEN localSource return flow DataPosts Entity`(): Unit = runBlocking{
        coEvery { localSource.getPostsTop() } returns redditPosts.data.children.toListDb()

        assertTrue(repository.getPostsTop(restore).first().isSuccess)
        assertEquals(
            Result.success(redditPosts.data.children.toListEntity()),
            repository.getPostsTop(restore).first()
        )
    }

}