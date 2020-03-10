import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Board extends JPanel implements ActionListener{

    Timer timer;
    Game game;

    final int WIDTH = 600;
    final int HEIGHT = 800;

    ArrayList<Sprite> actors;



    public Board(Game game){

        this.game = game;
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        timer = new Timer(1000/60, this);
        timer.start();

    }

    public void setup(){

        actors = new ArrayList<>();

        actors.add(new Player(Color.GREEN, WIDTH/2, HEIGHT/2, 20, 10, this, 10, game));

        //init buckets
        for(int i = 0; i < STATS.getNumBuckets(); i++){
            Color color = Color.GREEN;
            int x = 0;
            int y = 0;
            int diameter = 20;
            int height = HEIGHT - diameter;
            int width = WIDTH - diameter;

            switch(i){
                case 0:
                    color = Color.GREEN;
                    x = 0;
                    y = 0;
                    break;
                case 1:
                    color = Color.BLUE;
                    x = width;
                    y = 0;
                    break;
                case 2:
                    color = Color.RED;
                    x = width;
                    y = height;
                    break;
                case 3:
                    color = Color.ORANGE;
                    x = 0;
                    y = height;
                    break;
                case 4:
                    color = Color.PINK;
                    x = width/2;
                    y = 0;
                    break;
                case 5:
                    color = Color.YELLOW;
                    x = width;
                    y = height/2;
                    break;
                case 6:
                    color = Color.MAGENTA;
                    x = width/2;
                    y = height;
                    break;
                case 7:
                    color = Color.ORANGE;
                    x = 0;
                    y = height/2;
                    break;
            }

            actors.add(new PaintBucket(color, x, y, diameter, diameter, this));
        }

    }

    public void checkCollisions(){

        for(int i = 1; i < STATS.getNumBuckets() + 1; i++){
            if(actors.get(0).collidesWith(actors.get(i)))
                actors.get(0).setColor(actors.get(i).getColor());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(Sprite actor: actors){
            actor.move();
        }

        checkCollisions();

        repaint();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        for(Sprite actor: actors){
            actor.paint(g);
        }

    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
