package test2_sample;

public class CuongDoraemonCake {
    Topic[] topics;
    double A;

    public CuongDoraemonCake(Topic[] topics, double a) {
        this.topics = topics;
        A = a;
    }

    // Complexity: 0(N*LogN)
    public double weightByNumber(int X) {
        double[] weights = new double[topics.length];
        for (int i = 0; i < topics.length; i++) {
            weights[i] = topics[i].W;
        }
        MergeSort ms = new MergeSort();
        ms.mergeSort(weights);
        double total = 0;
        for (int i = weights.length - 1; i > weights.length - 1 - X; i--) {
            total += weights[i];
        }
        return total;
    }

    // Complexity: O(2^N)
    public double largestWeight() {
        boolean[] selected = new boolean[topics.length];
        SubSet.subset(topics.clone(), selected, 0, A);
        for (int i = 0; i < SubSet.bestSet.length; i++) {
            if (SubSet.bestSet[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        return SubSet.bestWeight;
    }

    public static void main(String[] args) {
        Topic[] topics = {
                new Topic(8.0, 7.0),
                new Topic(10.0, 8.0),
                new Topic(5.0, 3.0)
        };
        CuongDoraemonCake dc = new CuongDoraemonCake(topics, 10.0);
        System.out.println(dc.weightByNumber(1));
        System.out.println(dc.weightByNumber(2));
        System.out.println(dc.weightByNumber(3));
        System.out.println(dc.largestWeight());
    }
}

class Topic {
    double W;
    double S;

    public Topic(double w, double s) {
        W = w;
        S = s;
    }
}

class MergeSort {
    public void mergeSort(double[] arr) {
        if (arr.length > 1) {
            int n = arr.length;
            int middle = n / 2;

            // Create 2 sub-arrays from arr
            double[] sub1 = new double[middle];
            for (int i = 0; i < middle; i++) {
                sub1[i] = arr[i];
            }
            double[] sub2 = new double[n - middle];
            for (int i = middle; i < n; i++) {
                sub2[i - middle] = arr[i];
            }

            // Sort first and second halves
            mergeSort(sub1);
            mergeSort(sub2);

            // Merge sub1 and sub2 into the original array
            merge(sub1, sub2, arr);
        }
    }

    // Merge two sub-arrays sub1 and sub2 into the array dest
    public void merge(double[] sub1, double[] sub2, double[] dest) {
        int p1 = 0, p2 = 0, pDest = 0;  // pointers to 3 arrays
        while (p1 < sub1.length && p2 < sub2.length) {
            if (sub1[p1] <= sub2[p2]) {
                dest[pDest] = sub1[p1];
                p1++;
            } else {
                dest[pDest] = sub2[p2];
                p2++;
            }
            pDest++;
        }

        // copy remaining elements, if any
        while (p1 < sub1.length) {
            dest[pDest++] = sub1[p1++];
        }
        while (p2 < sub2.length) {
            dest[pDest++] = sub2[p2++];
        }
    }
}

class SubSet {
    static double bestWeight = 0;
    static boolean[] bestSet = {};
    static void subset(Topic[] input, boolean[] selected, int index, double A) {
        if (index == input.length) {
            process(input, selected, A);
            return;
        }

        // Not selected
        selected[index] = false;
        subset(input, selected, index + 1, A);

        // Selected
        selected[index] = true;
        subset(input, selected, index + 1, A);
    }

    static void process(Topic[] set, boolean[] selected, double A) {
        double totalWeight = 0;
        double totalSurface = 0;
        for (int i = 0; i < set.length; i++) {
            if (selected[i]) {
                totalWeight += set[i].W;
                totalSurface += set[i].S;
                if (totalSurface > A) {
                    return;
                }
            }
        }
        if (totalWeight > bestWeight) {
            bestWeight = totalWeight;
            bestSet = selected.clone();
        }
    }
}