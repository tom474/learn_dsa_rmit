package w7_brute_force;

public class SubSet {
    public static void main(String[] args) {
        int[] input = {1, 2, 3};
        boolean[] selected = {false, false, false};
        subset(input, selected, 0);
    }

    static void subset(int[] input, boolean[] selected, int index) {
        if (index == input.length) {
            process(input, selected);
            return;
        }

        // Not selected
        selected[index] = false;
        subset(input, selected, index + 1);

        // Selected
        selected[index] = true;
        subset(input, selected, index + 1);
    }

    static void process(int[] set, boolean[] selected) {
        System.out.print("Subset: ");
        for (int i = 0; i < set.length; i++) {
            if (selected[i]) {
                System.out.print(set[i] + " ");
            }
        }
        System.out.println();
    }
}
