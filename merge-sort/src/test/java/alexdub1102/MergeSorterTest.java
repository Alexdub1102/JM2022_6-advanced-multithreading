package alexdub1102;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Random;


class MergeSorterTest {

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 5, 10, 100000})
    void sortTest(int length) {
        int[] arr = generateRandomArray(length);

        int[] expected = new int[arr.length];
        System.arraycopy(arr, 0, expected, 0, arr.length);

        new MergeSorter(arr).sort();

        Arrays.sort(expected);
        assertArrayEquals(expected, arr);
    }

    private int[] generateRandomArray(int length) {
        return new Random().ints(length).toArray();
    }
}