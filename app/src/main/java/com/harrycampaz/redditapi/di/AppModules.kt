package com.harrycampaz.redditapi.di

import com.harrycampaz.core.utils.ConstantsApi
import com.harrycampaz.redditapi.data.PostsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val client = OkHttpClient()

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun postsApi(retrofit: Retrofit): PostsApi =
        retrofit.create(PostsApi::class.java)


    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ConstantsApi.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}