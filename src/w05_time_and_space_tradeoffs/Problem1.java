package w05_time_and_space_tradeoffs;

public class Problem1 {
    // Sort an integer array having the range value [i...max]
    static int[] countingSort(int[] arr, int max) {
        int[] frequency = new int[max + 1];
        int[] result = new int[arr.length];

        // Frequency counting
        for (int i = 0; i < arr.length; i++) {
            frequency[arr[i]]++;
        }

        // Calculate cumulative frequency
        for (int i = 1; i <= max; i++) {
            frequency[i] = frequency[i - 1] + frequency[i];
        }

        // Distribution step (right to left)
        for (int i = arr.length - 1; i >= 0; i--) {
            result[frequency[arr[i]] - 1] = arr[i];
            frequency[arr[i]]--;
        }

        return result;
    }

    static int[] generateArr(int size, int max) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = (int)(Math.random() * max + 1);
        }
        return res;
    }

    static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Correctness test
        int[] test = generateArr(10, 10);
        System.out.println("Before sorting:");
        printArr(test);
        System.out.println("After sorting:");
        printArr(countingSort(test, 10));

        // Performance test
        int size = 1000;
        int max = 1000000000;
        int[] test2 = generateArr(size, max);
        long t1 = System.currentTimeMillis();
        countingSort(test2, max);
        long t2 = System.currentTimeMillis();
        System.out.println("Distribution sort: " + (t2 - t1) + " milliseconds");

        long t3 = System.currentTimeMillis();
        java.util.Arrays.sort(test2);
        long t4 = System.currentTimeMillis();
        System.out.println("Java sort: " + (t4 - t3) + " milliseconds");
    }
}

// The sub-problem 1B can be solved with Radix sort