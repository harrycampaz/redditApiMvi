package com.harrycampaz.core.presentation

import com.harrycampaz.core.data.models.DataPostsModel
import com.harrycampaz.core.domain.DataPostsEntity

data class DataPostsVO(
    val author_fullname: String,
    val title: String,
    val created_utc: Double,
    val thumbnail: String,
    val num_comments: Int
)

fun DataPostsEntity.toViewObject() = DataPostsVO(
    author_fullname, title, created_utc, thumbnail, num_comments
)