package com.kannan.calculator.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kannan.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorButton(
    calculatorButtonState: CalculatorButtonState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                when (calculatorButtonState.highlightedLevel) {
                    HighlightedLevel.NEUTRAL -> MaterialTheme.colorScheme.surfaceVariant
                    HighlightedLevel.SEMI_HIGHLIGHTED -> MaterialTheme.colorScheme.inverseSurface
                    HighlightedLevel.HIGHLIGHTED -> MaterialTheme.colorScheme.tertiary
                    HighlightedLevel.STRONGLY_HIGHLIGHTED -> MaterialTheme.colorScheme.primary
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (calculatorButtonState.text != null) {
            Text(
                text = calculatorButtonState.text,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                color = when (calculatorButtonState.highlightedLevel) {
                    HighlightedLevel.NEUTRAL -> MaterialTheme.colorScheme.onSurfaceVariant
                    HighlightedLevel.SEMI_HIGHLIGHTED -> MaterialTheme.colorScheme.inverseOnSurface
                    HighlightedLevel.HIGHLIGHTED -> MaterialTheme.colorScheme.onTertiary
                    HighlightedLevel.STRONGLY_HIGHLIGHTED -> MaterialTheme.colorScheme.onPrimary
                }
            )
        } else {
            calculatorButtonState.content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonPreview() {
    CalculatorTheme {
        CalculatorButton(
            calculatorButtonState = calculatorButtonStates[0],
            modifier = Modifier.size(200.dp),
            onClick = {}
        )
    }
}