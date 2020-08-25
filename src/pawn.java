import java.util.LinkedList;

public class pawn extends piece {
	boolean has_moved; 
	
	public pawn(int c, int x, int y, int t) {
		super(c, x, y, t);
	}
	
	public LinkedList<Pair<Integer, Integer>> availableMoves(chessboard c) {
		LinkedList<Pair<Integer, Integer>> moves = new LinkedList<Pair<Integer, Integer>>();
		Pair<Integer,Integer> p;
		if (color == 0) {
			if (x_loc-1 > 0 && !c.boardSpaces[x_loc-1][y_loc].has_piece) {
				if (x_loc == 6 && !c.boardSpaces[x_loc-2][y_loc].has_piece) {
					p = new Pair<Integer,Integer>(x_loc-2, y_loc);
					if (checkMoveValidity(x_loc, y_loc, p, c))
						moves.add(p);
				}
				p = new Pair<Integer,Integer>(x_loc-1, y_loc);
				if (checkMoveValidity(x_loc, y_loc, p, c))
					moves.add(p);
			}
		} else {	
			if (x_loc+1 < 8 && !c.boardSpaces[x_loc+1][y_loc].has_piece) {
				if (x_loc == 1 && !c.boardSpaces[x_loc+2][y_loc].has_piece) {
					p = new Pair<Integer,Integer>(x_loc+2, y_loc);
					if (checkMoveValidity(x_loc, y_loc, p, c))
						moves.add(p);
				}
				p = new Pair<Integer,Integer>(x_loc+1, y_loc);
				if (checkMoveValidity(x_loc, y_loc, p, c))
					moves.add(p);
			}
		}
		if (color == 0) {
			if (x_loc-1 > 0) {
				if (y_loc-1 >= 0 && c.boardSpaces[x_loc-1][y_loc-1].has_piece && c.boardSpaces[x_loc-1][y_loc-1].p.color != color) {
					p = new Pair<Integer,Integer>(x_loc-1, y_loc-1);
					if (checkMoveValidity(x_loc, y_loc, p, c)) {
						moves.add(p);
					}
				}
				if (y_loc+1 < 8 && c.boardSpaces[x_loc-1][y_loc+1].has_piece && c.boardSpaces[x_loc-1][y_loc+1].p.color != color) {
					p = new Pair<Integer,Integer>(x_loc-1, y_loc+1);
					if (checkMoveValidity(x_loc, y_loc, p, c))
						moves.add(p);
				}
			}
		} else {
			if (x_loc+1 < 8) {	
				if (y_loc-1 >= 0 && c.boardSpaces[x_loc+1][y_loc-1].has_piece && c.boardSpaces[x_loc+1][y_loc-1].p.color != color) {
					p = new Pair<Integer,Integer>(x_loc+1, y_loc-1);
					if (checkMoveValidity(x_loc, y_loc, p, c))
						moves.add(p);
				}
				if (y_loc+1 < 8 && c.boardSpaces[x_loc+1][y_loc+1].has_piece && c.boardSpaces[x_loc+1][y_loc+1].p.color != color) {
					p = new Pair<Integer,Integer>(x_loc+1, y_loc+1);
					if (checkMoveValidity(x_loc, y_loc, p, c))
						moves.add(p);
				}	
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
