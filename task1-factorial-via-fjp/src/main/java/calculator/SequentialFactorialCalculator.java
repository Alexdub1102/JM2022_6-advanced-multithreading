package calculator;

import java.math.BigInteger;
import java.util.stream.Stream;

public class SequentialFactorialCalculator extends FactorialCalculator {

    public SequentialFactorialCalculator(int argument) {
        super(argument);
    }

    public BigInteger calculate() {
        return Stream.iterate(argument, a -> a > 0, i -> i - 1)
                .map(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .orElse(BigInteger.ONE);
    }
}
