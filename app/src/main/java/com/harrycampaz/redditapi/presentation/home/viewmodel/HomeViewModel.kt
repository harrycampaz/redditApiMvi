package com.harrycampaz.redditapi.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrycampaz.redditapi.domain.usecase.GetDataPostsUseCase
import com.harrycampaz.redditapi.presentation.home.intent.HomeAction
import com.harrycampaz.redditapi.presentation.home.viewstate.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val useCase: GetDataPostsUseCase
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
                when(event){
                    is HomeAction.DeleteItem -> {
                        Timber.e("Delete one item")
                    }
                    HomeAction.DeleteAllItem -> {
                        Timber.e("Delete all items")
                    }
                    HomeAction.LoadItem -> {
                        Timber.e("Loading possts")
                        val data = useCase.invoke()
                        data?.let {
                            Timber.e("This is data $it")
                        }
                    }
                }
            }
        }
    }

}