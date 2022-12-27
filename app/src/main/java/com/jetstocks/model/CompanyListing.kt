package com.jetstocks.model

import java.time.LocalDate

data class CompanyListing(
    val symbol: String,
    val name: String,
    val exchange: String,
    val assetType: String,
    val ipoDate: LocalDate,
    val delistingDate: LocalDate?,
    val status: String
)
