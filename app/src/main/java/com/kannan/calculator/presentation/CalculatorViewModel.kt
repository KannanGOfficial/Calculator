package com.kannan.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kannan.calculator.domain.CalculatorAction
import com.kannan.calculator.domain.ExpressionWriter
import com.kannan.calculator.presentation.components.CalculatorButtonState
import com.kannan.calculator.presentation.components.calculatorButtonStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CalculatorViewModel(
    private val expressionWriter: ExpressionWriter = ExpressionWriter()
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorScreenUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = CalculatorScreenUiState()
    )

    init {
        updateCalculatorButtonStatesUiState(
            calculatorButtonStates = calculatorButtonStates
        )
    }

    fun onUiAction(action: CalculatorAction) {
        expressionWriter.evaluate(action)
        updateExpressionUiState(expressionWriter.expression)
    }

    private fun updateExpressionUiState(expression: String): Unit = _uiState.update {
        it.copy(
            expression = expression
        )
    }

    private fun updateCalculatorButtonStatesUiState(calculatorButtonStates: List<CalculatorButtonState>): Unit =
        _uiState.update {
            it.copy(
                calculatorButtonStates = calculatorButtonStates
            )
        }

}

data class CalculatorScreenUiState(
    val expression: String = "",
    val calculatorButtonStates: List<CalculatorButtonState> = emptyList()
)