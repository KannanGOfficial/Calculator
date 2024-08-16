package com.kannan.calculator.domain

enum class ParenthesisType(val symbol : Char) {
    OPENING('('),
    CLOSING(')')
}

val parenthesisTypeSymbols = ParenthesisType.entries.map { it.symbol }.joinToString("")

fun parenthesisTypeFromSymbol(symbol: Char): ParenthesisType {
    return ParenthesisType.entries.find { it.symbol == symbol }
        ?: throw IllegalArgumentException("Invalid Symbol")
}