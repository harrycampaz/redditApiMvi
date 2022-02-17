package com.harrycampaz.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harrycampaz.core.data.models.DataPostsDb

@Dao
interface DataPostsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsert(postsDb: DataPostsDb): Long

    @Query("SELECT * FROM `data-posts` ORDER BY id")
    fun getDataPosts(): List<DataPostsDb>

    @Query("DELETE FROM `data-posts` WHERE title == :title")
    suspend fun deleteItem(title: String): Int

    @Query("DELETE FROM `data-posts`")
    suspend fun deleteAllItem(): Int

    @Query("UPDATE `data-posts` SET status = :value WHERE title == :title")
    suspend fun updateItem(title: String, value: Boolean): Int
}