import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class SpriteMap {

	private BufferedImage[][] sprites;
	private int col, row;

	public SpriteMap(String spritePath) {
		Image actualImage;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(spritePath));
		actualImage = ii.getImage();
		row = actualImage.getHeight(null) / Constants.SPRITE_HEIGHT;
		col= actualImage.getWidth(null) / Constants.SPRITE_WIDTH;
		sprites = new BufferedImage[row][col];
		for(int i=0;i<sprites.length;i++){
			for(int j=0;j<sprites[i].length;j++){
				sprites[i][j] = new BufferedImage(Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
				// (Imagen,posX,posY,tamañoCanvasX,tamañoCanvasY,VirtualXInit,VirtualYInit,VirtualXEnd,VirtualYEnd,Observer)
				sprites[i][j].createGraphics().drawImage(actualImage, 0, 0, 32, 32, 32 * j, 32*i, 32 * (j + 1), 32*(i+1), null);
			}
		}
		
		
		
	}

	public Image getSprite(int row, int col) {

		return (Image) sprites[row][col];
	}

}
