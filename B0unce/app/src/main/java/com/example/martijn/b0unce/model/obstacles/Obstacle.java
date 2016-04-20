package com.example.martijn.b0unce.model.obstacles;

import android.graphics.Paint;
import android.graphics.Point;

import com.example.martijn.b0unce.model.MapObject;
import com.example.martijn.b0unce.model.ball.Ball;
import com.example.martijn.b0unce.model.resources.GamePoint;

/**
 * Created by Martijn on 20-4-2016.
 */
public abstract class Obstacle extends MapObject{
    private Paint texture;
    private GamePoint position;

    public abstract void enter(Ball ball);
    public abstract void leave(Ball ball);
}
