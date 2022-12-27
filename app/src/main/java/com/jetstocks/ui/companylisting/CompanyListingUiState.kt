package com.jetstocks.ui.companylisting

import com.jetstocks.model.CompanyListing

sealed interface CompanyListingUiState {
    object Loading : CompanyListingUiState
    data class Success(val companyListings: List<CompanyListing>) : CompanyListingUiState
}