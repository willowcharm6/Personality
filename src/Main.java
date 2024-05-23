import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Main extends JPanel {
    private boolean[] keys;
    private boolean started;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bear> bears;
    private ArrayList<Character> characters;
    private Integer enemyCount = 5;
    private Integer money = 0;
    private static Font dayDream;
    private int mouseX;
    private int mouseY;

    public Main(int width, int height) {
        super();

        player = new Player(new Point(400, 700));
        started = false;
        enemies = new ArrayList<>();
        bears = new ArrayList<>();
        characters = new ArrayList<>();

        setSize(width, height);
        keys = new boolean[256];

        //for loop to make enemies
        for (int n = 0; n < 5; n++) {
            enemies.add(new Enemy(new Point(95, 125)));
        }
        //for bear to make bears
        for (int n = 0; n < 5; n++) {
            bears.add(new Bear(new Point(800-70, 100)));
        }
        //characters
        characters.add(new Character(new Point(100, 100),
                new BufferedImage[]{Resources.aminaBack, Resources.aminaBWalk1, Resources.aminaBack, Resources.aminaBWalk1},
                new BufferedImage[]{Resources.aminaFront, Resources.aminaFWalk1, Resources.aminaIdle, Resources.aminaFWalk1},
                new BufferedImage[]{Resources.aminaLeft, Resources.aminaLWalk, Resources.aminaLeft, Resources.aminaLWalk},
                new BufferedImage[]{Resources.aminaRight, Resources.aminaRWalk, Resources.aminaRight, Resources.aminaRWalk}, "amina"
        ));

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
            }
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
            }
        });



            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    int x = e.getX();
//                    int y = e.getY();
//                    System.out.println(x + ", " + y);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Optionally handle dragging if needed
            }
        });


        Timer timer = new Timer(1000/60, e -> update());
        timer.start();



    }
    public void update() {
        if (!started && keys[KeyEvent.VK_SPACE]) {
            started = true;
        }

        if (started) {
            //moving keys (AWSD and arrow)
            {
                int dx = 0;
                int dy = 0;
                if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) {
                    dx = -2;
                }
                if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) {
                    dy = 2;
                }
                if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) {
                    dx = 2;
                }
                if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) {
                    dy = -2;
                }
                player.move(dx, dy);
            }

            for (Enemy enemy : enemies) {
                enemy.update();
            }
            while (enemies.size() < enemyCount) {
                enemies.add(new Enemy(new Point(95, 125)));
            }
            for (Bear bear : bears) {
                bear.update();
            }
            while (bears.size() < enemyCount) {
                bears.add(new Bear(new Point(70, 100)));
            }

            for (Character chars : characters) {
                chars.followPlayer(player, 80);
            }

            if (keys[KeyEvent.VK_SPACE]) {  //jump?
                player.shoot();

//            player.jump();
                keys[KeyEvent.VK_SPACE] = false;  // no holding jump, ruins double jump
            }

        }

        repaint();  //update graphics
    }
    @Override
    // All drawing originates from this method
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        Font sizedFont = dayDream.deriveFont(24f);
        g2.setFont(sizedFont);

        if (started) {
            //background
            g2.setColor(new Color(0xbdd980));
            g2.fillRect(0, 0, 800, 800);

         g2.drawImage(Resources.lair, 0, -25, 200, 200, null);
         g2.drawImage(Resources.cave, 650, -25, 200, 200, null);

         g2.drawImage(Resources.auctionHouse, 250, 100, null);

            player.draw(g2);
            for (Enemy enemy : enemies) {
                enemy.draw(g2);
            }
            for (Enemy bear : bears) {
                bear.draw(g2);
            }
            for (Character chars : characters){
                chars.draw(g2);
            }

            g2.drawImage(Resources.auctionHouse2, 250, 100, null);


        }

        if (!started) {
            int stretchedWidth = 800;
            int stretchedHeight = 800;
            g2.drawImage(Resources.titleCard, 0, 0, stretchedWidth, stretchedHeight, null);
            g2.setColor(new Color(0x3c7c54));
            g2.drawString("PRESS SPACE TO START", 160, 550);
        }
    }



    public static void main(String[] args) {
        // font
        {
            // Load the custom font
            String fontPath = "./res/Daydream.ttf";
            try {
                dayDream = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(24f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(dayDream);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        }
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
