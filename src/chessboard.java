import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class chessboard {	
	boardSpace[][] boardSpaces = new boardSpace[8][8];
	LinkedList<Pair<Integer,Integer>> highlightSpaces = new LinkedList<Pair<Integer,Integer>>();
	JFrame f;
	JPanel p;
	JLabel label;
	Pair<Integer,Integer> bking;
	Pair<Integer,Integer> wking;
	int turn;
	boolean check; 
	boolean stale;
	boardSpace currentPiece; 
	
	public chessboard() {
		f = new JFrame("chessboard");
		p = new JPanel();
		label = new JLabel("Turn: White");
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(Color.BLACK));
		label.setPreferredSize(new Dimension(900, 50));
		p.setLayout(new GridLayout(8,8));
		p.setPreferredSize(new Dimension(900, 900));
		f.add(p, BorderLayout.CENTER);
		f.add(label, BorderLayout.NORTH);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		initBoardState();
		f.setBounds(50, 50, 900, 950);
		f.setVisible(true);
		
		turn = -1; // set turn to initialize in changeTurn()
		changeTurn();
		check = false; 
		stale = false;
		wking = new Pair<Integer,Integer>(7,3);
		bking = new Pair<Integer,Integer>(0,3);
	}
	
	private boolean checkCheckmate() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardSpaces[i][j].has_piece && boardSpaces[i][j].p.color == turn)
					if (boardSpaces[i][j].p.availableMoves(this).size() != 0)
						return false;
			}
		}
		label.setText("Checkmate - "+ ((turn == 1) ? "White wins!" : "Black wins!")); 
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardSpaces[i][j].has_piece)
					removeListeners(boardSpaces[i][j], this);
			}
		}
		return true;
	}
	
	private void stalemate() {
		label.setText("No available moves for either player; stalemate"); 
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardSpaces[i][j].has_piece)
					removeListeners(boardSpaces[i][j], this);
			}
		}
	}
	
	// Checks the gamestate to determine if check has occurred (for the current turn's player) 
	// Also checks for stalemate
	private boolean isChecked() {
		int x = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardSpaces[i][j].has_piece && boardSpaces[i][j].p.color != turn) {
					LinkedList<Pair<Integer,Integer>> l = boardSpaces[i][j].p.availableMoves(this);
					if (l.size() > 1) x = 1;
					for (int k = 0; k < l.size(); k++) {
						if (l.get(k).getX() == ((turn == 0) ? wking.getX() : bking.getX()) && l.get(k).getY() == ((turn == 0) ? wking.getY() : bking.getY())) 
							return true;
					}
				}
			}
		}
		if (x==0) {
			if (stale) {
				stalemate();
				System.out.println("Stalemate");
			} else {
				stale = true; 
				System.out.println("Stale set to true");
			}
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private boolean isStale() {
		// Need to check for stalemate state (availableMoves = 0, switch turns, availableMoves = 0 again) 
		return false; 
	}
	
	// Used in isValidMove()
	public void makeMove(int x1, int y1, int x2, int y2, piece p) {	
		boardSpaces[x2][y2].addPiece(p);				// move piece p to the new location 
		
		if (boardSpaces[x2][y2].p.type == 5) {			// update w/bking if king was moved 
			if (boardSpaces[x2][y2].p.color == 0)
				wking.setNewValues(x2, y2);
			else 
				bking.setNewValues(x2, y2);	
		}		
		
		boardSpaces[x1][y1].removePiece();				// and remove p from the old location
		boardSpaces[x2][y2].p.updateLocation(x2, y2);	// set the new coordinates for p
	}
	
	// Used in isValidMove()
	public void makeMove(int x1, int y1, int x2, int y2, piece p, piece replacedPiece) {	
		boardSpaces[x2][y2].addPiece(p);				// move piece p to the new location 
		
		if (boardSpaces[x2][y2].p.type == 5) {			// update w/bking if king was moved 
			if (boardSpaces[x2][y2].p.color == 0)
				wking.setNewValues(x2, y2);
			else 
				bking.setNewValues(x2, y2);	
		}		
		
		boardSpaces[x1][y1].removePiece();				// and remove p from the old location
		boardSpaces[x2][y2].p.updateLocation(x2, y2);	// set the new coordinates for p
		boardSpaces[x1][y1].addPiece(replacedPiece);	// replace the old piece
	}
	
	public boolean isValidMove(int x1, int y1, int x2, int y2, piece p) {
		// If white's turn, white move must not put white king in check state
		boolean return_value = true;
		piece removedPiece = null;
		if (boardSpaces[x2][y2].has_piece);
			removedPiece = boardSpaces[x2][y2].p;
		
		makeMove(x1, y1, x2, y2, p);		// try making the move
		if (turn == 0) {  					// and check for 'check' afterwards 
			if (boardSpaces[wking.getX()][wking.getY()].p.checked(this)) {
				return_value = false; 
			}
		} else {
			if (boardSpaces[bking.getX()][bking.getY()].p.checked(this)) {
				return_value = false; 
			}
		}
		
		if (removedPiece == null)			// reset board to its original state 
			makeMove(x2, y2, x1, y1, p);
		else
			makeMove(x2, y2, x1, y1, p, removedPiece);
	
		return return_value; 				// if no 'check', will return true 
	}
	
	// Sets the button listeners to the pieces of whoever's turn it is and removes listeners from the opposing player's pieces 
	private void changeTurn() {
		setHighlight();  	// call setHighlight() with no parameters in order to clear the highlighted spaces
		if (turn == -1) 
			turn = 0; 		//set turn to white to initialize
		else {
			turn = (turn == 0) ? 1 : 0; 
			check = (isChecked()) ? true : false;	// Checks to see if a check state exists
			if (check) 
				if (checkCheckmate())
					return;
			if (stale) changeTurn(); 				// If no moves are available, changes turn to the other player
		}
		label.setText((check) ? ((turn == 0) ? "Turn: White, Checked" : "Turn: Black, Checked") : ((turn == 0) ? "Turn: White" : "Turn: Black"));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardSpaces[i][j].has_piece) {
					if (boardSpaces[i][j].p.color == turn)
						addHighlightListener(boardSpaces[i][j], this);
					else 
						removeListeners(boardSpaces[i][j], this);
				}
			}
		}
	}
	
	// Adds listener to show valid move locations with highlight
	private void addHighlightListener(boardSpace b, chessboard c) {
		b.button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentPiece != null) {
					currentPiece.button.setBackground(currentPiece.col);
					currentPiece = null;
					//System.out.println("Set currentPiece to null");
				}
				LinkedList<Pair<Integer, Integer>> l = b.p.availableMoves(c);
				setHighlight(l);
				currentPiece = b;
				//System.out.println("set currentPiece to "+ b.getSymbol());
				currentPiece.button.setBackground(Color.cyan);
			}
		});
	}
		
	// Adds listener to a valid move location to accept a move 
	private void addMoveListener(boardSpace b, chessboard c) {
		b.button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(currentPiece.getSymbol()+" moved");
				if (b.has_piece) {				// remove the opponent's piece 
					b.removePiece();
				}
				b.addPiece(currentPiece.p);
				
				if (b.p.type == 5) {
					if (b.p.color == 0)
						wking.setNewValues(b.x_location, b.y_location);
					else 
						bking.setNewValues(b.x_location, b.y_location);	
				}		
				
				removeListeners(boardSpaces[currentPiece.p.x_loc][currentPiece.p.y_loc], c);
				boardSpaces[currentPiece.p.x_loc][currentPiece.p.y_loc].button.setBackground(boardSpaces[currentPiece.p.x_loc][currentPiece.p.y_loc].col);
				boardSpaces[currentPiece.p.x_loc][currentPiece.p.y_loc].removePiece();	// and remove piece from old location
				b.p.updateLocation(b.x_location, b.y_location);
				currentPiece = null;
				changeTurn();					// change to other player's turn 
				//System.out.println("called changeTurn()");
			}
		});
	}
	
	private void removeListeners(boardSpace b, chessboard c) {
		for (ActionListener a : b.button.getActionListeners())
			b.button.removeActionListener(a);
	}
	
	// highlights a list of coordinates (moves available for a selected piece)
	private void setHighlight(LinkedList<Pair<Integer, Integer>> l) {
		if (highlightSpaces.size() > 0) {
			Pair<Integer,Integer> p;
			while (highlightSpaces.size() > 0) {
				p = highlightSpaces.pop();
				boardSpaces[p.getX()][p.getY()].button.setBackground(boardSpaces[p.getX()][p.getY()].col);
				removeListeners(boardSpaces[p.getX()][p.getY()], this);
			}
		}
		Pair<Integer,Integer> p1;
		for (int i = 0; i < l.size(); i++) {
			p1 = l.get(i);
			boardSpaces[p1.getX()][p1.getY()].button.setBackground(Color.yellow);
			highlightSpaces.add(l.get(i));
			addMoveListener(boardSpaces[p1.getX()][p1.getY()], this);
		}
		if (currentPiece != null) {
			currentPiece.button.setBackground(currentPiece.col);
			currentPiece = null;
		}
	}
	
	// call with no parameters to clear all highlights
	private void setHighlight() {	
		if (highlightSpaces.size() > 0) {
			Pair<Integer,Integer> p;
			while (highlightSpaces.size() > 0) {
				p = highlightSpaces.pop();
				boardSpaces[p.getX()][p.getY()].button.setBackground(boardSpaces[p.getX()][p.getY()].col);
				removeListeners(boardSpaces[p.getX()][p.getY()], this);
			}
		}
	}
	
	// Initializes the chessboard to starting position
	private void initBoardState() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0) {
					if (j == 0 || j == 7) {
						boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new rook(1,i,j,2), this);
						boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/b_rook.png")));
					} else if (j == 1 || j == 6) {
						boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new knight(1,i,j,3), this);
						boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/b_knight.png")));
					} else if (j == 2 || j == 5) {
						boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new bishop(1,i,j,4), this);
						boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/b_bishop.png")));
					} else if (j == 3) {
						boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new king(1,i,j,5), this);
						boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/b_king.png")));
					} else if (j == 4) {
						boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new queen(1,i,j,6), this);
						boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/b_queen.png")));
					}
				} else if (i == 1) {
					boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new pawn(1,i,j,1), this);
					boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/b_pawn.png")));
				} else if (i == 6) {
					boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new pawn(0,i,j,1), this);
					boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/w_pawn.png")));
				} else if (i == 7) {
					if (j == 0 || j == 7) {
						boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new rook(0,i,j,2), this);
						boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/w_rook.png")));
					} else if (j == 1 || j == 6) {
						boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new knight(0,i,j,3), this);
						boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/w_knight.png")));
					} else if (j == 2 || j == 5) {
						boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new bishop(0,i,j,4), this);
						boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/w_bishop.png")));
					} else if (j == 3) {
						boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new king(0,i,j,5), this);
						boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/w_king.png")));
					} else if (j == 4) {
						boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p, new queen(0,i,j,6), this);
						boardSpaces[i][j].button.setIcon(new ImageIcon(getClass().getResource("/res/w_queen.png")));
					}
				} else 
					boardSpaces[i][j] = new boardSpace(i,j,new JButton(""), p);
			}
		}
	}
	
	// Used for debugging purposes, prints a visual depiction of the board
	public void printCurrentState() {
		System.out.println("Current turn: "+ ((turn == 0) ? "white" : "black"));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardSpaces[i][j].has_piece)
					System.out.print(boardSpaces[i][j].piece_type+"("+boardSpaces[i][j].p.color+") ");
				else 
					System.out.print(boardSpaces[i][j].piece_type+"    ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		new chessboard();
	}
}