import java.util.LinkedList;

public class knight extends piece {

	public knight(int c, int x, int y, int t) {
		super(c, x, y, t);
	}
	public LinkedList<Pair<Integer, Integer>> availableMoves(chessboard c) {
		LinkedList<Pair<Integer, Integer>> moves = new LinkedList<Pair<Integer, Integer>>();
		Pair<Integer,Integer> p;
		if (x_loc <= 6) {
			if (x_loc <= 5) {
				if (y_loc-1 >= 0 && c.boardSpaces[x_loc+2][y_loc-1].checkPiece() != color) {
					p = new Pair<Integer,Integer>(x_loc+2, y_loc-1);
					if (checkMoveValidity(x_loc, y_loc, p, c))
					moves.add(p);
				}
				if (y_loc+1 <= 7 && c.boardSpaces[x_loc+2][y_loc+1].checkPiece() != color) {
					p = new Pair<Integer,Integer>(x_loc+2, y_loc+1);
					if (checkMoveValidity(x_loc, y_loc, p, c))
					moves.add(p);
				}
			}
			if (y_loc-2 >= 0 && c.boardSpaces[x_loc+1][y_loc-2].checkPiece() != color) {
				p = new Pair<Integer,Integer>(x_loc+1, y_loc-2);
				if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
				
			}
			if (y_loc+2 <= 7 && c.boardSpaces[x_loc+1][y_loc+2].checkPiece() != color) {
				p = new Pair<Integer,Integer>(x_loc+1, y_loc+2);
				if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
			}
		}
		if (x_loc >= 1) {
			if (x_loc >= 2) {
				if (y_loc-1 >= 0 && c.boardSpaces[x_loc-2][y_loc-1].checkPiece() != color) {
					p = new Pair<Integer,Integer>(x_loc-2, y_loc-1);
					if (checkMoveValidity(x_loc, y_loc, p, c))
					moves.add(p);
				}
				if (y_loc+1 <= 7 && c.boardSpaces[x_loc-2][y_loc+1].checkPiece() != color) {
					p = new Pair<Integer,Integer>(x_loc-2, y_loc+1);
					if (checkMoveValidity(x_loc, y_loc, p, c))
					moves.add(p);
				}
			}
			if (y_loc-2 >= 0 && c.boardSpaces[x_loc-1][y_loc-2].checkPiece() != color) {
				p = new Pair<Integer,Integer>(x_loc-1, y_loc-2);
				if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
			}
			if (y_loc+2 <= 7 && c.boardSpaces[x_loc-1][y_loc+2].checkPiece() != color) {
				p = new Pair<Integer,Integer>(x_loc-1, y_loc+2);
				if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
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