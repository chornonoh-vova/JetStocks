package com.jetstocks.di

import com.jetstocks.data.CompanyListingRepository
import com.jetstocks.data.OfflineFirstCompanyListingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsCompanyListingRepository(
        companyListingRepository: OfflineFirstCompanyListingRepository
    ): CompanyListingRepository
}