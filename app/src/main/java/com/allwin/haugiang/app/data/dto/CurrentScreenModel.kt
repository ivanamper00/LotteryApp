package com.allwin.haugiang.app.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.allwin.haugiang.app.data.remote.AppDatabase

@Entity(tableName = AppDatabase.SCREENS_TABLE)
data class CurrentScreenModel(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val currentScreen: Int,
    val screenParams: List<ScreenParams>
)
