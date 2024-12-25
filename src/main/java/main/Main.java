package main;

import org.graphic.Bullet;
import org.graphic.Player;
import org.graphic.enemies.Zombie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class Main extends JPanel implements ActionListener {

    Image mainArena = new ImageIcon("OrdinaryArena.png").getImage();

    Image playerImage = new ImageIcon("Soldier.png").getImage();

    Image zombieImage = new ImageIcon("Zombie.png").getImage();

    Image bulletImage = new ImageIcon("Bullet.png").getImage();

    Image sparkImage = new ImageIcon("Spark.png").getImage();

    JFrame frame;

    Timer timer = new Timer(20, this);

    Player player = new Player();

    Zombie zombie;

    Random random = new Random();

    Bullet bullet;

    private List<Bullet> bullets = new ArrayList<>();

    private List<Spark> sparks = new ArrayList<>();

    public Main(JFrame frame) {
        this.frame = frame;
        timer.start();

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (zombie == null) {
                    spawnNewZombie();
                }
            }
        });

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    bullets.add(new Bullet(player.getX() + 40,
                            player.getY() + 40, e.getX(), e.getY()));

                    sparks.add(new Spark(player.getX() + 40, player.getY() + 40));
                }
            }
        });
    }


/*
    private void handleMouseClick(int mouseX, int mouseY) {
        if (zombie != null) {
            int zombieSize = 80;

            if (mouseX >= zombie.getX() && mouseX <= zombie.getX() + zombieSize &&
                    mouseY >= zombie.getY() && mouseY <= zombie.getY() + zombieSize) {

                System.out.println("You got this zombie!");

                spawnNewZombie();
            }
        }
    }
*/


    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.drawImage(mainArena, 0, 0, frame.getWidth(), frame.getHeight(), null);

        graphics.drawImage(playerImage, player.getX(), player.getY(), 80, 80, null);

        if (zombie != null) {
            graphics.drawImage(zombieImage, zombie.getX(), zombie.getY(), 80, 80, null);
        }


        for (Bullet bullet : bullets) {
            graphics.drawImage(bulletImage, bullet.getX(), bullet.getY(), 40, 40, null);
        }

        for (Spark spark : sparks) {
            graphics.drawImage(sparkImage, spark.getX(), spark.getY(), 40, 40, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();

        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.move();

            if (zombie != null && bullet.getX() >= zombie.getX() && bullet.getX() <= zombie.getX() + 80
                    && bullet.getY() >= zombie.getY() && bullet.getY() <= zombie.getY() + 80) {
                spawnNewZombie();
                iterator.remove();
                continue;
            }

            if (bullet.getX() < 0 || bullet.getX() > frame.getWidth() ||
                    bullet.getY() < 0 || bullet.getY() > frame.getHeight()) {
                iterator.remove();
            }

            if (!bullet.isActive()) {
                iterator.remove();
            }

            Iterator<Spark> sparkIterator = sparks.iterator();
            while (sparkIterator.hasNext()) {
                Spark spark = sparkIterator.next();
                spark.decreaseLife();

                if (!spark.isAlive()) {
                    sparkIterator.remove();
                }
            }
        }

        if (zombie != null) {
            zombie.moveToThePlayer(player.getX(), player.getY());
            if (isPlayerWasCaptured(player, zombie)) {
                System.out.println("Zombie got you! Game over!");
                timer.stop();
            }
        }
        repaint();

    }

    public boolean isPlayerWasCaptured(Player player, Zombie zombie) {
        int playerSize = 80;
        int zombieSize = 80;

        int playerCenterX = player.getX() + playerSize / 2;
        int playerCenterY = player.getY() + playerSize / 2;
        int zombieCenterX = zombie.getX() + zombieSize / 2;
        int zombieCenterY = zombie.getY() + zombieSize / 2;

        return playerCenterX == zombieCenterX && playerCenterY == zombieCenterY;
    }

    private void spawnNewZombie() {
        int x = random.nextInt(frame.getWidth() - 80);
        int y = random.nextInt(frame.getHeight() - 80);

        zombie = new Zombie(x, y);
    }

    private class Spark {
        private int x, y;
        private int shootLife = 20;

        public Spark(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void decreaseLife() {
            shootLife--;
        }

        public boolean isAlive() {
            return shootLife > 0;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
