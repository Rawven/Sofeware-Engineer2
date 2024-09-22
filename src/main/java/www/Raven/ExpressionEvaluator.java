package www.Raven;

import java.util.*;

public class ExpressionEvaluator {
    private static final Set<Character> OPERATORS = Set.of('+', '-', '*', '/');

    public static double evaluate(String expression) {
        List<String> tokens = tokenize(expression);
        return evaluateTokens(tokens);
    }

    private static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (OPERATORS.contains(c) || c == '(' || c == ')') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else {
                currentToken.append(c);
            }
        }
        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }
        return tokens;
    }

    private static double evaluateTokens(List<String> tokens) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                values.push(Double.parseDouble(token));
            } else if (token.equals("(")) {
                operators.push('(');
            } else if (token.equals(")")) {
                while (operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // Remove '('
            } else if (OPERATORS.contains(token.charAt(0))) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(token.charAt(0));
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private static double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: throw new UnsupportedOperationException("Operator not supported: " + operator);
        }
    }
}