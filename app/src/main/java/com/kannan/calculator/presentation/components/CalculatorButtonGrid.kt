package com.kannan.calculator.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kannan.calculator.domain.CalculatorAction
import com.kannan.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorButtonGrid(
    calculatorButtonStates: List<CalculatorButtonState>,
    modifier: Modifier = Modifier,
    onUiAction: (CalculatorAction) -> Unit
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(4),
        userScrollEnabled = false,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(calculatorButtonStates) { buttonState ->
            CalculatorButton(
                calculatorButtonState = buttonState,
                modifier = Modifier.aspectRatio(1f),
                onClick = { onUiAction.invoke(buttonState.action) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonGridPreview() {
    CalculatorTheme {
        CalculatorButtonGrid(
            calculatorButtonStates = calculatorButtonStates,
            modifier = Modifier.fillMaxSize(),
            onUiAction = {}
        )
    }
}