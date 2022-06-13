package calculator;

public enum CalculatorActions {
    minus("-"), plus("+"), multiply("*"), divide("/");

    public final String action;

    CalculatorActions(String action) {
        this.action = action;
    }
}
