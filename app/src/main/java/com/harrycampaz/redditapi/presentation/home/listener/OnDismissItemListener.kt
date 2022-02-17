package com.harrycampaz.redditapi.presentation.home.listener

import com.harrycampaz.core.presentation.DataPostsVO

interface OnDismissItemListener {
    fun onDismissItemListener(dataPostsVO: DataPostsVO, position: Int)
}