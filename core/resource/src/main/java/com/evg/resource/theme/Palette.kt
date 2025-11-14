package com.evg.resource.theme

import androidx.compose.ui.graphics.Color

data class AppPalette(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val error: Color,

    val text: Color,

    // TextField
    val textFieldPlaceholder: Color,
    val textFieldTitle: Color,
    val tileBackground: Color,

    // Button
    val buttonColor: Color,

    // Navigation
    val windowControllerBackground: Color,
)
