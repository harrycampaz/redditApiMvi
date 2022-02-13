package com.harrycampaz.redditapi.presentation.home.intent

import com.harrycampaz.core.domain.DataPostsEntity

sealed class HomeAction {
    object LoadItem: HomeAction()
    object DeleteAllItem: HomeAction()
    data class DeleteItem(val postsEntity: DataPostsEntity): HomeAction()
}