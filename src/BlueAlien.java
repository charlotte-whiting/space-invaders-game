import java.awt.Color;
import java.awt.Graphics;

public class BlueAlien extends Alien{
	public BlueAlien(int r, int c) {
		super(r,c);
		super.setType("Blue");
	}
	
	public void draw(Graphics g) {
		Color c = Color.BLUE;
		g.setColor(c);
		g.fillRect(super.getRow(), super.getCol(), 50, 50);
	}

}
