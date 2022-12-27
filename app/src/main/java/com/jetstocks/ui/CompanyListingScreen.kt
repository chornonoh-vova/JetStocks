package com.jetstocks.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetstocks.data.viewmodel.CompanyListingViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CompanyListingScreen(
  companyListingViewModel: CompanyListingViewModel = viewModel()
) {
  val companyListingState by companyListingViewModel.uiState.collectAsStateWithLifecycle()

  when (companyListingState) {
    CompanyListingUiState.Loading -> CompanyListingLoading()
    is CompanyListingUiState.Success -> CompanyListingList(companyListingState as CompanyListingUiState.Success)
  }
}

@Composable
fun CompanyListingList(companyListingState: CompanyListingUiState.Success) {
  LazyColumn(
    modifier = Modifier.fillMaxSize()
  ) {
    companyListingState.companyListings.map {
      item {
        Text(text = "${it.symbol} ${it.name}")
      }
    }
  }
}

@Composable
fun CompanyListingLoading() {
  Text(text = "Loading")
}