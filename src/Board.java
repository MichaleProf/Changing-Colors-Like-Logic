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

    long moment;
    final int DELAY = 3000;
    boolean initColor = false;

    ArrayList<Sprite> actors;

    Color[] colorSpectrum = {Color.GREEN, Color.BLUE, Color.RED, Color.ORANGE, Color.PINK, new Color(123, 12, 201) , Color.MAGENTA, Color.CYAN};



    public Board(Game game){

        this.game = game;
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        timer = new Timer(1000/60, this);



    }

    public void setup(){

        moment = System.currentTimeMillis();

        actors = new ArrayList<>();

        actors.add(new Player(Color.WHITE, WIDTH/2, HEIGHT/2, 20, 10, this, 9, game));

        //init buckets
        for(int i = 0; i < STATS.getNumBuckets(); i++){
            Color color = colorSpectrum[i];
            int x = 0;
            int y = 0;
            int diameter = 20;
            int height = HEIGHT - diameter;
            int width = WIDTH - diameter;

            switch(i){
                case 0:
                    x = 0;
                    y = 0;
                    break;
                case 1:
                    x = width;
                    y = 0;
                    break;
                case 2:
                    x = width;
                    y = height;
                    break;
                case 3:
                    x = 0;
                    y = height;
                    break;
                case 4:
                    x = width/2;
                    y = 0;
                    break;
                case 5:
                    x = width;
                    y = height/2;
                    break;
                case 6:
                    x = width/2;
                    y = height;
                    break;
                case 7:
                    x = 0;
                    y = height/2;
                    break;
            }

            actors.add(new PaintBucket(color, x, y, diameter, diameter, this));
        }

        //init enemies
        for(int i = 0; i < STATS.getNumEnemies(); i++){
            int colorInt = (int)(Math.random() * STATS.getNumBuckets());
            Color color = colorSpectrum[colorInt];

            int x = (int)(Math.random() * (WIDTH-50));
            int y = (int)(Math.random() * (HEIGHT-50));
            if(x > (WIDTH/2-25))
                x += 50;
            if(y > (HEIGHT/2-25))
                y= 50;

            actors.add(new Enemy(color, x, y, 20, 20, this));
        }

        if(!timer.isRunning())
            timer.start();

    }

    public void checkCollisions(){

        long gap = System.currentTimeMillis() - moment;

        if(gap > DELAY) {

            if(gap > DELAY && gap < DELAY+100){
                initColor = true;
                actors.get(0).setColor(colorSpectrum[0]);
            }

            //buckets change player color
            for (int i = 1; i < STATS.getNumBuckets() + 1; i++) {
                if (actors.get(0).collidesWith(actors.get(i)))
                    actors.get(0).setColor(actors.get(i).getColor());
            }

            //System.out.println(ahctors.lengt);
            //enemy player interaction
            for (int i = STATS.getNumBuckets() + 1; i < actors.size(); i++) {
                if (actors.get(0).collidesWith(actors.get(i))) {
                    if (actors.get(0).getColor().equals(actors.get(i).getColor()))
                        actors.get(i).setRemove();
                    else
                        actors.get(0).setRemove();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(actors.size() == STATS.getNumBuckets()+1 && actors.get(0).getClass().equals(Player.class)){
            STATS.updateLevel(this);
        }
        /*
        if(actors.get(0).getClass().equals(Player.class))
            setup();

         */

        for(Sprite actor: actors){
            actor.move();
        }

        checkCollisions();
        removeActors();

        repaint();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        if(actors.get(0).getClass().equals(Player.class)) {
            for (Sprite actor : actors) {
                actor.paint(g);
            }
        }else{

            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Font font = new Font("Serif", Font.PLAIN, 96);
            g2.setFont(font);
            g2.drawString("You Died", 40, 120);

        }


    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void removeActors(){

        ArrayList<Integer> removeSpots = new ArrayList<>();
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i).isRemove())
                removeSpots.add(i);
        }

        for(int i = 0; i < removeSpots.size(); i++){
            actors.remove(removeSpots.get(i)-i);
        }

    }




}
