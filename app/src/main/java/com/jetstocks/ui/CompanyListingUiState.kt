package com.jetstocks.ui

import com.jetstocks.data.model.CompanyListing

sealed interface CompanyListingUiState {
  object Loading : CompanyListingUiState
  data class Success(val companyListings: List<CompanyListing>) : CompanyListingUiState
}