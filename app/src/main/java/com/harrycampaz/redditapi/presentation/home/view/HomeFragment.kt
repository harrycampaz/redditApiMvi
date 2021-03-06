package com.harrycampaz.redditapi.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harrycampaz.core.presentation.DataPostsVO
import com.harrycampaz.redditapi.databinding.FragmentHomeBinding
import com.harrycampaz.redditapi.presentation.home.intent.HomeAction
import com.harrycampaz.redditapi.presentation.home.listener.OnClickItemListener
import com.harrycampaz.redditapi.presentation.home.listener.OnDismissItemListener
import com.harrycampaz.redditapi.presentation.home.view.adapter.PostsAdapter
import com.harrycampaz.redditapi.presentation.home.viewmodel.HomeViewModel
import com.harrycampaz.redditapi.presentation.home.viewstate.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class HomeFragment: Fragment(), OnDismissItemListener {

    private val viewModel: HomeViewModel by  viewModel()
    private var _binding: FragmentHomeBinding? = null

    lateinit var postsAdapter: PostsAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container,false).also {
            setupView(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListenerState()

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.mainIntet.send(HomeAction.LoadItem)
        }
    }


    private fun setupListenerState() {
        lifecycleScope.launchWhenStarted {
            viewModel.mainState.collectLatest { event ->
                when(event){
                    HomeState.Loading -> showLoading()
                    is HomeState.LoadDataPosts -> {
                        postsAdapter.differ.submitList(event.listDataPosts)
                        hideLoading()
                    }
                    HomeState.AllItemDeleted -> {
                        postsAdapter.differ.submitList(listOf())
                        Toast.makeText(context, "All item Deleted", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupView(homeBinding: FragmentHomeBinding) {
        context?.let {
            postsAdapter = PostsAdapter(it, this){dataPosts ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(dataPosts)
                findNavController().navigate(action)
                lifecycleScope.launch {
                    viewModel.mainIntet.send(HomeAction.UpdateItem(dataPosts))
                }
            }
        }
        homeBinding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
        }

        homeBinding.btnCLeanAllPosts.setOnClickListener {
            lifecycleScope.launch {
                viewModel.mainIntet.send(HomeAction.DeleteAllItem)
            }
        }

        homeBinding.sRefresh.setOnRefreshListener {
            lifecycleScope.launch(Dispatchers.Main) {
                viewModel.mainIntet.send(HomeAction.LoadItem)
                homeBinding.sRefresh.isRefreshing = false
            }
        }
    }

    private fun showLoading(){
        binding.pbHome.isVisible = true
        binding.llContent.isVisible = false
    }

    private fun hideLoading() {
        binding.pbHome.isVisible = false
        binding.llContent.isVisible = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDismissItemListener(dataPostsVO: DataPostsVO, position: Int) {
        lifecycleScope.launch {
            viewModel.mainIntet.send(HomeAction.DeleteItem(dataPostsVO))
            postsAdapter.notifyItemRemoved(position)
        }
    }
}