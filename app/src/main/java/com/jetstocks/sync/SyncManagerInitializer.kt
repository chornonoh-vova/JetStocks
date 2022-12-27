package com.jetstocks.sync

import android.content.Context
import androidx.startup.Initializer
import androidx.work.WorkManager
import com.jetstocks.work.WorkManagerInitializer

class SyncManagerInitializer: Initializer<SyncManager> {
  override fun create(context: Context): SyncManager {
    val workManager = WorkManager.getInstance(context)
    SyncManager.initialize(workManager)
    return SyncManager.getInstance()
  }

  override fun dependencies(): List<Class<out Initializer<*>>> {
    return listOf(WorkManagerInitializer::class.java)
  }
}