package com.jetstocks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jetstocks.database.dao.CompanyListingDao
import com.jetstocks.database.entity.CompanyListingEntity

@Database(entities = [CompanyListingEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class JetStocksDatabase : RoomDatabase() {
  abstract fun companyListingDao(): CompanyListingDao
}