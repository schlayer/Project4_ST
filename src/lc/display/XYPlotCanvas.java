package lc.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

/**
 * An XYPlotCanvas plots a set of X,Y points where
 * both X and Y are between 0 and 1 and the display is
 * scaled to fill the window.
 */
public class XYPlotCanvas extends JComponent {
	
	public XYPlotCanvas() {
		super();
		setPreferredSize(new Dimension(1000, 500));
	}
	
	protected int lastx, lasty;
	
	public void drawScale() { // Adds percentile scale lines
		int w = getWidth(); int h = getHeight();
		Graphics g = getGraphics();
		g.setColor(Color.BLUE);
		
		for (int y = 0; y <= h; y += (h/10)) {
			g.drawLine(0, y, w, y);
			
		}
		
		g.setColor(Color.WHITE);
		
		for (int y = 0; y <= h; y+= (h/50)) {
			if (y % (h/10) != 0) {
				g.drawLine(0, y, w, y);
			}
		}
		
		g.setColor(Color.BLACK);
	}
	
	public void addPoint(double x, double y) {
		int xi = (int)(x * getWidth());
		int yi = (int)((1.0-y) * getHeight());
		getGraphics().drawLine(lastx, lasty, xi, yi);
		lastx = xi;
		lasty = yi;
	}
	
	@Override
	public void paintComponent(Graphics g) {
	}
	
}
