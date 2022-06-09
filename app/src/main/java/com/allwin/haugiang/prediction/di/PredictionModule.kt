package com.allwin.haugiang.prediction.di

import com.allwin.haugiang.app.data.remote.AppDatabase
import com.allwin.haugiang.prediction.data.repository.PredictionRepoImp
import com.allwin.haugiang.prediction.domain.repository.PredictionRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object PredictionModule {

    @ActivityRetainedScoped
    @Provides
    fun providesPredictionRepo(
        service: AppDatabase
    ): PredictionRepo = PredictionRepoImp(service.predictionsDao)

}