package com.harrycampaz.redditapi.presentation.home.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harrycampaz.core.presentation.DataPostsVO
import com.harrycampaz.redditapi.databinding.PostsItemHolderBinding

class PostsViewHolder(private val postsItemHolderBinding: PostsItemHolderBinding): RecyclerView.ViewHolder(postsItemHolderBinding.root) {

    fun bind(postsVO: DataPostsVO){
        postsItemHolderBinding.tvTitle.text = postsVO.title
        postsItemHolderBinding.tvComment.text = "${postsVO.num_comments} - Comments"
    }
}