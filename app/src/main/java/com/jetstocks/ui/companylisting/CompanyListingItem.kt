package com.jetstocks.ui.companylisting

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetstocks.model.CompanyListing
import com.jetstocks.ui.theme.JetStocksTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyListingItem(item: CompanyListing, onClick: () -> Unit) {
    Surface(onClick = onClick) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f, true)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1
                )
                Text(
                    text = item.symbol,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = item.exchange,
                style = MaterialTheme.typography.bodySmall
            )

            Icon(
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = "Go to ${item.symbol}",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Preview
@Composable
fun CompanyListingItemPreview() {
    val item = CompanyListing(
        symbol = "NFLX",
        name = "Netflix Inc",
        exchange = "NASDAQ",
        assetType = "Stock",
        ipoDate = LocalDate.parse("2002-05-23"),
        delistingDate = null,
        status = "Active"
    )

    JetStocksTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colorScheme.background) {
            CompanyListingItem(item = item, onClick = {})
        }
    }
}
