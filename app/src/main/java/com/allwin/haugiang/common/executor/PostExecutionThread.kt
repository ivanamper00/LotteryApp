package com.allwin.haugiang.common.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}