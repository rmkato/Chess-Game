import java.util.LinkedList;

public class rook extends piece{

	public rook(int c, int x, int y, int t) {
		super(c, x, y, t);
	}
	public LinkedList<Pair<Integer, Integer>> availableMoves(chessboard c) {
		LinkedList<Pair<Integer, Integer>> moves = new LinkedList<Pair<Integer, Integer>>();
		Pair<Integer,Integer> p;
		int n;
		if (x_loc-1 >= 0 && c.boardSpaces[x_loc-1][y_loc].checkPiece() != color) { 
			n = -1;
			while (x_loc+n >= 0) {			// move up 
				if (c.boardSpaces[x_loc+n][y_loc].has_piece) {
					if (c.boardSpaces[x_loc+n][y_loc].checkPiece() != color) {
						p = new Pair<Integer,Integer>(x_loc+n, y_loc);
						if (checkMoveValidity(x_loc, y_loc, p, c)) {
							moves.add(p);
							break;
						}
					}
					break;
				}
				p = new Pair<Integer,Integer>(x_loc+n, y_loc);
				if (checkMoveValidity(x_loc, y_loc, p, c)) 
					moves.add(p);
				n--;
			}
		}
		if (x_loc+1 <= 7 && c.boardSpaces[x_loc+1][y_loc].checkPiece() != color) {
			n = 1;
			while (x_loc+n <= 7) {			// move down
				if (c.boardSpaces[x_loc+n][y_loc].has_piece) {
					if (c.boardSpaces[x_loc+n][y_loc].checkPiece() != color) {
						p = new Pair<Integer,Integer>(x_loc+n, y_loc);
						if (checkMoveValidity(x_loc, y_loc, p, c)) {
							moves.add(p);
							break;
						}
					}
					break;
				}
				p = new Pair<Integer,Integer>(x_loc+n, y_loc);
				if (checkMoveValidity(x_loc, y_loc, p, c)) 
					moves.add(p);
				n++;
			}
		}
		if (y_loc-1 >= 0 && c.boardSpaces[x_loc][y_loc-1].checkPiece() != color) {
			n = -1;
			while (y_loc+n >= 0) {			// move left
				if (c.boardSpaces[x_loc][y_loc+n].has_piece) {
					if (c.boardSpaces[x_loc][y_loc+n].checkPiece() != color) {
						p = new Pair<Integer,Integer>(x_loc, y_loc+n);
						if (checkMoveValidity(x_loc, y_loc, p, c)) {
							moves.add(p);
							break;
						}
					}
					break;
				}
				p = new Pair<Integer,Integer>(x_loc, y_loc+n);
				if (checkMoveValidity(x_loc, y_loc, p, c))
					moves.add(p);
				n--;
			}
		}
		if (y_loc+1 <= 7 && c.boardSpaces[x_loc][y_loc+1].checkPiece() != color) {
			n = 1;
			while (y_loc+n <= 7) {			// move right 
				if (c.boardSpaces[x_loc][y_loc+n].has_piece) {
					if (c.boardSpaces[x_loc][y_loc+n].checkPiece() != color) {
						p = new Pair<Integer,Integer>(x_loc, y_loc+n);
						if (checkMoveValidity(x_loc, y_loc, p, c)) {
							moves.add(p);
							break;
						}
					}
					break;
				}
				p = new Pair<Integer,Integer>(x_loc, y_loc+n);
				if (checkMoveValidity(x_loc, y_loc, p, c)) 
					moves.add(p);
				n++;
			}
		}
		return moves;
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
