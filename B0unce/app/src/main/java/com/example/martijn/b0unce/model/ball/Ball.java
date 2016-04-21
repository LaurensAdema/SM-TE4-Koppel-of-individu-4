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
    private int direction;
    private int bounces;

    private static float MAXSPEED = 0.8f;
    public static double RADIUS = 0.7;

    public Ball(GamePoint position, int weight, BallType type) {
        this.position   = position;
        this.weight     = weight;
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
                ArrayList<MapObject> collision = GameMech.Collision(this, newPos);
                boolean posOK = true;
                if ((newPos.x <= 0 || newPos.x+Ball.RADIUS >= Map.GRIDSIZE) || (newPos.y <= 0 || newPos.y+Ball.RADIUS >= Map.GRIDSIZE)) {

                    double normal = 0;
                    if(newPos.x <= 0)
                        normal = 180;
                    if(newPos.x + Ball.RADIUS >= Map.GRIDSIZE)
                        normal = 360;
                    if(newPos.y <= 0)
                        normal = 270;
                    if(newPos.y + Ball.RADIUS >= Map.GRIDSIZE)
                        normal = 90;


                    direction = (int) Math.floor(2 * normal - 180 - direction)%360;

                    bounces++;
                }
                for (MapObject collisionObject: collision) {
                    switch (collisionObject.getClass().getSimpleName())
                    {
                        case "Wall":
                            direction = (direction + 90) % 360;
                            posOK = false;
                            break;
                        case "SpeedUp":
                            speedUp();
                            break;
                        case "SpeedDown":
                            speedDown();
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
                speed -= 0.002 * weight;

            if(bounces > map.getMaxBounces())
                speed = 0;
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

    public int getBounces() {
        return bounces;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        speed = 0.5f;
    }

    public BallType getType() {
        return type;
    }
}
