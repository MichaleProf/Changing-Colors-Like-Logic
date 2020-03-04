import java.awt.*;

public class Enemy extends Sprite {
    @Override
    public void paint(Graphics g) {

        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());

    }
}
