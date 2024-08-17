package com.kannan.calculator.domain

sealed interface CalculatorAction {
    data object Clear : CalculatorAction
    data object Parenthesis : CalculatorAction
    data object Delete : CalculatorAction
    data object Calculate : CalculatorAction
    data object DecimalPoint : CalculatorAction
    data class Number(val number : Int) : CalculatorAction
    data class Operator(val operatorType: OperationType) : CalculatorAction
}