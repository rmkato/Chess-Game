import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* This class holds the JButton for each space on the chessboard
It can return the x/y location of the space and whether or not
it holds a piece */

public class boardSpace {
	int x_location;
	int y_location;
	JButton button;
	boolean has_piece;
	piece p; 
	int piece_type;
	Color col; 
	
	public boardSpace(int x, int y, JButton b, JPanel panel) {
		x_location = x;
		y_location = y;
		button = b;
		has_piece = false;
		p = null;
		piece_type = 0;
		col = ((x+y)%2 == 0) ? Color.white : Color.lightGray;
		button.setBackground(col);
		panel.add(button);
	}
	
	//public boardSpace(int x, int y, JButton b, JFrame f, piece new_p, chessboard c) {
	public boardSpace(int x, int y, JButton b, JPanel panel, piece new_p, chessboard c) {
		x_location = x;
		y_location = y;
		button = b;
		p = new_p;
		has_piece = true;
		piece_type = p.type;
		col = ((x+y)%2 == 0) ? Color.white : Color.lightGray;
		button.setBackground(col);
		panel.add(button);
	}
	
	// boardSpace constructor for vChessboard
	public boardSpace(int x, int y, piece new_p) {
		x_location = x;
		y_location = y;
		p = new_p;
		has_piece = true;
		piece_type = p.type;
		button = new JButton();
	}
	
	// constructor for vChessboard (empty space)
	public boardSpace(int x, int y) {
		x_location = x;
		y_location = y;
		has_piece = false;
		p = null;
		piece_type = 0;
		button = new JButton();
	}
	
	public int checkPiece() {
		if (p == null)
			return -1;
		return p.color;
	}
	
	public void addPiece(piece new_piece) {
		p = new_piece;
		has_piece = true;
		piece_type = p.type;
		//button.setText(getSymbol());
		setImage(p.type);
	}
	
	public void setImage(int t) {
		if (t == 1)
			button.setIcon(new ImageIcon(getClass().getResource((p.color == 0) ? "/res/w_pawn.png" : "/res/b_pawn.png")));
		else if (t == 2)
			button.setIcon(new ImageIcon(getClass().getResource((p.color == 0) ? "/res/w_rook.png" : "/res/b_rook.png")));
		else if (t == 3)
			button.setIcon(new ImageIcon(getClass().getResource((p.color == 0) ? "/res/w_knight.png" : "/res/b_knight.png")));
		else if (t == 4)
			button.setIcon(new ImageIcon(getClass().getResource((p.color == 0) ? "/res/w_bishop.png" : "/res/b_bishop.png")));
		else if (t == 5)
			button.setIcon(new ImageIcon(getClass().getResource((p.color == 0) ? "/res/w_king.png" : "/res/b_king.png")));
		else if (t == 6)
			button.setIcon(new ImageIcon(getClass().getResource((p.color == 0) ? "/res/w_queen.png" : "/res/b_queen.png")));	
	}
	
	public void removePiece() {
		if (!has_piece)
			return;
		p = null;
		has_piece = false;
		piece_type = 0;
		//button.setText("");
		button.setIcon(null);
	}
	
	public String getSymbol() {
		if (piece_type == 1)
			return (p.color == 0) ? "P" : "p";
		else if (piece_type == 2)
			return (p.color == 0) ? "R" : "r";
		else if (piece_type == 3)
			return (p.color == 0) ? "K" : "k";
		else if (piece_type == 4)
			return (p.color == 0) ? "B" : "b";
		else if (piece_type == 5)
			return (p.color == 0) ? "Ki" : "ki";
		else if (piece_type == 6)
			return (p.color == 0) ? "Q" : "q";
		return "";	
	}
}
