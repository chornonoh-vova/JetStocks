package com.jetstocks

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.jetstocks.sync.SyncWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class JetStocksApp : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    private val applicationScope = MainScope()

    override fun getWorkManagerConfiguration(): Configuration {
        var builder = Configuration.Builder()

        builder = if (BuildConfig.DEBUG) {
            builder.setMinimumLoggingLevel(Log.DEBUG)
        } else {
            builder.setMinimumLoggingLevel(Log.WARN)
        }

        return builder
            .setWorkerFactory(workerFactory)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            WorkManager.getInstance(this@JetStocksApp.applicationContext).enqueueUniqueWork(
                SyncWorker.WorkName,
                ExistingWorkPolicy.KEEP,
                SyncWorker.startUpSyncWork()
            )
        }
    }
}