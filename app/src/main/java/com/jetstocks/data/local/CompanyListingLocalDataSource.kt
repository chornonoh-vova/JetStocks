package com.jetstocks.data.local

import com.jetstocks.database.dao.CompanyListingDao
import com.jetstocks.database.entity.CompanyListingEntity
import com.jetstocks.database.entity.asEntity
import com.jetstocks.database.entity.asExternalModel
import com.jetstocks.model.CompanyListing
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CompanyListingLocalDataSource @Inject constructor(
    private val companyListingDao: CompanyListingDao
) {
    fun getCompanyListings() = companyListingDao.getCompanyListings().map {
        it.map(CompanyListingEntity::asExternalModel)
    }

    suspend fun persistCompanyListings(companyListings: List<CompanyListing>) =
        companyListingDao.insertAll(companyListings.map(CompanyListing::asEntity))
}