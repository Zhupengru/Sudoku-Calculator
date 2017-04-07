//sudoku
public class LeetSudoku {
	public static void main(String[] args){
		char[][] board = {{'.','.','.',  '.','.','.',  '.','.','.'},
						  {'.','.','.',  '.','.','.',  '.','8','.'},
						  {'6','.','.',  '5','.','.',  '.','.','.'},
						  
						  {'.','.','7',  '.','.','2',  '.','9','3'},
						  {'.','.','.',  '.','.','.',  '.','5','2'},
						  {'.','5','.',  '.','.','.',  '.','.','.'},
						  
						  {'.','.','.',  '.','.','.',  '.','.','.'},
						  {'.','1','.',  '.','.','.',  '.','.','.'},
						  {'.','.','.',  '.','5','.',  '.','7','.'}};
		System.out.println(isValidSudoku(board));
	}
	public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.') 
                    if (!check(board, i, j, board[i][j])) return false;
            }
        }
        return true;
    }
    public static boolean check(char[][] board, int row, int col, char c){
        for (int i = 0; i < 9; i++){
            if (board[row][i] != '.' && board[row][i] == c && i != col) return false;
            if (board[i][col] != '.' && board[i][col] == c && i != row) return false;
            if (board[i / 3 + (row / 3) * 3][i % 3 + (col / 3) * 3] != '.' &&
                board[i / 3 + (row / 3) * 3][i % 3 + (col / 3) * 3] == c &&
                ((i / 3 + (row / 3) * 3) != row ||
                (i % 3 + (col / 3) * 3) != col)) return false;
        }
        return true;
    }
}
