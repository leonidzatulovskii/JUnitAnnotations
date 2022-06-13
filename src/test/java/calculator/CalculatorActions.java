package calculator;

public enum CalculatorActions {
    MINUS("-"),
    PLUS("+"),
    MULTIPLY("*"),
    DIVIDE("/");

    public final String action;

    CalculatorActions(String action) {
        this.action = action;
    }
}
