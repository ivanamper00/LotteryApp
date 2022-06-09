package com.allwin.haugiang.app.data.remote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.allwin.haugiang.app.data.dto.CurrentScreenModel
import io.reactivex.Single


@Dao
interface ScreensDao {

    @Query("SELECT * FROM ${AppDatabase.SCREENS_TABLE}")
    fun getAllScreensActivity(): Single<List<CurrentScreenModel>>

    @Query("SELECT * FROM ${AppDatabase.SCREENS_TABLE} ORDER BY `id` DESC LIMIT 1")
    fun getCurrentScreen(): Single<CurrentScreenModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurrentScreen(param: CurrentScreenModel): Single<Unit>

}