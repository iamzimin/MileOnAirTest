package com.evg.purchases.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.MileOnAirTestTheme

@Composable
fun DateHeaderItem(date: String) {
    Text(
        text = date,
        style = AppTheme.typography.body,
        color = AppTheme.colors.text,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    )
}

@Preview
@Composable
private fun DateHeaderItemPreview() {
    MileOnAirTestTheme {
        DateHeaderItem(
            date = "123123"
        )
    }
}