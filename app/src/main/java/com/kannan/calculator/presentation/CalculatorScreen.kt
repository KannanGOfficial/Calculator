package com.kannan.calculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kannan.calculator.domain.CalculatorAction
import com.kannan.calculator.presentation.components.CalculatorButtonGrid
import com.kannan.calculator.presentation.components.CalculatorDisplay
import com.kannan.calculator.presentation.components.calculatorButtonStates
import com.kannan.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorScreen(
    uiState: CalculatorScreenUiState,
    uiAction: (CalculatorAction) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CalculatorDisplay(
                expression = uiState.expression,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 25.dp,
                            bottomEnd = 25.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(
                        vertical = 64.dp,
                        horizontal = 16.dp
                    )
            )

            Spacer(modifier = Modifier.height(8.dp))

            CalculatorButtonGrid(
                calculatorButtonStates = uiState.calculatorButtonStates,
                onUiAction = uiAction,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CalculatorScreenPreview() {
    CalculatorTheme {
        CalculatorScreen(
            uiState = CalculatorScreenUiState("3+5", calculatorButtonStates),
            uiAction = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}