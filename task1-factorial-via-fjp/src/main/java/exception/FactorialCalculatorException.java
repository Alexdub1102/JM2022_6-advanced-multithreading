package exception;

public class FactorialCalculatorException extends RuntimeException {
    private static final String RESULTS_DO_NOT_MATCH =
            "SequentialResult and parallelResult do not match!";
    private static final String INVALID_ARGUMENT =
            "Argument not valid: must be natural number, but is: [%s]!";

    private FactorialCalculatorException(String message) {
        super(message);
    }

    public static FactorialCalculatorException resultsNotMatch() {
        return new FactorialCalculatorException(RESULTS_DO_NOT_MATCH);
    }

    public static FactorialCalculatorException invalidArgument(int argument) {
        return new FactorialCalculatorException(
                String.format(INVALID_ARGUMENT, argument));
    }
}
