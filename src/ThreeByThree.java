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
            boolean isUniqueValue = false;
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
                    generateNote(board, note);
                    String noteRow = note[i][0] + "," + note[i][1] + "," + note[i][2] + "," + note[i][3] + "," + note[i][4] + "," + note[i][5] + "," + note[i][6] + "," + note[i][7] + "," + note[i][8];
                    for (int j = 0; j < 9; ++j) {
                        generateNote(board, note);
                        String noteCol = note[0][j] + "," + note[1][j] + "," + note[2][j] + "," + note[3][j] + "," + note[4][j] + "," + note[5][j] + "," + note[6][j] + "," + note[7][j] + "," + note[8][j];
                        int[] blockAxis = getBlockAxis(i, j);
                        int x = blockAxis[0];
                        int y = blockAxis[1];
                        String noteBlock = note[x][y] + "," + note[x][y + 1] + "," + note[x][y + 2] + "," + note[x + 1][y] + "," + note[x + 1][y + 1] + "," + note[x + 1][y + 2] + "," + note[x + 2][y] + "," + note[x + 2][y + 1] + "," + note[x + 2][y + 2];
                        // now we have [ noteRow, noteCol, noteBlock] which contain all possible numbers
                        // check uniqueness of each type (row col and block)
//                        System.out.println(noteBlock);

                        // TODO check col here but check the case if the i is 0 (on one iteration)
                        if (i == 0) {
                            int indexCol = 0;
                            for(int k = 0; k < noteCol.length(); ++k) {
                                char ch = noteCol.charAt(k);
                                if (ch == ',') ++indexCol;
                                if (isUniqueInString(noteCol, ch)) {
                                    board[indexCol][j] = Character.getNumericValue(ch);
                                    isUniqueValue = true;
                                }
                            }
                        }
                        // TODO check block here but check the case if x % 3 == 0 && y % 3 == 0
                        if (x % 3 == 0 && y % 3 == 0) {
                            // 0 3 6
                            int indexBlock = 0;
                            for(int k = 0; k < noteBlock.length(); ++k) {
                                char ch = noteBlock.charAt(k);
                                if (ch == ',') ++indexBlock;
                                if (isUniqueInString(noteBlock, ch)) {
                                    // where am i at block ?
                                    int subRow = indexBlock < 3 ? 0 : indexBlock < 6?1:2;
                                    int subCol = indexBlock % 3;
                                    if (x == 0 && y == 0) {
                                        //block 1
                                        board[subRow][subCol] = Character.getNumericValue(ch);
                                        isUniqueValue = true;
                                    }
                                    if (x == 0 && y == 3) {
                                        //block 2
                                        board[subRow][3 + subCol] = Character.getNumericValue(ch);
                                        isUniqueValue = true;
                                    }
                                    if (x == 0 && y == 6) {
                                        //block 3
                                        board[subRow][6 + subCol] = Character.getNumericValue(ch);
                                        isUniqueValue = true;
                                    }

                                    if (x == 3 && y == 0) {
                                        //block 4
                                        board[3 + subRow][subCol] = Character.getNumericValue(ch);
                                        isUniqueValue = true;
                                    }
                                    if (x == 3 && y == 3) {
                                        //block 5
                                        board[3+ subRow][3 + subCol] = Character.getNumericValue(ch);
                                        isUniqueValue = true;
                                    }
                                    if (x == 3 && y == 6) {
                                        //block 6
                                        board[3+ subRow][6 + subCol] = Character.getNumericValue(ch);
                                        isUniqueValue = true;
                                    }

                                    if (x == 6 && y == 0) {
                                        //block 7
                                        board[6 + subRow][subCol] = Character.getNumericValue(ch);
                                        isUniqueValue = true;
                                    }
                                    if (x == 6 && y == 3) {
                                        //block 8
                                        board[6 + subRow][3 + subCol] = Character.getNumericValue(ch);
                                        isUniqueValue = true;
                                    }
                                    if (x == 6 && y == 6) {
                                        //block 9
                                        board[6 + subRow][6 + subCol] = Character.getNumericValue(ch);
                                        isUniqueValue = true;
                                    }
                                }
                            }
                        }
                    }
                    System.out.println(noteRow);
                    // TODO check row here
                    int indexRow = 0;
                    for(int k = 0; k < noteRow.length(); ++k) {
                        char ch = noteRow.charAt(k);
                        if (ch == ',') ++indexRow;
                        if (isUniqueInString(noteRow, ch)) {
                            board[i][indexRow] = Character.getNumericValue(ch);
                            isUniqueValue = true;
                        }
                    }
                }
                if (!isUniqueValue) {
                    isFinish = true;
                }
            }

            // if (!isSingleValue) should check the unique of the possible numbers in the row
            // if the unique does not exist check in column
            // if the unique in column not exist check in block

        }
        generateNote(board, note);
        printBoard(board);
        printNote(note);
    }

    static boolean isUniqueInString(String string, char ch) {
        int count = 0;
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == ch) {
                ++count;
            }
        }
        if (count == 1) return true;
        return false;
    }

    static String getUniquesInString(String noteRow) {
        String uniques = "";
        for (int i = 0; i < noteRow.length(); ++i) {
            boolean isUnique = true;
            for (int j = i+1; j < noteRow.length() - 1; ++j) {
                if (noteRow.charAt(i) == noteRow.charAt(j)) isUnique = false;
            }
            if (isUnique) {
                uniques += noteRow.charAt(i);
            }
        }
        return uniques;
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
