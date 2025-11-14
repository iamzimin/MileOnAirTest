package com.evg.purchases.domain.repository

import com.evg.purchases.domain.model.Purchase

interface PurchasesRepository {
    suspend fun getPurchases(): Result<List<Purchase>>
}