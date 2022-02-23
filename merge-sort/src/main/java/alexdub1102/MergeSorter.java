package alexdub1102;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSorter {
    private final int[] arr;
    private final int[] copy;

    public MergeSorter(int[] arr) {
        this.arr = arr;
        this.copy = new int[arr.length];
    }

    public void sort() {
        var forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(new SortTask(0, arr.length - 1));
        forkJoinPool.shutdown();
    }

    private class SortTask extends RecursiveAction {
        private final int lo;
        private final int hi;

        public SortTask(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected void compute() {
            if (hi <= lo) return;
            int mid = lo + (hi-lo)/2;
            var task1 = new SortTask(lo, mid);
            task1.fork();

            var task2 = new SortTask(mid+1, hi);
            task2.fork();

            task1.join();
            task2.join();

            merge(lo, mid, hi);
        }

        private void merge(int lo, int mid, int hi) {
            if (hi + 1 - lo >= 0) {
                System.arraycopy(arr, lo, copy, lo, hi + 1 - lo);
            }
            int i = lo;
            int j = mid + 1;
            for (int k = lo; k <= hi ; k++) {
                if (i > mid) {
                    arr[k] = copy[j++];
                } else if (j > hi) {
                    arr[k] = copy[i++];
                } else if (copy[j] < copy[i]) {
                    arr[k] = copy[j++];
                } else {
                    arr[k] = copy[i++];
                }
            }
        }
    }
}
