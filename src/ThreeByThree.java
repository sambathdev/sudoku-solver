import java.util.Arrays;

public class ThreeByThree {
    int[][] board;
    ThreeByThree(int[][] _board) {
        this.board = _board;
    }
    public void solve() {
        String[][] note = {
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
        };
        boolean isFinish = false;
        while(!isFinish) {
            generateNote(board, note);
            boolean isSingleValue = false;
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (!note[i][j].isBlank() && !note[i][j].contains("-")) {
                        int number = Integer.parseInt(note[i][j]);
                        board[i][j] = number;
                        isSingleValue = true;
                    }
                }
            }
            generateNote(board, note);
            if (!isSingleValue) {
                for (int i = 0; i < 9; ++i) {
                    // loop through row and column
                    String noteRow = note[i][0] + "-" + note[i][1] + "-" + note[i][2] + "-" + note[i][3] + "-" + note[i][4] + "-" + note[i][5] + "-" + note[i][6] + "-" + note[i][7] + "-" + note[i][8];
                    String noteCol = note[0][i] + "-" + note[1][i] + "-" + note[2][i] + "-" + note[3][i] + "-" + note[4][i] + "-" + note[5][i] + "-" + note[6][i] + "-" + note[7][i] + "-" + note[8][i];
                    System.out.println(noteRow);
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
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                note[i][j] = "";
                if (board[i][j] == 0) {
                    for (int num = 1; num <= 9; ++num) {
                        int[] row = board[i];
                        int[] col = {board[0][j], board[1][j], board[2][j], board[3][j], board[4][j], board[5][j], board[6][j], board[7][j], board[8][j]};
                        int[] blockAxis = getBlockAxis(i, j);
                        int x = blockAxis[0];
                        int y = blockAxis[1];
                        int[] block = {
                                board[x][y], board[x + 1][y], board[x + 2][y],
                                board[x][y + 1], board[x + 1][y + 1], board[x + 2][y + 1],
                                board[x][y + 2], board[x + 1][y + 2], board[x + 2][y + 2],
                        };
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
        int x = 0;
        int y = 0;
        if ( 0 <= i && i <= 2) {
            // top 3
            if (0 <= j && j <= 2) {
                x = 0;
                y = 0;
            } else if (3 <= j && j <= 5) {
                x = 0;
                y = 3;
            } else if (6 <= j && j <= 8) {
                x = 0;
                y = 6;
            }
        }
        if ( 3 <= i && i <= 5) {
            if (0 <= j && j <= 2) {
                x = 3;
                y = 0;
            } else if (3 <= j && j <= 5) {
                x = 3;
                y = 3;
            } else if (6 <= j && j <= 8) {
                x = 3;
                y = 6;
            }
        }
        if ( 6 <= i && i <= 8) {
            // bottom 3
            if (0 <= j && j <= 2) {
                x = 6;
                y = 0;
            } else if (3 <= j && j <= 5) {
                x = 6;
                y = 3;
            } else if (6 <= j && j <= 8) {
                x = 6;
                y = 6;
            }
        }
        int[] pair = {x, y};
        return pair;
    }

    public static boolean contain(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    static void printNote(String[][] board) {
        for (int i = 0; i < 9; ++i) {
            System.out.println( Arrays.toString(board[i]) );
        }
    }
    static void printBoard(int[][] board) {
        for (int i = 0; i < 9; ++i) {
            System.out.println( Arrays.toString(board[i]) );
        }
    }

}
