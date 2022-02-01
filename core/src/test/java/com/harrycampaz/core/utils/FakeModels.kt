package com.harrycampaz.core.utils

import com.harrycampaz.core.data.models.ChildrenModel
import com.harrycampaz.core.data.models.DataModel
import com.harrycampaz.core.data.models.DataPostsModel

fun getFakeDataPostsModel(n: Int) = DataPostsModel(
    author_fullname = "t2_p7o61 $n",
    title = "Sunday Rant/Rage",
    created_utc = 1643482364.0,
    thumbnail = "image",
    num_comments = 5
)

fun getFakeChildren(n: Int = 0) = ChildrenModel(
    data = getFakeDataPostsModel(n)
)

fun getFakeData() = DataModel(
    listOf(
        getFakeChildren(0),
        getFakeChildren(2),
        getFakeChildren(3),
        getFakeChildren(4)
    )
)

