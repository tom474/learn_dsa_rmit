package w7_brute_force;

public class Problem1 {
    public static void main(String[] args) {
        // Selection Sort
        System.out.println("Selection sort - First Test");
        SelectionSort.sort(new int[] {6, 2, 7, 9, 1});
        System.out.println("Selection sort - Second test");
        SelectionSort.sort(new int[] {1, 3, 5, 7, 9});
        System.out.println("Selection sort - Third test");
        SelectionSort.sort(new int[] {10, 8, 6, 4, 2});

        // Bubble sort
        System.out.println("Bubble sort - First test");
        BubbleSort.sort(new int[] {6, 2, 7, 9, 1});
        System.out.println("Bubble sort - Second test");
        BubbleSort.sort(new int[] {1, 3, 5, 7, 9});
        System.out.println("Bubble sort - Third test");
        BubbleSort.sort(new int[] {10, 8, 6, 4, 2});
    }
}

class SelectionSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Find the index of smallest element
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the minimum element with the first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

            // Display
            display(arr);
        }
    }

    private static void display(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i] + " ");
        }
        System.out.println(stringBuilder);
    }
}

class BubbleSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;    // Used to check if there is any swap
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap 2 elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;     // There is a swap
                }
            }
            display(arr);
            // Array in sorted order?
            if (!swapped) {
                return;
            }
        }
    }

    private static void display(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i] + " ");
        }
        System.out.println(stringBuilder);
    }
}
