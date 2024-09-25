package w08_decrease_and_conquer;

public class Problem2 {
    public static void main(String[] args) {
        // Lomuto
        int[] test = new int[] {3, 5, 2, 1, 8, 9, 6, 7, 4};
        int smallest = quickSelectLomuto(test, 0, test.length - 1, 1);
        System.out.println("Smallest: " + smallest);

        test = new int[] {3, 5, 2, 1, 8, 9, 6, 7, 4};
        int secondSmallest = quickSelectLomuto(test, 0, test.length - 1, 2);
        System.out.println("Second smallest: " + secondSmallest);

        test = new int[] {3, 5, 2, 1, 8, 9, 6, 7, 4};
        int fifthSmallest = quickSelectLomuto(test, 0, test.length - 1, 5);
        System.out.println("Fifth smallest: " + fifthSmallest);

        // Hoare
        test = new int[] {3, 5, 2, 1, 8, 9, 6, 7, 4};
        smallest = quickSelectHoare(test, 0, test.length - 1, 1);
        System.out.println("Smallest: " + smallest);

        test = new int[] {3, 5, 2, 1, 8, 9, 6, 7, 4};
        secondSmallest = quickSelectHoare(test, 0, test.length - 1, 2);
        System.out.println("Second smallest: " + secondSmallest);

        test = new int[] {3, 5, 2, 1, 8, 9, 6, 7, 4};
        fifthSmallest = quickSelectHoare(test, 0, test.length - 1, 5);
        System.out.println("Fifth smallest: " + fifthSmallest);
    }

    // Quick select - Lomuto Partition
    static int quickSelectLomuto(int[] arr, int left, int right, int k) {
        int pivot = partitionLomuto(arr, left, right);
        if (k - 1 == pivot) {
            return arr[pivot];
        }
        if (k - 1 > pivot) {
            return quickSelectLomuto(arr, pivot + 1, right, k);
        }
        return quickSelectLomuto(arr, left, pivot - 1, k);
    }

    // Lomuto partition
    // Return a partition point p
    // Where all elements arr[left, p - 1] <= arr[p] <= all elements arr[p + 1, right]
    static int partitionLomuto(int[] arr, int left, int right) {
        int pivot = arr[right];     // Select the right-most element as pivot
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                // Swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                // Increase i
                i++;
            }
        }
        // Swap
        int temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;

        return i;
    }

    // quick select - Hoare partition
    static int quickSelectHoare(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        int p = partitionHoare(arr, left, right);
        if (k - 1 <= p) {
            return quickSelectHoare(arr, left, p, k);
        }
        return quickSelectHoare(arr, p + 1, right, k);
    }

    // Hoare partition
    // Return a partition point p
    // Where all elements arr[left, p] <= all elements arr[p + 1, right]
    static int partitionHoare(int[] arr, int left, int right) {
        int p = arr[left];      // Select the left-most element as pivot
        int front = left;
        int back = right;
        while (true) {
            while (arr[front] < p) {
                front++;
            }
            while (arr[back] > p) {
                back--;
            }
            if (front >= back) {
                return back;
            }
            // Swapping
            int t = arr[front];
            arr[front] = arr[back];
            arr[back] = t;
            front++;
            back--;
        }
    }
}
