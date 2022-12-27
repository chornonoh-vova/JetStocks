package com.jetstocks.csv

import com.jetstocks.model.CompanyListing
import de.siegmar.fastcsv.reader.NamedCsvRow
import java.time.LocalDate

object CsvParser {
    fun parseCompanyListing(row: NamedCsvRow): CompanyListing {
        val symbol = row.getField("symbol")
        val name = row.getField("name")
        val exchange = row.getField("exchange")
        val assetType = row.getField("assetType")
        val ipoDate = LocalDate.parse(row.getField("ipoDate"))
        val delistingDate = row.getField("delistingDate").let {
            if (it != "null") LocalDate.parse(it) else null
        }
        val status = row.getField("status")

        return CompanyListing(
            symbol = symbol,
            name = name,
            exchange = exchange,
            assetType = assetType,
            ipoDate = ipoDate,
            delistingDate = delistingDate,
            status = status
        )
    }
}