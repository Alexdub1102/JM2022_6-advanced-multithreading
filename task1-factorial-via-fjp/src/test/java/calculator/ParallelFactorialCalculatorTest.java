package calculator;

import static org.junit.jupiter.api.Assertions.*;

import exception.FactorialCalculatorException;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class ParallelFactorialCalculatorTest {

    @Test
    void shouldThrowExceptionWithNegativeArgument() {
        assertThrows(FactorialCalculatorException.class,
                () -> getParallelFactorialCalculator(-1));
    }

    @Test
    void shouldReturn1WithArgument0() {
        assertEquals(BigInteger.ONE,
                getParallelFactorialCalculator(0).calculate());
    }

    @Test
    void shouldReturn1WithArgument1() {
        assertEquals(BigInteger.ONE,
                getParallelFactorialCalculator(1).calculate());
    }

    @Test
    void shouldReturn2WithArgument2() {
        assertEquals(BigInteger.TWO,
                getParallelFactorialCalculator(2).calculate());
    }


    @Test
    void shouldReturn120WithArgument5() {
        assertEquals(BigInteger.valueOf(120),
                getParallelFactorialCalculator(5).calculate());
    }

    private ParallelFactorialCalculator getParallelFactorialCalculator(int i) {
        return new ParallelFactorialCalculator(i);
    }
}