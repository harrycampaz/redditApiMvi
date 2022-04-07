package com.harrycampaz.redditapi.presentation.home.viewmodel


import com.harrycampaz.core.data.models.toEntity
import com.harrycampaz.core.domain.DataPostsEntity
import com.harrycampaz.core.presentation.toViewObject

import com.harrycampaz.redditapi.domain.usecase.DeleteAllPostsUseCase
import com.harrycampaz.redditapi.domain.usecase.DeleteItemPostsUseCase
import com.harrycampaz.redditapi.domain.usecase.GetDataPostsUseCase
import com.harrycampaz.redditapi.domain.usecase.UpdateItemPostUseCase
import com.harrycampaz.redditapi.presentation.home.intent.HomeAction
import com.harrycampaz.redditapi.presentation.home.viewstate.HomeState
import com.harrycampaz.redditapi.utils.CoroutinesTestRule
import com.harrycampaz.redditapi.utils.getFakeDataPostsModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test


class HomeViewModelTest {
    @Rule
    @JvmField
    var coroutinesTestRule = CoroutinesTestRule()

    private val  getDataPostsUseCase: GetDataPostsUseCase = mockk(relaxUnitFun = true)

    private val deleteAllPosts: DeleteAllPostsUseCase = mockk(relaxUnitFun = true)
    private val deleteItemPostsUseCase: DeleteItemPostsUseCase  = mockk(relaxUnitFun = true)
    private val updateItemPostUseCase: UpdateItemPostUseCase =  mockk(relaxUnitFun = true)

    private val listData: List<DataPostsEntity> = mockk(relaxUnitFun = true)


    @Test
    fun `GIVEN viewModel  WHEN send Action DeleteItem THEN State is DeleteItemSuccess`() = coroutinesTestRule.runBlockingTest {

        // Given
        val viewModel = createViewModel()
        val eventList = mutableListOf<HomeState>()

        coEvery { deleteItemPostsUseCase.invoke(getFakeDataPostsModel().toEntity()) } returns 1
        // WHEN
        viewModel.mainIntet.send(HomeAction.DeleteItem(getFakeDataPostsModel().toEntity().toViewObject()))

        val job = launch {
            viewModel.mainState.collect {
                eventList.add(it)
            }
        }

        assertTrue(eventList.first() is HomeState.DeleteItemSuccess)
        job.cancel()
    }

    @Test
    fun `GIVEN viewModel  WHEN send Action DeleteAllItem THEN State is AllItemDeleted`() = coroutinesTestRule.runBlockingTest {

        // Given
        val viewModel = createViewModel()
        val eventList = mutableListOf<HomeState>()

        coEvery { getDataPostsUseCase.invoke(true) } returns Result.success(listData)
        // WHEN
        viewModel.mainIntet.send(HomeAction.DeleteAllItem)

        val job = launch {
            viewModel.mainState.collect {
                eventList.add(it)
            }
        }

        assertTrue(eventList.first() is HomeState.AllItemDeleted)
        job.cancel()
    }


    private fun createViewModel():  HomeViewModel {
        return HomeViewModel(
            getDataPostsUseCase,
            deleteAllPosts,
            deleteItemPostsUseCase,
            updateItemPostUseCase
        )
    }
}