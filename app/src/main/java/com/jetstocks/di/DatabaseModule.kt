package com.jetstocks.di

import android.content.Context
import androidx.room.Room
import com.jetstocks.database.JetStocksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesJetStocksDatabase(@ApplicationContext context: Context): JetStocksDatabase =
        Room.databaseBuilder(
            context,
            JetStocksDatabase::class.java,
            "JetStocks"
        ).build()

    @Provides
    fun providesCompanyListingDao(database: JetStocksDatabase) = database.companyListingDao()
}