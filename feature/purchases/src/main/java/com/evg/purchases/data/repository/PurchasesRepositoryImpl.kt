package com.evg.purchases.data.repository

import com.evg.purchases.data.model.PurchasesResponse
import com.evg.purchases.domain.mapper.toPurchase
import com.evg.purchases.domain.model.Purchase
import com.evg.purchases.domain.repository.PurchasesRepository
import kotlinx.serialization.json.Json

class PurchasesRepositoryImpl(

) : PurchasesRepository {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val staticJsonData = """
        {
           "data":[
              {
                 "date":"2022-09-10T21:55:33Z",
                 "name":[
                    "123",
                    "321"
                 ]
              },
              {
                 "date":"2022-09-10T21:50:33Z",
                 "name":[
                    "1234",
                    "4321"
                 ]
              },
              {
                 "date":"2022-09-08T01:55:33Z",
                 "name":[
                    "12345",
                    "54321"
                 ]
              },
              {
                 "date":"2022-09-07T21:55:33Z",
                 "name":[
                    "123456",
                    "654321"
                 ]
              },
              {
                 "date":"2022-09-07T11:55:33Z",
                 "name":[
                    "1234567",
                    "7654321"
                 ]
              }
           ]
        }
    """.trimIndent()

    override suspend fun getPurchases(): Result<List<Purchase>> {
        return try {
            val response = json.decodeFromString<PurchasesResponse>(staticJsonData)
            Result.success(response.data.map { it.toPurchase() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}