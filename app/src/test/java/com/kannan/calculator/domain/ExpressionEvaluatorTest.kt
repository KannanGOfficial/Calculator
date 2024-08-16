package com.kannan.calculator.domain

import org.junit.Assert
import org.junit.Test

class ExpressionEvaluatorTest {

    private lateinit var expressionEvaluator: ExpressionEvaluator
    private val delta = 0.0

    @Test
    fun `Simple expression is properly parsed`() {

        val input = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Operation(OperationType.ADD),
            ExpressionPart.Number(2.0)
        )
        expressionEvaluator = ExpressionEvaluator(input)

        val actual = expressionEvaluator.invoke()

        val expected = 5.0

        Assert.assertEquals(expected, actual, delta)
    }

    @Test
    fun `Expression with parenthesis is properly parsed`() {

        val input = listOf(
            ExpressionPart.Parenthesis(ParenthesisType.OPENING),
            ExpressionPart.Number(5.0),
            ExpressionPart.Operation(OperationType.ADD),
            ExpressionPart.Number(2.0),
            ExpressionPart.Parenthesis(ParenthesisType.CLOSING),
            ExpressionPart.Operation(OperationType.DIVIDE),
            ExpressionPart.Number(3.0)
        )

        expressionEvaluator = ExpressionEvaluator(input)

        val actual = expressionEvaluator.invoke()

        val expected = 2.3333333333333335

        Assert.assertEquals(expected, actual, delta)
    }

    @Test
    fun `DecimalNumbers are properly parsed`() {

        val input = listOf(
            ExpressionPart.Number(5.0),
            ExpressionPart.Operation(OperationType.ADD),
            ExpressionPart.Number(2.0),
            ExpressionPart.Operation(OperationType.DIVIDE),
            ExpressionPart.Number(3.0)
        )

        expressionEvaluator = ExpressionEvaluator(input)

        val actual = expressionEvaluator.invoke()

        val expected = 5.666666666666667

        Assert.assertEquals(expected, actual, delta)
    }

    @Test
    fun `Multiple digit numbers are properly parsed`() {

        val input = listOf(
            ExpressionPart.Number(58409.0),
            ExpressionPart.Operation(OperationType.SUBTRACT),
            ExpressionPart.Number(854850.0),
            ExpressionPart.Operation(OperationType.DIVIDE),
            ExpressionPart.Number(4895.0),
            ExpressionPart.Operation(OperationType.ADD),
            ExpressionPart.Number(69.09)
        )

        expressionEvaluator = ExpressionEvaluator(input)

        val actual = expressionEvaluator.invoke()

        val expected = 58303.45261491317

        Assert.assertEquals(expected, actual, delta)
    }

    @Test
    fun `Complex expressions are properly parsed`() {

        val input = listOf(
            ExpressionPart.Parenthesis(ParenthesisType.OPENING),
            ExpressionPart.Number(2308.098),
            ExpressionPart.Operation(OperationType.DIVIDE),
            ExpressionPart.Number(98794.4),
            ExpressionPart.Parenthesis(ParenthesisType.CLOSING),
            ExpressionPart.Operation(OperationType.SUBTRACT),
            ExpressionPart.Parenthesis(ParenthesisType.OPENING),
            ExpressionPart.Number(7479.0),
            ExpressionPart.Operation(OperationType.ADD),
            ExpressionPart.Number(49793.0),
            ExpressionPart.Parenthesis(ParenthesisType.CLOSING),
            ExpressionPart.Operation(OperationType.ADD),
            ExpressionPart.Number(899.0),
        )

        expressionEvaluator = ExpressionEvaluator(input)

        val actual = expressionEvaluator.invoke()

        val expected = -56372.976637360014

        Assert.assertEquals(expected, actual, delta)
    }
}