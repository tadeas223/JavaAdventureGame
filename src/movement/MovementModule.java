package test;

import engine.Engine;
import gameObject.Module;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementModule extends Module {
    private float speed;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private int upKey = KeyEvent.VK_W;
    private int downKey = KeyEvent.VK_S;
    private int rightKey = KeyEvent.VK_A;
    private int leftKey = KeyEvent.VK_D;

    public MovementModule(float speed) {
        this.speed = speed;

        Engine.getInstance().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == upKey) {
                    up = true;
                } else if (e.getKeyCode() == downKey) {
                    down = true;
                } else if (e.getKeyCode() == leftKey) {
                    left = true;
                } else if (e.getKeyCode() == rightKey) {
                    right = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == upKey) {
                    up = false;
                } else if (e.getKeyCode() == downKey) {
                    down = false;
                } else if (e.getKeyCode() == leftKey) {
                    left = false;
                } else if (e.getKeyCode() == rightKey) {
                    right = false;
                }
            }
        });
    }

    @Override
    public void update(float time) {

        if (up) {
            source.moveY((int) -(speed * time));
        }
        if (down) {
            source.moveY((int) (speed * time));
        }
        if (left) {
            source.moveX((int) (speed * time));
        }
        if (right) {
            source.moveX((int) -(speed * time));
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getUpKey() {
        return upKey;
    }

    public void setUpKey(int upKey) {
        this.upKey = upKey;
    }

    public int getDownKey() {
        return downKey;
    }

    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    public void setKeys(int upKey, int downKey, int leftKey, int rightKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}
