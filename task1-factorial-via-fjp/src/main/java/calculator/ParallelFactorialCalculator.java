package calculator;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

public class ParallelFactorialCalculator extends FactorialCalculator {

    public ParallelFactorialCalculator(int argument) {
        super(argument);
    }

    public BigInteger calculate() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        BigInteger result = pool.invoke(new FactorialTask(1, argument));
        pool.shutdown();
        return result;
    }

    private static class FactorialTask extends RecursiveTask<BigInteger> {

        private final int first;
        private final int last;

        public FactorialTask(int first, int last) {
            this.first = first;
            this.last = last;
        }

        @Override
        protected BigInteger compute() {
            int baseChunk = 100;
            if (last - first < baseChunk) {
                return Stream.iterate(last, a -> a > first - 1, i -> i - 1)
                        .map(BigInteger::valueOf)
                        .reduce(BigInteger::multiply)
                        .orElse(BigInteger.ONE);
            }
            int mid = first + (last - first) / 2;

            FactorialTask task1 = new FactorialTask(first, mid);
            task1.fork();

            FactorialTask task2 = new FactorialTask(mid + 1, last);
            task2.fork();

            return task2.join().multiply(task1.join());
        }
    }
}
