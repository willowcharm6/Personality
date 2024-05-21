import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Main extends JPanel {
    private boolean[] keys;
    private boolean started;
    private Player player;
    private ArrayList<Enemy> enemies;
    private Integer enemyCount = 5;

    public Main(int width, int height) {
        super();

        player = new Player(new Point(400, 700));
        started = false;
        enemies = new ArrayList<>();

        setSize(width, height);
        keys = new boolean[256];

        //for loop to make enemies
        for (int n = 0; n < 5; n++) {
            enemies.add(new Enemy(new Point(30, 30)));
        }

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
    public void update() {
        if (!started && keys[KeyEvent.VK_SPACE]) {
            started = true;
        }

        //moving keys (AWSD and arrow)
        {
            if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) {
                player.move(-2, 0);
            }
            if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) {
                player.move(0, 2);
            }
            if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) {
                player.move(2, 0);
            }
            if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) {
                player.move(0, -2);
            }
        }

        if(keys[KeyEvent.VK_SPACE]){  //jump?
//            player.jump();
            keys[KeyEvent.VK_SPACE] = false;  // no holding jump, ruins double jump
        }

        if (started) {
            for (Enemy enemy : enemies) {
                enemy.update();
            }
            while (enemies.size() < enemyCount) {
                enemies.add(new Enemy(new Point(60, 60)));
            }
        }

        repaint();  //update graphics
    }
    @Override
    // All drawing originates from this method
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if (started) {
            //background
            g2.setColor(new Color(0xbdd980));
            g2.fillRect(0, 0, 800, 800);

            player.draw(g2);
            for (Enemy enemy : enemies) {
                enemy.draw(g2);
            }
        }

        if (!started) {
            int stretchedWidth = 800;
            int stretchedHeight = 800;
            g2.drawImage(Resources.titleCard, 0, 0, stretchedWidth, stretchedHeight, null);

            g2.setColor(new Color(0x3c7c54));
            g2.setFont(new Font("impact", Font.PLAIN, 60));
            g2.drawString("PRESS SPACE TO START", 130, 550);
        }

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
