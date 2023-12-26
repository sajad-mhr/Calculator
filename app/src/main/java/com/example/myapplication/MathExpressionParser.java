package com.example.myapplication;

import java.util.Stack;

public class MathExpressionParser {

    public static String evaluateExpression(String expression) {
        try {
            return String.valueOf(eval(expression));
        } catch (Exception e) {
            return "Err";
        }
    }

    private static double eval(String expression) {
        // Tokenize the expression into numbers, operators, and parentheses
        String[] tokens = expression.split("(?=[+\\-*/()])|(?<=[+\\-*/()])");

        // Separate the numbers and operators
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                numbers.push(Double.parseDouble(token));
            } else if (token.equals("(")) {
                operators.push('(');
            } else if (token.equals(")")) {
                while (operators.peek() != '(') {
                    applyOperator(numbers, operators.pop());
                }
                operators.pop(); // Pop the '('
            } else {
                // Token is an operator
                char operator = token.charAt(0);
                while (!operators.isEmpty() && hasPrecedence(operator, operators.peek())) {
                    applyOperator(numbers, operators.pop());
                }
                operators.push(operator);
            }
        }

        // Process any remaining operators
        while (!operators.isEmpty()) {
            applyOperator(numbers, operators.pop());
        }

        // The result is the only number left on the stack
        return numbers.pop();
    }

    private static void applyOperator(Stack<Double> numbers, char operator) {
        double b = numbers.pop();
        double a = numbers.pop();
        switch (operator) {
            case '+':
                numbers.push(a + b);
                break;
            case '-':
                numbers.push(a - b);
                break;
            case '*':
                numbers.push(a * b);
                break;
            case '/':
                numbers.push(a / b);
                break;
        }
    }

    private static boolean hasPrecedence(char op1, char op2) {
        return (op2 != '(' && op2 != ')' && getPrecedence(op1) < getPrecedence(op2));
    }

    private static int getPrecedence(char operator) {
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

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
