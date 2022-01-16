import java.awt.Color;
import java.awt.Graphics;

public class Alien {
	private int row,col;
	private String type;
	
	public Alien(int r, int c) {
		row = r;
		col = c;
	}
	
	public void draw(Graphics g) {
		Color c = Color.BLUE;
		g.setColor(c);
		g.fillRect(row, col, 50, 50);
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
	
	public void setType(String s) {
		type = s;
	}
	
	public String getType() {
		return type;
	}
}
