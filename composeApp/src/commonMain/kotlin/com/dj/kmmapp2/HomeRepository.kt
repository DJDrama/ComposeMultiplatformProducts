package com.dj.kmmapp2

import com.dj.kmmapp2.apiClient.httpClient
import com.dj.kmmapp2.data.Product
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class HomeRepository {

    private suspend fun getProductsApi(): List<Product> {
        val response = httpClient.get("https://fakestoreapi.com/products")
        return response.body()
    }

    fun getProducts() = flow {
        emit(getProductsApi())
    }
}