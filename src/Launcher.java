import javax.swing.JFrame;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 1L;

	public Launcher() {

		add(new Board());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setTitle("RType v0.01");
		setVisible(true);
		setResizable(false);

	}

	public static void main(String[] args) {

		new Launcher();
	}

}