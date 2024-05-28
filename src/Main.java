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
    private int frameCount;
    private boolean started;
    private int mouseX;
    private int mouseY;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bear> bears;
    private ArrayList<Character> characters;
    private ArrayList<Projectile> projectile;
    private ArrayList<Point> projPoint;

    private Integer enemyCount = 5;
    private Integer money = 0;
    private static Font dayDream;


    public Main(int width, int height) {
        super();

        player = new Player(new Point(400, 700), Resources.playerBStill);
        started = false;
        enemies = new ArrayList<>();
        bears = new ArrayList<>();
        characters = new ArrayList<>();
        projectile = new ArrayList<>();
        projPoint = new ArrayList<>();
        frameCount = 0;


        setSize(width, height);
        keys = new boolean[256];

        //for loop to make enemies
        for (int n = 0; n < 5; n++) {
            enemies.add(new Enemy(new Point(95, 125), Resources.enemyFront));
        }
        //for bear to make bears
        for (int n = 0; n < 5; n++) {
            bears.add(new Bear(new Point(800-70, 100), Resources.bearFront));
        }
        //characters
        characters.add(new Character(new Point(400, 300),
                new BufferedImage[]{Resources.aminaBack, Resources.aminaBWalk1, Resources.aminaBack, Resources.aminaBWalk1},
                new BufferedImage[]{Resources.aminaFront, Resources.aminaFWalk1, Resources.aminaIdle, Resources.aminaFWalk1},
                new BufferedImage[]{Resources.aminaLeft, Resources.aminaLWalk, Resources.aminaLeft, Resources.aminaLWalk},
                new BufferedImage[]{Resources.aminaRight, Resources.aminaRWalk, Resources.aminaRight, Resources.aminaRWalk}, "amina", Resources.aminaFront
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
                mouseX = e.getX();
                mouseY = e.getY();
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
        frameCount++;

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
                enemies.add(new Enemy(new Point(95, 125), Resources.enemyFront));
            }
            for (Bear bear : bears) {
                bear.update();
            }
            while (bears.size() < enemyCount) {
                bears.add(new Bear(new Point(740, 100), Resources.bearFront));
            }

            for (Character chars : characters) {
                chars.followPlayer(player, 80);
                if (frameCount > 15) {
                    projectile.add(new Projectile(chars.getLocation(), 1, Resources.aminaSwirl, new Point(mouseX, mouseY)));
                    frameCount = 0;
                }

            }

//            System.out.println(mouseX + ", " + mouseY);


            if (projPoint.size()>0) {
                for (Projectile proj : projectile) {
                    proj.followMouse(6);
                }
            }


            // Help this doesn't work :(
            // delete projectile if hits bear, enemy, or is out of frame
            for (int i = 0; i < projectile.size(); i++) {

                for (int j = 0; j < bears.size(); j++) {
                    if (projectile.get(i).intersects(bears.get(j)) && projectile.size()>0 && bears.size()>0) {
                        projectile.remove(i);
                        bears.get(i).loseHealth(30);
                    }
                }
                for (int j = 0; j < enemies.size(); j++) {
                    if (projectile.get(i).intersects(enemies.get(j)) && projectile.size()>0 && enemies.size()>0) {
                        projectile.remove(i);
                        enemies.get(i).loseHealth(30);
                    }
                }

                if (projectile.get(i).getX() > 800 || projectile.get(i).getX() < 0 || projectile.get(i).getY() > 800 || projectile.get(i).getY() < 0){
                    projectile.remove(i);
                }
            }

            for (int j = 0; j < bears.size(); j++) {
                if (bears.get(j).getHealth() < 1) {
                    bears.remove(j);
                    j--;
                    player.changeCoins(5);
                }
            }
            for (int j = 0; j < enemies.size(); j++) {
                if (enemies.get(j).getHealth() < 1) {
                    enemies.remove(j);
                    j--;
                    player.changeCoins(5);
                }
            }




            if (keys[KeyEvent.VK_SPACE]) {  //jump?
                projPoint.add(new Point(mouseX, mouseY));

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

         g2.setColor(new Color(0x3c7c54));
         g2.setFont(sizedFont);
         g2.drawString("Coins: " + player.getCoins(), 580, 775);


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
            for (Projectile proj : projectile){
                proj.draw2(g2, Resources.aminaSwirl);
            }

            g2.drawImage(Resources.auctionHouse2, 250, 100, null);
            g2.drawImage(Resources.lair2, 0, -25, 200, 200, null);

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
        int VIEWPORT_WIDTH = 400;
        int VIEWPORT_HEIGHT = 400;
        int characterX = 400;
        int characterY = 400;
        window.setBounds(0, 0, width, height + 22); //(x, y, w, h) 22 due to title bar.

        JPanel panel = new Main(width, height);
        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }

}
