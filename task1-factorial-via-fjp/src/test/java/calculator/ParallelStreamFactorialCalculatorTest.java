package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.FactorialCalculatorException;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class ParallelStreamFactorialCalculatorTest {

    @Test
    void shouldThrowExceptionWithNegativeArgument() {
        assertThrows(FactorialCalculatorException.class,
                () -> getParallelStreamFactorialCalculator(-1));
    }

    @Test
    void shouldReturn1WithArgument0() {
        assertEquals(BigInteger.ONE,
                getParallelStreamFactorialCalculator(0).calculate());
    }

    @Test
    void shouldReturn1WithArgument1() {
        assertEquals(BigInteger.ONE,
                getParallelStreamFactorialCalculator(1).calculate());
    }

    @Test
    void shouldReturn2WithArgument2() {
        assertEquals(BigInteger.TWO,
                getParallelStreamFactorialCalculator(2).calculate());
    }


    @Test
    void shouldReturn120WithArgument5() {
        assertEquals(BigInteger.valueOf(120),
                getParallelStreamFactorialCalculator(5).calculate());
    }

    private ParallelStreamFactorialCalculator getParallelStreamFactorialCalculator(int i) {
        return new ParallelStreamFactorialCalculator(i);
    }
}