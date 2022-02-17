package com.harrycampaz.redditapi.presentation.home.listener

import com.harrycampaz.core.presentation.DataPostsVO

interface OnClickItemListener {
    fun onClickItemListener(dataPostsVO: DataPostsVO,onClick: () -> Unit)
}