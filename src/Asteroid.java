import java.awt.Color;
import java.awt.Graphics;

public class Asteroid {
	private int row,col;
	
	public Asteroid(int r, int c) {
		row = r;
		col = c;
	}
	public void draw(Graphics g) {
		Color c = new Color(150,75,0);
		g.setColor(c);
		g.fillRect(row,col, 25, 25);
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
