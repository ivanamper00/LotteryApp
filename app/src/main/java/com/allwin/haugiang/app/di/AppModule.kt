package com.allwin.haugiang.app.di

import android.content.Context
import androidx.room.Room
import com.allwin.haugiang.app.data.remote.AppDatabase
import com.allwin.haugiang.app.data.repository.ScreensRepoImp
import com.allwin.haugiang.app.domain.repository.ScreensRepo
import com.allwin.haugiang.common.constant.FeedsConstants
import com.allwin.haugiang.common.executor.ExecutionThread
import com.allwin.haugiang.common.executor.PostExecutionThread
import com.allwin.haugiang.common.thread.ExecutorThread
import com.allwin.haugiang.common.thread.PostExecutorThread
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesThreadExecutor(): ExecutionThread = ExecutorThread(Schedulers.io())

    @Singleton
    @Provides
    fun providesPostThreadExecutor(): PostExecutionThread = PostExecutorThread(AndroidSchedulers.mainThread())

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext
        context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun providesScreensRepo(
        service: AppDatabase
    ): ScreensRepo = ScreensRepoImp(service.screensDao)

    @Singleton
    @Provides
    @Named(FeedsConstants.FEED)
    fun providesFeedsRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FeedsConstants.BASE_FEED_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideHeaders(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient
            .addInterceptor(interceptor)
        return httpClient.build()
    }

}