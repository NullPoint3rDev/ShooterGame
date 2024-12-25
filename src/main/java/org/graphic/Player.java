package org.graphic;

import java.awt.event.KeyEvent;

public class Player {

    private int x = 0;

    private int y = 0;

    private int speed = 5;

    private int mapX = 0;

    private int mapY = 0;

    Directions playerDirections = Directions.NONE;


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

    public void move() {
//        x -= speed;
//        mapX += speed;

        switch (playerDirections) {
            case UP:
                y += speed;
                break;
            case DOWN:
                y -= speed;
                break;
            case LEFT:
                x += speed;
                break;
            case RIGHT:
                x -= speed;
                break;
        }
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            playerDirections = Directions.DOWN;
        }
        if (key == KeyEvent.VK_S) {
            playerDirections = Directions.UP;
        }
        if (key == KeyEvent.VK_A) {
            playerDirections = Directions.RIGHT;
        }
        if (key == KeyEvent.VK_D) {
            playerDirections = Directions.LEFT;
        }
    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W && playerDirections == Directions.DOWN) {
            playerDirections = Directions.NONE;
        }
        if (key == KeyEvent.VK_S && playerDirections == Directions.UP) {
            playerDirections = Directions.NONE;
        }
        if (key == KeyEvent.VK_A && playerDirections == Directions.RIGHT) {
            playerDirections = Directions.NONE;
        }
        if (key == KeyEvent.VK_D && playerDirections == Directions.LEFT) {
            playerDirections = Directions.NONE;
        }
    }
}
