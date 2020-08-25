import java.util.LinkedList;

public abstract class piece {
	int color;  // 0 = white, 1 = black
	int x_loc;
	int y_loc;
	int type;   // 0 = empty, 1 = pawn, 2 = rook, 3 = knight, 4 = bishop, 5 = king, 6 = queen
	
	public piece(int c, int x, int y, int t) {
		color = c;
		x_loc = x;
		y_loc = y;
		type = t;
	}
	
	public void updateLocation(int new_x, int new_y) {
		x_loc = new_x;
		y_loc = new_y;
	}
	
	public abstract LinkedList<Pair<Integer, Integer>> availableMoves(chessboard c);
	public abstract boolean checkMoveValidity(int x, int y, Pair<Integer,Integer> p, chessboard c);
	public abstract boolean checked(chessboard c);
}
