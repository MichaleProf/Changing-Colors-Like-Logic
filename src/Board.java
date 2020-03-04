import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Board extends JPanel implements ActionListener{

    Timer timer;
    Game game;

    ArrayList<Sprite> actors;

    public Board(Game game){

        this.game = game;
        setPreferredSize(new Dimension(600,800));
        setBackground(Color.BLACK);
        timer = new Timer(1000/60, this);
        timer.start();

    }

    public void setup(){

    }

    public void checkCollisions(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        for(Sprite actor: actors){
            actor.paint(g);
        }

    }
}
