import org.w3c.dom.css.Rect;

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
import java.awt.geom.Area;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Main extends JPanel {
    private boolean[] keys;
    private boolean rightKeyPressed = false;
    private boolean firstTime;
    private boolean leftKeyPressed = false;
    private int frameCount;
    private int frameCountDamage;
    private int frameCountDoor;
    private int mouseX;
    private int mouseY;
    private boolean isstarted, inAuctionHouse, inShop = false, inInventory = false, lost, prevInAuctionHouse;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bear> bears;
    private ArrayList<Character> characters;
    private ArrayList<Character> shopChars;
    private ArrayList<Projectile> projectile;
    private ArrayList<Point> projPoint;
    private Integer enemyCount = 5;
    private Integer charIndex = 0, shopCharsIndex = 0;
    private static Font dayDream;


    public Main(int width, int height) {
        super();

        player = new Player(new Point(400, 400), Resources.playerBStill);
        isstarted = false;
        enemies = new ArrayList<>();
        bears = new ArrayList<>();
        characters = new ArrayList<>();
        shopChars = new ArrayList<>();
        projectile = new ArrayList<>();
        projPoint = new ArrayList<>();
        frameCount = 0;
        frameCountDamage = 0;
        frameCountDoor = 0;
        inAuctionHouse = false;
        firstTime = true;
        lost = false;
        prevInAuctionHouse = false;

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
        //characters with instance fields: spawn point, images for back, front, left, right, string name, fullbodypic, little pic, attack
        characters.add(new Character(new Point(200, 400),
                new BufferedImage[]{Resources.aminaBack, Resources.aminaBWalk1, Resources.aminaBack, Resources.aminaBWalk1},
                new BufferedImage[]{Resources.aminaFront, Resources.aminaFWalk1, Resources.aminaIdle, Resources.aminaFWalk1},
                new BufferedImage[]{Resources.aminaLeft, Resources.aminaLWalk, Resources.aminaLeft, Resources.aminaLWalk},
                new BufferedImage[]{Resources.aminaRight, Resources.aminaRWalk, Resources.aminaRight, Resources.aminaRWalk}, 
                "amina", Resources.amina, Resources.aminaFront, Resources.aminaSwirl
        ));
        shopChars.add(new Character(new Point(200, 400),
                new BufferedImage[]{Resources.caspianBack, Resources.caspianBWalk1, Resources.caspianBack, Resources.caspianBWalk2},
                new BufferedImage[]{Resources.caspianFront, Resources.caspianFWalk1, Resources.caspianIdle, Resources.caspianFWalk2},
                new BufferedImage[]{Resources.caspianLeft, Resources.caspianLWalk, Resources.caspianLeft, Resources.caspianLWalk},
                new BufferedImage[]{Resources.caspianRight, Resources.caspianRWalk, Resources.caspianRight, Resources.caspianRWalk},
                "caspian", Resources.caspian, Resources.caspianIdle, Resources.caspianRain
        ));
        shopChars.add(new Character(new Point(200, 400),
                new BufferedImage[]{Resources.melonieBack, Resources.melonieBWalk1, Resources.melonieBack, Resources.melonieBWalk2},
                new BufferedImage[]{Resources.melonieFront, Resources.melonieFWalk1, Resources.melonieIdle, Resources.melonieFWalk2},
                new BufferedImage[]{Resources.melonieLeft, Resources.melonieLWalk, Resources.melonieLeft, Resources.melonieLWalk},
                new BufferedImage[]{Resources.melonieRight, Resources.melonieRWalk, Resources.melonieRight, Resources.melonieRWalk},
                "melonie", Resources.melonie, Resources.melonieIdle, Resources.melonieMelon
        ));
        shopChars.add(new Character(new Point(200, 400),
                new BufferedImage[]{Resources.violaBack, Resources.violaBWalk1, Resources.violaBack, Resources.violaBWalk2},
                new BufferedImage[]{Resources.violaFront, Resources.violaFWalk1, Resources.violaIdle, Resources.violaFWalk2},
                new BufferedImage[]{Resources.violaLeft, Resources.violaLWalk, Resources.violaLeft, Resources.violaLWalk},
                new BufferedImage[]{Resources.violaRight, Resources.violaRWalk, Resources.violaRight, Resources.violaRWalk},
                "viola", Resources.viola, Resources.violaIdle, Resources.violaGuitar
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
                    //check if exit button clicked, if so, set player location to outside of auction house to leave
                    if(e.getX() < 600 + 110 && e.getX() > 600 && e.getY() > 650 && e.getY() < 650 + 40 && inAuctionHouse){
                        player.setLocation(new Point(400, 300));
                    }
                    //check if inventory button clicked
                    // g2.fillRect(55, 65, 240, 40);
                    if(e.getX() < 55 + 240 && e.getX() > 55 && e.getY() > 65 && e.getY() < 65 + 40 && inAuctionHouse){
                        inShop = false;
                        inInventory = true;
                    }
                    //check if shop button clicked
                    // g2.fillRect(625, 65, 115, 40);
                    if(e.getX() < 625 + 115 && e.getX() > 625 && e.getY() > 65 && e.getY() < 65 + 40 && inAuctionHouse){
                        inInventory = false;
                        inShop = true;
                    }
                    if (inAuctionHouse && inShop){
                        // g2.fillRect(95, 650, 282, 40); Buy Button
                        if (e.getX() < 95 + 282 && e.getX() > 95 && e.getY() > 650 && e.getY() < 650 + 40 && player.getCoins() >= 50) {
                            characters.add(shopChars.get(shopCharsIndex));
                            shopChars.remove(shopChars.get(shopCharsIndex));
                            player.changeCoins(-50);
                            // Ensure shopCharsIndex is within bounds after removal
                            if (shopCharsIndex >= shopChars.size()) {
                                shopCharsIndex = shopChars.size() - 1;
                            }
                        }
                    }
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
        if (!isstarted && keys[KeyEvent.VK_SPACE]) {
            isstarted = true;
        }
        if (isstarted && (player.getX() < (200 + 250) &&
                player.getX() > 310) && (player.getY() < (150 + 100) && player.getY() > 160)){
            inAuctionHouse = true;
        }
        else inAuctionHouse = false;
        // music stuff
        {
            if (isstarted) {
                frameCount++;
                frameCountDamage++;
            }

        if (Resources.musicNormal != null && isstarted && !inAuctionHouse){
            Resources.musicNormal.start();
            Resources.musicNormal.loop(Clip.LOOP_CONTINUOUSLY);
            Resources.musicAuction.stop();
        }

        if(inAuctionHouse) {
            Resources.musicNormal.stop();
            Resources.musicAuction.start();
            Resources.musicAuction.loop(Clip.LOOP_CONTINUOUSLY);

        }

        if (!isstarted && keys[KeyEvent.VK_SPACE]) {
            isstarted = true;
        }
        if (isstarted && (player.getX() < (200 + 250) &&
                player.getX() > 310) && (player.getY() < (150 + 100) && player.getY() > 160)){
            inAuctionHouse = true;
        }
        else inAuctionHouse = false;

        if (inAuctionHouse != prevInAuctionHouse) {
            if (inAuctionHouse) {
                if (Resources.door != null) {
                    Resources.door.stop(); // Stop the clip if it is already playing
                    Resources.door.setFramePosition(0); // Rewind to the beginning
                    Resources.door.start();
                    frameCountDoor = 0;
                }
            }
            if(!inAuctionHouse){
                Resources.door.stop(); // Stop the clip if it is already playing
                Resources.door.setFramePosition(0); // Rewind to the beginning
                Resources.door.start();
                frameCountDoor = 0;
            }
            prevInAuctionHouse = inAuctionHouse;
        }

            if (frameCountDoor >= 100) {
                if (Resources.door != null) {
                    Resources.door.stop();
                }
            } else {
                frameCountDoor++;
            }
        }

        if (isstarted) {
            //moving keys
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
                if (inInventory) {
                    if (keys[KeyEvent.VK_RIGHT]) {
                        if (!rightKeyPressed) {  // Key was just pressed
                            if (charIndex < characters.size() - 1) {
                                charIndex += 1;
                            }
                            rightKeyPressed = true;  // Set flag to indicate the key is pressed
                        }
                    } else {
                        rightKeyPressed = false;  // Reset flag when key is released
                    }

                    // Check for left arrow key
                    if (keys[KeyEvent.VK_LEFT]) {
                        if (!leftKeyPressed) {  // Key was just pressed
                            if (charIndex> 0) {
                                charIndex -= 1;
                            }
                            leftKeyPressed = true;  // Set flag to indicate the key is pressed
                        }
                    } else {
                        leftKeyPressed = false;  // Reset flag when key is released
                    }
                }
                if (inShop) {
                    // Check for right arrow key
                    if (keys[KeyEvent.VK_RIGHT]) {
                        if (!rightKeyPressed) {  // Key was just pressed
                            if (shopCharsIndex < shopChars.size() - 1) {
                                shopCharsIndex += 1;
                            }
                            rightKeyPressed = true;  // Set flag to indicate the key is pressed
                        }
                    } else {
                        rightKeyPressed = false;  // Reset flag when key is released
                    }

                    // Check for left arrow key
                    if (keys[KeyEvent.VK_LEFT]) {
                        if (!leftKeyPressed) {  // Key was just pressed
                            if (shopCharsIndex > 0) {
                                shopCharsIndex -= 1;
                            }
                            leftKeyPressed = true;  // Set flag to indicate the key is pressed
                        }
                    } else {
                        leftKeyPressed = false;  // Reset flag when key is released
                    }
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

                characters.get(charIndex).followPlayer(player, 80);
                if (frameCount > 5 && keys[KeyEvent.VK_SPACE] && isstarted) {
                    projectile.add(new Projectile(characters.get(charIndex).getLocation(), 1,
                            characters.get(charIndex).getAttack(), new Point(mouseX, mouseY)));
                    frameCount = 0;
                }

            // enemies do 5 damage, but they see you from further away
            for (Enemy enemy: enemies) {
                if(!inAuctionHouse)
                    enemy.followPlayer(player);
                if (enemy.intersects(player) && frameCountDamage > 15 && !inAuctionHouse){
                    player.loseHealth(5);
                    frameCountDamage = 0;
                }
            }

            // bears do 15 damage, but you have to get close for them to notice you
            for (Bear bear: bears){
                if(!inAuctionHouse)
                    bear.followPlayer(player);
                if(bear.getHealth() < 90)
                    bear.attackPlayer(player);
                if (bear.intersects(player) && frameCountDamage > 15 && !inAuctionHouse){
                    player.loseHealth(15);
                    frameCountDamage = 0;
                }
            }

            if (player.getHealth() < 1){
                lost = true;
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

                    Resources.coin.stop();
                    Resources.coin.setFramePosition(0);
                    Resources.coin.start();
                }
            }
            for (int j = 0; j < enemies.size(); j++) {
                if (enemies.get(j).getHealth() < 1) {
                    enemies.remove(j);
                    j--;
                    player.changeCoins(5);

                    Resources.coin.stop();
                    Resources.coin.setFramePosition(0);
                    Resources.coin.start();
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
        Font smallerFont = dayDream.deriveFont(15f);
        Font smallestFont = dayDream.deriveFont(10f);
        Font biggerFont = dayDream.deriveFont(35f);
        g2.setFont(sizedFont);
        //colors
        Color brown = new Color(101, 67, 49);
        Color green = new Color(0x3c7c54);
        Color blue = new Color(38, 49, 79);
        Color beige = new Color(232, 219, 189);
        Color red = new Color(150, 56, 42);
        Color pink = new Color(177, 94, 116);

        if (isstarted) {
            //background
            g2.setColor(new Color(0xbdd980));
            g2.fillRect(0, 0, 800, 800);

            g2.drawImage(Resources.details, 0, 0, null);
            g2.drawImage(Resources.details2, 0, 0, null);


            g2.drawImage(Resources.lair, 0, -25, 200, 200, null);
         g2.drawImage(Resources.cave, 650, -25, 200, 200, null);

         g2.drawImage(Resources.auctionHouse, 250, 100, null);

         g2.setColor(green);
         g2.setFont(sizedFont);

            player.draw(g2);
            for (Enemy enemy : enemies) {
                enemy.draw(g2);
            }
            for (Enemy bear : bears) {
                bear.draw(g2);
            }
            characters.get(charIndex).draw(g2);
            for (Projectile proj : projectile){
                proj.draw2(g2, proj.getImage());
            }

            g2.drawImage(Resources.auctionHouse2, 250, 100, null);
            g2.drawImage(Resources.lair2, 0, -25, 200, 200, null);

            // only see a portion, viewport
            Color greenWithOpacity = new Color(60, 124, 84, 128);
            g2.setColor(greenWithOpacity);
            if (isstarted && !inAuctionHouse) {
                Area a = new Area(new Rectangle(0, 0, 800, 800));
                a.subtract(new Area(new Rectangle(player.getX() - 200, player.getY() - 200, 400, 400)));
                g2.fill(a);

                g2.setColor(new Color(60, 124, 84, 191));
                Area ab = new Area(new Rectangle(0, 0, 800, 800));
                ab.subtract(new Area(new Rectangle(player.getX() - 250, player.getY() - 250, 500, 500)));
                g2.fill(ab);

                g2.setColor(green);
                Area b = new Area(new Rectangle(0,0,800,800));
                b.subtract(new Area(new Rectangle(player.getX() - 300, player.getY() - 300, 600, 600)));
                g2.fill(b);
            }

        }
        if (isstarted && inAuctionHouse && !lost) {
            int x = 60;
            g2.drawImage(Resources.inAuctionHouse, 0, 0, 800, 800, null);
            // exit button
            {
            g2.setColor(brown);
            g2.fillRect(600, 650, 110, 40);
            g2.setColor(beige);
            g2.setFont(sizedFont);
            g2.drawString("Exit", 607, 675);
            }
            g2.setColor(brown);
            g2.drawString("Inventory", x, 90);
            g2.drawString("Shop", 630, 90);

            if (firstTime && !inInventory && !inShop) {
                g2.drawImage(Resources.arrows, 0, 0, null);
                g2.setColor(brown);
                g2.setFont(sizedFont);
                g2.drawString("Select an Option", 200, 400);
                g2.setFont(smallerFont);
                g2.drawString("Click everything twice for it to work", 150, 430);
            }

            if (inInventory && !inShop) {
                g2.drawImage(characters.get(charIndex).getFullbody(), 400 - 270 / 2, 400 - 444 / 2, null);
                g2.setFont(smallerFont);
                g2.drawString("Use < > to select character", x, 700);
                g2.setColor(brown);
                g2.setStroke(new BasicStroke(5.0f));
                g2.drawLine(60, 103, 290, 103);
                if (characters.get(charIndex).getType().equals("amina")) {
                    g2.setColor(green);
                    g2.setFont(biggerFont);
                    g2.drawString("Amina", x, 150);
                    g2.setFont(smallerFont);
                    g2.drawString("My skill is sprouting", 400, 125);
                    g2.drawString("plants to defend myself.", 400, 155);
                    g2.drawString("Attacks:", 600, 220);
                    g2.setFont(smallestFont);
                    g2.drawString("With the power of nature,", x, 220);
                    g2.drawString("I create shields", x, 240);
                    g2.drawString("and barriers,", x, 260);
                    g2.drawString("protecting myself and", x, 280);
                    g2.drawString("those I care about.", x, 300);
                    g2.drawImage(Resources.aminaSwirl, 600, 250, 16 * 5, 16 * 5, null);
                    g2.drawImage(Resources.aminaPlants, 600, 250 + 16 * 5, 16 * 5, 16 * 5, null);

                }
                if (characters.get(charIndex).getType().equals("caspian")) {
                    g2.setColor(blue);
                    g2.setFont(biggerFont);
                    g2.drawString("Caspian", x, 150);
                    g2.setFont(smallerFont);
                    g2.drawString("Riding those waves,", 470, 125);
                    g2.drawString("I bring the fight", 470, 155);
                    g2.drawString("right to my enemies!", 470, 185);
                    g2.drawString("Attacks:", 600, 220);
                    g2.setFont(smallestFont);
                    g2.drawString("I harnessed my", x, 220);
                    g2.drawString("connection with the ocean", x, 240);
                    g2.drawString("and discovered a skill", x, 260);
                    g2.drawString("to summon and", x, 280);
                    g2.drawString("manipulate waves.", x, 300);
                    g2.drawImage(Resources.caspianRain, 600, 250, 16 * 5, 16 * 5, null);
                    g2.drawImage(Resources.caspianWave1, 600, 250 + 16 * 5, 16 * 5, 16 * 5, null);
                }
                if (characters.get(charIndex).getType().equals("melonie")) {
                    g2.setColor(pink);
                    g2.setFont(biggerFont);
                    g2.drawString("Melonie", x, 150);
                    g2.setFont(smallerFont);
                    g2.drawString("I have a unique skill", 445, 125);
                    g2.drawString("- shooting watermelon ", 445, 155);
                    g2.drawString("seeds with precision!", 445, 185);
                    g2.drawString("Attacks:", 600, 220);
                    g2.setFont(smallestFont);
                    g2.drawString("I grew up in a small town", x, 220);
                    g2.drawString("surrounded by nature and", x, 240);
                    g2.drawString("set the record for spitting", x, 260);
                    g2.drawString("watermelon seeds the", x, 280);
                    g2.drawString("farthest at our annual", x, 300);
                    g2.drawString("festival!", x, 320);
                    g2.drawImage(Resources.melonieMelon, 600, 250, 16 * 5, 16 * 5, null);
                    g2.drawImage(Resources.melonieMelonSeed, 600, 250 + 16 * 5, 16 * 5, 16 * 5, null);
                }
                if (characters.get(charIndex).getType().equals("viola")) {
                    g2.setColor(red);
                    g2.setFont(biggerFont);
                    g2.drawString("Viola", x, 150);
                    g2.setFont(smallerFont);
                    g2.drawString("I honed my skills", 480, 125);
                    g2.drawString("to shoot fiery", 480, 155);
                    g2.drawString("music notes!", 480, 185);
                    g2.drawString("Attacks:", 600, 220);
                    g2.setFont(smallestFont);
                    g2.drawString("I'm a talented musician", x, 220);
                    g2.drawString("and a daredevil who", x, 240);
                    g2.drawString("loves skydiving,", x, 260);
                    g2.drawString("bungee jumping,", x, 280);
                    g2.drawString("and fire poi spinning.", x, 300);
                    g2.drawImage(Resources.violaGuitar, 600, 250, 16 * 5, 16 * 5, null);
                    g2.drawImage(Resources.violaNote, 600, 250 + 16 * 5, 16 * 5, 16 * 5, null);
                }
            }
            if (inShop && !inInventory && !shopChars.isEmpty()){
                g2.setColor(brown);
                g2.fillRect(95, 650, 282, 40);
                g2.setStroke(new BasicStroke(5.0f));
                g2.drawLine(630, 103, 733, 103);
                g2.setColor(beige);
                g2.drawString("Buy: 50 coins", 100, 675);
                g2.drawImage(shopChars.get(shopCharsIndex).getFullbody(), 400 - 270 / 2, 400 - 444 / 2, null);
                if (shopChars.get(shopCharsIndex).getType().equals("caspian")) {
                    g2.setColor(blue);
                    g2.setFont(biggerFont);
                    g2.drawString("Caspian", x, 150);
                    g2.setFont(smallerFont);
                    g2.drawString("Riding those waves,", 470, 125);
                    g2.drawString("I bring the fight", 470, 155);
                    g2.drawString("right to my enemies!", 470, 185);
                    g2.drawString("Attacks:", 600, 220);
                    g2.setFont(smallestFont);
                    g2.drawString("I harnessed my", x, 220);
                    g2.drawString("connection with the ocean", x, 240);
                    g2.drawString("and discovered a skill", x, 260);
                    g2.drawString("to summon and", x, 280);
                    g2.drawString("manipulate waves.", x, 300);
                    g2.drawImage(Resources.caspianRain, 600, 250, 16 * 5, 16 * 5, null);
                    g2.drawImage(Resources.caspianWave1, 600, 250 + 16 * 5, 16 * 5, 16 * 5, null);
                }
                if (shopChars.get(shopCharsIndex).getType().equals("melonie")) {
                    g2.setColor(pink);
                    g2.setFont(biggerFont);
                    g2.drawString("Melonie", x, 150);
                    g2.setFont(smallerFont);
                    g2.drawString("I have a unique skill", 445, 125);
                    g2.drawString("- shooting watermelon ", 445, 155);
                    g2.drawString("seeds with precision!", 445, 185);
                    g2.drawString("Attacks:", 600, 220);
                    g2.setFont(smallestFont);
                    g2.drawString("I grew up in a small town", x, 220);
                    g2.drawString("surrounded by nature and", x, 240);
                    g2.drawString("set the record for spitting", x, 260);
                    g2.drawString("watermelon seeds the", x, 280);
                    g2.drawString("farthest at our annual", x, 300);
                    g2.drawString("festival!", x, 320);
                    g2.drawImage(Resources.melonieMelon, 600, 250, 16 * 5, 16 * 5, null);
                    g2.drawImage(Resources.melonieMelonSeed, 600, 250 + 16 * 5, 16 * 5, 16 * 5, null);
                }
                if (shopChars.get(shopCharsIndex).getType().equals("viola")) {
                    g2.setColor(red);
                    g2.setFont(biggerFont);
                    g2.drawString("Viola", x, 150);
                    g2.setFont(smallerFont);
                    g2.drawString("I honed my skills", 480, 125);
                    g2.drawString("to shoot fiery", 480, 155);
                    g2.drawString("music notes!", 480, 185);
                    g2.drawString("Attacks:", 600, 220);
                    g2.setFont(smallestFont);
                    g2.drawString("I'm a talented musician", x, 220);
                    g2.drawString("and a daredevil who", x, 240);
                    g2.drawString("loves skydiving,", x, 260);
                    g2.drawString("bungee jumping,", x, 280);
                    g2.drawString("and fire poi spinning.", x, 300);
                    g2.drawImage(Resources.violaGuitar, 600, 250, 16 * 5, 16 * 5, null);
                    g2.drawImage(Resources.violaNote, 600, 250 + 16 * 5, 16 * 5, 16 * 5, null);
                }
            }
        }

        g2.setColor(new Color(0xc48116));
        g2.setFont(sizedFont);
        g2.drawString("Coins: " + player.getCoins(), 580, 775);
        g2.setColor(new Color(0x630f1a));
        if(!inAuctionHouse)
            g2.drawString("Health: " + player.getHealth(), 550, 740);
        else
            g2.drawString("Health: " + player.getHealth(), 30, 775);


        if (!isstarted) {
            int stretchedWidth = 800;
            int stretchedHeight = 800;
            g2.drawImage(Resources.titleCard, 0, 0, stretchedWidth, stretchedHeight, null);
            g2.setColor(new Color(0x3c7c54));
            g2.drawString("PRESS SPACE TO START", 160, 500);
            g2.drawImage(Resources.tutorial, 0, 0, null);
        }

        if (isstarted && lost) {
            g2.drawImage(Resources.loseScreen, 0, 0, null);
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
        window.setResizable(false);
    }

}
