package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.FactorialCalculatorException;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class SequentialFactorialCalculatorTest {

    @Test
    void shouldThrowExceptionWithNegativeArgument() {
        assertThrows(FactorialCalculatorException.class,
                () -> getSequentialFactorialCalculator(-1));
    }

    @Test
    void shouldReturn1WithArgument0() {
        assertEquals(BigInteger.ONE,
                getSequentialFactorialCalculator(0).calculate());
    }

    @Test
    void shouldReturn1WithArgument1() {
        assertEquals(BigInteger.ONE,
                getSequentialFactorialCalculator(1).calculate());
    }

    @Test
    void shouldReturn2WithArgument2() {
        assertEquals(BigInteger.TWO,
                getSequentialFactorialCalculator(2).calculate());
    }


    @Test
    void shouldReturn120WithArgument5() {
        assertEquals(BigInteger.valueOf(120),
                getSequentialFactorialCalculator(5).calculate());
    }

    private SequentialFactorialCalculator getSequentialFactorialCalculator(int i) {
        return new SequentialFactorialCalculator(i);
    }
}