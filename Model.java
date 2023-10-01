public class Model {

    private Viewer viewer;
    private ReversePolishNotation reversePolishNotation;
    private StringBuilder mainText;
    private StringBuilder secondaryText;

    public Model(Viewer viewer) {
        this.viewer = viewer;
        reversePolishNotation = new ReversePolishNotation();
        mainText = new StringBuilder();
        secondaryText = new StringBuilder();
    }

    public void doAction(String command) {
        if (isOperation(command)) {
            doOperation(command);
        } else {
            switch (command) {
                case "C":
                    nullify("0");
                    break;
                case "<":
                    removeLastChar();
                    break;
                case "=":
                    process();
                    break;
                case "Negate":
                    negate();
                    break;
                case ".":
                    point();
                    break;
                default:
                    continueExpression(command);
            }
        }
    }



    private void point() {
	String string = mainText.length() == 0 ? viewer.getMainTextField().getText() : mainText.toString();
        if (!string.contains(".")) {
	        mainText.setLength(0);
            mainText.append(string).append(".");
            viewer.setMainTextField(mainText.toString());
            secondaryText.setLength(0);
            viewer.setSecondaryTextField("");
        }
    }

    private void negate() {
        String text = mainText();
        if (text.length() > 0 && !text.equals("0")) {
            if (text.charAt(0) == '-') {
                mainText.setLength(0);
                mainText.append(text.substring(1));
            } else {
                mainText.setLength(0);
                mainText.append("-").append(text);
            }
            secondaryText.setLength(0);
            viewer.setSecondaryTextField("");
            viewer.setMainTextField(mainText.toString());
        }

    }

    private void nullify(String text) {
        mainText.setLength(0);
        secondaryText.setLength(0);
        viewer.setSecondaryTextField("");
        viewer.setMainTextField(text);
    }

    private void continueExpression(String command) {
        mainText.append(command);
        viewer.setMainTextField(mainText.toString());
    }

    private boolean isOperation(String command) {
        return "x/+-".contains(command);
    }

    private void doOperation(String operation) {
        if (secondaryText.length() != 0 && secondaryText.indexOf(" = ") == -1) {
            String result = roundDouble(reversePolishNotation.reversPolishNotation(secondaryText + mainText()));
            secondaryText.setLength(0);
            mainText.setLength(0);
            viewer.setMainTextField("0");
            secondaryText.append(result).append(operation);
            viewer.setSecondaryTextField(secondaryText.toString());
        }
        else {
            secondaryText.setLength(0);
            secondaryText.append(mainText()).append(operation);
            viewer.setSecondaryTextField(secondaryText.toString());
            mainText.setLength(0);
            viewer.setMainTextField("0");
        }
    }

    private void removeLastChar() {
        mainText.setLength(mainText.length() == 0 ? 0 : mainText.length() - 1);
        viewer.setMainTextField(mainText.length() == 0 ? "0" : mainText.toString());
    }

    private String roundDouble(double result) {
        return Math.round(result) == result ? String.valueOf((int) result) : String.valueOf(result);
    }

    private void process() {
        try {
            mainText.setLength(0);
            String s = mainText();
            mainText.append(roundDouble(reversePolishNotation.reversPolishNotation(secondaryText + s)));
            secondaryText.append(s).append(" = ");
            viewer.setMainTextField(mainText.toString());
            viewer.setSecondaryTextField(secondaryText.toString());
        } catch (ArithmeticException e) {
            nullify("Can't divide by 0");
        } catch (Exception ignored) {
            nullify("");
        }
    }

    private String mainText() {
        return mainText.length() == 0 ? viewer.getMainTextField().getText() : mainText.toString();
    }

}
