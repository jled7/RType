import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Sprite implements Entity, Drawable {

    private int positionX, positionY, width, height;
    private Image image;
    private boolean isVisible;
    private SpriteMap sprite;

    public Sprite(int positionX, int positionY) {
        isVisible = true;
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public Sprite(int positionX, int positionY, Image image) {
        isVisible = true;
        this.positionX = positionX;
        this.positionY = positionY;
        this.image = image;
    }
    public Sprite(int positionX, int positionY, SpriteMap sprite) {
        isVisible = true;
        this.positionX = positionX;
        this.positionY = positionY;
        this.sprite = sprite;
    }

    @Override
    public void setDimension(int width, int height) {

        setDimension(new Dimension(width, height));
    }

    @Override
    public void setDimension(Dimension d) {

        this.height = d.height;
        this.width = d.width;

    }

    @Override
    public int getWidth() {

        return this.width;
    }

    @Override
    public int getHeight() {    

        return this.height;
    }

    public Image getImage() {

        return this.image;
    }
    public Image getImage(int row,int col){
        return sprite.getSprite(row,col);
       }

    public void setImage(Image image) {

        this.image = image;

    }

    @Override
    public int getPositionX() {

        return this.positionX;
    }

    @Override
    public void setPositionX(int positionX) {
this.positionX = positionX;

    }

    @Override
    public int getPositionY() {
return this.positionY;  }

    @Override
    public void setPositionY(int positionY) {

        this.positionY = positionY;

    }

    @Override
    public boolean isVisible() {

        return isVisible;
    }

    @Override
    public void setVisible(boolean isVisible) {

        this.isVisible = isVisible;

    }

    @Override
    public abstract void update();

    public abstract Rectangle getBounderies();
    
    @Override
    public abstract void draw(Graphics2D g);

}
