package com.allwin.haugiang.common.thread

import com.allwin.haugiang.common.executor.ExecutionThread
import io.reactivex.Scheduler

class ExecutorThread(override val scheduler: Scheduler) : ExecutionThread