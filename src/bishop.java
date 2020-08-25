import java.util.LinkedList;

public class bishop extends piece {

	public bishop(int c, int x, int y, int t) {
		super(c, x, y, t);
	}
	public LinkedList<Pair<Integer, Integer>> availableMoves(chessboard c) {
		LinkedList<Pair<Integer, Integer>> moves = new LinkedList<Pair<Integer, Integer>>();
		Pair<Integer,Integer> p; 
		int n, m;
		
		if (x_loc-1 >= 0 && y_loc-1 >= 0 && c.boardSpaces[x_loc-1][y_loc-1].checkPiece() != color) {   // move up-left 
			n = -1; 
			while (x_loc+n >= 0 && y_loc+n >= 0) {
				if (c.boardSpaces[x_loc+n][y_loc+n].has_piece) {
					if (c.boardSpaces[x_loc+n][y_loc+n].checkPiece() != color) {
						p = new Pair<Integer,Integer>(x_loc+n, y_loc+n);
						if (checkMoveValidity(x_loc, y_loc, p, c)) {
							moves.add(p);
							break;
						}
					} 
					break;
				}
				p = new Pair<Integer,Integer>(x_loc+n, y_loc+n);
				if (checkMoveValidity(x_loc, y_loc, p, c)) 
					moves.add(p);
				n--;
			}
		}
		if (x_loc-1 >= 0 && y_loc+1 <= 7 && c.boardSpaces[x_loc-1][y_loc+1].checkPiece() != color) {   // move up-right
			n = -1;
			m = 1;
			while (x_loc+n >= 0 && y_loc+m <= 7) {
				if (c.boardSpaces[x_loc+n][y_loc+m].has_piece) {
					if (c.boardSpaces[x_loc+n][y_loc+m].checkPiece() != color) {
						p = new Pair<Integer,Integer>(x_loc+n, y_loc+m);
						if (checkMoveValidity(x_loc, y_loc, p, c)) {
							moves.add(p);
							break;
						}
					} 
					break;
				}
				p = new Pair<Integer,Integer>(x_loc+n, y_loc+m);
				if (checkMoveValidity(x_loc, y_loc, p, c)) 
					moves.add(p);
				n--;
				m++;
			}
		}
		if (x_loc+1 <= 7 && y_loc-1 >= 0 && c.boardSpaces[x_loc+1][y_loc-1].checkPiece() != color) {   // move down-left
			n = 1;
			m = -1;
			while (x_loc+n <= 7 && y_loc+m >= 0) {
				if (c.boardSpaces[x_loc+n][y_loc+m].has_piece) {
					if (c.boardSpaces[x_loc+n][y_loc+m].checkPiece() != color) {
						p = new Pair<Integer,Integer>(x_loc+n, y_loc+m);
						if (checkMoveValidity(x_loc, y_loc, p, c)) {
							moves.add(p);
							break;
						}
					} 
					break;
				}
				p = new Pair<Integer,Integer>(x_loc+n, y_loc+m);
				if (checkMoveValidity(x_loc, y_loc, p, c)) 
					moves.add(p);
				n++;
				m--;
			}
		}
		if (x_loc+1 <= 7 && y_loc+1 <= 7 && c.boardSpaces[x_loc+1][y_loc+1].checkPiece() != color) {   // move down-right
			n = 1;
			while (x_loc+n <= 7 && y_loc+n <= 7) {
				if (c.boardSpaces[x_loc+n][y_loc+n].has_piece) {
					if (c.boardSpaces[x_loc+n][y_loc+n].checkPiece() != color) {
						p = new Pair<Integer,Integer>(x_loc+n, y_loc+n);
						if (checkMoveValidity(x_loc, y_loc, p, c)) {
							moves.add(p);
							break;
						}
					} 
					break;
				}
				p = new Pair<Integer,Integer>(x_loc+n, y_loc+n);
				if (checkMoveValidity(x_loc, y_loc, p, c)) 
					moves.add(p);
				n++;
			}
		}
		return moves;
	}
	
	public LinkedList<Pair<Integer, Integer>> checkedMoves(chessboard c, Pair<Integer, Integer> king) {
		LinkedList<Pair<Integer, Integer>> moves = new LinkedList<Pair<Integer, Integer>>();
		
		return moves;
	}
	
	public boolean willCheck(int x, int y, Pair<Integer,Integer> p, chessboard c) {
		return false;
	}
	
	public boolean checkMoveValidity(int x, int y, Pair<Integer,Integer> p, chessboard c) {
		if (c.isValidMove(x, y, p.getX(), p.getY(), this))
			return true;
		return false;
	}
	
	public boolean checked(chessboard c) {
		return false;
	}
}