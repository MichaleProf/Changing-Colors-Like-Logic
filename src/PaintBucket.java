import java.awt.*;

public class PaintBucket extends Sprite {


    @Override
    public void paint(Graphics g) {

        g.setColor(getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());

    }
}
