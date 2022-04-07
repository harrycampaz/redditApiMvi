package com.harrycampaz.redditapi.presentation.home.viewstate

import com.harrycampaz.core.presentation.DataPostsVO

sealed class HomeState {

    object Loading: HomeState()
    data class LoadDataPosts(val listDataPosts: List<DataPostsVO>): HomeState()
    object AllItemDeleted: HomeState()
    object DeleteItemSuccess: HomeState()
}