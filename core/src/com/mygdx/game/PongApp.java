package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

public class PongApp extends ApplicationAdapter {
	private Paddle paddleLeft, paddleRight;
    private Ball ball;
    private BitmapFont scoreFont;
    private int scoreLeft, scoreRight;

	private SpriteBatch batch;
	private Texture background;

	final float PADDLE_WIDTH = 20;
    final float PADDLE_HEIGHT = 100;
    final float BALL_RADIUS = 10;
    final float PADDLE_SPEED = 200;
    final float BALL_SPEED = 200;

    @Override
    public void create() {
        batch = new SpriteBatch();
        scoreFont = new BitmapFont();
		background = new Texture("Board.png");

        // Create the left and right paddles
        paddleLeft = new Paddle(0, Gdx.graphics.getHeight() / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_SPEED, true);
        paddleRight = new Paddle(Gdx.graphics.getWidth() - PADDLE_WIDTH, Gdx.graphics.getHeight() / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_SPEED, false);

        // Create the ball
        ball = new Ball(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, BALL_RADIUS);
        ball.setVelocity(BALL_SPEED, BALL_SPEED);

        // Initialize scores
        scoreLeft = 0;
        scoreRight = 0;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1); // Clear the screen
        // Draw the background
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		handleInput(); // Handle user input

        update(Gdx.graphics.getDeltaTime()); // Update the game objects

        // Start drawing
        batch.begin();
        paddleLeft.render(batch);
        paddleRight.render(batch);
        ball.render(batch);

        // Draw the scores
        scoreFont.draw(batch, "Left: " + scoreLeft, 10, Gdx.graphics.getHeight() - 10);
        scoreFont.draw(batch, "Right: " + scoreRight, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 10);
        batch.end();
    }

    private void update(float deltaTime) {
        paddleLeft.update(deltaTime, Gdx.input.isTouched()); // Example control for the left paddle
        paddleRight.update(deltaTime, false); // Replace with actual AI or second player control

        ball.update(deltaTime);

        checkCollisions();
    }

    private void handleInput() {
        // Handle input for paddles. This example uses touch input for the left paddle.


        if (Gdx.input.isTouched()) {
            paddleRight.setPosition(Gdx.input.getY() - PADDLE_HEIGHT / 2);
        }
        // For the right paddle, you could add AI or second player controls here.
    }

    private void checkCollisions() {
        // Check for ball collisions with paddles
        if (Intersector.overlaps(ball.getBounds(), paddleLeft.getBounds())) {
            ball.reverseVelocityX();
        }
        if (Intersector.overlaps(ball.getBounds(), paddleRight.getBounds())) {
            ball.reverseVelocityX();
        }

        // Check for ball collisions with top and bottom screen edges
        if (ball.getBounds().y <= 0 || ball.getBounds().y >= Gdx.graphics.getHeight() - 2 * BALL_RADIUS) {
            ball.reverseVelocityY();
        }

        // Check if a point has been scored
        if (ball.getBounds().x <= 0) {
            scoreRight++;
            resetBall();
        } else if (ball.getBounds().x >= Gdx.graphics.getWidth()) {
            scoreLeft++;
            resetBall();
        }
    }

    private void resetBall() {
        ball.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        ball.setVelocity(BALL_SPEED, BALL_SPEED);
    }
}
