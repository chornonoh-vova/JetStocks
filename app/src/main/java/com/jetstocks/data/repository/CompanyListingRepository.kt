package com.jetstocks.data.repository

import com.jetstocks.data.model.CompanyListing
import kotlinx.coroutines.flow.Flow

interface CompanyListingRepository {
  fun getCompanyListings(): Flow<List<CompanyListing>>

  suspend fun syncCompanyListings()
}