import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KBListener extends KeyAdapter {

	public static boolean isPressedUp, isPressedDown, isPressedLeft, isPressedRight, isPressedSpace, isPressedEsc;
	public static boolean locked;

	public static int keyUpDownArePressed() {

		return isPressedUp ? isPressedDown ? 11 : 10 : isPressedDown ? 01 : 00;
	}

	public static int keyLeftRightArePressed() {

		return isPressedLeft ? isPressedRight ? 11 : 10 : isPressedRight ? 01 : 00;
	}

	public static boolean keySpacePressed() {

		return isPressedSpace;
	}

	public static boolean keyEscPressed() {

		return isPressedEsc;
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		if ( key == KeyEvent.VK_Q ) {
			isPressedUp = false;
		}
		if ( key == KeyEvent.VK_A ) {
			isPressedDown = false;
		}
		if ( key == KeyEvent.VK_O ) {
			isPressedLeft = false;
		}
		if ( key == KeyEvent.VK_P ) {
			isPressedRight = false;
		}
		if ( key == KeyEvent.VK_SPACE ) {
			isPressedSpace = false;
		}
		if ( key == KeyEvent.VK_ESCAPE ) {
			isPressedEsc = false;
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if ( key == KeyEvent.VK_Q ) {
			isPressedUp = true;
		}
		if ( key == KeyEvent.VK_A ) {
			isPressedDown = true;
		}
		if ( key == KeyEvent.VK_O ) {
			isPressedLeft = true;
		}
		if ( key == KeyEvent.VK_P ) {
			isPressedRight = true;
		}
		if ( key == KeyEvent.VK_SPACE ) {
			isPressedSpace = true;
		}
		if ( key == KeyEvent.VK_ESCAPE ) {
			isPressedEsc = true;
		}

	}
}
