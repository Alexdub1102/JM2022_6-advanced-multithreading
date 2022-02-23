package calculator;

import java.math.BigInteger;
import java.util.stream.Stream;

public class ParallelStreamFactorialCalculator extends FactorialCalculator{

    public ParallelStreamFactorialCalculator(int argument) {
        super(argument);
    }

    public BigInteger calculate() {
        return Stream.iterate(argument, a -> a > 0, i -> i - 1)
                .parallel()
                .map(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .orElse(BigInteger.ONE);
    }
}
