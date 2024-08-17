package com.kannan.calculator.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.kannan.calculator.domain.CalculatorAction
import com.kannan.calculator.domain.OperationType

data class CalculatorButtonState(
    val text: String?,
    val action: CalculatorAction,
    val highlightedLevel: HighlightedLevel,
    val content: @Composable () -> Unit = {}
)

val calculatorButtonStates = listOf(
    CalculatorButtonState(
        text = "AC",
        action = CalculatorAction.Clear,
        highlightedLevel = HighlightedLevel.HIGHLIGHTED
    ),

    CalculatorButtonState(
        text = "()",
        action = CalculatorAction.Parenthesis,
        highlightedLevel = HighlightedLevel.SEMI_HIGHLIGHTED
    ),

    CalculatorButtonState(
        text = "%",
        action = CalculatorAction.Operator(OperationType.PERCENT),
        highlightedLevel = HighlightedLevel.SEMI_HIGHLIGHTED
    ),

    CalculatorButtonState(
        text = "/",
        action = CalculatorAction.Operator(OperationType.DIVIDE),
        highlightedLevel = HighlightedLevel.SEMI_HIGHLIGHTED
    ),

    CalculatorButtonState(
        text = "7",
        action = CalculatorAction.Number(7),
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = "8",
        action = CalculatorAction.Number(8),
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = "9",
        action = CalculatorAction.Number(9),
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = "x",
        action = CalculatorAction.Operator(OperationType.MULTIPLY),
        highlightedLevel = HighlightedLevel.SEMI_HIGHLIGHTED
    ),

    CalculatorButtonState(
        text = "4",
        action = CalculatorAction.Number(4),
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = "5",
        action = CalculatorAction.Number(5),
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = "6",
        action = CalculatorAction.Number(6),
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = "-",
        action = CalculatorAction.Operator(OperationType.SUBTRACT),
        highlightedLevel = HighlightedLevel.SEMI_HIGHLIGHTED
    ),

    CalculatorButtonState(
        text = "1",
        action = CalculatorAction.Number(1),
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = "2",
        action = CalculatorAction.Number(2),
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = "3",
        action = CalculatorAction.Number(3),
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = "+",
        action = CalculatorAction.Operator(OperationType.ADD),
        highlightedLevel = HighlightedLevel.SEMI_HIGHLIGHTED
    ),

    CalculatorButtonState(
        text = "0",
        action = CalculatorAction.Number(0),
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = ".",
        action = CalculatorAction.DecimalPoint,
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = null,
        action = CalculatorAction.Delete,
        content = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = null
            )
        },
        highlightedLevel = HighlightedLevel.NEUTRAL
    ),

    CalculatorButtonState(
        text = "=",
        action = CalculatorAction.Calculate,
        highlightedLevel = HighlightedLevel.STRONGLY_HIGHLIGHTED
    ),
)

