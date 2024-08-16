package com.kannan.calculator.domain

import org.junit.Assert
import org.junit.Test

class ExpressionParserTest {

    private lateinit var expressionParser: ExpressionParser

    @Test
    fun `Simple expression is properly parsed`() {

        //Given
        expressionParser = ExpressionParser("3+2")

        //Do something
        val actual = expressionParser.invoke()

        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Operation(OperationType.ADD),
            ExpressionPart.Number(2.0)
        )

        //Assert Expected == Actual
        Assert.assertEquals(actual, expected)

    }

    @Test
    fun `Expression with parenthesis is properly parsed`() {

        expressionParser = ExpressionParser("(5+2)/3")

        val actual = expressionParser.invoke()

        val expected = listOf(
            ExpressionPart.Parenthesis(ParenthesisType.OPENING),
            ExpressionPart.Number(5.0),
            ExpressionPart.Operation(OperationType.ADD),
            ExpressionPart.Number(2.0),
            ExpressionPart.Parenthesis(ParenthesisType.CLOSING),
            ExpressionPart.Operation(OperationType.DIVIDE),
            ExpressionPart.Number(3.0)
        )

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `DecimalNumbers are properly parsed`() {

        expressionParser = ExpressionParser("5.0+2/3")

        val actual = expressionParser.invoke()

        val expected = listOf(
            ExpressionPart.Number(5.0),
            ExpressionPart.Operation(OperationType.ADD),
            ExpressionPart.Number(2.0),
            ExpressionPart.Operation(OperationType.DIVIDE),
            ExpressionPart.Number(3.0)
        )

        Assert.assertEquals(actual, expected)

    }

    @Test
    fun `Multiple digit numbers are properly parsed`() {

        expressionParser = ExpressionParser("58409-854850/4895+69.09")

        val actual = expressionParser.invoke()

        val expected = listOf(
            ExpressionPart.Number(58409.0),
            ExpressionPart.Operation(OperationType.SUBTRACT),
            ExpressionPart.Number(854850.0),
            ExpressionPart.Operation(OperationType.DIVIDE),
            ExpressionPart.Number(4895.0),
            ExpressionPart.Operation(OperationType.ADD),
            ExpressionPart.Number(69.09)
        )

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `Complex expressions are properly parsed`() {

        expressionParser = ExpressionParser("(2308.098/98794.4)-(7479+49793)+899")

        val actual = expressionParser.invoke()

        val expected = listOf(
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

        Assert.assertEquals(actual, expected)
    }
}