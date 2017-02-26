import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private RenderingHints rh;
    private static Status status;
    private static Menu menu;

    private Stars backgroundStars;
    private Font font;

    private Nave player;
    private ArrayList<UFO> ufos;

    public static SpriteMap sprites;
    private static int numberOfUfos;
    private static boolean newGame;
    public static int dificultVSspeed;

    public Board() {
        // Set Initial State
        setStatus(Status.MENU);

        // Loading of SpriteMap
        loadSpriteMap();

        // Set initial Font
        font = new Font("Arial", Font.BOLD, 40);

        // Creating the Random Star background
        backgroundStars = new Stars();

        // Creation player
        player = new Nave(-30, 300, sprites);

        // Key-Listener and Double Buffer
        addKeyListener(new KBListener());
        setFocusable(true);
        setDoubleBuffered(true);
        rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Create MENU
        menu = new Menu();
        // Create UFOS
        // createUFOS();

        // TIMER //
        Timer timer = new Timer(Constants.TIMER_RATE, this);
        timer.start();
    }

    private void createUFOS(int cantidad) {

        ufos = new ArrayList<UFO>();
        for (int i = 0; i < cantidad; i++) {
            ufos.add(new UFOType2(900+UFOType2.position[i][0]*50, -20+UFOType2.position[i][1]*50, sprites));
            ufos.add(new UFOType1(900+UFOType1.position[i][0]*50, -20+UFOType1.position[i][1]*50, sprites));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch ( status ) {
            case MENU:
            menu.update();
            if ( KBListener.keyEscPressed() ) {
                System.exit(0);
            }
            break;
            case PRE:
            backgroundStars.update();
            break;
            case GAME:
            backgroundStars.update();
            player.update();
            if ( KBListener.keyEscPressed() ) {
                setStatus(Status.MENU);
                KBListener.isPressedEsc = false;
            }
            break;
            case GAMEOVER:
            backgroundStars.update();
            if ( KBListener.keyEscPressed() ) {
                setStatus(Status.MENU);
                KBListener.isPressedEsc = false;
            }
            break;
            case WIN:
            backgroundStars.update();
            if ( KBListener.keyEscPressed() ) {
                setStatus(Status.MENU);
                KBListener.isPressedEsc = false;
            }
            break;
            default:
            break;
        }
        repaint();
    }

    private static void setStatus(Status state) {

        Board.status = state;
    }

    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(rh);
        switch ( status ) {
            case MENU:
            menu.draw(g2d);
            break;

            case PRE:
            previousAnimation(g2d);
            break;

            case GAME:
            play(g2d);
            break;

            case GAMEOVER:
            displayGameOver(g2d);
            break;
            case WIN:
            displayWIN(g2d);
            break;
            default:
            break;
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    private void displayWIN(Graphics2D g){
        backgroundStars.draw(g);
        player.draw(g);
        g.drawString("Press ESC for MENU",450,350);
        g.setFont(font);
        g.drawString("You WIN!", 370, 300);
    }
    private void displayGameOver(Graphics2D g){
        backgroundStars.draw(g);
        player.draw(g);
        g.drawString("Press ESC for MENU",500,350);
        g.setFont(font);

        for (UFO ufo : ufos) {
            ufo.draw(g);
        }
                g.drawString("Game Over!", 370, 300);
        
    }
    private void play(Graphics2D g) {
        int i=0;
        backgroundStars.draw(g);
        if(!player.isVisible())
            setStatus(Status.GAMEOVER);
        player.draw(g);
        for (Disparo bullet : player.getDisparos()) {
            bullet.draw(g);
        }
        for (UFO ufo : ufos) {
            ufo.checkCollision(player.getDisparos());
            ufo.checkCollision(player);
            ufo.draw(g);
            if(ufo.isVisible())
                i+=1;
        }
        if(i==0){
            setStatus(Status.WIN);
        }

    }

    private void resetData() {
        player.revive();
        player.setPositionX(-30);
        player.setPositionY(300);
        createUFOS(numberOfUfos);
        newGame = false;

    }

    public static void newGame(int i) {

        numberOfUfos = i;
        newGame = true;
        setStatus(Status.PRE);
    }

    private void previousAnimation(Graphics2D g) {
        player.setImage(sprites.getSprite(1,6));
        backgroundStars.draw(g);
        if ( newGame )
            resetData();
        if ( player.getPositionX() < 100 ) {
            player.setPositionX(player.getPositionX() + 1);
            player.draw(g);
            g.setColor(Color.white);
            g.setFont(font);
            g.drawString("Let's GO!", 400, 300);
        } else {
            setStatus(Status.GAME);
        }

    }

    private void loadSpriteMap() {

        sprites = new SpriteMap("images/tilemap.png");

    }
}
