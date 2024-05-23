package w7_brute_force;

public class Problem4 {
    int[] board;
    int BOARD_SIZE = 8;
    boolean ALL = false;    // Process all solutions or just one?
    boolean stop = false;

    public Problem4() {
        this.board = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = i;
        }
    }

    public void start() {
        permute(new boolean[BOARD_SIZE], new int[BOARD_SIZE], 0);
    }

    void permute(boolean[] taken, int[] current, int index) {
        if (stop) {
            return;
        }

        if (index == BOARD_SIZE) {
            process(current);
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (taken[i]) {
                continue;
            }
            current[index] = board[i];
            taken[i] = true;
            if (!pruning(current, index)) {
                permute(taken, current, index + 1);
            }
            taken[i] = false;
        }
    }

    void process(int[] cols) {
        System.out.print("Board configuration: ");
        for (int colValue : cols) {
            System.out.print(colValue + " ");
        }
        System.out.println();
        if (!ALL) {
            // One solution is enough
            stop = true;
            processWithMoreBeautiful(cols);
        }
    }

    void processWithMoreBeautiful(int[] cols) {
        System.out.println("Board configuration");
        char[][] board = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
        };
        for (int c = 0; c < cols.length; c++) {
            board[cols[c]][c] = 'Q';
        }
        for (int r = 0; r < cols.length; r++) {
            System.out.println(new String(board[r]));
        }
    }

    boolean pruning(int[] current, int newIndex) {
        // Same row?
        // Do not need to check (why?)

        // Same diagonal - (bottom left) -> (top right)
        // And             (top left)    -> (bottom right)
        for (int col = newIndex - 1; col >= 0; col--) {
            if (Math.abs(current[col] - current[newIndex]) == Math.abs(newIndex - col)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Problem4 p = new Problem4();
        p.start();
    }
}
