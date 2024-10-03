package com.github.juanncode.challengeapuestatotal.retrofit

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class MockInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val path = request.url.encodedPath
        val json = when (path) {
            "/bets" -> loadJsonFromAssets("betsHistory.json")
            "/detail-bets" -> loadJsonFromAssets("betsDetailHistory.json")
            else -> throw IllegalArgumentException("Unknown endpoint")
        }

        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("Mock response")
            .body(json.toResponseBody("application/json".toMediaTypeOrNull()))
            .build()
    }

    private fun loadJsonFromAssets(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}