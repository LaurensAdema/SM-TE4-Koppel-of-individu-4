package com.example.martijn.b0unce.model.ball;

import android.graphics.Point;

import com.example.martijn.b0unce.model.resources.GamePoint;

/**
 * Created by laure on 20-4-2016.
 */
public class Circle extends BallType{

    @Override
    public GamePoint move(GamePoint start, int direction, float speed) {
        double directionRAD = Math.toRadians(direction);
        return new GamePoint((float)(start.x + speed * Math.cos(directionRAD)),(float)(start.y + speed * Math.sin(directionRAD)));
    }
}
