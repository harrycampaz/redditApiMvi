package com.harrycampaz.redditapi.utils

import com.harrycampaz.core.data.models.DataPostsModel

fun getFakeDataPostsModel() = DataPostsModel(
    author_fullname = "autor",
    title = "This is the title",
    created_utc = 100.0,
    thumbnail = "image_url",
    num_comments = 10
)