

public class Main {
    public static void main(String[] args) {
        int[][] board = {
                {0, 6, 0, 1, 0, 0, 7,4,9},
                {0,7,4,5,9,0,6,1,2},
                {0,2,0,0,0,0,3,5,0},
                {0,9,6,8,7,0,0,0,5},
                {5,0,0,0,0,0,0,7,6},
                {7,0,3,0,0,6,0,0,0},
                {6,5,8,4,0,0,0,0,0},
                {0,0,0,0,1,5,8,0,0},
                {9,1,0,6,8,0,0,0,4}
        };
        ThreeByThree threeByThree = new ThreeByThree(board);
        threeByThree.solve();
    }
}
