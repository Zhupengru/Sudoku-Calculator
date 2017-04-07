//Solve the SUDOKU
public class Sudoku {
	static boolean finish = false;
	static int[][] result = new int[9][9];
	static int count = 0;
	public static void main(String[] args){
		int[][] matrix = {{0,0,0,  0,0,5,  6,0,0},
			              {0,2,0,  0,0,0,  0,0,0},
			              {0,0,0,  0,2,7,  0,0,5},
					 
					      {0,6,0,  1,0,3,  0,8,0},
					      {0,0,0,  0,0,8,  5,6,0},
					      {0,0,0,  7,0,2,  0,1,3},
					 
					      {0,0,6,  0,0,0,  0,5,0},
					      {0,0,2,  0,0,1,  4,0,0},
					      {4,7,0,  3,0,0,  0,0,2}};
		
		int[][][] potentialSolution = new int[9][9][10];
		setPotential(matrix, potentialSolution);
		/*for (int i = 1; i < 10; i++)
			System.out.print(potentialSolution[3][0][i]+" ");
		System.out.println("\n");*/
		
		int[][] degree = new int[9][9];
		calculateDegree(potentialSolution, degree);
		/*for (int i = 0; i< 9; i++){
			for (int j = 0; j < 9; j++){
				System.out.print(degree[i][j]+" ");
			}
			System.out.println();
		}*/
		
		singleFill(matrix, degree, potentialSolution);
		if (ifFullfill(matrix)) {
			System.out.println("Done!");
			printMatrix(matrix);
			return;
		}
		int starti = 0,startj = 0;
		for (int i = 8; i >= 0; i--){
			for (int j = 8; j >= 0; j--){
				if (matrix[i][j] == 0) {
					starti = i; startj = j;
				}
			}
		}		
		recursionFill(matrix, potentialSolution, starti,startj);
		if (finish);
		else{
			System.out.println("no result");
		}
		
		
		
	}
	
	public static void calculateDegree(int[][][] potential, int[][] degree){
		for (int i = 0; i< 9; i++){
			for (int j = 0; j < 9; j++){
				int cnt = 0;
				for (int k = 1; k < 10; k++){
					if (potential[i][j][k] == 0) cnt++;
				}
				degree[i][j] = cnt;
			}
		}
	}
	
	public static void setPotential(int[][] matrix, int[][][] potential){
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				for (int k = 1; k < 10; k++)
					potential[i][j][k] = 0;
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				//check if origin
				if (matrix[i][j] > 0) 
					for (int k = 1; k < 10; k++)
						potential[i][j][k] = 1;
				//check column and row
				for (int k = 0; k < 9; k++){
					if (matrix[k][j] > 0) potential[i][j][matrix[k][j]] = 1;
					if (matrix[i][k] > 0) potential[i][j][matrix[i][k]] = 1;
				}
				
				//check square
				if(i % 3 == 0 && j % 3 == 0){
					for (int m = 0; m < 3; m++){
						for (int n = 0; n < 3; n++){
							if (matrix[i+m][j+n] > 0) potential[i][j][matrix[i+m][j+n]] = 1;
						}
					}
				}
				if(i % 3 == 0 && j % 3 == 1){
					for (int m = 0; m < 3; m++){
						for (int n = -1; n < 2; n++){
							if (matrix[i+m][j+n] > 0) potential[i][j][matrix[i+m][j+n]] = 1;
						}
					}
				}
				if(i % 3 == 0 && j % 3 == 2){
					for (int m = 0; m < 3; m++){
						for (int n = -2; n < 1; n++){
							if (matrix[i+m][j+n] > 0) potential[i][j][matrix[i+m][j+n]] = 1;
						}
					}
				}
				if(i % 3 == 1 && j % 3 == 0){
					for (int m = -1; m < 2; m++){
						for (int n = 0; n < 3; n++){
							if (matrix[i+m][j+n] > 0) potential[i][j][matrix[i+m][j+n]] = 1;
						}
					}
				}
				if(i % 3 == 1 && j % 3 == 1){
					for (int m = -1; m < 2; m++){
						for (int n = -1; n < 2; n++){
							if (matrix[i+m][j+n] > 0) potential[i][j][matrix[i+m][j+n]] = 1;
						}
					}
				}
				if(i % 3 == 1 && j % 3 == 2){
					for (int m = -1; m < 2; m++){
						for (int n = -2; n < 1; n++){
							if (matrix[i+m][j+n] > 0) potential[i][j][matrix[i+m][j+n]] = 1;
						}
					}
				}
				if(i % 3 == 2 && j % 3 == 0){
					for (int m = -2; m < 1; m++){
						for (int n = 0; n < 3; n++){
							if (matrix[i+m][j+n] > 0) potential[i][j][matrix[i+m][j+n]] = 1;
						}
					}
				}
				if(i % 3 == 2 && j % 3 == 1){
					for (int m = -2; m < 1; m++){
						for (int n = -1; n < 2; n++){
							if (matrix[i+m][j+n] > 0) potential[i][j][matrix[i+m][j+n]] = 1;
						}
					}
				}
				if(i % 3 == 2 && j % 3 == 2){
					for (int m = -2; m < 1; m++){
						for (int n = -2; n < 1; n++){
							if (matrix[i+m][j+n] > 0) potential[i][j][matrix[i+m][j+n]] = 1;
						}
					}
				}
				
			}
		}
	}
	
	public static void singleFill(int[][] matrix, int[][] degree, int[][][] potential){
		boolean canContinue = true;
		while(canContinue){
			for (int i = 0; i < 9; i++){
				for (int j = 0; j < 9; j++){
					if (degree[i][j] == 1) {
						for (int k = 1; k < 10; k++)
							if (potential[i][j][k] == 0){
								potential[i][j][k] = 1;
								matrix[i][j] = k;
							}
					}
				}
			}
			setPotential(matrix, potential);
			calculateDegree(potential, degree);
			canContinue = false;
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					if (degree[i][j] == 1) canContinue = true;
		}
	}
	
	public static void recursionFill(int[][] matrix, int[][][] potential, int i, int j){
		if (ifFullfill(matrix)) {
			printMatrix(matrix);
			finish = true;
			count++;
			return;
		}
		if (count == 1)
			for (int m = 0; m < 9; m++){
				for (int n = 0 ; n < 9; n++)
					result[m][n] = matrix[i][j];
			}
		if (finish) return;
		
		for (int k = 1; k < 10; k++){
			if (potential[i][j][k] == 0){
				int ii = i, jj = j;
				matrix[i][j] = k;
				//printMatrix(matrix);
				setPotential(matrix, potential);
				for (int m = 8; m >= 0; m--){
					for (int n = 8; n >= 0; n--){
						if (matrix[m][n] == 0) {
							i = m; j = n;
						}
					}
				}
				recursionFill(matrix, potential, i, j);
				i = ii; j = jj;
				matrix[i][j] = 0;
				setPotential(matrix, potential);
			}
		}
		//return;
	}
	
	public static boolean ifFullfill(int[][] matrix){
		boolean fullfill = true;
		for (int i = 0; i< 9; i++)
			for (int j = 0; j < 9; j++)
				if (matrix[i][j] == 0) fullfill = false;
		return fullfill;
	}
	
	public static void printMatrix(int[][] matrix){
		for (int i = 0; i< 9; i++){
			if (i % 3 == 0 && i > 0) System.out.println();
			for (int j = 0; j < 9; j++){
				if (j % 3 == 0) System.out.print("  ");
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("\n");
	}
}
