package com.harrycampaz.redditapi.di

import com.harrycampaz.core.utils.ConstantsApi
import com.harrycampaz.redditapi.data.PostsApi
import com.harrycampaz.redditapi.data.datasource.IRemotePostsDataSource
import com.harrycampaz.redditapi.data.datasource.RemotePostsDataSourceImpl
import com.harrycampaz.redditapi.data.repository.PostsDataRepositoryImpl
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository
import com.harrycampaz.redditapi.domain.usecase.GetDataPostsUseCase
import com.harrycampaz.redditapi.presentation.home.viewmodel.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModules = module {

    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { providePostsApi(get()) }

    single<IRemotePostsDataSource> { RemotePostsDataSourceImpl(get()) }
    single<IPostsDataRepository> { PostsDataRepositoryImpl(get()) }

    single { GetDataPostsUseCase(get()) }

    viewModel { HomeViewModel(get()) }
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