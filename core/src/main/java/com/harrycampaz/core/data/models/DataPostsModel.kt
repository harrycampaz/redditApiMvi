package com.harrycampaz.core.data.models

import com.harrycampaz.core.domain.DataPostsEntity

data class DataPostsModel(
    val author_fullname: String,
    val title: String,
    val created_utc: Double,
    val thumbnail: String,
    val num_comments: Int
)

fun DataPostsModel.toEntity() = DataPostsEntity(
    author_fullname, title, created_utc, thumbnail, num_comments
)

fun List<ChildrenModel>.toListEntity(): List<DataPostsEntity> =
    this.map {
        it.data.toEntity()
    }