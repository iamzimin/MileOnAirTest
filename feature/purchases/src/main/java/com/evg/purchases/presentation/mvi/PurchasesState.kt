package com.evg.purchases.presentation.mvi

import com.evg.purchases.presentation.model.PurchaseGroup

data class PurchasesState(
    val purchaseGroups: List<PurchaseGroup> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)