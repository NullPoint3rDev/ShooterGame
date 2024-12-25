package org.graphic.enemies;

import org.graphic.Directions;

import java.util.Random;

public class Zombie {

    private int x = 0;

    private int y = 0;

    private int speed = 2;

    private int mapX = 0;

    private int mapY = 0;

    Directions zombieDirections = Directions.NONE;

    public Zombie(int screenWidth, int screenHeight) {
        Random randomZombieSpawn = new Random();
        int side = randomZombieSpawn.nextInt(4);

        switch (side) {
            case 0:
                x = randomZombieSpawn.nextInt(screenWidth);
                y = -100;
                break;
            case 1:
                x = randomZombieSpawn.nextInt(screenWidth);
                y = screenHeight + 100;
                break;
            case 2:
                x = -100;
                y = randomZombieSpawn.nextInt(screenHeight);
                break;
            case 3:
                x = screenWidth + 100;
                y = randomZombieSpawn.nextInt(screenHeight);
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public void moveToThePlayer(int playerX, int playerY) {
        int dx = playerX - x;
        int dy = playerY - y;
        double length = Math.sqrt(dx * dx + dy * dy);

        x += (int) (speed * (dx / length));
        y += (int) (speed * (dy / length));
    }
}
