package com.example.martijn.b0unce.model.obstacles;

import com.example.martijn.b0unce.model.ball.Ball;

/**
 * Created by Martijn on 20-4-2016.
 */
public class SpeedDown extends Obstacle {
    private float multiplier;

    public SpeedDown(float multiplier)
    {
        this.multiplier = multiplier;
    }
    @Override
    public void enter(Ball ball) {
        
    }

    @Override
    public void leave(Ball ball) {

    }

    public float getMultiplier() {
        return multiplier;
    }
}
