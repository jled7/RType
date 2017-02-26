public class UFOType1 extends UFO {

    public static int[][] position = {{2,2},{3,7},{4,4},{6,2},{6,9},{7,6},
            {9,3},{10,8},{11,5},{13,1},{14,9},{15,6},
            {17,2},{18,9},{20,6},{21,3},{22,8},{23,1},
            {24,5},{25,10},{26,2},{27,8},{29,4},{30,9},
            {31,2},{33,6},{36,3},{38,9},{39,2},{41,6}};
    public UFOType1(int positionX, int positionY, SpriteMap sprite) {

        super(positionX, positionY, sprite);

    }

    @Override
    protected void move() {

        setPositionX(getPositionX()-Constants.ENEMY_SPEED*Board.dificultVSspeed);

    }

}
