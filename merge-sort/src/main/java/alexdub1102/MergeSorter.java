package alexdub1102;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSorter {
    private final int[] arr;
    private final int[] copy;

    public MergeSorter(int[] arr) {
        this.arr = arr;
        copy = new int[arr.length];
    }

    public void sort() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new SortTask(0, arr.length - 1));
        pool.shutdown();
    }

    private class SortTask extends RecursiveAction {
        int lo;
        int hi;

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
            task2.compute();
            task1.join();
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
