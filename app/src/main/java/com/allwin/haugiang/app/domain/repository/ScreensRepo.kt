package com.allwin.haugiang.app.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.allwin.haugiang.app.data.dto.CurrentScreenModel
import com.allwin.haugiang.app.data.remote.AppDatabase
import io.reactivex.Single

interface ScreensRepo {

    fun getAllScreensActivity(): Single<List<CurrentScreenModel>>

    fun getCurrentScreen(): Single<CurrentScreenModel>

    fun saveCurrentScreen(param: CurrentScreenModel): Single<Unit>

}