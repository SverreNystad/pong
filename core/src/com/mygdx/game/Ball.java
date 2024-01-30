package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    private Circle bounds; // To handle the position and collision
    private Vector2 velocity;
    private Texture texture = new Texture("Ball.png");

    public Ball(float x, float y, float radius) {
        bounds = new Circle(x, y, radius);
        velocity = new Vector2();
    }

    public void setVelocity(float x, float y) {
        velocity.set(x, y);
    }

    public void update(float delta) {
        // Move ball based on its velocity
        bounds.x += velocity.x * delta;
        bounds.y += velocity.y * delta;
    }

    public void render(SpriteBatch batch) {
        // Draw the ball
        batch.draw(texture, bounds.x - bounds.radius, bounds.y - bounds.radius, bounds.radius * 2, bounds.radius * 2);
    }

    public Circle getBounds() {
        return bounds;
    }

    public void setPosition(float x, float y) {
        bounds.setPosition(x, y);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void reverseVelocityX() {
        velocity.x = -velocity.x;
    }

    public void reverseVelocityY() {
        velocity.y = -velocity.y;
    }
}
