package com.evg.purchases.presentation.mapper

import com.evg.purchases.domain.model.Purchase
import com.evg.purchases.presentation.model.PurchaseGroup
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun List<Purchase>.groupByDate(): List<PurchaseGroup> {
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault())

    val grouped = this
        .groupBy { purchase ->
            val instant = Instant.parse(purchase.date)
            val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
            localDate
        }
        .map { (date, purchases) ->
            date.format(dateFormatter) to purchases.sortedByDescending { Instant.parse(it.date) }
        }
        .sortedByDescending { (dateString, _) ->
            val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault())
            LocalDate.parse(dateString, dateFormatter)
        }

    return buildList {
        grouped.forEach { (date, purchases) ->
            add(PurchaseGroup.DateHeader(date))
            purchases.forEach { purchase ->
                add(PurchaseGroup.PurchaseItem(purchase))
            }
        }
    }
}
