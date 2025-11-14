package com.evg.purchases.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.evg.purchases.presentation.mvi.PurchasesViewModel
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun PurchasesRoot(
    modifier: Modifier,
    viewModel: PurchasesViewModel = koinViewModel(),
) {
    PurchasesScreen(
        modifier = modifier,
        state = viewModel.collectAsState().value,
        dispatch = viewModel::dispatch,
    )
}