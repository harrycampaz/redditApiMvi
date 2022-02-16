package com.harrycampaz.redditapi.presentation.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.harrycampaz.core.presentation.DataPostsVO
import com.harrycampaz.redditapi.databinding.PostsItemHolderBinding
import com.harrycampaz.redditapi.presentation.home.view.viewholder.PostsViewHolder

class PostsAdapter: RecyclerView.Adapter<PostsViewHolder>() {

    private val differCallback = object: DiffUtil.ItemCallback<DataPostsVO>(){
        override fun areItemsTheSame(oldItem: DataPostsVO, newItem: DataPostsVO): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: DataPostsVO, newItem: DataPostsVO): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding  = PostsItemHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}