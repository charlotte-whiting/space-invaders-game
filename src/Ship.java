import java.awt.Color;
import java.awt.Graphics;

public class Ship {
	private int row,col;
	
	public Ship(int r, int c) {
		row = r;
		col = c;
	}
	
	public void draw(Graphics g) {
		Color c = Color.DARK_GRAY;
		g.setColor(c);
		g.fillRect(row,col, 50, 50);
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setCol(int c) {
		col = c;
	}
	
	public void setRow(int r) {
		row = r;
	}
}
