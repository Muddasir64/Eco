import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame {


    public Game() {
        Herbivore.Initialize();
        Plant.Initialize();
        Carnivore.Initialize();
        Cannibal.Initialize();
        initUI();
    }

    private void initUI() {

        add(new Board());


        setResizable(false);
        pack();

        setTitle("Ecosystem");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            Game game = new Game();
            game.setVisible(true);
        });
    }
}