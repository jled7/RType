 

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Nave extends Sprite implements Animated {

    private int speedX, speedY, directionX, directionY;
    private boolean isDead;
    private Image actualImage,original;
    private ArrayList<Animation> animations;
    private ArrayList<Disparo> disparos;
    private Animation animationActive;
    private Rectangle bounderieAnimating; // For better collision detection when
                                            // is animated

    public Nave(int positionX, int positionY, SpriteMap sprite) {

        super(positionX, positionY);
        actualImage = sprite.getSprite(1, 6);
        original = sprite.getSprite(1, 6);
        super.setImage(actualImage);
        setDimension(25, 31);
        /* bounderieAnimating = new Rectangle(x, y, width, height) */
        disparos = new ArrayList<Disparo>();

        // Animations
        animations = new ArrayList<Animation>();
        // EXPLOSION ANIMATION
        int explosionFrames[] = { 0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
        animations.add(new Animation(sprite, 10, explosionFrames));
        // MOVING ANIMATION
        int moveFrames[] = { 26, 25, 24, 23, 22, 21 };
        animations.add(new Animation(sprite, 10, moveFrames));
        int moveRevFrames[] = { 21, 22, 23, 24, 25, 26 };
        animations.add(new Animation(sprite, 10, moveRevFrames));

    }

    @Override
    public void update() {
        if(!isDead)
            move();
        updateAnimation(animations);
        Disparo.increaseShootDelay();

    }
    public void revive(){
        actualImage = original;
        setVisible(true);
        isDead = false;
    }

    public ArrayList<Disparo> getDisparos() {

        for (int i = 0; i < disparos.size(); i++) {
            if ( !disparos.get(i).isVisible() )
                disparos.remove(i);
        }
        return disparos;
    }

    private void move() {

        switch ( KBListener.keyUpDownArePressed() ) {
        case 00:
            if ( directionY != 0 ) {
                playAnimation(Constants.MOVETONORMAL_ANIMATION);
                directionY = 0;
            }
            speedY = directionY;
            break;
        case 01:
            if ( directionY != 1 ) {
                playAnimation(Constants.MOVE_ANIMATION);
                directionY = 1;
            }
            speedY = Constants.NAVE_SPEED;
            break;
        case 10:
            if ( directionY != -1 ) {
                playAnimation(Constants.MOVE_ANIMATION);
                directionY = -1;
            }
            speedY = -Constants.NAVE_SPEED;
            break;
        case 11:
            speedY = directionY * -Constants.NAVE_SPEED;
            break;
        }
        switch ( KBListener.keyLeftRightArePressed() ) {
        case 00:
            speedX = 0;
            break;
        case 01:
            directionX = 1;
            speedX = Constants.NAVE_SPEED;
            break;
        case 10:
            directionX = -1;
            speedX = -Constants.NAVE_SPEED;
            break;
        case 11:
            speedX = directionX * -Constants.NAVE_SPEED;
            break;
        }
        if(!(getPositionY()<0 || getPositionY()>550)){
            setPositionY(getPositionY() + speedY);
        }else{
            if(getPositionY()<0)
                setPositionY(0);
                else
                    setPositionY(550);
        }
           if(!(getPositionX()<0 || getPositionX()>950)){
            setPositionX(getPositionX() + speedX);
        }else{
            if(getPositionX()<0)
                setPositionX(0);
                else
                    setPositionX(950);
        }  
        
   
        if ( KBListener.keySpacePressed() ) {
            disparo();
        }
         
    }
    public void die(){
        playAnimation(Constants.EXPLOSION_ANIMATION);
        isDead = true;
    }
    private void disparo() {

        if ( Disparo.canShoot() ) {
            disparos.add(new Disparo(getPositionX() + 10, getPositionY()));
        }
    }

    @Override
    public Rectangle getBounderies() {

        if ( speedY != 0 )
            return new Rectangle(getPositionX() + 3, getPositionY() + 7, getWidth(), 20);
        return new Rectangle(getPositionX() + 3, getPositionY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Graphics2D g) {
        if(isVisible())
            g.drawImage(getImage(), getPositionX(), getPositionY(), null);
        g.setColor(Color.white);
        g.drawString("Bullets: " + disparos.size(), 50, 50);
        if ( isDead && animationActive == null ) {
            setVisible(false);
        }
        // g.drawRect(getPositionX(), getPositionY(), getWidth(), getHeight());
//      if ( speedY != 0 )
//          g.drawRect(getPositionX() + 3, getPositionY() + 7, getWidth(), 20);
//      else
//          g.drawRect(getPositionX() + 3, getPositionY(), getWidth(), getHeight());

    }

    @Override
    public void updateAnimation(ArrayList<Animation> a) {

        if ( animationActive != null ) {
            if ( animationActive.isAnimating() ) {
                animationActive.animate();
            } else {
                animationActive = null;
            }

        }
    }

    @Override
    public void playAnimation(int animationType) {
        
        animationActive = animations.get(animationType);
        animationActive.startAnimation(this);

    }

    @Override
    public void stopAnimation() {

        for (Animation a : animations) {
            a.stopAnimation();
        }

    }

}
