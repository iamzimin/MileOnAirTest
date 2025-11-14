package com.evg.purchases.domain.mapper

import com.evg.purchases.data.model.PurchaseItem
import com.evg.purchases.domain.model.Purchase

fun PurchaseItem.toPurchase(): Purchase {
    return Purchase(
        date = date,
        name = name
    )
}
