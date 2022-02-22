import calculator.FactorialCalculator;
import calculator.ParallelFactorialCalculator;
import calculator.ParallelStreamFactorialCalculator;
import calculator.SequentialFactorialCalculator;
import exception.FactorialCalculatorException;

import java.math.BigInteger;

/**
 * Task 1
 * Use FJP to calculate the factorial.
 * Compare with the sequential implementation.
 * Use BigInteger to keep values.
 */
public class App {
    public static void main(String[] args) {
        final int numEvalRuns = 10;
        final int argument = 50000;

        System.out.println("Evaluating Sequential Implementation...");
        FactorialCalculator sfc = new SequentialFactorialCalculator(argument);
        BigInteger sequentialResult = sfc.calculate();
        double sequentialTime = 0;
        for (int i = 0; i < numEvalRuns; i++) {
            long start = System.currentTimeMillis();
            sfc.calculate();
            sequentialTime += System.currentTimeMillis() - start;
        }
        sequentialTime /= numEvalRuns;

        System.out.println("Evaluating ParallelStream Implementation...");
        FactorialCalculator psfc = new ParallelStreamFactorialCalculator(argument);
        BigInteger parallelStreamResult = psfc.calculate();
        double parallelStreamTime = 0;
        for (int i = 0; i < numEvalRuns; i++) {
            long start = System.currentTimeMillis();
            psfc.calculate();
            parallelStreamTime += System.currentTimeMillis() - start;
        }
        parallelStreamTime /= numEvalRuns;

        // display sequential and parallel results for comparison
        if (!sequentialResult.equals(parallelStreamResult)) {
            throw FactorialCalculatorException.resultsNotMatch();
        }
        printResults(sequentialTime, parallelStreamTime);

        System.out.println("Evaluating Parallel Implementation...");
        FactorialCalculator pfc = new ParallelFactorialCalculator(argument);
        BigInteger parallelResult = pfc.calculate();
        double parallelTime = 0;
        for (int i = 0; i < numEvalRuns; i++) {
            long start = System.currentTimeMillis();
            pfc.calculate();
            parallelTime += System.currentTimeMillis() - start;
        }
        parallelTime /= numEvalRuns;

        // display sequential and parallel results for comparison
        if (!sequentialResult.equals(parallelResult)) {
            throw FactorialCalculatorException.resultsNotMatch();
        }
        printResults(sequentialTime, parallelTime);
    }

    private static void printResults(double sequentialTime, double parallelTime) {
        int numWorkers = Runtime.getRuntime().availableProcessors();
        System.out.format("Average Sequential Time: %.1f ms\n", sequentialTime);
        System.out.format("Average Parallel Time: %.1f ms\n", parallelTime);
        System.out.format("Speedup: %.2f \n", sequentialTime / parallelTime);
        System.out.format("Available processors: %s\n", numWorkers);
        System.out.format("Efficiency: %.2f%%\n",
                100 * (sequentialTime / parallelTime) / numWorkers);
    }
}
