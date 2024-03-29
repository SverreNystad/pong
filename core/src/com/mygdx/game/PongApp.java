package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The main entry point for the Pong game.
 * This class is responsible for creating the game objects and rendering them to the screen.
 * It is also responsible for handling user input and updating the game state.
 */
public class PongApp extends ApplicationAdapter {
	private Paddle paddleLeft, paddleRight;
    private Ball ball;
    private BitmapFont scoreFont, gameOverFont;
    private int scoreLeft, scoreRight;

	private SpriteBatch batch;
	private Texture background;

	private final float PADDLE_WIDTH = 20;
    private final float PADDLE_HEIGHT = 100;
    private final float BALL_RADIUS = 10;
    private final float PADDLE_SPEED = 300;
    private final float BALL_SPEED = 250;

	private PongAI pongAI;

    @Override
    public void create() {
        batch = new SpriteBatch();
        scoreFont = new BitmapFont();
        gameOverFont = new BitmapFont();
		background = new Texture("Board.png");

        // Create the left and right paddles
        paddleLeft = new Paddle(0, Gdx.graphics.getHeight() / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_SPEED, true, Gdx.graphics.getHeight());
        paddleRight = new Paddle(Gdx.graphics.getWidth() - PADDLE_WIDTH, Gdx.graphics.getHeight() / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_SPEED, false, Gdx.graphics.getHeight());

		
        // Create the ball
        ball = new Ball(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, BALL_RADIUS);
        ball.setVelocity(BALL_SPEED, BALL_SPEED);
		
		pongAI = new PongAI(ball, paddleLeft);
        // Initialize scores
        scoreLeft = 0;
        scoreRight = 0;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        // Draw the background
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        if (isGameOver()) {
		    batch.begin();
            gameOverFont.draw(batch, "Game Over", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
            batch.end();
            return;
        }
		handleInput();

        update(Gdx.graphics.getDeltaTime());

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
        pongAI.update(deltaTime); 
        ball.update(deltaTime);

        checkCollisions();
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            paddleRight.setPosition(Gdx.input.getY() - PADDLE_HEIGHT / 2);
        }
        // Make the paddle stay on the screen
        if (paddleRight.getBounds().y < 0) {
            paddleRight.setPosition(0);
        } else if (paddleRight.getBounds().y > Gdx.graphics.getHeight() - PADDLE_HEIGHT) {
            paddleRight.setPosition(Gdx.graphics.getHeight() - PADDLE_HEIGHT);
        }
    }

    private void checkCollisions() {
        // Check for ball collisions with paddles
        if (Intersector.overlaps(ball.getBounds(), paddleLeft.getBounds())) {
            ball.reverseVelocityX();
            ball.setVelocity(ball.getVelocity().x * 1.1f, ball.getVelocity().y * 1.1f);
        }
        if (Intersector.overlaps(ball.getBounds(), paddleRight.getBounds())) {
            ball.reverseVelocityX();
            ball.setVelocity(ball.getVelocity().x * 1.1f, ball.getVelocity().y * 1.1f);
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

    private boolean isGameOver() {
        return scoreLeft >= 5 || scoreRight >= 5;
    }
}
