package com.evg.settings.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.resource.custom.BackButton
import com.evg.resource.custom.CustomCard
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.ColumnSpace
import com.evg.resource.theme.HorizontalPadding
import com.evg.resource.theme.HorizontalPaddingTile
import com.evg.resource.theme.IconSize
import com.evg.resource.theme.MileOnAirTestTheme
import com.evg.resource.theme.VerticalPadding
import com.evg.settings.presentation.mvi.SettingsState
import com.evg.settings.presentation.mvi.SettingsAction

@Composable
fun SettingsScreen(
    modifier: Modifier,
    state: SettingsState,
    dispatch: (SettingsAction) -> Unit,
    onRegistrationScreen: () -> Unit,
) {
    LaunchedEffect(Unit) {
        dispatch(SettingsAction.LoadUser)
    }

    var isBiometricsEnabled by remember { mutableStateOf(true) }

    val userName = state.settingsUser?.name ?: stringResource(R.string.user_stumb)
    val userSurname = state.settingsUser?.surname ?: stringResource(R.string.user_stumb)
    val cardIconSpace = 6.dp


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = HorizontalPadding,
                vertical = VerticalPadding,
            )
            .verticalScroll(rememberScrollState()),
    ) {
        // Name
        Text(
            text = userName,
            style = AppTheme.typography.heading,
            color = AppTheme.colors.text,
        )
        Spacer(modifier = Modifier.height(HorizontalPaddingTile))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = userSurname,
                style = AppTheme.typography.heading,
                color = AppTheme.colors.text,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(R.drawable.edit),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = AppTheme.colors.text,
            )
        }

        Spacer(modifier = Modifier.height(ColumnSpace))

        // Phone
        Text(
            text = "+79243014334",
            style = AppTheme.typography.large,
            color = AppTheme.colors.textFieldTitle,
        )

        Spacer(modifier = Modifier.height(ColumnSpace))


        // Purchases
        Text(
            text = stringResource(R.string.my_purchases).uppercase(),
            style = AppTheme.typography.small,
            color = AppTheme.colors.textFieldTitle,
        )
        Spacer(modifier = Modifier.height(HorizontalPaddingTile))
        CustomCard(
            leftContent = {
                Image(
                    painter = painterResource(R.drawable.on),
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentDescription = null,
                )
            },
            rightContent = {
                Icon(
                    painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier.size(IconSize).rotate(180f),
                    tint = AppTheme.colors.text,
                )
            },
            onClick = {},
        )

        Spacer(modifier = Modifier.height(ColumnSpace))


        // Settings
        Text(
            text = stringResource(R.string.settings).uppercase(),
            style = AppTheme.typography.small,
            color = AppTheme.colors.textFieldTitle,
        )
        Spacer(modifier = Modifier.height(HorizontalPaddingTile))

        // Email
        CustomCard(
            leftContent = {
                Text(
                    text = stringResource(R.string.email),
                    color = AppTheme.colors.textFieldTitle,
                    style = AppTheme.typography.body,
                )
            },
            rightContent = {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "kursantik341@gmail.com",
                            color = AppTheme.colors.text,
                            style = AppTheme.typography.body,
                        )
                        Spacer(modifier = Modifier.padding(horizontal = cardIconSpace))
                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize).rotate(180f),
                            tint = AppTheme.colors.text,
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = stringResource(R.string.need_confirm),
                        color = AppTheme.colors.error,
                        style = AppTheme.typography.small,
                    )
                }
            },
            onClick = {},
        )
        Spacer(modifier = Modifier.height(HorizontalPaddingTile))

        // Biometrics
        CustomCard(
            leftContent = {
                Text(
                    text = stringResource(R.string.log_in_biometrics),
                    color = AppTheme.colors.textFieldTitle,
                    style = AppTheme.typography.body,
                )
            },
            rightContent = {
                Switch(
                    checked = isBiometricsEnabled,
                    onCheckedChange = { isBiometricsEnabled = it },
                    modifier = Modifier
                        .scale(0.7f)
                        .height(20.dp),
                    colors = SwitchDefaults.colors().copy(
                        checkedTrackColor = AppTheme.colors.primary
                    )
                )
            },
            onClick = {
                isBiometricsEnabled = !isBiometricsEnabled
            },
        )
        Spacer(modifier = Modifier.height(HorizontalPaddingTile))

        // Change code
        CustomCard(
            leftContent = {
                Text(
                    text = stringResource(R.string.change_code),
                    color = AppTheme.colors.textFieldTitle,
                    style = AppTheme.typography.body,
                )
            },
            rightContent = {
                Icon(
                    painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier.size(IconSize).rotate(180f),
                    tint = AppTheme.colors.text,
                )
            },
            onClick = {},
        )
        Spacer(modifier = Modifier.height(HorizontalPaddingTile))

        // Registration bank
        CustomCard(
            leftContent = {
                Text(
                    text = stringResource(R.string.registration_bank_clients),
                    color = AppTheme.colors.textFieldTitle,
                    style = AppTheme.typography.body,
                )
            },
            rightContent = {
                Icon(
                    painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier.size(IconSize).rotate(180f),
                    tint = AppTheme.colors.text,
                )
            },
            onClick = {
                onRegistrationScreen()
            },
        )
        Spacer(modifier = Modifier.height(HorizontalPaddingTile))

        // Language
        CustomCard(
            leftContent = {
                Text(
                    text = stringResource(R.string.language),
                    color = AppTheme.colors.textFieldTitle,
                    style = AppTheme.typography.body,
                )
            },
            rightContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.russian),
                        color = AppTheme.colors.text,
                        style = AppTheme.typography.body,
                    )
                    Spacer(modifier = Modifier.padding(horizontal = cardIconSpace))
                    Icon(
                        painter = painterResource(R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier.size(IconSize).rotate(180f),
                        tint = AppTheme.colors.text,
                    )
                }
            },
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    MileOnAirTestTheme {
        Surface(color = AppTheme.colors.background) {
            SettingsScreen(
                modifier = Modifier,
                state = SettingsState(),
                dispatch = {},
                onRegistrationScreen = {},
            )
        }
    }
}