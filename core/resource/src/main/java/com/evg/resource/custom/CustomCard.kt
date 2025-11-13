package com.evg.resource.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.resource.extensions.clickableRipple
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.MileOnAirTestTheme

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    leftContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(BorderRadius))
            .background(AppTheme.colors.tileBackground)
            .then(
                if (onClick != null) {
                    Modifier.clickableRipple(
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            )
            .padding(horizontal = 16.dp, vertical = 20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
        ) {
            Box(
                modifier = Modifier.weight(1f),
            ) {
                leftContent()
            }
            Spacer(modifier = Modifier.padding(horizontal = 12.dp))
            Box(
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                rightContent()
            }
        }
    }
}

@Preview
@Composable
private fun CustomCardPreview() {
    MileOnAirTestTheme {
        Surface(color = AppTheme.colors.background) {
            CustomCard(
                leftContent = {
                    Text(
                        text = "E-mail",
                        color = AppTheme.colors.text,
                        style = AppTheme.typography.body,
                    )
                },
                rightContent = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "kursantik341@gmail.com",
                            color = AppTheme.colors.text,
                            style = AppTheme.typography.body,
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = AppTheme.colors.text,
                        )
                    }
                },
                onClick = {},
            )
        }
    }
}
