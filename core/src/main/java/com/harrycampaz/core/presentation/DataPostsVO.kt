package com.harrycampaz.core.presentation

import android.os.Parcelable
import com.harrycampaz.core.domain.DataPostsEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPostsVO(
    val author_fullname: String,
    val title: String,
    val created_utc: Double,
    val thumbnail: String,
    val num_comments: Int,
    val status: Boolean = false
): Parcelable

fun DataPostsEntity.toViewObject() = DataPostsVO(
    author_fullname, title, created_utc, thumbnail, num_comments, status
)

fun List<DataPostsEntity>.toListViewObject() = this.map {
    it.toViewObject()
}

fun DataPostsVO.toEntity() = DataPostsEntity(
    author_fullname, title, created_utc, thumbnail, num_comments
)