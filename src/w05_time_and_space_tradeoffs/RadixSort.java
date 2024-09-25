package w05_time_and_space_tradeoffs;

public class RadixSort {
    private int[] values;
    static final int BASE = 10;

    public RadixSort(int[] values) {
        this.values = values;
    }

    // Apply counting sort to array values
    // based on the digit i (counting from the right)
    // Assume: Decimal base is used
    // The parameter is the result of raising ten to
    // the power of i (i start from 0)
    private void countingSort(int tenPower) {
        int[] temp = new int[values.length];
        int[] count = new int[BASE];

        // Frequency counting
        for (int i = 0; i < values.length; i++) {
            int digit = (values[i] / tenPower) % BASE;
            count[digit]++;
        }

        // Cumulative frequency counting
        for (int i = 1; i < BASE; i++) {
            count[i] += count[i - 1];
        }

        // Distribute values to the correct locations
        for (int i = values.length - 1; i >= 0; i--) {
            int digit = (values[i] / tenPower) % BASE;
            temp[count[digit] - 1] = values[i];
            count[digit]--;
        }

        // Update attributes
        values = temp;
    }

    public void sort() {
        // Get the maximum value in values
        int max = values[0];
        for (int i = 1; i < values.length; i++) {
            if (max < values[i]) {
                max = values[i];
            }
        }

        // How many digits in max?
        int digits = (max + "").length();
        int tenPower = 1;
        for (int i = 0; i < digits; i++) {
            countingSort(tenPower);
            tenPower *= 10;
        }
    }

    public int[] get() {
        return values;
    }

    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort(new int[] {
                1, 22, 333, 4444, 55555, 666666, 7777777, 88888888,
                19, 21, 383, 12345, 1000000000, 999999999, 1234567,
                3456789, 900, 1001
        });
        radixSort.sort();
        int[] res = radixSort.get();
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
