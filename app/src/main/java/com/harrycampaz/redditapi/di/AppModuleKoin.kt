package com.harrycampaz.redditapi.di

import com.harrycampaz.core.data.datasource.ILocalPostsDataSource
import com.harrycampaz.core.data.datasource.LocalPostsDataSourceImpl
import com.harrycampaz.core.data.local.DatabaseHelper
import com.harrycampaz.core.utils.ConstantsApi
import com.harrycampaz.redditapi.data.PostsApi
import com.harrycampaz.redditapi.data.datasource.IRemotePostsDataSource
import com.harrycampaz.redditapi.data.datasource.RemotePostsDataSourceImpl
import com.harrycampaz.redditapi.data.repository.PostsDataRepositoryImpl
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository
import com.harrycampaz.redditapi.domain.usecase.DeleteAllPostsUseCase
import com.harrycampaz.redditapi.domain.usecase.DeleteItemPostsUseCase
import com.harrycampaz.redditapi.domain.usecase.GetDataPostsUseCase
import com.harrycampaz.redditapi.presentation.home.viewmodel.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModules = module {

    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { providePostsApi(get()) }

    single { DatabaseHelper(androidContext()) }

    single<ILocalPostsDataSource> { LocalPostsDataSourceImpl(get()) }

    single<IRemotePostsDataSource> { RemotePostsDataSourceImpl(get()) }

    single<IPostsDataRepository> { PostsDataRepositoryImpl(get(), get()) }

    single { GetDataPostsUseCase(get()) }
    single { DeleteAllPostsUseCase(get()) }
    single { DeleteItemPostsUseCase(get()) }

    viewModel { HomeViewModel(get(), get(), get()) }
}

fun providePostsApi(retrofit: Retrofit): PostsApi =
    retrofit.create(PostsApi::class.java)

fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(ConstantsApi.BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder()
        .build()
}