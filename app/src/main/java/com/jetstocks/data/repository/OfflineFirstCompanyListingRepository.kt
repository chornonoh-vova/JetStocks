package com.jetstocks.data.repository

import com.jetstocks.data.local.CompanyListingLocalDataSource
import com.jetstocks.data.remote.CompanyListingRemoteDataSource
import javax.inject.Inject

class OfflineFirstCompanyListingRepository @Inject constructor(
  private val companyListingLocalDataSource: CompanyListingLocalDataSource,
  private val companyListingRemoteDataSource: CompanyListingRemoteDataSource
) : CompanyListingRepository {
  override fun getCompanyListings() = companyListingLocalDataSource.getCompanyListings()

  override suspend fun syncCompanyListings() {
    val companyListings = companyListingRemoteDataSource.getCompanyListings()

    companyListingLocalDataSource.persistCompanyListings(companyListings)
  }
}