package com.harrycampaz.redditapi.di

import com.harrycampaz.core.utils.ConstantsApi
import com.harrycampaz.redditapi.data.PostsApi
import com.harrycampaz.redditapi.data.datasource.IRemotePostsDataSource
import com.harrycampaz.redditapi.data.datasource.RemotePostsDataSourceImpl
import com.harrycampaz.redditapi.data.repository.PostsDataRepositoryImpl
import com.harrycampaz.redditapi.domain.repository.IPostsDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val client = OkHttpClient()

@Module
@InstallIn(ActivityComponent::class)
class AppModules {

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

@Module
@InstallIn(ActivityComponent::class)
abstract class DataModule {
    @Binds
    abstract fun providerRepository(repositoryImpl: PostsDataRepositoryImpl): IPostsDataRepository

    @Binds
    abstract fun providerDataSouce(dataSourceImpl: RemotePostsDataSourceImpl): IRemotePostsDataSource

}
