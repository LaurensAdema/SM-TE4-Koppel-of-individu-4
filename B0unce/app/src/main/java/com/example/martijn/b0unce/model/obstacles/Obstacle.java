package com.example.martijn.b0unce.model.obstacles;


import android.graphics.Color;
import android.graphics.Point;

import com.example.martijn.b0unce.model.MapObject;
import com.example.martijn.b0unce.model.ball.Ball;
import com.example.martijn.b0unce.model.resources.GamePoint;

/**
 * Created by Martijn on 20-4-2016.
 */
public abstract class Obstacle extends MapObject{
    protected int texture = 0xff000000;
    protected GamePoint position;

    public Obstacle(GamePoint position) {
        super(position);
        this.position = position;
    }

    public abstract void enter(Ball ball);
    public abstract void leave(Ball ball);
    public int getTexture(){return texture;}
}
