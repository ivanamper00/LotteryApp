package com.allwin.haugiang.app.data.repository

import com.allwin.haugiang.app.data.dto.CurrentScreenModel
import com.allwin.haugiang.app.data.remote.ScreensDao
import com.allwin.haugiang.app.domain.repository.ScreensRepo
import io.reactivex.Single

class ScreensRepoImp(
    private val screensDao: ScreensDao
): ScreensRepo {

    override fun getAllScreensActivity(): Single<List<CurrentScreenModel>> = screensDao.getAllScreensActivity()

    override fun getCurrentScreen(): Single<CurrentScreenModel> = screensDao.getCurrentScreen()

    override fun saveCurrentScreen(param: CurrentScreenModel): Single<Unit> = screensDao.saveCurrentScreen(param)

}