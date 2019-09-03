package com.example.threadingexample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.canonicalName

    private val executor = ThreadExecutorExample()

    private lateinit var listener: CustomListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listener = (object : CustomListener {
            override fun example(result: String) {
                Log.i(tag, "CustomListener example: $result thread:${Thread.currentThread()}")
                resultText.text = result
            }
        })
    }

    fun onAsyncTaskFinished(result: String) {
        Log.i(tag, "onPostExecute result: $result thread:${Thread.currentThread()}")
        resultText.text = result
//        listener.example(result)
    }

    fun onAsyncTaskPressed(view: View) {
        AsyncTaskExample(this).execute()
    }

    fun onExecutorPressed(view: View) {
        executor.execute {
            Thread.sleep(2000).apply {  }
            val threadName = Thread.currentThread()
            Log.i(tag, "Runnable thread:$threadName")
//            executorResultText.text = "RunnableFinished" // Mal

            executorResultText.post {
                Log.i(tag, "Runnable post thread:${Thread.currentThread()}")
                executorResultText.text = "RunnableFinished $threadName" // Bien
            }
        }
    }
}

interface CustomListener {

    fun example(result: String)
}