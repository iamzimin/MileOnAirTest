package com.evg.resource.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.MileOnAirTestTheme

enum class FieldType {
    PARTICIPANT_NUMBER,
    CODE,
    NAME,
}

@Composable
fun CustomTestField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    hint: String,
    fieldType: FieldType,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    val defaultErrorMessage = stringResource(R.string.incorrect_data)
    val displayErrorMessage = errorMessage ?: defaultErrorMessage
    
    val visualTransformation = if (fieldType == FieldType.PARTICIPANT_NUMBER) {
        participantNumberVisualTransformation()
    } else {
        VisualTransformation.None
    }
    
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (fieldType == FieldType.PARTICIPANT_NUMBER) {
                    val digitsOnly = newValue.filter { it.isDigit() }.take(16)
                    onValueChange(digitsOnly)
                } else {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    color = AppTheme.colors.textFieldPlaceholder,
                    style = AppTheme.typography.body
                )
            },
            singleLine = true,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = when (fieldType) {
                FieldType.PARTICIPANT_NUMBER, FieldType.CODE -> KeyboardOptions(keyboardType = KeyboardType.Number)
                FieldType.NAME -> KeyboardOptions(keyboardType = KeyboardType.Text)
            },
            colors = textFieldColors(isError),
            shape = RoundedCornerShape(BorderRadius),
        )

        Text(
            text = if (isError) displayErrorMessage else hint,
            color = if (isError) AppTheme.colors.error else AppTheme.colors.textFieldTitle,
            style = AppTheme.typography.small,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}

private fun participantNumberVisualTransformation(): VisualTransformation {
    return VisualTransformation { text ->
        val digits = text.text.filter { it.isDigit() }
        val formatted = digits.chunked(4).joinToString(" ")
        TransformedText(
            AnnotatedString(formatted),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val digitCount = text.text.take(offset).count { it.isDigit() }
                    return digitCount + (digitCount - 1) / 4
                }
                
                override fun transformedToOriginal(offset: Int): Int {
                    var digitCount = 0
                    for (i in formatted.indices) {
                        if (i >= offset) break
                        if (formatted[i].isDigit()) digitCount++
                    }
                    return digitCount
                }
            }
        )
    }
}

@Composable
private fun textFieldColors(isError: Boolean): TextFieldColors {
    val borderColor = if (isError) AppTheme.colors.error else Color.Transparent
    
    return OutlinedTextFieldDefaults.colors(
        focusedTextColor = AppTheme.colors.text,
        unfocusedTextColor = AppTheme.colors.text,
        disabledTextColor = AppTheme.colors.text,
        errorTextColor = AppTheme.colors.text,
        focusedContainerColor = AppTheme.colors.tileBackground,
        unfocusedContainerColor = AppTheme.colors.tileBackground,
        disabledContainerColor = AppTheme.colors.tileBackground,
        errorContainerColor = AppTheme.colors.tileBackground,
        focusedBorderColor = borderColor,
        unfocusedBorderColor = borderColor,
        disabledBorderColor = Color.Transparent,
        errorBorderColor = AppTheme.colors.error,
        cursorColor = AppTheme.colors.text,
        focusedPlaceholderColor = AppTheme.colors.textFieldPlaceholder,
        unfocusedPlaceholderColor = AppTheme.colors.textFieldPlaceholder,
        errorPlaceholderColor = AppTheme.colors.textFieldPlaceholder,
    )
}

fun validateField(value: String, fieldType: FieldType): Boolean {
    return when (fieldType) {
        FieldType.PARTICIPANT_NUMBER -> {
            value.replace(" ", "").matches(Regex("^\\d{16}$"))
        }
        FieldType.CODE -> {
            value.isNotBlank()
        }
        FieldType.NAME -> {
            value.isNotBlank() && value.matches(Regex("^[a-zA-Z\\s]*$"))
        }
    }
}

@Preview
@Composable
private fun CustomTestFieldPreview() {
    MileOnAirTestTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(AppTheme.colors.background)
        ) {
            var participantNumber by remember { mutableStateOf("1") }
            var code by remember { mutableStateOf("123") }
            var name by remember { mutableStateOf("") }
            var surname by remember { mutableStateOf("") }

            CustomTestField(
                value = participantNumber,
                onValueChange = { participantNumber = it },
                placeholder = stringResource(R.string.participant_number),
                hint = stringResource(R.string.participant_number_hint),
                fieldType = FieldType.PARTICIPANT_NUMBER,
                isError = participantNumber.isNotEmpty() && !validateField(participantNumber, FieldType.PARTICIPANT_NUMBER)
            )

            CustomTestField(
                value = code,
                onValueChange = { code = it },
                placeholder = stringResource(R.string.code),
                hint = stringResource(R.string.code_hint),
                fieldType = FieldType.CODE,
                isError = code.isNotEmpty() && !validateField(code, FieldType.CODE),
                modifier = Modifier.padding(top = 16.dp)
            )

            CustomTestField(
                value = name,
                onValueChange = { name = it },
                placeholder = stringResource(R.string.first_name),
                hint = stringResource(R.string.first_name_hint),
                fieldType = FieldType.NAME,
                isError = name.isNotEmpty() && !validateField(name, FieldType.NAME),
                modifier = Modifier.padding(top = 16.dp)
            )

            CustomTestField(
                value = surname,
                onValueChange = { surname = it },
                placeholder = stringResource(R.string.last_name),
                hint = stringResource(R.string.last_name_hint),
                fieldType = FieldType.NAME,
                isError = surname.isNotEmpty() && !validateField(surname, FieldType.NAME),
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}