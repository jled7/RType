import java.awt.Dimension;


public interface Entity
{
    /**
     * An example of a method header - replace this comment with your own
     * 
     * @param  y    a sample parameter for a method
     * @return        the result produced by sampleMethod 
     */
    public void setDimension(int width, int height);

    public void setDimension(Dimension d);

    public int getWidth();

    public int getHeight();

    public int getPositionX();

    public void setPositionX(int positionX);

    public int getPositionY();

    public void setPositionY(int positionY);

    public boolean isVisible() ;

    public void setVisible(boolean isVisible);
    
    public abstract void update();
    
}