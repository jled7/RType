import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Disparo extends Sprite {

	private static int disparoDelay;

	public Disparo(int positionX, int positionY) {

		super(positionX, positionY);
		setImage(Board.sprites.getSprite(1, 7));
		setDimension(11, 11);
		disparoDelay = 0;
	}

	@Override
	public void update() {

		if ( getPositionX() > Constants.WINDOW_WIDTH ) {
			setVisible(false);
		}
		setPositionX(getPositionX() + Constants.BULLET_SPEED);

	}

	public static boolean canShoot() {

		return disparoDelay >= Constants.BULLET_DELAY;
	}

	public static void increaseShootDelay() {

		disparoDelay++;
	}

	@Override
	public Rectangle getBounderies() {

		// To center Rectangle X+10,y+10
		return new Rectangle(getPositionX() + 10, getPositionY() + 10, getWidth(), getHeight());
	}

	@Override
	public void draw(Graphics2D g) {

		if ( isVisible() ) {
			g.drawImage(getImage(), getPositionX(), getPositionY(), null);
			update();
		}

	}

}
