import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Honors {
	
	
	
	private class Move{
		private int row = 0;
		private int column = 0;
		private int num_moves = 0;
		
		private Move(int row, int column, int num_moves) {
			this.row = row;
			this.column = column;
			this.num_moves = num_moves;
		}
	}
	
	public static int min_moves(int[][] board) {
		ArrayList<Move> queue = new ArrayList<Move>();
		//int visited = 10;
		int[][] visited = new int[board.length][board[0].length];
		int moves = -1;
		Honors honors = new Honors();
		queue.add(honors.new Move(0,0,0));
		visited[0][0] = board[0][0] ;
		int num_rows = board.length - 1;
		int num_columns = board[0].length - 1;
		while (queue.size() != 0) {
			
			Move move = queue.remove(0);
			int jumps = board[move.row][move.column];
			
			 if(move.row == num_rows && move.column == num_columns) {
				
				moves = move.num_moves;
				break;
				
				
			} else {
			
				if(move.column - jumps >= 0) {
					if(visited[move.row][move.column - jumps] == 0) {
						if(move.row == num_rows && move.column - jumps == num_columns) {
							moves = move.num_moves + 1;
							break;
							
						} else {
						
						queue.add(honors.new Move(move.row,move.column - jumps,move.num_moves+1));
						visited[move.row][move.column - jumps] = board[move.row][move.column - jumps];
					}
				}
			}
				
				if(move.column + jumps <= num_columns) {
					if(visited[move.row][move.column + jumps] == 0) {
						if(move.row == num_rows && move.column + jumps == num_columns) {
							moves = move.num_moves + 1;
							break;
						} else {
						
						queue.add(honors.new Move(move.row,move.column + jumps,move.num_moves+1));
						visited[move.row][move.column + jumps] = board[move.row][move.column + jumps];
					}
				}
			}
				
				if(move.row - jumps >= 0) {
					if(visited[move.row - jumps][move.column] == 0) {
						if(move.row - jumps == num_rows && move.column == num_columns) {
							moves = move.num_moves + 1;
							break;
						} else {
						queue.add(honors.new Move(move.row - jumps,move.column,move.num_moves+1));
						visited[move.row - jumps][move.column] = board[move.row - jumps][move.column];
					}
				}
			}
				
				if(move.row + jumps <= num_rows) {
					if(visited[move.row + jumps][move.column] == 0) {
						if(move.row - jumps == num_rows && move.column == num_columns) {
							moves = move.num_moves + 1;
							break;
						} else {
						queue.add(honors.new Move(move.row + jumps,move.column,move.num_moves+1));
						visited[move.row + jumps][move.column] = board[move.row + jumps][move.column];
					}
				}
			}
				
				
				
				
			
			
		}
		}
		
		return moves;
	}

	public void test(String filename) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(filename));
		int num_rows = sc.nextInt();
		int num_columns = sc.nextInt();
		int [][]board = new int[num_rows][num_columns];
		for (int i=0; i<num_rows; i++) {
			char line[] = sc.next().toCharArray();
			for (int j=0; j<num_columns; j++) {
				board[i][j] = (int)(line[j]-'0');
			}
			
		}
		sc.close();
		int answer = min_moves(board);
		System.out.println(answer);
	}

	public static void main(String[] args) throws FileNotFoundException{
		Honors honors = new Honors();
		honors.test(args[0]); // run 'javac Honors.java' to compile, then run 'java Honors testfilename'
		
		// or you can test like this
		// int [][]board = {1,2,3}; // not actual example
		// int answer = min_moves(board); 
		// System.out.println(answer);
	}

}

