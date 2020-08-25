import java.util.LinkedList;

public class king extends piece {

	public king(int c, int x, int y, int t) {
		super(c, x, y, t);
	}
	
	public LinkedList<Pair<Integer, Integer>> availableMoves(chessboard c) {
		LinkedList<Pair<Integer, Integer>> moves = new LinkedList<Pair<Integer, Integer>>();	
		Pair<Integer,Integer> p;
		if (x_loc-1 >= 0 && c.boardSpaces[x_loc-1][y_loc].checkPiece() != color) {  // up
			p = new Pair<Integer,Integer>(x_loc-1, y_loc);
			if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
		}
		if (x_loc-1 >= 0 && y_loc-1 >= 0 && c.boardSpaces[x_loc-1][y_loc-1].checkPiece() != color) {// up-left
			p = new Pair<Integer,Integer>(x_loc-1, y_loc-1);
			if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
		}
		if (y_loc+1 <= 7 && c.boardSpaces[x_loc][y_loc-1].checkPiece() != color) {// left
			p = new Pair<Integer,Integer>(x_loc, y_loc-1);
			if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
		}
		if (x_loc+1 <= 7 && y_loc-1 >= 0 && c.boardSpaces[x_loc+1][y_loc-1].checkPiece() != color) {// down-left
			p = new Pair<Integer,Integer>(x_loc+1, y_loc-1);
			if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
		}
		if (x_loc+1 <= 7 && c.boardSpaces[x_loc+1][y_loc].checkPiece() != color) {// down
			p = new Pair<Integer,Integer>(x_loc+1, y_loc);
			if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
		}
		if (x_loc+1 <= 7 && y_loc+1 <= 7 && c.boardSpaces[x_loc+1][y_loc+1].checkPiece() != color) {// down-right
			p = new Pair<Integer,Integer>(x_loc+1, y_loc+1);
			if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
		}
		if (y_loc+1 <= 7 && c.boardSpaces[x_loc][y_loc+1].checkPiece() != color) {// right
			p = new Pair<Integer,Integer>(x_loc, y_loc+1);
			if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
		}
		if (x_loc-1 >= 0 && y_loc+1 <= 7 && c.boardSpaces[x_loc-1][y_loc+1].checkPiece() != color) {// up-right
			p = new Pair<Integer,Integer>(x_loc-1, y_loc+1);
			if (checkMoveValidity(x_loc, y_loc, p, c))
				moves.add(p);
		}
		return moves;
	}
	
	public boolean checkMoveValidity(int x, int y, Pair<Integer,Integer> p, chessboard c) {
		if (c.isValidMove(x, y, p.getX(), p.getY(), this))
			return true;
		return false;
	}
	
	// will check the board's state to see if the king is checked
	public boolean checked(chessboard c) {
		// up
		int n = -1;
		while (x_loc+n >= 0) {	
			if (c.boardSpaces[x_loc+n][y_loc].has_piece) {
				if (c.boardSpaces[x_loc+n][y_loc].p.color != color && (c.boardSpaces[x_loc+n][y_loc].piece_type == 2 || c.boardSpaces[x_loc+n][y_loc].piece_type == 6))
					return true;
				break;
			}
			n--;
		}
		// left
		n = -1;
		while (y_loc+n >= 0) {	
			if (c.boardSpaces[x_loc][y_loc+n].has_piece) {
				if (c.boardSpaces[x_loc][y_loc+n].p.color != color && (c.boardSpaces[x_loc][y_loc+n].piece_type == 2 || c.boardSpaces[x_loc][y_loc+n].piece_type == 6))
					return true;
				break;
			}
			n--;
		}
		// down
		n = 1; 
		while (x_loc+n <= 7) {	
			if (c.boardSpaces[x_loc+n][y_loc].has_piece) {
				if (c.boardSpaces[x_loc+n][y_loc].p.color != color && (c.boardSpaces[x_loc+n][y_loc].piece_type == 2 || c.boardSpaces[x_loc+n][y_loc].piece_type == 6))
					return true;
				break;
			}
			n++;
		}
		// right
		n = 1;
		while (y_loc+n <= 7) {	 
			if (c.boardSpaces[x_loc][y_loc+n].has_piece) {
				if (c.boardSpaces[x_loc][y_loc+n].p.color != color && (c.boardSpaces[x_loc][y_loc+n].piece_type == 2 || c.boardSpaces[x_loc][y_loc+n].piece_type == 6))
					return true;
				break;
			}
			n++;
		}
		// up-left
		n = -1; 
		while (x_loc+n >=0 && y_loc+n >= 0) {	
			if (c.boardSpaces[x_loc+n][y_loc+n].has_piece) {
				if (c.boardSpaces[x_loc+n][y_loc+n].p.color != color && (c.boardSpaces[x_loc+n][y_loc+n].piece_type == 4 || c.boardSpaces[x_loc+n][y_loc+n].piece_type == 6))
					return true;
				break;
			}
			n--;
		}
		// up-right
		n = -1; 
		int m = 1;
		while (x_loc+n >=0 && y_loc+m <= 7) {
			if (c.boardSpaces[x_loc+n][y_loc+m].has_piece) {
				if (c.boardSpaces[x_loc+n][y_loc+m].p.color != color && (c.boardSpaces[x_loc+n][y_loc+m].piece_type == 4 || c.boardSpaces[x_loc+n][y_loc+m].piece_type == 6))
					return true;
				break;
			}
			n--;
			m++;
		}
		// down-left
		n = 1; 
		m = -1;
		while (x_loc+n <= 7 && y_loc+m >= 0) {
			if (c.boardSpaces[x_loc+n][y_loc+m].has_piece) {
				if (c.boardSpaces[x_loc+n][y_loc+m].p.color != color && (c.boardSpaces[x_loc+n][y_loc+m].piece_type == 4 || c.boardSpaces[x_loc+n][y_loc+m].piece_type == 6))
					return true;
				break;
			}
			n++;
			m--;
		}
		// down-right
		n = 1; 
		while (x_loc+n <= 7 && y_loc+n <= 7) {	
			if (c.boardSpaces[x_loc+n][y_loc+n].has_piece) {
				if (c.boardSpaces[x_loc+n][y_loc+n].p.color != color && (c.boardSpaces[x_loc+n][y_loc+n].piece_type == 4 || c.boardSpaces[x_loc+n][y_loc+n].piece_type == 6))
					return true;
				break;
			}
			n++;
		}
		return false;
	}
}
