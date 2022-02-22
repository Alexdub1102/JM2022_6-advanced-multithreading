import calculator.FactorialCalculator;
import calculator.ParallelFactorialCalculator;
import calculator.ParallelStreamFactorialCalculator;
import calculator.SequentialFactorialCalculator;
import exception.FactorialCalculatorException;

import java.math.BigInteger;

/**
 * Task 1 Use FJP to calculate the factorial. Compare with the sequential implementation.
 * Use BigInteger to keep values.
 */
public class App {

  private final int numEvalRuns;
  private final int argument;

  public App(int numEvalRuns, int argument) {
    this.numEvalRuns = numEvalRuns;
    this.argument = argument;
  }

  public static void main(String[] args) {
    new App(10, 50000).run();
  }

  public void run() {
    System.out.println("Evaluating Sequential Implementation...");

    FactorialCalculator sfc = new SequentialFactorialCalculator(argument);
    BigInteger sequentialResult = sfc.calculate();
    double sequentialTime = getAverageTime(sfc);

    System.out.println("Evaluating Parallel Implementation...");

    FactorialCalculator pfc = new ParallelFactorialCalculator(argument);
    BigInteger parallelResult = pfc.calculate();
    validateResults(sequentialResult, parallelResult);
    double parallelTime = getAverageTime(pfc);
    printResults(sequentialTime, parallelTime);

    System.out.println("Evaluating ParallelStream Implementation...");

    FactorialCalculator psfc = new ParallelStreamFactorialCalculator(argument);
    BigInteger parallelStreamResult = psfc.calculate();
    validateResults(sequentialResult, parallelStreamResult);
    double parallelStreamTime = getAverageTime(psfc);
    printResults(sequentialTime, parallelStreamTime);
  }

  private void validateResults(BigInteger sequentialResult,
      BigInteger parallelResult) {
    if (!sequentialResult.equals(parallelResult)) {
      throw FactorialCalculatorException.resultsNotMatch();
    }
  }

  private double getAverageTime(FactorialCalculator sfc) {
    double sequentialTime = 0;
    for (int i = 0; i < numEvalRuns; i++) {
      long start = System.currentTimeMillis();
      sfc.calculate();
      sequentialTime += System.currentTimeMillis() - start;
    }
    sequentialTime /= numEvalRuns;
    return sequentialTime;
  }

  private void printResults(double sequentialTime, double parallelTime) {
    int numWorkers = Runtime.getRuntime().availableProcessors();
    System.out.format("Average Sequential Time: %.1f ms\n", sequentialTime);
    System.out.format("Average Parallel Time: %.1f ms\n", parallelTime);
    System.out.format("Speedup: %.2f \n", sequentialTime / parallelTime);
    System.out.format("Available processors: %s\n", numWorkers);
    System.out.format("Efficiency: %.2f%%\n",
        100 * (sequentialTime / parallelTime) / numWorkers);
  }
}
