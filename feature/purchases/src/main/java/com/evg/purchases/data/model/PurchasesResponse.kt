package com.evg.purchases.data.model

import com.evg.purchases.domain.model.Purchase
import kotlinx.serialization.Serializable

@Serializable
data class PurchasesResponse(
    val data: List<PurchaseItem>
)

@Serializable
data class PurchaseItem(
    val date: String,
    val name: List<String>
)
