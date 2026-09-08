import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SudokuSolver {
    private final int M = 3;
    private final int N = M * M;
    private int[][] grid;
    private ArrayList<Set<Integer>> rows = new ArrayList<>();
    private ArrayList<Set<Integer>> cols = new ArrayList<>();
    private ArrayList<Set<Integer>> squares = new ArrayList<>();
    private Set<Integer> nums = new HashSet<>();

    public SudokuSolver(String fileName) {
        // read the puzzle file
        try (Scanner in = new Scanner(new File(fileName))) {

            this.grid = new int[N][N];

            for (int row = 0; row < N; row++) {
                String line = in.next();

                for (int col = 0; col < N; col++) {
                    String strVal = line.substring(col, col + 1);
                    int number;
                    if (strVal.equals("x")) {
                        number = 0;
                    } else {
                        number = Integer.parseInt(strVal);
                    }
                    this.grid[row][col] = number;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open: " + fileName);
        }

        // create the list of sets for each row (this.rows)
        // ...
        rows = new ArrayList<>();
        for (int n = 0; n < grid.length; n++) {
            this.rows.add(new HashSet<>());
            for (int p = 0; p < grid[0].length; p++) {
                if (grid[n][p] != 0) {
                    this.rows.get(n).add(grid[n][p]);
                }
            }
        }

        // create the list of sets for each col (this.cols)
        // ...

        cols = new ArrayList<>();
        for (int n = 0; n < grid.length; n++) {
            this.cols.add(new HashSet<>());
            for (int p = 0; p < grid[0].length; p++) {
                if (grid[p][n] != 0) {
                    this.cols.get(n).add(grid[p][n]);
                }
            }
        }

        // create the list of sets for each square (this.squares)
        /* the squares are added to the list row-by-row:
            0 1 2
            3 4 5
            6 7 8
         */
        // ...
        squares = new ArrayList<>();
        for (int squareRow = 0; squareRow < M; squareRow++) {
            for (int squareCol = 0; squareCol < M; squareCol++) {
                this.squares.add(new HashSet<>());
                for (int row = squareRow * M; row < (squareRow + 1) * M; row++) {
                    for (int col = squareCol * M; col < (squareCol + 1) * M; col++) {
                        if (grid[row][col] != 0) {
                            this.squares.get(squareRow * M + squareCol).add(grid[row][col]);
                        }
                    }
                }
            }
        }
        


        // create a hash set for [1..9] (this.nums)
        // ...
        this.nums = new HashSet<>();
        for(int i = 1; i <= 9; i++) {
            this.nums.add(i);
        }

        // visually inspect that all the sets are correct
        for (int row = 0; row < N; row++) {
            System.out.println("row " + row + ": " + this.rows.get(row));
        }
        for (int col = 0; col < N; col++) {
            System.out.println("col " + col + ": " + this.cols.get(col));
        }
        for (int square = 0; square < N; square++) {
            System.out.println("square " + square + ": " + this.squares.get(square));
        }
        System.out.println(this.nums);
    }

    public boolean solve() {
        // find an empty location, if any
        boolean finished = true;
        int nextRow = -1;
        int nextCol = -1;
        for (int row = 0; row < N && finished; row++) {
            for (int col = 0; col < N && finished; col++) {
                if (this.grid[row][col] == 0) {
                    finished = false;
                    nextRow = row;
                    nextCol = col;
                }
            }
        }

        // the board is complete; we solved it
        if (finished) {
            return true;
        }

        // get all possible numbers for the row and column we are trying to populate
        /*
            Create a new set based on the this.nums and remove all elements in the sets
            corresponding to nextRow, nextCol, and the corresponding square (use the
            removeAll method).

            Properly indexing the squares list of sets is tricky. Verify that your
            algorithm is correct.
         */
        Set<Integer> possibleNums = new HashSet<Integer>();
        possibleNums.addAll(this.nums);
        for (int n : this.rows.get(nextRow)) {
            possibleNums.remove(n);
        }
        for (int n : this.cols.get(nextCol)) {
            possibleNums.remove(n);
        }
        for (int n : this.squares.get((nextRow / M) * M + nextCol / M)) {
            possibleNums.remove(n);
        }
        
        // ...

        // if there are no possible numbers, we cannot solve the board in its current state
        if (possibleNums.isEmpty()) {
            return false;
        }

        // try each possible number
        for (Integer possibleNum : possibleNums) {
            rows.get(nextRow).add(possibleNum);
            cols.get(nextCol).add(possibleNum);
            squares.get((nextRow / M) * M + nextCol / M).add(possibleNum);
            grid[nextRow][nextCol] = possibleNum;

            // recursively solve the board
            if (this.solve()) {
                // the board is solved!
                return true;
            } else {
                /*
                 Undo the move before trying another possible number by setting the corresponding
                 element in the grid back to 0 and removing possibleNum from all three corresponding
                 sets.
                 */
                // ...
                rows.get(nextRow).remove(possibleNum);
                cols.get(nextCol).remove(possibleNum);
                squares.get((nextRow / M) * M + nextCol / M).remove(possibleNum);
                grid[nextRow][nextCol] = 0;
            }
        }

        return false;
    }

    public String toString() {
        String str = "";

        for (int[] row : grid) {
            for (int val : row) {
                str += val + "\t";
            }

            str += "\n";
        }

        return str;
    }

    public int mapCellToSquare(int row, int col)
    {
        /*
         * Given the specific row and column in the grid, return the index for the
         *  corresponding square set in the list
        */ 

        

        return (row / M) * M + col / M;
    }

    public static void main(String[] args) {
        String fileName = "Sudoku/src/puzzle1.txt";

        SudokuSolver solver = new SudokuSolver(fileName);
        System.out.println(solver);
        if (solver.solve()) {
            System.out.println("Solved!");
            System.out.println(solver);
        } else {
            System.out.println("Unsolveable...");
        }
    }
}