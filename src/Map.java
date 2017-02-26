import java.awt.Dimension;
import java.awt.Graphics2D;

public abstract class Map implements Entity, Drawable {

	private int positionX; 
	private boolean isVisible;


	@Override
	public void setDimension(int width, int height) {
		;
	}

	@Override
	public void setDimension(Dimension d) {
		;
	}

	@Override
	public int getWidth() {

		return Constants.WINDOW_WIDTH;
	}

	@Override
	public int getHeight() {

		return Constants.WINDOW_HEIGHT;
	}

	

	@Override
	public int getPositionX() {

		return positionX;
	}

	@Override
	public void setPositionX(int positionX) {

		this.positionX = positionX;

	}

	@Override
	public int getPositionY() {

		return 0;
	}

	@Override
	public void setPositionY(int positionY) {

		;

	}

	@Override
	public boolean isVisible() {

		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public void setVisible(boolean isVisible) {

		this.isVisible = isVisible;

	}

	@Override
	public abstract void update();

	@Override
	public abstract void draw(Graphics2D g);


}
