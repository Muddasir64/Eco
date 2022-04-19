import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 30;

    private Timer timer;
    private int x, y;

    public Board() {

        initBoard();
    }



    private void initBoard() {

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        x = INITIAL_X;
        y = INITIAL_Y;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Draw(g);
    }

    private void Draw(Graphics g) {

        Toolkit.getDefaultToolkit().sync();
        if(Herbivore.HerbivoreList.size()==0){
            Herbivore.Initialize();
        }
        if(Carnivore.CarnivoreList.size()==0){
            Carnivore.Initialize();
        }
        if(Cannibal.CannibalList.size()==0){
            Cannibal.Initialize();
        }

        for(int x=0;x<Plant.PlantList.size();x++){
            g.setColor(Plant.PlantList.get(x).color);
            g.fillOval(Plant.PlantList.get(x).x,Plant.PlantList.get(x).y,Plant.PlantList.get(x).size,Plant.PlantList.get(x).size);
        }
        for(int x=0;x<Herbivore.HerbivoreList.size();x++){
            g.setColor(Herbivore.HerbivoreList.get(x).color);
            g.fillOval(Herbivore.HerbivoreList.get(x).x,Herbivore.HerbivoreList.get(x).y,Herbivore.HerbivoreList.get(x).size,Herbivore.HerbivoreList.get(x).size);
        }
        for(int x=0;x<Carnivore.CarnivoreList.size();x++){
            g.setColor(Carnivore.CarnivoreList.get(x).color);
            g.fillOval(Carnivore.CarnivoreList.get(x).x,Carnivore.CarnivoreList.get(x).y,Carnivore.CarnivoreList.get(x).size,Carnivore.CarnivoreList.get(x).size);
        }
        for(int x=0;x<Cannibal.CannibalList.size();x++){
            g.setColor(Cannibal.CannibalList.get(x).color);
            g.fillOval(Cannibal.CannibalList.get(x).x,Cannibal.CannibalList.get(x).y,Cannibal.CannibalList.get(x).size,Cannibal.CannibalList.get(x).size);
        }
        for(int x=0;x<Plant.PlantList.size();x++){
            Plant.PlantList.get(x).checkStatus();
        }
        for(int x=0;x<Herbivore.HerbivoreList.size();x++){
            if(Herbivore.HerbivoreList.get(x).size>11){
                Herbivore.HerbivoreList.get(x).OffSpring();
                Herbivore.HerbivoreList.remove(x);
            }
            Herbivore.HerbivoreList.get(x).followTarget();
            if(Herbivore.HerbivoreList.get(x).eaten>5){
                Herbivore.HerbivoreList.remove(x);
            }
        }
        for(int x=0;x<Carnivore.CarnivoreList.size();x++){
            if(Carnivore.CarnivoreList.get(x).size>15){
                Carnivore.CarnivoreList.get(x).Birth();
                Carnivore.CarnivoreList.remove(x);
            }
            Carnivore.CarnivoreList.get(x).followTarget();
            if(Carnivore.CarnivoreList.get(x).eaten>5){
                Carnivore.CarnivoreList.remove(x);
            }

        }
        for(int x=0;x<Cannibal.CannibalList.size();x++) {
            if (Cannibal.CannibalList.get(x).size > 15) {
                Cannibal.CannibalList.get(x).Offspring();
                Cannibal.CannibalList.remove(x);
            }
            Cannibal.CannibalList.get(x).followTarget();
            if (Cannibal.CannibalList.get(x).eaten == 8) {
                Cannibal.CannibalList.remove(x);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        x += 1;
        y += 1;

        if (y > B_HEIGHT) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }

        repaint();
    }
}