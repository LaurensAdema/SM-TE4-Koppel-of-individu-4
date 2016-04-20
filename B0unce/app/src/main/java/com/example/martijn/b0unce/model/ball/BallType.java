package com.example.martijn.b0unce.model.ball;

import android.graphics.Point;

import com.example.martijn.b0unce.model.resources.GamePoint;

/**
 * Created by Martijn on 20-4-2016.
 */
public abstract class BallType {
    public abstract GamePoint move(GamePoint start, int direction, float speed);
}
