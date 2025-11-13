package com.evg.resource.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun MileOnAirTestTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppColors provides purpleDarkPalette,
        LocalAppTypography provides textSize,
        content = content,
    )
}