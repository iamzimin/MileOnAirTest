package com.evg.purchases.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.purchases.domain.model.Purchase
import com.evg.purchases.presentation.model.PurchaseGroup
import com.evg.purchases.presentation.mvi.PurchasesState
import com.evg.purchases.presentation.mvi.PurchasesAction
import com.evg.resource.R
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.ColumnSpace
import com.evg.resource.theme.HorizontalPadding
import com.evg.resource.theme.MileOnAirTestTheme
import com.evg.resource.theme.VerticalPadding

@Composable
fun PurchasesScreen(
    modifier: Modifier = Modifier,
    state: PurchasesState,
    dispatch: (PurchasesAction) -> Unit,
) {
    if (state.isLoading) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    horizontal = HorizontalPadding,
                    vertical = VerticalPadding,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.loading),
                style = AppTheme.typography.body,
                color = AppTheme.colors.text,
            )
            Spacer(modifier = Modifier.height(ColumnSpace))
            CircularProgressIndicator(
                color = AppTheme.colors.primary,
            )
        }
    } else if (state.error != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    horizontal = HorizontalPadding,
                    vertical = VerticalPadding,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.error, state.error),
                style = AppTheme.typography.body,
                color = AppTheme.colors.text,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(ColumnSpace))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = {
                    dispatch(PurchasesAction.LoadPurchases)
                },
                shape = RoundedCornerShape(BorderRadius),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colors.primary,
                    contentColor = AppTheme.colors.text,
                ),
            ) {
                Text(
                    text = stringResource(R.string.refresh),
                    style = AppTheme.typography.body.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    horizontal = HorizontalPadding,
                    vertical = VerticalPadding,
                ),
        ) {
            items(
                count = state.purchaseGroups.size,
                key = { index ->
                    when (val item = state.purchaseGroups[index]) {
                        is PurchaseGroup.DateHeader -> item.date
                        is PurchaseGroup.PurchaseItem -> "${item.purchase.date}_${item.purchase.name.joinToString()}"
                    }
                }
            ) { index ->
                val item = state.purchaseGroups[index]
                when (item) {
                    is PurchaseGroup.DateHeader -> {
                        DateHeaderItem(date = item.date)
                    }
                    is PurchaseGroup.PurchaseItem -> {
                        PurchaseItem(names = item.purchase.name)
                    }
                }

                if (index < state.purchaseGroups.size - 1) {
                    val nextItem = state.purchaseGroups[index + 1]
                    if (item is PurchaseGroup.PurchaseItem && nextItem is PurchaseGroup.DateHeader) {
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            color = AppTheme.colors.text.copy(alpha = 0.4f),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PurchasesScreenPreview() {
    MileOnAirTestTheme {
        Surface(color = AppTheme.colors.background) {
            PurchasesScreen(
                state = PurchasesState(
                    error = "123"
                ),
                dispatch = {},
            )
        }
    }
}