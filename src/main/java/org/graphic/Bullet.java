package org.graphic;

public class Bullet {

    private int x, y;

    private int targetX, targetY;

    private int speed = 50;

    private boolean active = true;

    public Bullet(int x, int y, int targetX, int targetY) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public void move() {
        if (!active) return;

        double angle = Math.atan2(targetY - y, targetX - x);
        x += (int) (speed * Math.cos(angle));
        y += (int) (speed * Math.sin(angle));

        if (Math.abs(x - targetX) < speed && Math.abs(y - targetY) < speed) {
            active = false;
        }
    }

    public boolean isActive() {
        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
