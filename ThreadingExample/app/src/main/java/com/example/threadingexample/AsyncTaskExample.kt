package com.example.threadingexample

import android.content.Context
import android.os.AsyncTask
import android.util.Log

class AsyncTaskExample(private val context: Context) :
    AsyncTask<Void, Void, String>() {

    private val tag = AsyncTaskExample::class.java.canonicalName

    override fun onPreExecute() {
        super.onPreExecute()
        Log.i(tag, "onPreExecute thread:${Thread.currentThread()}")
    }

    override fun doInBackground(vararg params: Void?): String {
        Log.i(tag, "doInBackground thread:${Thread.currentThread()}")
        Thread.sleep(30000)
        return "Custom result"
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        Log.i(tag, "onPostExecute thread:${Thread.currentThread()}")
        if (context is MainActivity) {
            context.onAsyncTaskFinished(result)
        }
    }
}