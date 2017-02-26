import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class UFO extends Sprite implements Animated {

    private ArrayList<Animation> animations;
    private Animation animationActive;
    private boolean isDead;

    public UFO(int positionX, int positionY, SpriteMap sprite) {
       
        super(positionX, positionY);
        setImage(Board.sprites.getSprite(1, 8));
        setDimension(15, 20);

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

        updateAnimation(animations);
        if ( !isDead ) {
            if ( getPositionX() < -20 )
                setPositionX(Constants.WINDOW_WIDTH);

            move();

        }
        if ( isDead && animationActive == null ) {
            setVisible(false);
        }

    }

    @Override
    public Rectangle getBounderies() {

        return new Rectangle(getPositionX() + 10, getPositionY() + 5, getWidth(), getHeight());
    }

    @Override
    public void draw(Graphics2D g) {

        if ( isVisible() ) {
            g.drawImage(getImage(), getPositionX(), getPositionY(), null);
            update();
        }

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

    public void checkCollision(ArrayList<Disparo> disparos) {

        for (Disparo bullet : disparos)
            checkCollision(bullet);

    }

    public boolean checkCollision(Disparo bullet) {

        if ( isVisible() && !isDead ) {
            if ( getBounderies().intersects(bullet.getBounderies()) ) {
                playAnimation(Constants.EXPLOSION_ANIMATION);
                bullet.setVisible(false);
                isDead = true;
                return true;
            }
        }
        return false;
    }
    public boolean checkCollision(Nave nave){
        if(isVisible() && !isDead) {
            if(getBounderies().intersects(nave.getBounderies())){
                nave.die();
                isDead = true;
                return true;
            }
        }
        return false;
    }

    protected abstract void move();
}
