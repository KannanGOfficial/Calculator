package com.kannan.calculator.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kannan.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorDisplay(
    expression: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        BasicTextField(
            value = expression,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 80.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.End
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun CalculatorDisplayPreview() {
    CalculatorTheme {
        CalculatorDisplay(
            expression = "5+4",
            modifier = Modifier.size(400.dp)
        )
    }
}