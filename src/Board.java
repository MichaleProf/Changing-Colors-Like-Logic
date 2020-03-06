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

        Player player = new Player(Color.GREEN, WIDTH/2, HEIGHT/2, 10, 5, this, 10, game);
        actors.add(player);

    }

    public void checkCollisions(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(Sprite actor: actors){
            actor.move();
        }

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
