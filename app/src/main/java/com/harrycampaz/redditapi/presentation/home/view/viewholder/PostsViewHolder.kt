package com.harrycampaz.redditapi.presentation.home.view.viewholder

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harrycampaz.core.presentation.DataPostsVO
import com.harrycampaz.redditapi.R
import com.harrycampaz.redditapi.databinding.PostsItemHolderBinding
import com.harrycampaz.redditapi.presentation.home.listener.OnDismissItemListener

class PostsViewHolder(private val postsItemHolderBinding: PostsItemHolderBinding,
                      private val onClick: (DataPostsVO) -> Unit
): RecyclerView.ViewHolder(postsItemHolderBinding.root) {

    fun bind(postsVO: DataPostsVO, context: Context, onDismissItemListener: OnDismissItemListener){
        postsItemHolderBinding.tvTitle.text = postsVO.title
        postsItemHolderBinding.tvComment.text = "${postsVO.num_comments} - Comments"

        if(postsVO.thumbnail.isNotEmpty()){
            postsItemHolderBinding.ivthumbnail.loadImg(postsVO.thumbnail)
        }
        if(postsVO.status){
            postsItemHolderBinding.clItem.setBackgroundColor(context.resources.getColor(R.color.purple_700))
        }

        postsItemHolderBinding.root.setOnClickListener {
            onClick.invoke(postsVO)
        }

        postsItemHolderBinding.btnDismiss.setOnClickListener {
            onDismissItemListener.onDismissItemListener(postsVO, adapterPosition)
        }
    }
}

fun ImageView.loadImg(url: String){
    Glide.with(this)
        .load(url)
        .into(this)
}