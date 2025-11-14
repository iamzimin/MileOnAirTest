package com.evg.purchases.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.purchases.domain.model.Purchase
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.MileOnAirTestTheme

@Composable
fun PurchaseItem(names: List<String>) {
    names.forEach { nameItem ->
        Text(
            text = nameItem,
            style = AppTheme.typography.body,
            color = AppTheme.colors.text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        )
    }
}

@Preview
@Composable
private fun PurchaseItemPreview() {
    MileOnAirTestTheme {
        PurchaseItem(
            names = listOf("123", "456")
        )
    }
}