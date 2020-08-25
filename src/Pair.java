
public class Pair<F, S> {
	private F x;
	private S y;
	
	public Pair(F x1, S y1) {
		x = x1;
		y = y1;
	}
	public F getX() {
		return x;
	}
	public S getY() {
		return y;
	}
	
	public void setNewValues(F x2, S y2) {
		x = x2;
		y = y2;
	}
	
	public String toString() {
		return x.toString()+", "+y.toString();
	}
}
