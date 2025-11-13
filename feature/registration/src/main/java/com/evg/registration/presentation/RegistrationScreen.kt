package com.evg.registration.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.registration.domain.model.RegistrationUser
import com.evg.registration.presentation.mvi.RegistrationState
import com.evg.registration.presentation.mvi.RegistrationAction
import com.evg.resource.R
import com.evg.resource.custom.CustomTestField
import com.evg.resource.custom.FieldType
import com.evg.resource.custom.validateField
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.ColumnSpace
import com.evg.resource.theme.HorizontalPadding
import com.evg.resource.theme.MileOnAirTestTheme
import com.evg.resource.theme.VerticalPadding
import kotlin.text.isNotEmpty

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    state: RegistrationState,
    dispatch: (RegistrationAction) -> Unit,
    onPreviousScreen: () -> Unit,
) {
    var participantNumber by rememberSaveable { mutableStateOf(state.registrationUser?.participantNumber ?: "") }
    var code by rememberSaveable { mutableStateOf(state.registrationUser?.code ?: "") }
    var name by rememberSaveable { mutableStateOf(state.registrationUser?.name ?: "") }
    var surname by rememberSaveable { mutableStateOf(state.registrationUser?.surname ?: "") }

    LaunchedEffect(state.registrationUser) {
        state.registrationUser?.let { user ->
            participantNumber = user.participantNumber
            code = user.code
            name = user.name
            surname = user.surname
        }
    }

    val isParticipantNumberValid = validateField(participantNumber, FieldType.PARTICIPANT_NUMBER)
    val isCodeValid = validateField(code, FieldType.CODE)
    val isNameValid = validateField(name, FieldType.NAME)
    val isSurnameValid = validateField(surname, FieldType.NAME)
    
    val isFormValid = isParticipantNumberValid && isCodeValid && isNameValid && isSurnameValid

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = HorizontalPadding,
                vertical = VerticalPadding,
            )
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = stringResource(R.string.registration_bank_clients),
            style = AppTheme.typography.heading.copy(
                fontWeight = FontWeight.SemiBold,
            ),
            color = AppTheme.colors.text,
        )
        Spacer(modifier = Modifier.height(ColumnSpace))

        CustomTestField(
            value = participantNumber,
            onValueChange = { participantNumber = it },
            placeholder = stringResource(R.string.participant_number),
            hint = stringResource(R.string.participant_number_hint),
            fieldType = FieldType.PARTICIPANT_NUMBER,
            isError = participantNumber.isNotEmpty() && !isParticipantNumberValid
        )
        Spacer(modifier = Modifier.height(ColumnSpace))

        CustomTestField(
            value = code,
            onValueChange = { code = it },
            placeholder = stringResource(R.string.code),
            hint = stringResource(R.string.code_hint),
            fieldType = FieldType.CODE,
            isError = code.isNotEmpty() && !isCodeValid,
        )
        Spacer(modifier = Modifier.height(ColumnSpace))

        CustomTestField(
            value = name,
            onValueChange = { name = it },
            placeholder = stringResource(R.string.first_name),
            hint = stringResource(R.string.first_name_hint),
            fieldType = FieldType.NAME,
            isError = name.isNotEmpty() && !isNameValid,
        )
        Spacer(modifier = Modifier.height(ColumnSpace))

        CustomTestField(
            value = surname,
            onValueChange = { surname = it },
            placeholder = stringResource(R.string.last_name),
            hint = stringResource(R.string.last_name_hint),
            fieldType = FieldType.NAME,
            isError = surname.isNotEmpty() && !isSurnameValid,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp),
            text = buildAnnotatedString {
                append(stringResource(R.string.agreement_text1))
                append(" ")
                val part2 = stringResource(R.string.agreement_text2)
                withStyle(
                    style = SpanStyle(
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(part2)
                }
            },
            style = AppTheme.typography.body,
            color = AppTheme.colors.text,
            textAlign = TextAlign.Center,
        )
        
        Spacer(modifier = Modifier.height(ColumnSpace))

        Button(
            onClick = {
                dispatch(
                    RegistrationAction.SaveUser(
                        user = RegistrationUser(
                            name = name,
                            surname = surname,
                            participantNumber = participantNumber,
                            code = code,
                        )
                    )
                )
                onPreviousScreen()
            },
            enabled = isFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(BorderRadius),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colors.primary,
                disabledContainerColor = AppTheme.colors.error.copy(
                    alpha = 0.7f
                ),
                contentColor = AppTheme.colors.text,
                disabledContentColor = AppTheme.colors.text,
            ),
        ) {
            Text(
                text = stringResource(R.string.continue_button),
                style = AppTheme.typography.body.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
        }
        Spacer(modifier = Modifier.height(ColumnSpace))
    }
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    MileOnAirTestTheme {
        Surface(color = AppTheme.colors.background) {
            RegistrationScreen(
                state = RegistrationState(),
                dispatch = {},
                onPreviousScreen = {},
            )
        }
    }
}