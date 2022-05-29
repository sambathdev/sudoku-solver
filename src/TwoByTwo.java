import java.util.Arrays;

public class TwoByTwo {
    int[][] board;
    TwoByTwo(int[][] _board) {
        this.board = _board;
    }
    public void solve() {
        String[][] note = {
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
        };
        boolean isFinish = false;
        while(!isFinish) {
            generateNote(board, note);
            boolean isSingleValue = false;
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    if (!note[i][j].isBlank() && !note[i][j].contains("-")) {
                        int number = Integer.parseInt(note[i][j]);
                        board[i][j] = number;
                        isSingleValue = true;
                    }
                }
            }
            // if (!isSingleValue) should check the unique of the possible numbers in the row
            // if the unique does not exist check in column
            // if the unique in column not exist check in block


            if (!isSingleValue) isFinish = true;
        }
        generateNote(board, note);
        printBoard(board);
        printNote(note);
    }

    static void generateNote(int[][] board, String[][] note) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                note[i][j] = "";
                if (board[i][j] == 0) {
                    for (int num = 1; num <= 4; ++num) {
                        int[] row = board[i];
                        int[] col = {board[0][j], board[1][j], board[2][j], board[3][j]};
                        int[] blockAxis = getBlockAxis(i, j);
                        int[] block = {board[blockAxis[0]][blockAxis[1]], board[blockAxis[0] + 1][blockAxis[1]], board[blockAxis[0]][blockAxis[1] + 1], board[blockAxis[0] + 1][blockAxis[1] + 1]};
                        boolean containInRow = contain(row, num);
                        boolean containInCol = contain(col, num);
                        boolean containInBlock = contain(block, num);
                        if (!containInRow && !containInCol && !containInBlock) {
                            String seperator;
                            if (note[i][j].isBlank()) seperator = "";
                            else seperator = "-";
                            note[i][j] += seperator + String.valueOf(num);
                        }
                    }
                }
            }
        }
    }
    public static int[] getBlockAxis(int i, int j) {
        int x;
        int y;
        if( i < 2) {
            // block 1 or 2
            if ( j < 2) {
                x = 0;
                y = 0;
            } else {
                x = 0;
                y = 2;
            }
        }else{
            // block 3 or 4
            if ( j < 2) {
                x = 2;
                y = 0;
            } else {
                x = 2;
                y = 2;
            }
        }
        int[] pair = {x, y};
        return pair;
    }

    public static boolean contain(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    static void printNote(String[][] board) {
        for (int i = 0; i < 4; ++i) {
            System.out.println( Arrays.toString(board[i]) );
        }
    }
    static void printBoard(int[][] board) {
        for (int i = 0; i < 4; ++i) {
            System.out.println( Arrays.toString(board[i]) );
        }
    }

}
