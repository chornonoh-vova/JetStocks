package com.jetstocks.data.remote

import com.jetstocks.csv.CsvParser
import com.jetstocks.model.CompanyListing
import de.siegmar.fastcsv.reader.NamedCsvReader
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import okhttp3.internal.closeQuietly
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class CompanyListingRemoteDataSource @Inject constructor(
    private val okHttpClient: OkHttpClient
) {
    private val url = "https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=demo"

    private val cacheControl by lazy {
        CacheControl.Builder().maxStale(8, TimeUnit.HOURS).build()
    }

    private val csvReaderBuilder by lazy {
        NamedCsvReader.builder()
    }

    suspend fun getCompanyListings(): List<CompanyListing> {
        val request = Request.Builder()
            .url(url)
            .cacheControl(cacheControl)
            .build()

        val response = awaitCallback(okHttpClient.newCall(request))

        return response.body!!.use { body ->
            csvReaderBuilder.build(body.charStream())
                .stream()
                .map(CsvParser::parseCompanyListing)
                .collect(Collectors.toList())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun awaitCallback(call: Call): Response =
        suspendCancellableCoroutine { continuation ->
            val callback = object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    continuation.resume(response) {
                        // If we have a response but we're cancelled while resuming, we need to
                        // close() the unused response
                        if (response.body != null) {
                            response.closeQuietly()
                        }
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }
            }

            call.enqueue(callback)

            continuation.invokeOnCancellation {
                try {
                    call.cancel()
                } catch (t: Throwable) {
                    // Ignore cancel exception
                }
            }
        }
}
