import java.awt.image.BufferedImage;



public class Animation {

	private int animationDelay;
	private boolean isPlaying;
	private BufferedImage[] animation;
	private Sprite object;

	public Animation(SpriteMap sprites, int delay, int[] frames) {

		animation = new BufferedImage[frames.length];
		for (int i = 0; i < frames.length; i++) {
			animation[i] = (BufferedImage) sprites.getSprite(frames[i] / 20, frames[i] % 20);
		}

	}

	public void startAnimation(Sprite s) {

		this.object = s;
		isPlaying = true;
	}
	public void stopAnimation() {

		this.object = null;
		isPlaying = false;
	}

	public boolean isAnimating() {

		return isPlaying;
	}

	public void animate() {
		
		if ( isPlaying ) {
			animationDelay++;
			object.setImage(animation[animationDelay / Constants.ANIMATION_SPEED]);
			
			if ( animationDelay >= animation.length * Constants.ANIMATION_SPEED - 1 ) {
				
				isPlaying = false;
				animationDelay = 0;

			}
		}
	}

}
