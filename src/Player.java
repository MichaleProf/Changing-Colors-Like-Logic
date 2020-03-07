import java.awt.*;

public class Player extends Sprite {

    Game game;

    public Player(Color color, int x, int y, int width, int height, Board board, int speed, Game game){

        super(color, x, y, width, height, board);
        setSpeed(speed);
        this.game = game;

    }

    @Override
    public void move() {
        int tempSpeed = getSpeed();
        //System.out.println(tempSpeed);

        boolean areAtLeastTwoDirectionsPressed = game.isLeft() && (game.isDown() || game.isUp() || game.isRight()) || game.isDown() && (game.isUp() || game.isRight()) || game.isUp() && game.isRight();
        if(areAtLeastTwoDirectionsPressed)
            tempSpeed = (int)Math.sqrt(Math.pow(tempSpeed, 2)/2);

        //System.out.println(tempSpeed + " " + 2);

        setDx(0);
        setDy(0);


        if(game.isUp())
            setDy(getDy() - tempSpeed);
        if(game.isDown())
            setDy(getDy() + tempSpeed);
        if(game.isRight())
            setDx(getDx() + tempSpeed);
        if(game.isLeft())
            setDx(getDx() - tempSpeed);

        //System.out.println(game.isUp() + " " + game.isRight() + " " + game.isDown() + " " + game.isLeft());
        //System.out.println(getDx() + " " + getDy());

        setX(getX() + (int)getDx());
        setY(getY() + (int)getDy());

        if(getX() > getBoard().getWIDTH() - getWidth())
            setX(getBoard().getWIDTH() - getWidth());
        if(getX() < 0)
            setX(0);
        if(getY() > getBoard().getHEIGHT() - getHeight())
            setY(getBoard().getHEIGHT() - getHeight());
        if(getY() < 0)
            setY(0);

    }

    @Override
    public void paint(Graphics g) {

        g.setColor(getColor());

        int[] xPoints = {0, getWidth()/2, getWidth()};
        int[] yPoints = {getHeight(), 0, getHeight()};
        for(int i = 0; i < 3; i++){
            xPoints[i] += getX();
            yPoints[i] += getY();
        }

        g.fillPolygon(xPoints, yPoints, 3);

    }
}
