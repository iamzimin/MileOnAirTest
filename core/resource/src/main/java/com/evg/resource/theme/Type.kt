package com.evg.resource.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class AppTypography(
    val heading: TextStyle,
    val large: TextStyle,
    val body: TextStyle,
    val small: TextStyle,
)

val textSize = AppTypography(
    heading = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
    ),
    large = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
    ),
    body = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    small = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    ),
)