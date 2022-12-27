package com.jetstocks

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.jetstocks.sync.SyncManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class JetStocksApplication : Application(), Configuration.Provider {
  @Inject
  lateinit var workerFactory: HiltWorkerFactory

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

    SyncManager.getInstance().sync()
  }
}