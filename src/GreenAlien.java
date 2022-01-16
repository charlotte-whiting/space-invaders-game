import java.awt.Color;
import java.awt.Graphics;

public class GreenAlien extends Alien{
	
	public GreenAlien(int r, int c) {
		super(r, c);
		super.setType("Green");
	}
	
	public void draw(Graphics g) {
		Color c = Color.GREEN;
		g.setColor(c);
		g.fillRect(super.getRow(), super.getCol(), 50, 50);
	}
	
}
