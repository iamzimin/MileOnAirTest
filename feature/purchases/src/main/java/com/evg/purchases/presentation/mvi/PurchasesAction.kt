package com.evg.purchases.presentation.mvi

sealed class PurchasesAction {
    data object LoadPurchases : PurchasesAction()
}