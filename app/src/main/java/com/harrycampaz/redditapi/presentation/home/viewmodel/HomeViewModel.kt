package com.harrycampaz.redditapi.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrycampaz.core.presentation.toEntity
import com.harrycampaz.core.presentation.toListViewObject
import com.harrycampaz.redditapi.domain.usecase.DeleteAllPostsUseCase
import com.harrycampaz.redditapi.domain.usecase.DeleteItemPostsUseCase
import com.harrycampaz.redditapi.domain.usecase.GetDataPostsUseCase
import com.harrycampaz.redditapi.domain.usecase.UpdateItemPostUseCase
import com.harrycampaz.redditapi.presentation.home.intent.HomeAction
import com.harrycampaz.redditapi.presentation.home.viewstate.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataPostsUseCase: GetDataPostsUseCase,
    private val deleteAllPosts: DeleteAllPostsUseCase,
    private val deleteItemPostsUseCase: DeleteItemPostsUseCase,
    private val updateItemPostUseCase: UpdateItemPostUseCase
) : ViewModel() {

    val mainIntet = Channel<HomeAction>(Channel.UNLIMITED)

    private val _mainState = MutableStateFlow<HomeState>(HomeState.Loading)

    val mainState: StateFlow<HomeState>
        get() = _mainState

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mainIntet.consumeAsFlow().collect { event ->
                when (event) {
                    is HomeAction.DeleteItem -> {
                        deleteItemPostsUseCase.invoke(event.postsVO.toEntity())

                    }
                    HomeAction.DeleteAllItem -> {
                        launch(Dispatchers.IO) {
                            deleteAllPosts.invoke()
                        }
                        _mainState.value = HomeState.AllItemDeleted
                    }
                    HomeAction.LoadItem -> {
                        launch(Dispatchers.IO) {
                            val data = getDataPostsUseCase.invoke(true)
                            data?.let {
                                it.getOrNull()?.let { list ->
                                    _mainState.value = HomeState.LoadDataPosts(list.toListViewObject())
                                }
                            }
                        }
                    }
                    is HomeAction.UpdateItem -> {
                        updateItemPostUseCase.invoke(event.postsVO.toEntity())
                    }
                }
            }
        }
    }

}