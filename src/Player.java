import java.awt.*;

public class Player extends Sprite {



    @Override
    public void paint(Graphics g) {

        g.setColor(getColor());

        int[] xpoints = {0, getWidth()/2, getWidth()};
        int[] ypoints = {getHeight(), 0, getHeight()};
        for(int i = 0; i < 3; i++){
            xpoints[i] += getX();
            ypoints[i] += getY();
        }

        g.fillPolygon(xpoints, ypoints, 3);

    }
}
