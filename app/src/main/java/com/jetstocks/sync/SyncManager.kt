package com.jetstocks.sync

import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class SyncManager private constructor(private val workManager: WorkManager) {
  fun sync() {
    workManager.enqueueUniqueWork(
      SyncWorker.WorkName,
      ExistingWorkPolicy.KEEP,
      SyncWorker.startUpSyncWork()
    )
  }

  companion object {
    private lateinit var syncManager: SyncManager

    fun initialize(workManager: WorkManager) {
      syncManager = SyncManager(workManager)
    }

    fun getInstance(): SyncManager {
      if (!(::syncManager.isInitialized)) {
        throw Exception("Sync not initialized. You might forgot to call initialize(workManager: WorkManager)")
      }
      Log.d("TAG", "getInstance:  $syncManager]")
      return syncManager
    }
  }
}