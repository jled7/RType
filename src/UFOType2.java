import java.util.Random;

public class UFOType2 extends UFO {

    public static int[][] position = {{1,5},{2,8},{5,6},{8,4},{9,6},{11,2},
            {12,7},{13,3},{15,2},{16,4},{16,9},{18,4},
            {17,6},{19,2},{20,8},{22,5},{24,3},{25,7},
            {27,5},{28,2},{30,6},{32,8},{33,3},{34,8},
            {35,3},{36,6},{37,7},{38,4},{39,7},{40,2}};

    private Random random;
    private int directionY;

    public UFOType2(int positionX, int positionY, SpriteMap sprite) {

        super(positionX, positionY, sprite);
        random = new Random();
        directionY = 1;
    }

    @Override
    protected void move() {

        if ( random.nextInt(200) < 2 ) {
            if ( random.nextBoolean() )
                directionY = directionY * -1;
        }
        setPositionX(getPositionX() - Constants.ENEMY_SPEED*Board.dificultVSspeed);
        setPositionY(getPositionY() + Constants.ENEMY_SPEED * directionY*Board.dificultVSspeed);
        if ( getPositionY() > Constants.WINDOW_HEIGHT-100 || getPositionY() < 40 )
            directionY = directionY * -1;
    }

}
