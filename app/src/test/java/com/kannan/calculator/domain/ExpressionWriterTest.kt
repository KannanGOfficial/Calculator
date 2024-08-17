package com.kannan.calculator.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ExpressionWriterTest {
    private lateinit var expressionWriter: ExpressionWriter

    @Before
    fun setup() {
        expressionWriter = ExpressionWriter()
    }

    @Test
    fun `Initial Parenthesis are properly parsed`() {
        expressionWriter.evaluate(CalculatorAction.Parenthesis)
        expressionWriter.evaluate(CalculatorAction.Number(5))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))
        expressionWriter.evaluate(CalculatorAction.Number(6))
        expressionWriter.evaluate(CalculatorAction.Parenthesis)

        val actual = expressionWriter.expression

        val expected = "(5+6)"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Closing parenthesis are not parsed at initial`() {
        expressionWriter.evaluate(CalculatorAction.Parenthesis)
        expressionWriter.evaluate(CalculatorAction.Parenthesis)

        val actual = expressionWriter.expression

        val expected = "(("

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Multiply operator should not be parsed at initial`() {
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.MULTIPLY))

        val actual = expressionWriter.expression

        val expected = ""

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Divide operator should not be parsed at initial`() {
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.DIVIDE))

        val actual = expressionWriter.expression

        val expected = ""

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Percent operator should not be parsed at initial`() {
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.PERCENT))

        val actual = expressionWriter.expression

        val expected = ""

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Add operator should be parsed at initial`() {
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))

        val actual = expressionWriter.expression

        val expected = "+"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Subtract operator should not be parsed at initial`() {
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.SUBTRACT))

        val actual = expressionWriter.expression

        val expected = "-"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Multiple Add operator should not be parsed`() {
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))

        val actual = expressionWriter.expression

        val expected = "+"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Multiple Subtract operator should not be parsed`() {
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.SUBTRACT))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.SUBTRACT))

        val actual = expressionWriter.expression

        val expected = "-"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Multiple Multiply operator should not be parsed`() {
        expressionWriter.evaluate(CalculatorAction.Number(5))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.MULTIPLY))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.MULTIPLY))

        val actual = expressionWriter.expression

        val expected = "5x"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Multiple Divide operator should not be parsed`() {
        expressionWriter.evaluate(CalculatorAction.Number(5))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.DIVIDE))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.DIVIDE))

        val actual = expressionWriter.expression

        val expected = "5/"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Multiple Percent operator should not be parsed`() {
        expressionWriter.evaluate(CalculatorAction.Number(5))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.PERCENT))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.PERCENT))

        val actual = expressionWriter.expression

        val expected = "5%"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Clear function is working properly`() {
        expressionWriter.evaluate(CalculatorAction.Number(8))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))
        expressionWriter.evaluate(CalculatorAction.Number(5))
        expressionWriter.evaluate(CalculatorAction.Clear)

        val actual = expressionWriter.expression

        val expected = ""

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Delete function is working properly`() {
        expressionWriter.evaluate(CalculatorAction.Number(8))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))
        expressionWriter.evaluate(CalculatorAction.Number(5))
        expressionWriter.evaluate(CalculatorAction.Delete)

        val actual = expressionWriter.expression

        val expected = "8+"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Multiple delete function is working properly`() {
        expressionWriter.evaluate(CalculatorAction.Number(8))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))
        expressionWriter.evaluate(CalculatorAction.Number(5))
        expressionWriter.evaluate(CalculatorAction.Delete)
        expressionWriter.evaluate(CalculatorAction.Number(6))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))
        expressionWriter.evaluate(CalculatorAction.Delete)

        val actual = expressionWriter.expression

        val expected = "8+6"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Decimal point should not be parsed when the number already have a decimal point`() {
        expressionWriter.evaluate(CalculatorAction.Number(8))
        expressionWriter.evaluate(CalculatorAction.DecimalPoint)
        expressionWriter.evaluate(CalculatorAction.Number(5))
        expressionWriter.evaluate(CalculatorAction.Number(6))
        expressionWriter.evaluate(CalculatorAction.DecimalPoint)

        val actual = expressionWriter.expression

        val expected = "8.56"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Decimal point should not be parsed after the parenthesis`() {
        expressionWriter.evaluate(CalculatorAction.Parenthesis)
        expressionWriter.evaluate(CalculatorAction.Number(8))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))
        expressionWriter.evaluate(CalculatorAction.Number(5))
        expressionWriter.evaluate(CalculatorAction.Parenthesis)
        expressionWriter.evaluate(CalculatorAction.DecimalPoint)

        val actual = expressionWriter.expression

        val expected = "(8+5)"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Decimal point should not be parsed initial`() {
        expressionWriter.evaluate(CalculatorAction.DecimalPoint)

        val actual = expressionWriter.expression

        val expected = ""

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Parenthesis should not be parsed after the numbers when opening and close parenthesis count is equal`() {
        expressionWriter.evaluate(CalculatorAction.Parenthesis)
        expressionWriter.evaluate(CalculatorAction.Number(8))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))
        expressionWriter.evaluate(CalculatorAction.Number(5))
        expressionWriter.evaluate(CalculatorAction.Parenthesis)
        expressionWriter.evaluate(CalculatorAction.Parenthesis)

        val actual = expressionWriter.expression

        val expected = "(8+5)"

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Opening Parenthesis not equal to closing parenthesis return a format error`() {
        expressionWriter.evaluate(CalculatorAction.Parenthesis)
        expressionWriter.evaluate(CalculatorAction.Parenthesis)
        expressionWriter.evaluate(CalculatorAction.Calculate)

        val actual = expressionWriter.expression

        val expected = ExpressionWriter.FORMAT_ERROR

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Simple expression given correct output`(){
        expressionWriter.evaluate(CalculatorAction.Number(8))
        expressionWriter.evaluate(CalculatorAction.Operator(OperationType.ADD))
        expressionWriter.evaluate(CalculatorAction.Number(10))
        expressionWriter.evaluate(CalculatorAction.Calculate)

        val actual = expressionWriter.expression

        val expected = "18.0"

        Assert.assertEquals(expected, actual)
    }
}