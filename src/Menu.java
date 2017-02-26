import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

enum MenuStatus {
    STILL, FACIL, NORMAL, COMPLICADO,IMPOSIBLE;
}

public class Menu extends Map {
       
    public static final String rutaPath = "images/menuTrans.png";
    private Image background;
    private Stars backgroundStars;
    private MenuStatus state;
    private Font font,fontGray;

    public Menu() {

        state = MenuStatus.STILL;
        backgroundStars = new Stars();
        ImageIcon ii = new ImageIcon(this.getClass().getResource(rutaPath));
        background = ii.getImage();
        font = new Font("Arial", Font.BOLD, 25);
        fontGray = new Font("Arial",Font.BOLD,15);
    }

    @Override
    public void update() {

        backgroundStars.update();
        switch ( state ) {
        case STILL:

            if ( KBListener.keySpacePressed() ) {
                setMenuStatus(MenuStatus.FACIL);
                KBListener.isPressedSpace = false;

            }
            break;
        case FACIL:
            if ( KBListener.keySpacePressed() ) {
                Board.newGame(10);
                Board.dificultVSspeed = 1;
            }
            switch ( KBListener.keyLeftRightArePressed() ) {
            case 00:
                break;
            case 01:
                setMenuStatus(MenuStatus.NORMAL);
                KBListener.isPressedRight = false;
                break;
            case 10:
                break;
            case 11:
                break;
            }
            break;
        case NORMAL:
            if ( KBListener.keySpacePressed() ) {
                Board.newGame(15);
                Board.dificultVSspeed = 2;
            }
            switch ( KBListener.keyLeftRightArePressed() ) {
            case 00:
                break;
            case 01:
                setMenuStatus(MenuStatus.COMPLICADO);
                KBListener.isPressedRight = false;
                break;
            case 10:
                setMenuStatus(MenuStatus.FACIL);
                KBListener.isPressedLeft = false;
                break;
            case 11:
                break;
            }
            break;
        case COMPLICADO:
            if ( KBListener.keySpacePressed() ) {
                Board.newGame(20);
                Board.dificultVSspeed = 3;
            }
            switch ( KBListener.keyLeftRightArePressed() ) {
            case 00:
                break;
            case 01:
                setMenuStatus(MenuStatus.IMPOSIBLE);
                KBListener.isPressedRight = false;
                break;
            case 10:
                setMenuStatus(MenuStatus.NORMAL);
                KBListener.isPressedLeft = false;
                break;
            case 11:
                break;
            }
            break;
        case IMPOSIBLE:
            if ( KBListener.keySpacePressed() ) {
                Board.newGame(30);
                Board.dificultVSspeed = 4;
            }
            switch ( KBListener.keyLeftRightArePressed() ) {
            case 00:
                break;
            case 01:
                break;
            case 10:
                setMenuStatus(MenuStatus.COMPLICADO);
                KBListener.isPressedLeft = false;
                break;
            case 11:
                break;
            }
            break;
        }
    }

    @Override
    public void draw(Graphics2D g) {

        backgroundStars.draw(g);
        g.drawImage(background, 0, 0, null);
        g.setFont(font);
        g.setColor(Color.white);
        switch ( state ) {
        case STILL:
            g.drawString("PRESS SPACE TO START", 350, 480);
            break;
        case FACIL:
            g.setColor(Color.CYAN);
            g.drawString("FACIL", 450, 500);
            g.setColor(Color.GRAY);
            g.setFont(fontGray);
            g.drawString("NORMAL", 600, 500);
            g.drawString("COMPLICADO", 700, 500);
            g.drawString("IMPOSIBLE", 830, 500);
            break;
        case NORMAL:
            g.setColor(Color.CYAN);
            g.drawString("NORMAL", 450, 500);
            g.setColor(Color.gray);
            g.setFont(fontGray);
            g.drawString("FACIL", 350, 500);
            g.drawString("COMPLICADO", 610, 500);
            g.drawString("IMPOSIBLE", 730, 500);
            break;
        case COMPLICADO:
            g.setColor(Color.CYAN);
            g.drawString("COMPLICADO", 450, 500);
            g.setColor(Color.gray);
            g.setFont(fontGray);
            g.drawString("FACIL", 250, 500);
            g.drawString("NORMAL", 350, 500);
            g.drawString("IMPOSIBLE", 650, 500);
            break;
        case IMPOSIBLE:
            g.setColor(Color.CYAN);
            g.drawString("IMPOSIBLE", 450, 500);
            g.setColor(Color.gray);
            g.setFont(fontGray);
            g.drawString("FACIL", 120, 500);
            g.drawString("NORMAL", 220, 500);
            g.drawString("COMPLICADO", 320, 500);
            break;  
        }

    }

    private void setMenuStatus(MenuStatus state) {

        this.state = state;
    }
}
