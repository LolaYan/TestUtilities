package javaCoding.Matrix;

import java.util.HashSet;

public class isValidSudoku {
	
	
	
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9)
			return false;
		
		// check each column
		for (int i = 0; i < 9; i++) {
			boolean[] m = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					if (m[(int) (board[i][j] - '1')]) {
						return false;
					}
					m[(int) (board[i][j] - '1')] = true;
				}
			}
		}

		// check each row
		for (int j = 0; j < 9; j++) {
			boolean[] m = new boolean[9];
			for (int i = 0; i < 9; i++) {
				if (board[i][j] != '.') {
					if (m[(int) (board[i][j] - '1')]) {
						return false;
					}
					m[(int) (board[i][j] - '1')] = true;
				}
			}
		}

		// check each 3*3 matrix
		for (int block = 0; block < 9; block++) {
			boolean[] m = new boolean[9];
			for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
				for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
					if (board[i][j] != '.') {
						if (m[(int) (board[i][j] - '1')]) {
							return false;
						}
						m[(int) (board[i][j] - '1')] = true;
					}
				}
			}
		}

		return true;
	}
	public void solveSudoku(char[][] board) {
	    solve(board);
	}
	 
	public boolean solve(char[][] board){
	    for(int i=0; i<9; i++){
	        for(int j=0; j<9; j++){
	            if(board[i][j]!='.')
	                continue;
	 
	            for(int k=1; k<=9; k++){
	                board[i][j] = (char) (k+'0');
	                if(isValid(board, i, j) && solve(board))
	                    return true;
	                board[i][j] = '.';    
	            }
	 
	            return false;
	        }
	    }
	 
	    return true; // does not matter
	}
	 
	public boolean isValid(char[][] board, int i, int j){
	    HashSet<Character> set = new HashSet<Character>();
	 
	    for(int k=0; k<9; k++){
	        if(set.contains(board[i][k]))
	            return false;
	 
	        if(board[i][k]!='.' ){
	            set.add(board[i][k]);
	        }
	    }
	 
	    set.clear();
	 
	    for(int k=0; k<9; k++){
	        if(set.contains(board[k][j]))
	            return false;
	 
	        if(board[k][j]!='.' ){
	            set.add(board[k][j]);
	        }
	    }
	 
	    set.clear();
	 
	    for(int m=0; m<3; m++){
	        for(int n=0; n<3; n++){
	            int x=i/3*3+m;
	            int y=j/3*3+n;
	            if(set.contains(board[x][y]))
	                return false;
	 
	            if(board[x][y]!='.'){
	                set.add(board[x][y]);
	            }    
	        }
	    }
	 
	    return true;
	}
}
