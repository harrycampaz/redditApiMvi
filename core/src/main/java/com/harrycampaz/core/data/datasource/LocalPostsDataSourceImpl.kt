package com.harrycampaz.core.data.datasource

import com.harrycampaz.core.data.local.DatabaseHelper
import com.harrycampaz.core.data.models.DataPostsDb

class LocalPostsDataSourceImpl(private val databaseHelper: DatabaseHelper): ILocalPostsDataSource {

    override suspend fun upsert(post: DataPostsDb): Long {
        return databaseHelper.postDao().upsert(post)
    }
    override suspend fun getPostsTop(): List<DataPostsDb> {
        return databaseHelper.postDao().getDataPosts()
    }

    override suspend fun deleteItemDataPosts(post: DataPostsDb): Int {
        return databaseHelper.postDao().deleteItem(post.title)
    }

    override suspend fun deleteAllItemDataPosts(): Int {
        return databaseHelper.postDao().deleteAllItem()
    }

    override suspend fun updatePosts(post: DataPostsDb): Int {
        return databaseHelper.postDao().updateItem(post.title, true)
    }
}