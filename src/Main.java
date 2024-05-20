import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JPanel {
    private boolean[] keys;
    private Sprite player;

    public Main(int width, int height) {
        super();

        player = new Sprite(Resources.player, new Point(400, 700));


        setSize(width, height);
        keys = new boolean[256];

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
            }
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
            }
        });
        Timer timer = new Timer(1000/60, e -> update());
        timer.start();


    }
    public void update(){

        // I left this here as a reminder how to handle the key input
//        int dx = keys[KeyEvent.VK_A] ? -1 : 0;
//        dx = keys[KeyEvent.VK_D] ? 1 : dx;

        if (keys[KeyEvent.VK_A]||keys[KeyEvent.VK_LEFT]){
            player.move(-2, 0);
        }

        if(keys[KeyEvent.VK_SPACE]){  //jump?
//            player.jump();
            keys[KeyEvent.VK_SPACE] = false;  // no holding jump, ruins double jump
        }

        repaint();  //update graphics
    }
    @Override
    // All drawing originates from this method
    public void paintComponent(Graphics2D g2) {
        super.paintComponent(g2);
        Graphics2D g = (Graphics2D) g2;

        player.draw(g2);

    }
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int width = 800;
        int height = 800;
        window.setBounds(0, 0, width, height + 22); //(x, y, w, h) 22 due to title bar.

        JPanel panel = new Main(width, height);
        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(true);
    }

}
