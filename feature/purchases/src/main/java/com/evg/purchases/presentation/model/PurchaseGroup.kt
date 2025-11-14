package com.evg.purchases.presentation.model

import com.evg.purchases.domain.model.Purchase

sealed class PurchaseGroup {
    data class DateHeader(val date: String) : PurchaseGroup()
    data class PurchaseItem(val purchase: Purchase) : PurchaseGroup()
}