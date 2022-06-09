package com.allwin.haugiang.common.thread

import com.allwin.haugiang.common.executor.PostExecutionThread
import io.reactivex.Scheduler

class PostExecutorThread(override val scheduler: Scheduler) : PostExecutionThread