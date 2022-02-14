package com.harrycampaz.redditapi.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrycampaz.redditapi.domain.usecase.DeleteAllPostsUseCase
import com.harrycampaz.redditapi.domain.usecase.DeleteItemPostsUseCase
import com.harrycampaz.redditapi.domain.usecase.GetDataPostsUseCase
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
    private val deleteItemPostsUseCase: DeleteItemPostsUseCase
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
                        Timber.e("Delete one item")
                    }
                    HomeAction.DeleteAllItem -> {
                        launch(Dispatchers.IO) {
                            deleteAllPosts.invoke()
                        }
                    }
                    HomeAction.LoadItem -> {
                        Timber.e("Loading possts")

                        launch(Dispatchers.IO) {
                            val data = getDataPostsUseCase.invoke(false)
                            data?.let {
                                Timber.e("This is data $it")
                            }
                        }

                    }
                }
            }
        }
    }

}