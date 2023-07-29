import java.util.ArrayDeque;

public class Calculator {
    public static float calculate(String expression) {
        ArrayDeque<Operation> stack = new ArrayDeque<>();

        boolean parsing = false;
        boolean isDecimal = false;
        int from = -1;
        float value = 0;
        Type expected = Type.expression;
        for (int i = 0, n = expression.length(); i < n; ++i) {
            final char ch = expression.charAt(i);

            switch (ch) {
                case '(':
                    if (expected != Type.expression) {
                        throw new IllegalArgumentException("Parentheses at " + i + " not allowed");
                    }
                    stack.addLast(new Operation(Operator.open, 0));
                    continue;
                case ')':
                    if (parsing) {
                        value = Float.parseFloat(expression.substring(from, i));
                        parsing = isDecimal = false;
                    }
                    value = calculateParentheses(stack, value);
                    expected = Type.operator;
                    continue;
                case '.':
                    if (isDecimal || expected == Type.operator) {
                        throw new IllegalArgumentException("Point at " + i + " not allowed");
                    }
                    isDecimal = true;
                    expected = Type.number;
                    if (!parsing) {
                        parsing = true;
                        from = i;
                    }
                    continue;
                case '+':
                case '-':
                case '*':
                case '/':
                    if (ch == '-' && !parsing && expected != Type.operator) {
                        parsing = true;
                        expected = Type.number;
                        from = i;
                        continue;
                    }

                    if (expected == Type.expression) {
                        throw new IllegalArgumentException("Unexpected operator " + ch + " at " + i);
                    }

                    if (parsing) {
                        parsing = false;
                        value = Float.parseFloat(expression.substring(from, i));
                    }
                    expected = Type.expression;

                    Operation operation = new Operation(ch, value);
                    while (!stack.isEmpty()
                            && stack.getLast().operator != Operator.open
                            && stack.getLast().operator.priority >= operation.operator.priority) {
                        operation.left = stack.removeLast().evaluate(operation.left);
                    }
                    stack.addLast(operation);
                    continue;
            }

            if (Character.isDigit(ch)) {
                if (expected == Type.operator) {
                    throw new IllegalArgumentException("Unexpected digit " + ch + " at " + i);
                }
                if (!parsing) {
                    parsing = true;
                    from = i;
                    expected = Type.number;
                }
            }
        }

        if (parsing) {
            value = Float.parseFloat(expression.substring(from));
        }
        while (!stack.isEmpty()) {
            value = stack.removeLast().evaluate(value);
        }

        return value;
    }

    private static float calculateParentheses(ArrayDeque<Operation> stack, float value) {
        while (!stack.isEmpty()) {
            Operation operation = stack.removeLast();
            if (operation.operator == Operator.open) {
                return value;
            }
            value = operation.evaluate(value);
        }
        throw new IllegalStateException("Parenthesis do not match");
    }

    static class Operation {
        Operator operator;
        float left;

        Operation(Operator operator, float left) {
            this.operator = operator;
            this.left = left;
        }

        Operation(char operator, float left) {
            this(Operator.of(operator), left);
        }

        float evaluate(float right) {
            switch (operator) {
                case add:
                    return left + right;
                case subtract:
                    return left - right;
                case multiply:
                    return left * right;
                case divide:
                    return left / right;
            }
            throw new IllegalStateException("Illegal operator " + operator);
        }
    }

    enum Operator {
        add('+', (byte) 1),
        multiply('*', (byte) 2),
        divide('/', (byte) 2),
        subtract('-', (byte) 1),
        open('(', Byte.MAX_VALUE);

        private final char character;
        private final byte priority;

        Operator(char character, byte priority) {
            this.character = character;
            this.priority = priority;
        }

        static Operator of(char ch) {
            return switch (ch) {
                case '+' -> add;
                case '-' -> subtract;
                case '*' -> multiply;
                case '/' -> divide;
                default -> throw new IllegalArgumentException("Invalid operator " + ch);
            };
        }
    }

    enum Type {
        // '(' or number
        expression,
        number,
        operator
    }
}
