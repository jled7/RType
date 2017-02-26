import java.util.ArrayList;


public interface Animated {

	public void updateAnimation(ArrayList<Animation> a);
	public void playAnimation(int animationType);
	public void stopAnimation();
}
