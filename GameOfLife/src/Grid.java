import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Grid extends JPanel {
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 300, 150);
	}
}
