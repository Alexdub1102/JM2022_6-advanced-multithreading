package calculator;

import exception.FactorialCalculatorException;

import java.math.BigInteger;

public abstract class FactorialCalculator {
    protected final int argument;

    protected FactorialCalculator(int argument) {
        if (argument < 0) {
            throw FactorialCalculatorException.invalidArgument(argument);
        }
        this.argument = argument;
    }

    public abstract BigInteger calculate();
}
