package com.jetstocks.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.jetstocks.BuildConfig
import com.jetstocks.data.CompanyListingRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val companyListingRepository: CompanyListingRepository
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            companyListingRepository.syncCompanyListings()
        } catch (exception: Exception) {
            Result.retry()
        }

        Result.success()
    }

    companion object {
        const val WorkName = "${BuildConfig.APPLICATION_ID}.SyncWork"

        private val SyncConstraints
            get() = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        fun startUpSyncWork() =
            OneTimeWorkRequestBuilder<SyncWorker>()
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .setConstraints(SyncConstraints)
                .setInputData(Data.Builder().build())
                .build()
    }
}