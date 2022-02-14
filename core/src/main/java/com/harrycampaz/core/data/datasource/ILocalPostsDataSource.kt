package com.harrycampaz.core.data.datasource

import com.harrycampaz.core.data.models.DataPostsDb
import com.harrycampaz.core.domain.DataPostsEntity

interface ILocalPostsDataSource {
    suspend fun upsert(post: DataPostsDb): Long
    suspend fun getPostsTop(): List<DataPostsDb>
    suspend fun deleteItemDataPosts(post: DataPostsDb): Int
    suspend fun deleteAllItemDataPosts(): Int
}