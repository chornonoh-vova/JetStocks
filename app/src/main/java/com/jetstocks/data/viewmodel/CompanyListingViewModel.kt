package com.jetstocks.data.viewmodel

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetstocks.data.model.CompanyListing
import com.jetstocks.data.repository.CompanyListingRepository
import com.jetstocks.ui.CompanyListingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CompanyListingViewModel @Inject constructor(
  private val companyListingRepository: CompanyListingRepository
) : ViewModel() {
  private val companyListingsState = snapshotFlow {
    companyListingRepository.getCompanyListings()
      .map<List<CompanyListing>, CompanyListingUiState>(CompanyListingUiState::Success)
      .onStart { emit(CompanyListingUiState.Loading) }
  }

  val uiState: StateFlow<CompanyListingUiState> =
    companyListingsState
      .flatMapLatest { it }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CompanyListingUiState.Loading
      )
}