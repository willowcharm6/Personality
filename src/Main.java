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
    private int mouseX;
    private int mouseY;
    private boolean isstarted, inAuctionHouse;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bear> bears;
    private ArrayList<Character> characters;
    private ArrayList<Projectile> projectile;
    private ArrayList<Point> projPoint;
    private Integer enemyCount = 5;
    private Integer money = 0, charIndex = 0;
    private static Font dayDream;


    public Main(int width, int height) {
        super();

        player = new Player(new Point(400, 700), Resources.playerBStill);
        isstarted = false;
        enemies = new ArrayList<>();
        bears = new ArrayList<>();
        characters = new ArrayList<>();
        projectile = new ArrayList<>();
        projPoint = new ArrayList<>();
        frameCount = 0;
        inAuctionHouse = false;

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
                new BufferedImage[]{Resources.aminaRight, Resources.aminaRWalk, Resources.aminaRight, Resources.aminaRWalk}, 
                "amina", Resources.aminaFront, Resources.amina
        ));
        characters.add(new Character(new Point(700, 100),
                new BufferedImage[]{Resources.caspianBack, Resources.caspianBWalk1, Resources.caspianBack, Resources.caspianBWalk2},
                new BufferedImage[]{Resources.caspianFront, Resources.caspianFWalk1, Resources.caspianIdle, Resources.caspianFWalk2},
                new BufferedImage[]{Resources.caspianLeft, Resources.caspianLWalk, Resources.caspianLeft, Resources.caspianLWalk},
                new BufferedImage[]{Resources.caspianRight, Resources.caspianRWalk, Resources.caspianRight, Resources.caspianRWalk}, "caspian", Resources.caspianIdle, Resources.caspian
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
        if(isstarted)
            frameCount++;
        
        if (!isstarted && keys[KeyEvent.VK_SPACE]) {
            isstarted = true;
        }
        if (isstarted && (player.getX() < (200 + 250) && player.getX() > 310) && (player.getY() < (150 + 100) && player.getY() > 160)){
            inAuctionHouse = true;
        }
        else inAuctionHouse = false;

        if (isstarted) {
            //moving keys (AWSD and arrow)
            {
                int dx = 0;
                int dy = 0;
                if (keys[KeyEvent.VK_A]) {
                    dx = -2;
                }
                if (keys[KeyEvent.VK_S]) {
                    dy = 2;
                }
                if (keys[KeyEvent.VK_D]) {
                    dx = 2;
                }
                if (keys[KeyEvent.VK_W]) {
                    dy = -2;
                }
                player.move(dx, dy);
            }
            if (inAuctionHouse){
                if (keys[KeyEvent.VK_RIGHT] && charIndex < characters.size() - 1){
                    charIndex += 1;
                }
                if (keys[KeyEvent.VK_LEFT] && charIndex > 0){
                    charIndex -= 1;
                }
                if (keys[KeyEvent.VK_RIGHT] && charIndex == characters.size() - 1){
                    charIndex = 0;
                }
                if (keys[KeyEvent.VK_LEFT] && charIndex == 0){
                    charIndex = characters.size() - 1;
                }
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
                if (frameCount > 5 && keys[KeyEvent.VK_SPACE] && started) {
                    projectile.add(new Projectile(chars.getLocation(), 1, Resources.aminaSwirl, new Point(mouseX, mouseY)));
                    frameCount = 0;
                }
            }

            if (projPoint.size() > 0) {
                for (Projectile proj : projectile) {
                    proj.followMouse(6);
                }
            }

            // Remove projectiles if they hit a bear, enemy, or are out of frame
            for (int i = projectile.size() - 1; i >= 0; i--) {
                Projectile proj = projectile.get(i);
                boolean removed = false;

                // Check intersection with bears
                for (int j = 0; j < bears.size(); j++) {
                    if (proj.intersects(bears.get(j))) {
                        bears.get(j).loseHealth(30);
                        projectile.remove(i);
                        removed = true;
                        break;
                    }
                }

                // Check intersection with enemies if not already removed
                if (!removed) {
                    for (int j = 0; j < enemies.size(); j++) {
                        if (proj.intersects(enemies.get(j))) {
                            enemies.get(j).loseHealth(30);
                            projectile.remove(i);
                            removed = true;
                            break;
                        }
                    }
                }

                // Check if the projectile is out of frame if not already removed
                if (!removed) {
                    if (proj.getX() > 800 || proj.getX() < 0 || proj.getY() > 800 || proj.getY() < 0) {
                        projectile.remove(i);
                    }
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

            if (keys[KeyEvent.VK_SPACE]) {
                projPoint.add(new Point(mouseX, mouseY));
                keys[KeyEvent.VK_SPACE] = false;
            }
        }

        repaint(); // Update graphics
    }

    @Override
    // All drawing originates from this method
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        Font sizedFont = dayDream.deriveFont(24f);
        g2.setFont(sizedFont);

        if (isstarted) {
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
        if (isstarted && inAuctionHouse){
            g2.drawImage(Resources.inAuctionHouse, 0, 0, 800, 800, null);
            g2.drawImage(characters.get(charIndex).getFullbody(), 400-(270/2), 400-(444/2), 800, 800, null);
        }

        if (!isstarted) {
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
