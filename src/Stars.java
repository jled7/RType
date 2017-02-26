 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Stars extends Map {

	private BufferedImage background, safeBackground;
	private Random magicNumber;
	private int positionX;
	
	public Stars() {
		positionX = 0;
		magicNumber = new Random();
		background = new BufferedImage(Constants.WINDOW_WIDTH * 2, Constants.WINDOW_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
		safeBackground = new BufferedImage(Constants.WINDOW_WIDTH * 2, Constants.WINDOW_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
		
		for (int i = 0; i < background.getWidth(); i++) {
			for (int j = 0; j < background.getHeight(); j++) {
				if ( magicNumber.nextInt(Constants.RANDOM_STAR_MAGIC_NUMBER) <= 1 ) {
					background.setRGB(i, j, Color.white.getRGB());
					safeBackground.setRGB(i, j, Color.white.getRGB());
				}
			}
		}
	}

	@Override
	public void update() {
		positionX -= 1;
		if(positionX<=-2048){
			positionX=0;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(background, positionX, 0, null);
		g.drawImage(safeBackground, positionX + 2048, 0, null);
	}

}
