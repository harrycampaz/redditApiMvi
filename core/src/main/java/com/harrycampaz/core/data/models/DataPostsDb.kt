package com.harrycampaz.core.data.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.harrycampaz.core.domain.DataPostsEntity

@Entity(
    tableName = "data-posts", indices = [Index(value = ["title"], unique = true)]
)
@Keep
data class DataPostsDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val author_fullname: String,
    val title: String,
    val created_utc: Double,
    val thumbnail: String,
    val num_comments: Int,
    val status: Boolean = false
)

fun DataPostsDb.toEntity() = DataPostsEntity(
    author_fullname, title, created_utc, thumbnail, num_comments
)

fun DataPostsEntity.toDb() = DataPostsDb (
    author_fullname = author_fullname,
    title = title,
    created_utc = created_utc,
    thumbnail = thumbnail,
    num_comments = num_comments,
    status = status
)

fun List<DataPostsDb>.toListEntity(): List<DataPostsEntity> =
    this.map {
        it.toEntity()
    }