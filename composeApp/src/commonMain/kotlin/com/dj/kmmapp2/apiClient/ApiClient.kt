package com.dj.kmmapp2.apiClient

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }
}