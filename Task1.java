// На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.

public class solution {

  public static void printMatrix(char[][] board) {
    int n = board.length;
    System.out.println("------------------------------");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(board[i][j] + "|");
      }
      System.out.println();
    }
    System.out.println("------------------------------");

  }

  public static void updateBoard(int row, int col, boolean[][] logicalBoard) {
    int n = logicalBoard.length;

    for (int j = 0; j < n; j++) {
      logicalBoard[row][j] = false;
    }

    for (int j = 0; j < n; j++) {
      logicalBoard[j][col] = false;
    }

    int r = row;
    int c = col;
    while (r >= 0 && c >= 0) {
      logicalBoard[r][c] = false;
      r--;
      c--;
    }

    r = row;
    c = col;
    while (r < 8 && c < 8) {
      logicalBoard[r][c] = false;
      r++;
      c++;
    }

    r = row;
    c = col;
    while (r < 8 && c >= 0) {
      logicalBoard[r][c] = false;
      r++;
      c--;
    }

    r = row;
    c = col;
    while (r >= 0 && c < 8) {
      logicalBoard[r][c] = false;
      r--;
      c++;
    }
  }

  public static char[][] copyArray(char[][] original) {
    char[][] copy = new char[original.length][];
    for (int i = 0; i < original.length; i++) {
      copy[i] = original[i].clone();
    }
    return copy;
  }

  public static boolean[][] copyArray(boolean[][] original) {
    boolean[][] copy = new boolean[original.length][];
    for (int i = 0; i < original.length; i++) {
      copy[i] = original[i].clone();
    }
    return copy;
  }

  public static void arrangeQueens(char[][] board, boolean[][] logicalBoard, int rowNumber) {
    if (rowNumber == 8) {
      printMatrix(board);
      return;
    }

    int n = board.length;
    for (int column = 0; column < n; column++) {
      if (logicalBoard[rowNumber][column]) {
        char[][] newBoard = copyArray(board);
        boolean[][] newLogicalBoard = copyArray(logicalBoard);
        newBoard[rowNumber][column] = 'Q';
        newLogicalBoard[rowNumber][column] = false;
        updateBoard(rowNumber, column, newLogicalBoard);
        arrangeQueens(newBoard, newLogicalBoard, rowNumber + 1);
      }
    }
  }

  public static void arrangeQueens() {
    int N = 8;
    char[][] board = new char[8][8];
    boolean[][] logicalBoard = new boolean[8][8];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        board[i][j] = ' ';
        logicalBoard[i][j] = true;
      }
    }
    arrangeQueens(board, logicalBoard, 0);
  }

  public static void main(String[] args){
    arrangeQueens();
  }

}
