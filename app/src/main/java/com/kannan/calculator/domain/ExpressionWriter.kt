package com.kannan.calculator.domain

class ExpressionWriter {

    var expression: String = ""

    fun evaluate(calculatorAction: CalculatorAction) {
        when (calculatorAction) {

            CalculatorAction.Clear -> expression = ""

            CalculatorAction.Delete -> expression = expression.dropLast(1)

            is CalculatorAction.Number -> expression += calculatorAction.number

            CalculatorAction.Calculate -> {
                val parser = ExpressionParser(prepareForCalculation())
                val evaluator = ExpressionEvaluator(parser.invoke())
                expression = try {
                    evaluator.invoke().toString()
                } catch (e: RuntimeException) {
                    FORMAT_ERROR
                }
            }

            CalculatorAction.DecimalPoint -> {
                if (canEnterDecimalPoint()) {
                    expression += "."
                }
            }

            is CalculatorAction.Operator -> {
                if (canEnterOperator(calculatorAction.operatorType)) {
                    expression += calculatorAction.operatorType.symbol
                }
            }

            CalculatorAction.Parenthesis -> processParenthesis()
        }
    }

    private fun canEnterOperator(operator: OperationType): Boolean =
        when (operator) {
            OperationType.ADD, OperationType.SUBTRACT -> {
                expression.isEmpty() || expression.last() in "0123456789()"
            }

            else -> expression.isNotEmpty() && expression.last() in "0123456789)"
        }

    private fun canEnterDecimalPoint(): Boolean {
        if (expression.isEmpty() || expression.last() in "$operationTypeSymbols.()")
            return false
        return !expression.takeWhile {
            it in "0123456789."
        }.contains(".")
    }

    private fun processParenthesis() {
        val openingCount = expression.count { it == '(' }
        val closingCount = expression.count { it == ')' }

        expression += when {
            expression.isEmpty() || expression.last() in "$operationTypeSymbols(" -> "("
            expression.last() in "0123456789)" && openingCount == closingCount -> return
            else -> ")"
        }
    }

    private fun prepareForCalculation(): String {
        val newExpression = expression.takeWhile {
            it in "$operationTypeSymbols(."
        }

        return newExpression
    }

    companion object {
        const val FORMAT_ERROR = "Format Error"
    }
}