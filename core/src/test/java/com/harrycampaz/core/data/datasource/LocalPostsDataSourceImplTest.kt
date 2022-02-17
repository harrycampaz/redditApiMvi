package com.harrycampaz.core.data.datasource

import com.harrycampaz.core.data.local.DatabaseHelper
import com.harrycampaz.core.data.models.DataPostsDb
import com.harrycampaz.core.data.models.toDb
import com.harrycampaz.core.utils.getFakeData
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test


class LocalPostsDataSourceImplTest {
    private lateinit var localPostsDataSourceImpl: LocalPostsDataSourceImpl
    private val databaseHelper: DatabaseHelper = mockk(relaxed = true)
    private val dataPosts: List<DataPostsDb> = mockk(relaxed = true)

    @Test
    fun `WHEN datasource getPosts  THEN exec databaseHelper getPostsTop`(): Unit = runBlocking {

        // WHEN
        localPostsDataSourceImpl = LocalPostsDataSourceImpl(databaseHelper)

        localPostsDataSourceImpl.getPostsTop().first()

        // THEN
        coVerify { databaseHelper.postDao().getDataPosts()}
    }

    @Test
    fun `WHEN datasource upsert  THEN exec databaseHelper getPostsTop`(): Unit = runBlocking {

        // WHEN
        localPostsDataSourceImpl = LocalPostsDataSourceImpl(databaseHelper)

        localPostsDataSourceImpl.upsert(getFakeData().children.first().data.toDb())

        // THEN
        coVerify { databaseHelper.postDao().upsert(getFakeData().children.first().data.toDb())}
    }

    @Test
    fun `WHEN datasource deleteAllItemDataPosts  THEN exec databaseHelper deleteAllItemDataPosts`(): Unit = runBlocking {

        // WHEN
        localPostsDataSourceImpl = LocalPostsDataSourceImpl(databaseHelper)

        localPostsDataSourceImpl.deleteAllItemDataPosts()

        // THEN
        coVerify { databaseHelper.postDao().deleteAllItem()}
    }

    @Test
    fun `WHEN datasource updatePosts  THEN exec databaseHelper updateItem`(): Unit = runBlocking {

        // WHEN
        localPostsDataSourceImpl = LocalPostsDataSourceImpl(databaseHelper)

        localPostsDataSourceImpl.updatePosts(getFakeData().children.first().data.toDb())

        // THEN
        coVerify { databaseHelper.postDao().updateItem(getFakeData().children.first().data.toDb().title, true)}
    }


}