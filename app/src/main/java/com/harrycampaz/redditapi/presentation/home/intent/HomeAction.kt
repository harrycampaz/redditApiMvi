package com.harrycampaz.redditapi.presentation.home.intent

import com.harrycampaz.core.presentation.DataPostsVO

sealed class HomeAction {
    object LoadItem: HomeAction()
    object DeleteAllItem: HomeAction()
    data class DeleteItem(val postsVO: DataPostsVO): HomeAction()
}