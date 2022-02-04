package com.harrycampaz.redditapi.data.datasource

import com.harrycampaz.core.data.models.RedditPosts
import com.harrycampaz.core.data.models.toListEntity
import com.harrycampaz.redditapi.data.PostsApi
import io.mockk.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.lang.RuntimeException


class RemotePostsDataSourceImplTest {
    private lateinit var dataSourceImpl: RemotePostsDataSourceImpl
    private val api: PostsApi = mockk(relaxed = true)
    private val redditPosts: RedditPosts = mockk(relaxed = true)

    @Test
    fun `WHEN datasource getPosts  THEN exec PostAPI getPostsTop`(): Unit = runBlocking {

        // WHEN
        dataSourceImpl = RemotePostsDataSourceImpl(api)

        dataSourceImpl.getPostsTop().first()

        // THEN
        coVerify { api.getPostsTop() }
    }

    @Test
    fun `WHEN datasource getPosts THEN Return RedditPosts`(): Unit = runBlocking{
        // WHEN
        coEvery { api.getPostsTop() } returns redditPosts
        dataSourceImpl = RemotePostsDataSourceImpl(api)

        // THEN
        assertTrue(dataSourceImpl.getPostsTop().first().isSuccess)

        assertEquals(Result.success(redditPosts.data.children.toListEntity()), dataSourceImpl.getPostsTop().first())

    }

    @Test
    fun `WHEN datasource getPosts THEN Return Error`(): Unit = runBlocking{
        // WHEN
        coEvery { api.getPostsTop() }.throws(RuntimeException("Some Error"))
        dataSourceImpl = RemotePostsDataSourceImpl(api)

        // THEN
        assertEquals("Some Error", dataSourceImpl.getPostsTop().first().exceptionOrNull()?.message)

    }

}