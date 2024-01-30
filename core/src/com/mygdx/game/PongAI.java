package com.mygdx.game;

public class PongAI {

    private Ball ball;
    private Paddle paddle;
    
    public PongAI(Ball ball, Paddle paddle) {
        this.ball = ball;
        this.paddle = paddle;
    }

    public void update(float delta) {
        // Move the paddle up or down based on the ball position
        if (ball.getBounds().y > paddle.getBounds().y + paddle.getBounds().height / 2) {
            paddle.update(delta, true);
        } else if (ball.getBounds().y < paddle.getBounds().y - paddle.getBounds().height / 2) {
            paddle.update(delta, false);
        }
    }
}
