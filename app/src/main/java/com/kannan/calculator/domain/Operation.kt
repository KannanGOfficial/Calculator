package com.kannan.calculator.domain

enum class OperationType(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x'),
    DIVIDE('/'),
    PERCENT('%')
}

fun operationTypeFromSymbol(symbol: Char): OperationType {
    return OperationType.entries.find { it.symbol == symbol }
        ?: throw IllegalArgumentException("Invalid Symbol")
}

val operationTypeSymbols = OperationType.entries.map { it.symbol }.joinToString("")