package com.example.martijn.b0unce.model.ball;

import android.graphics.Point;

import com.example.martijn.b0unce.model.Game;
import com.example.martijn.b0unce.model.Map;
import com.example.martijn.b0unce.model.MapObject;
import com.example.martijn.b0unce.model.obstacles.SpeedDown;
import com.example.martijn.b0unce.model.obstacles.SpeedUp;
import com.example.martijn.b0unce.model.resources.GameMech;
import com.example.martijn.b0unce.model.resources.GamePoint;

import java.util.ArrayList;

/**
 * Created by Martijn on 20-4-2016.
 */
public class Ball extends MapObject{
    private float speed;
    private float weight;
    private BallType type;
    private Map map;
    private GamePoint position;
    private int direction;
    private int bounces;

    private static int MAXSPEED = 5;
    private static double RADIUS = 0.7;

    public Ball(GamePoint position, int weight, BallType type) {
        this.position   = position;
        this.weight     = weight;
        this.map        = map;
        this.type       = type;
    }

    public void setMap(Map map)
    {
        this.map = map;
    }

    public void Move() {
        if(speed > 0)
        {
            GamePoint newPos = this.type.move(position, direction, speed);
            if (newPos.x < 0 || newPos.y > Map.GRIDSIZE) {
                ArrayList<MapObject> collision = GameMech.Collision(this, newPos);
                boolean posOK = true;
                for (MapObject collisionObject: collision) {
                    switch (collisionObject.getClass().getSimpleName())
                    {
                        case "Wall":
                            direction = (direction + 90) % 360;
                            posOK = false;
                            break;
                        case "SpeedUp":
                            speed = speed * ((SpeedUp)collisionObject).getMultiplier();
                            break;
                        case "SpeedDown":
                            speed = speed * -((SpeedDown)collisionObject).getMultiplier();
                            break;
                        case "ReSwipe":
                            map.setSwipe(true);
                            break;
                        case "Accelerator":
                            break;
                        case "Gyroscope":
                            break;
                        case "Ramp":
                            break;
                    }
                }
                if (posOK)
                {
                    this.position = newPos;
                }
                speed -= 0.5 * weight;
            } else {
                speed = 0;
            }
        }
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
