package com.harrycampaz.core.domain

data class DataPostsEntity(
    val author_fullname: String,
    val title: String,
    val created_utc: Double,
    val thumbnail: String,
    val num_comments: Int,
    val status: Boolean = false
)
