package com.evg.resource.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.resource.extensions.clickableRipple
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.MileOnAirTestTheme

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 2.dp,
                color = AppTheme.colors.secondary,
                shape = RoundedCornerShape(24.dp),
            )
            .background(AppTheme.colors.buttonColor)
            .clickableRipple(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 12.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = AppTheme.colors.text,
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(R.string.back),
                color = Color.White,
                style = AppTheme.typography.body,
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    MileOnAirTestTheme {
        Surface(color = AppTheme.colors.background) {
            BackButton(
                onClick = {}
            )
        }
    }
}