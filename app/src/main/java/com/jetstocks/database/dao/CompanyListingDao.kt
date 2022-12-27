package com.jetstocks.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jetstocks.database.entity.CompanyListingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyListingDao {
    @Query("SELECT * FROM company_listing ORDER BY symbol ASC")
    fun getCompanyListings(): Flow<List<CompanyListingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(entity: CompanyListingEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<CompanyListingEntity>): List<Long>
}