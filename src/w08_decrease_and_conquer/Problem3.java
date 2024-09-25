package w08_decrease_and_conquer;

public class Problem3 {
    public static void main(String[] args) {
        // Compare the results of our binary search square root
        // With the built-in square root function
        System.out.printf("\nBuilt-in sqrt(5): %.6f", Math.sqrt(5));
        System.out.printf("\nBinary search sqrt(5): %.6f", sqrt(5));

        System.out.printf("\nBuilt-in sqrt(101): %.6f", Math.sqrt(101));
        System.out.printf("\nBinary search sqrt(101): %.6f", sqrt(101));

        System.out.printf("\nBuilt-in sqrt(0.5): %.6f", Math.sqrt(0.5));
        System.out.printf("\nBinary search sqrt(0.5): %.6f", sqrt(0.5));
    }

    static double sqrt(double X) {
        double eps = 0.0000001;
        double min = 0;
        double max = X;
        if (X < 1) {
            max = 1;
        }
        while (max - min > eps) {
            double mid = (min + max) / 2.0;
            if (mid * mid < X) {
                min = mid;
            } else {
                max = mid;
            }
        }
        return min;
    }
}
