import java.awt.Color;
import java.awt.Graphics;

public class RedAlien extends Alien{
	
	public RedAlien(int r, int c) {
		super(r,c);
		super.setType("Red");
	}
	
	public void draw(Graphics g) {
		Color c = Color.RED;
		g.setColor(c);
		g.fillRect(super.getRow(), super.getCol(), 50, 50);
	}

}
