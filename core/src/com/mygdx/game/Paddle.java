package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Paddle {

    private Rectangle bounds; // To handle the position and collision
    private float speed;
    private final Texture COMPUTER = new Texture("Computer.png");
    private final Texture PLAYER = new Texture("Player.png");
    private Texture texture;

    public Paddle(float x, float y, float width, float height, float speed, boolean isComputer) {
        bounds = new Rectangle(x, y, width, height);
        this.speed = speed;
        this.texture = (isComputer) ? COMPUTER : PLAYER; 
    }

    public void update(float delta, boolean moveUp) {
        // Move paddle up or down
        if (moveUp) {
            bounds.y += speed * delta;
        } else {
            bounds.y -= speed * delta;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setPosition(float y) {
        bounds.y = y;
    }
}
