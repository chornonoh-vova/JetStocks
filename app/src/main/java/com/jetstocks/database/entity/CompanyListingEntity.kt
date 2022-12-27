package com.jetstocks.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jetstocks.data.model.CompanyListing
import java.time.LocalDate

@Entity(tableName = "company_listing")
data class CompanyListingEntity(
  @PrimaryKey val symbol: String,
  val name: String,
  val exchange: String,
  @ColumnInfo(name = "asset_type") val assetType: String,
  @ColumnInfo(name = "ipo_date") val ipoDate: LocalDate,
  @ColumnInfo(name = "delisting_date") val delistingDate: LocalDate?,
  val status: String
)

fun CompanyListing.asEntity() = CompanyListingEntity(
  symbol = symbol,
  name = name,
  exchange = exchange,
  assetType = assetType,
  ipoDate = ipoDate,
  delistingDate = delistingDate,
  status = status
)

fun CompanyListingEntity.asExternalModel() = CompanyListing(
  symbol = symbol,
  name = name,
  exchange = exchange,
  assetType = assetType,
  ipoDate = ipoDate,
  delistingDate = delistingDate,
  status = status
)