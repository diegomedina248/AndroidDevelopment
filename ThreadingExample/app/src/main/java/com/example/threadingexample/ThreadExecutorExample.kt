package com.example.threadingexample

import android.util.Log
import java.util.concurrent.*

class ThreadExecutorExample : Executor {

    private val tag = ThreadExecutorExample::class.java.canonicalName
    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        this.threadPoolExecutor = ThreadPoolExecutor(
            3, 5, 10, TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>(), JobThreadFactory()
        )
    }

    override fun execute(runnable: Runnable) {
        Log.i(tag, "execute thread:${Thread.currentThread()}")
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {

        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}