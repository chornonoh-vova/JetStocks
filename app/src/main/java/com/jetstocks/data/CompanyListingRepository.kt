package com.jetstocks.data

import com.jetstocks.model.CompanyListing
import kotlinx.coroutines.flow.Flow

interface CompanyListingRepository {
    fun getCompanyListings(): Flow<List<CompanyListing>>

    suspend fun syncCompanyListings()
}