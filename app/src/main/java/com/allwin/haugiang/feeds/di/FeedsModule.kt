package com.allwin.haugiang.feeds.di

import com.allwin.haugiang.common.constant.FeedsConstants
import com.allwin.haugiang.feeds.data.remote.FeedService
import com.allwin.haugiang.feeds.data.repository.FeedsRepoImp
import com.allwin.haugiang.feeds.domain.repository.FeedsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
object FeedsModule {

    @ActivityRetainedScoped
    @Provides

    fun providesFeedsService(
        @Named(FeedsConstants.FEED)
        retrofit: Retrofit
    ): FeedService = retrofit.create(FeedService::class.java)

    @ActivityRetainedScoped
    @Provides
    fun provideFeedsRepo(service: FeedService): FeedsRepo = FeedsRepoImp(service)

}