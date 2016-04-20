package com.example.martijn.b0unce.model.ball;

import android.graphics.Point;

import com.example.martijn.b0unce.model.Map;

/**
 * Created by Martijn on 20-4-2016.
 */
public class Ball {
    private float speed;
    private float weight;
    private BallType type;
    private Map map;
    private Point position;
    private int direction;
    private int bounces;

    private static int MAXSPEED = 5;
    private static double RADIUS = 0.7;

    public Ball(Point position, int weight, Map map, BallType type) {
        this.position   = position;
        this.weight     = weight;
        this.map        = map;
        this.type       = type;
    }

    public void Move() {
        this.position = this.type.move(position, direction);



        speed -= 0.5 * weight;
    }

    public void speedUp() {
        if((speed+=0.5) > Ball.MAXSPEED)
            speed = Ball.MAXSPEED;
    }

    public void speedDown() {
        if((speed-=0.5) < 0)
            speed = 0;
    }
}
