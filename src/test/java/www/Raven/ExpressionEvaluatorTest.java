package www.Raven;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class ExpressionEvaluatorTest {

    @Test
    public void testSimpleAddition() {
        String expression = "3 + 5";
        double result = ExpressionEvaluator.evaluate(expression);
        assertEquals(8.0, result, 0.001);
    }

    @Test
    public void testSubtraction() {
        String expression = "10 - 4";
        double result = ExpressionEvaluator.evaluate(expression);
        assertEquals(6.0, result, 0.001);
    }

    @Test
    public void testMultiplication() {
        String expression = "7 * 6";
        double result = ExpressionEvaluator.evaluate(expression);
        assertEquals(42.0, result, 0.001);
    }

    @Test
    public void testDivision() {
        String expression = "8 / 2";
        double result = ExpressionEvaluator.evaluate(expression);
        assertEquals(4.0, result, 0.001);
    }

    @Test
    public void testMultipleOperators() {
        String expression = "3 + 5 * 2";
        double result = ExpressionEvaluator.evaluate(expression);
        assertEquals(13.0, result, 0.001);
    }

    @Test
    public void testParentheses() {
        String expression = "(1 + 2) * (3 + 4)";
        double result = ExpressionEvaluator.evaluate(expression);
        assertEquals(21.0, result, 0.001);
    }

    @Test
    public void testComplexExpression() {
        String expression = "10 + 2 * 6";
        double result = ExpressionEvaluator.evaluate(expression);
        assertEquals(22.0, result, 0.001);
    }

    @Test(expected = EmptyStackException.class)
    public void testNegativeNumbers() {
        String expression = "-1 + -2";
        ExpressionEvaluator.evaluate(expression);
    }
}