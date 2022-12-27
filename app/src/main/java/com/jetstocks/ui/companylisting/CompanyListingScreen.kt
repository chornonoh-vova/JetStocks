package com.jetstocks.ui.companylisting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

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
    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        companyListingState.companyListings.map {
            item {
                CompanyListingItem(
                    item = it,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun CompanyListingLoading() {
    Text(text = "Loading")
}