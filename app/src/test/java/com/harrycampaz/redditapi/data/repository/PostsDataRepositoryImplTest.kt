package com.harrycampaz.redditapi.data.repository

import com.harrycampaz.core.data.datasource.ILocalPostsDataSource
import com.harrycampaz.core.data.models.*
import com.harrycampaz.core.domain.DataPostsEntity
import com.harrycampaz.redditapi.data.datasource.IRemotePostsDataSource
import com.harrycampaz.redditapi.utils.getFakeDataPostsModel
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

    @Test
    fun`WHEN repository deleteAllPosts THEN  deleteAllItemDataPosts exec`(): Unit = runBlocking {


        repository.deleteAllPosts()

        coVerify { localSource.deleteAllItemDataPosts()  }
    }

    @Test
    fun`WHEN repository deleteItemPosts THEN  deleteItemDataPosts exec`(): Unit = runBlocking {

        val dataPostsModel = getFakeDataPostsModel()

        repository.deleteItemPosts(dataPostsModel.toEntity())

        coVerify { localSource.deleteItemDataPosts(dataPostsModel.toDb())  }
    }

    @Test
    fun`WHEN repository updatePosts THEN  updatePosts exec`(): Unit = runBlocking {

        val dataPostsModel = getFakeDataPostsModel()

        repository.updatePosts(dataPostsModel.toEntity())

        coVerify { localSource.updatePosts(dataPostsModel.toDb())  }
    }
}