import java.awt.Color;
import java.awt.Graphics;

public class Laser {
	int row,col;
	
	public Laser(int r, int c) {
		row = r;
		col = c;
	}
	
	public void draw(Graphics g) {
		Color c = Color.ORANGE;
		g.setColor(c);
		g.fillRect(row, col, 10, 25);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setRow(int r) {
		row = r;
	}
	
	public void setCol(int c) {
		col = c;
	}
}
