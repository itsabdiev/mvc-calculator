public class ReversePolishNotation {

    public double reversPolishNotation(String expression) {
        CustomStack<Object> mainStack = new CustomStack<>();
        CustomStack<Object> operations = new CustomStack<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentCharacter = expression.charAt(i);
            if (isOperator(currentCharacter) && i != 0) {
                while (!operations.isEmpty() && priority((char) operations.peek()) >= priority(currentCharacter)) {
                    calculateValue(mainStack, (char) operations.pop());
                }
                operations.push(currentCharacter);
            } else {
                StringBuilder operand = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.' || (expression.charAt(i) == '-') && i == 0)) {
                    operand.append(expression.charAt(i++));
                }
                --i;
                mainStack.push(Double.parseDouble(operand.toString()));
            }
        }

        while (!operations.isEmpty())
            calculateValue(mainStack, (char) operations.pop());
        return (Double) mainStack.pop();
    }

    private void calculateValue(CustomStack<Object> stack, char operation) throws RuntimeException{
        double answer;
        double x = (double) stack.pop();
            double y = (double) stack.pop();
            switch (operation) {
                case '+':
                    answer = x + y;
                    stack.push(answer);
                    break;
                case '-':
                    answer = y - x;
                    stack.push(answer);
                    break;
                case 'x':
                    answer = x * y;
                    stack.push(answer);
                    break;
                case '/':
                    if (x == 0)
                        throw new ArithmeticException("Invalid operation");
                    answer = y / x;
                    stack.push(answer);
                    break;
            }
        }

    private int priority(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case 'x':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private boolean isOperator(char character) {
        return "x/+-".contains(String.valueOf(character));
    }

}

