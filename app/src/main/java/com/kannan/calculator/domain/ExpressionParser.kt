package com.kannan.calculator.domain

class ExpressionParser(private val calculationString: String) {

    fun invoke(): List<ExpressionPart> {

        val result = mutableListOf<ExpressionPart>()

        var i = 0

        while (i < calculationString.length) {

            val symbol = calculationString[i]

            when {
                symbol in parenthesisTypeSymbols -> {
                    val parenthesisType = parenthesisTypeFromSymbol(symbol)
                    val expressionPart = ExpressionPart.Parenthesis(parenthesisType)
                    result.add(expressionPart)
                }

                symbol in operationTypeSymbols -> {
                    val operationType = operationTypeFromSymbol(symbol)
                    val expressionPart = ExpressionPart.Operation(operationType)
                    result.add(expressionPart)
                }

                symbol.isDigit() -> {
                    val parseNumberResult = parseNumber(i)
                    i = parseNumberResult.indexValue
                    val expressionPart = ExpressionPart.Number(parseNumberResult.number)
                    result.add(expressionPart)
                    continue
                }
            }

            i++
        }

        return result
    }

    private fun parseNumber(startingIndex: Int): ParseNumberResult {

        var i = startingIndex

        val resultString = buildString {
            while (i < calculationString.length && calculationString[i] in numberTypeFromSymbols) {
                append(calculationString[i])
                i++
            }
        }

        return ParseNumberResult(indexValue = i, number = resultString.toDouble())

    }

    private data class ParseNumberResult(
        val indexValue: Int,
        val number: Double
    )

    private val numberTypeFromSymbols: String = "0123456789."
}