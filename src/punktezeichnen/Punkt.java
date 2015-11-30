package punktezeichnen;

/**
 * Diese Klasse repraesentiert einen Punkt mit seinen (x,y)-Koordinaten.
 * 
 * @author Lukas Schramm
 * @version 1.0
 * 
 */
public class Punkt {
	
	private int x = 0;
	private int y = 0;
	
	public Punkt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}