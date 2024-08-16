package com.kannan.calculator.domain

sealed interface ExpressionPart {
    data class Number(val number: Double) : ExpressionPart
    data class Operation(val operationType: OperationType) : ExpressionPart
    data class Parenthesis(val parenthesisType: ParenthesisType) : ExpressionPart
}