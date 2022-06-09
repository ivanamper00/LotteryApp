package com.allwin.haugiang.common.executor

import io.reactivex.Scheduler

interface ExecutionThread {
    val scheduler: Scheduler
}